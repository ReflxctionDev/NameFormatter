package net.reflxction.nameformatter;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * All supported color values for chat
 */
public enum ChatColor {
    /**
     * Represents black
     */
    BLACK('0', 0x00, "BLACK"),
    /**
     * Represents dark blue
     */
    DARK_BLUE('1', 0x1, "DARK_BLUE"),
    /**
     * Represents dark green
     */
    DARK_GREEN('2', 0x2, "DARK_GREEN"),
    /**
     * Represents dark blue (aqua)
     */
    DARK_AQUA('3', 0x3, "DARK_AQUA"),
    /**
     * Represents dark red
     */
    DARK_RED('4', 0x4, "DARK_RED"),
    /**
     * Represents dark purple
     */
    DARK_PURPLE('5', 0x5, "DARK_PURPLE"),
    /**
     * Represents gold
     */
    GOLD('6', 0x6, "GOLD"),
    /**
     * Represents gray
     */
    GRAY('7', 0x7, "GRAY"),
    /**
     * Represents dark gray
     */
    DARK_GRAY('8', 0x8, "DARK_GRAY"),
    /**
     * Represents blue
     */
    BLUE('9', 0x9, "BLUE"),
    /**
     * Represents green
     */
    GREEN('a', 0xA, "GREEN"),
    /**
     * Represents aqua
     */
    AQUA('b', 0xB, "AQUA"),
    /**
     * Represents red
     */
    RED('c', 0xC, "RED"),
    /**
     * Represents light purple
     */
    LIGHT_PURPLE('d', 0xD, "LIGHT_PURPLE"),
    /**
     * Represents yellow
     */
    YELLOW('e', 0xE, "YELLOW"),
    /**
     * Represents white
     */
    WHITE('f', 0xF, "WHITE"),
    /**
     * Represents magical characters that change around randomly
     */
    MAGIC('k', 0x10, true),
    /**
     * Makes the text bold.
     */
    BOLD('l', 0x11, true),
    /**
     * Makes a line appear through the text.
     */
    STRIKETHROUGH('m', 0x12, true),
    /**
     * Makes the text appear underlined.
     */
    UNDERLINE('n', 0x13, true),
    /**
     * Makes the text italic.
     */
    ITALIC('o', 0x14, true),
    /**
     * Resets all previous chat colors or formats.
     */
    RESET('r', 0x15);

    /**
     * The special character which prefixes all chat colour codes. Use this if
     * you need to dynamically convert colour codes from your custom format.
     */
    public static final char COLOR_CHAR = '\u00A7';
    private final static Map<Integer, ChatColor> BY_ID = Maps.newHashMap();
    private final static Map<Character, ChatColor> BY_CHAR = Maps.newHashMap();

    static {
        for (ChatColor color : values()) {
            BY_ID.put(color.intCode, color);
            BY_CHAR.put(color.code, color);
        }
    }

    private final int intCode;
    private final char code;
    private final boolean isFormat;
    private final String toString;

    ChatColor(char code, int intCode) {
        this(code, intCode, false);
    }

    ChatColor(char code, int intCode, boolean isFormat) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = isFormat;
        this.toString = new String(new char[]{COLOR_CHAR, code});
    }

    ChatColor(char code, int intCode, String name) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = false;
        this.toString = name;
    }

    /**
     * Translates a string using an alternate color code character into a
     * string that uses the internal ChatColor.COLOR_CODE color code
     * character. The alternate color code character will only be replaced if
     * it is immediately followed by 0-9, A-F, a-f, K-O, k-o, R or r.
     *
     * @param textToTranslate Text containing the alternate color code character.
     * @return Text containing the ChatColor.COLOR_CODE color code character.
     */
    public static String translateAlternateColorCodes(String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = ChatColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

    public static ChatColor fromName(String name) {
        for (ChatColor color : values()) {
            if (color.toString().equals(name)) {
                return color;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return toString;
    }
}
