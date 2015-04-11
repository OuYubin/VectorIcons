package cn.robin.vectorIconPack.component

import java.awt.{Font, GraphicsEnvironment}
import java.lang.reflect.{Field, Modifier}

import cn.robin.vectorIconPack.FontIcon
import cn.robin.vectorIconPack.api.IconFontEP
import cn.robin.vectorIconPack.font.{IIconFont, IIconFonts}
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.extensions.Extensions

/**
 * Created by robin on 15-4-8.
 */
class FontIconComponent extends ApplicationComponent {

  val CACHED_IMAGE_ICON: String = "$CachedImageIcon"

  val MY_URL: String = "myUrl"

  val MY_REAL_ICON: String = "myRealIcon"

  val ALL_ICONS: String = "com.intellij.icons.AllIcons"

  var iconFontList: java.util.List[IIconFonts] = new java.util.ArrayList[IIconFonts]()


  override def initComponent(): Unit = {
    registerFonts
    val ideaIconClass: Class[_] = Class.forName(ALL_ICONS)
    val classes: Array[Class[_]] = ideaIconClass.getDeclaredClasses
    classes.foreach(clazz => fixIcon(clazz))
  }

  def registerFonts: Unit = {
    val iconFontBeans = Extensions.getExtensions(IconFontEP.ICON_EP_NAME)
    iconFontBeans.foreach(iconFontBean => initFont(iconFontBean))
  }

  def initFont(iconFontEP: IconFontEP) = {
    try {
      val ttf = iconFontEP.getTtf;
      val stream = iconFontEP.getFontClass.getResourceAsStream(ttf)
      val font = Font.createFont(Font.TRUETYPE_FONT, stream)
      if (GraphicsEnvironment.getLocalGraphicsEnvironment.registerFont(font)) {
        val iconFonts = iconFontEP.getIConFonts
        iconFonts.setFont(font)
        iconFontList.add(iconFonts)
      }
      stream.close()
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
  }


  def fixIcon(clazz: Class[_]): Unit = {
    val fields: Array[Field] = clazz.getDeclaredFields
    fields.foreach(field => fixIcon(field))
    val classes: Array[Class[_]] = clazz.getDeclaredClasses
    classes.foreach(clazz => fixIcon(clazz))
  }


  def fixIcon(field: Field) = {
    try {
      if (Modifier.isStatic(field.getModifiers)) {
        //--静态属性信息可通过null参数获取对象实例,非静态属性需要通过传入类对象获取实例
        val icon = field.get(null)
        val iconClass = icon.getClass

        if (iconClass.getName.endsWith(CACHED_IMAGE_ICON)) {
          val fieldName = field.getName
          val iconFontArray = iconFontList.toArray
          iconFontArray.foreach(iconFonts => if (iconFonts.asInstanceOf[IIconFonts].getIConFont(fieldName) != null) patchFields(icon, iconFonts.asInstanceOf[IIconFonts].getFont, iconFonts.asInstanceOf[IIconFonts].getIConFont(fieldName)))
        }
      }

    } catch {
      case e: Exception => e.printStackTrace()
    }


  }

  def patchFields(obj: Object, font: Font, iconFont: IIconFont) = {
    try {
      val clazz = obj.getClass
      val urlField = clazz.getDeclaredField(MY_URL)
      val realIconField = clazz.getDeclaredField(MY_REAL_ICON)

      val fontAwesomeIcon = new FontIcon(font, iconFont)
      realIconField.setAccessible(true)
      realIconField.set(obj, fontAwesomeIcon)
      urlField.setAccessible(true)
      urlField.set(obj, null)
    } catch {
      case ex: Exception => ex.printStackTrace()
    }

  }


  override def disposeComponent(): Unit = {

  }

  override def getComponentName: String = {
    return "FontAwesomeIconComponent"
  }
}
