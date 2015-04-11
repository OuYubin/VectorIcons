package cn.robin.vectorIconPack

import java.awt._
import java.awt.image.BufferedImage
import javax.swing.Icon

import cn.robin.vectorIconPack.font.IIconFont
import com.intellij.util.ui.UIUtil

/**
 * Created by robin on 15-4-8.
 */
class FontIcon(font: Font, iconFont: IIconFont) extends Icon {

  setSize(14)

  var size: Integer = _
  var width: Integer = _
  var height: Integer = _
  var deriveFont: Font = _

  def setSize(size: Integer) = {
    if (size > 0) {
      this.size = size
      deriveFont = font.deriveFont(Font.PLAIN, size.floatValue())
      val bufferImage: BufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB)
      val graphics2d: Graphics2D = GraphicsEnvironment.getLocalGraphicsEnvironment.createGraphics(bufferImage)
      graphics2d.setFont(deriveFont)
      this.width = graphics2d.getFontMetrics.charWidth(iconFont.getCode())
      this.height = graphics2d.getFontMetrics.getHeight
      graphics2d.dispose()
    }
  }

  override def paintIcon(c: Component, g: Graphics, x: Int, y: Int): Unit = {
    val bufferedImage = new BufferedImage(getIconWidth, getIconHeight, BufferedImage.TYPE_INT_ARGB)
    val graphics2d = bufferedImage.getGraphics.asInstanceOf[Graphics2D]
    graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB)
    graphics2d.setFont(deriveFont)
    if (!UIUtil.isUnderDarcula)
      graphics2d.setColor(Color.DARK_GRAY)
    else
      graphics2d.setColor(Color.LIGHT_GRAY)
    graphics2d.drawString(String.valueOf(iconFont.getCode), 0, graphics2d.getFontMetrics.getAscent)
    graphics2d.dispose();
    g.drawImage(bufferedImage, x, y, null)
  }


  override def getIconHeight: Int = {
    return height
  }

  override def getIconWidth: Int = {
    return width
  }
}
