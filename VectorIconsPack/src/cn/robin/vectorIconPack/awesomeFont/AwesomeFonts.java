package cn.robin.vectorIconPack.awesomeFont;


import cn.robin.vectorIconPack.font.AbstractIconFonts;
import cn.robin.vectorIconPack.font.IIconFont;

/**
 * Created by robin on 15-4-8.
 */

public class AwesomeFonts extends AbstractIconFonts {


    enum AwesomeFont implements IIconFont {
        FA_FOLDER_OPEN("Menu_open", '\uf114'),
        FA_SAVE("Menu_saveall", '\uf0c7'),
        FA_REFRESH("Refresh", '\uf021'),
        FA_UNDO("Undo", '\uf0e2'),
        FA_REPEAT("Redo", '\uf01e'),
        FA_SCISSORS("Menu_cut", '\uf0c4'),
        FA_COPY("Copy", '\uf0c5'),
        FA_PASTE("Menu_paste", '\uf0ea'),
        FA_SEARCH("Menu_find", '\uf002'),
        FA_SEARCH_PLUS("Menu_replace", '\uf00e'),
        FA_ARROW_LEF("Back", '\uf060'),
        FA_ARROW_RIGHT("Forward", '\uf061'),
        FA_PLAY("Execute", '\uf04b'),
        FA_BUG("StartDebugger", '\uf188'),
        FA_DEBUG("Debug", '\uf188'),
        FA_STOP("Suspend", '\uf04d'),
        FA_GLOBE("Web", '\uf0ac'),
        PP_WEB("PpWeb", '\uf0ac'),
        FA_GEARS("Settings", '\uf085'),
        FA_GEAR("ProjectStructure", '\uf013'),
        FA_QUESTION("Help", '\uf128'),
        FA_CAMERA_RETRO("ProfileMemory", '\uf083'),
        FA_PRINT("Print", '\uf02f'),
        FA_EDIT("EditSource", '\uf044'),
        FA_FIRE("Compile", '\uf06d');

        String desc;
        char code;

        AwesomeFont(String desc, char code) {
            this.desc = desc;
            this.code = code;
        }

        public char getCode() {
            return code;
        }
    }


    public IIconFont getIConFont(String desc) {
        for (AwesomeFont fontAwesome : AwesomeFont.values()) {
            if (fontAwesome.desc.equals(desc)) {
                return fontAwesome;
            }
        }
        return null;
    }
}





