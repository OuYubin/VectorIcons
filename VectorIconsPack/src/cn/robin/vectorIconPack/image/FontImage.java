package cn.robin.vectorIconPack.image;

import cn.robin.vectorIconPack.font.IIconFont;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class FontImage extends BufferedImage {

    public static final int SIZE=14;

    public FontImage(Font font,IIconFont iconFont){
        this(14,14,BufferedImage.TYPE_INT_ARGB);
        Font deriveFont = font.deriveFont(Font.PLAIN, SIZE);
        //val bufferImage: BufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB)
        Graphics2D graphics2d= GraphicsEnvironment.getLocalGraphicsEnvironment().createGraphics(this);
        graphics2d.setFont(deriveFont);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);
        graphics2d.drawString(String.valueOf(iconFont.getCode()), 0, graphics2d.getFontMetrics().getAscent());
        //int width = graphics2d.getFontMetrics().charWidth(iconFont.getCode());
        //int height = graphics2d.getFontMetrics().getHeight();
        //val graphics2d = bufferedImage.getGraphics.asInstanceOf[Graphics2D]
        //graphics2d.setFont(deriveFont)
        //if (!UIUtil.isUnderDarcula)
        //    graphics2d.setColor(Color.DARK_GRAY)
        //else
        //    graphics2d.setColor(Color.LIGHT_GRAY)
       // graphics2d.dispose();

    }


    public FontImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }
}
