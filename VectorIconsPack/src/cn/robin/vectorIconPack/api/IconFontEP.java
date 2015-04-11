package cn.robin.vectorIconPack.api;

import cn.robin.vectorIconPack.font.IIconFonts;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.AbstractExtensionPointBean;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.util.xmlb.annotations.Attribute;

/**
 * Created by robin on 15-4-10.
 */
public class IconFontEP extends AbstractExtensionPointBean {

    public static final ExtensionPointName<IconFontEP> ICON_EP_NAME = ExtensionPointName.create("cn.robin.vectorIconPack.iconFont");
    @Attribute("id")
    public String id;

    @Attribute("implementClass")
    public String implementClass;

    @Attribute("ttf")
    public String ttf;

    public Class<? extends IIconFonts> fontClass;

    public IIconFonts fontIcon;

    public String getTtf(){
        return ttf;
    }

    public IIconFonts getIConFonts() {
        if (fontIcon == null) {
            try {
                fontIcon = instantiate(getFontClass(), ApplicationManager.getApplication().getPicoContainer());

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return fontIcon;
    }

    public Class<? extends IIconFonts> getFontClass() {
        if (fontClass == null) {
            try {
                fontClass = findClass(implementClass);

            } catch (Exception e) {
                return null;
            }
        }
        return fontClass;
    }
}