package cn.robin.vectorIconPack.font;

import java.awt.*;

/**
 * Created by robin on 15-4-11.
 */
public abstract class AbstractIconFonts implements IIconFonts {

    private Font font;

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

}
