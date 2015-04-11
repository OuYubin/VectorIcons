package cn.robin.vectorIconPack.iconFont;

import cn.robin.vectorIconPack.font.AbstractIconFonts;
import cn.robin.vectorIconPack.font.IIconFont;

/**
 * Created by robin on 15-4-10.
 */
public class IconFonts extends AbstractIconFonts {

    enum IconFont implements IIconFont {

        IE("Explorer16", '\ue601'),
        CHROME("Chrome16", '\ue603'),
        SAFARI("Safari16", '\ue600'),
        FIREFOX("Firefox16", '\ue604'),
        OPERA("Opera16", '\ue602'),
        RUN_WITH_COVERAGE("RunWithCoverage", '\ue605');

        String desc;

        char code;

        IconFont(String desc, char code) {
            this.desc = desc;
            this.code = code;
        }


        @Override
        public char getCode() {
            return code;
        }
    }

    public IIconFont getIConFont(String desc) {
        for (IconFont iconFont : IconFont.values()) {
            if (iconFont.desc.equals(desc)) {
                return iconFont;
            }
        }
        return null;
    }
}
