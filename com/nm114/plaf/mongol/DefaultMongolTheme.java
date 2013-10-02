/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm114.plaf.mongol;

import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;

import sun.awt.AppContext;
import sun.security.action.GetPropertyAction;
import sun.swing.SwingUtilities2;

/**
 * A concrete implementation of {@code MongolTheme} providing
 * the original look of the Java Look and Feel, code-named "Steel". Refer
 * to {@link MongolLookAndFeel#setCurrentTheme} for details on changing
 * the default theme.
 * <p>
 * All colors returned by {@code DefaultMongolTheme} are completely
 * opaque.
 *
 * <h3><a name="fontStyle"></a>Font Style</h3>
 *
 * {@code DefaultMongolTheme} uses bold fonts for many controls.  To make all
 * controls (with the exception of the internal frame title bars and
 * client decorated frame title bars) use plain fonts you can do either of
 * the following:
 * <ul>
 * <li>Set the system property <code>swing.boldMongol</code> to
 *     <code>false</code>.  For example,
 *     <code>java&nbsp;-Dswing.boldMongol=false&nbsp;MyApp</code>.
 * <li>Set the defaults property <code>swing.boldMongol</code> to
 *     <code>Boolean.FALSE</code>.  For example:
 *     <code>UIManager.put("swing.boldMongol",&nbsp;Boolean.FALSE);</code>
 * </ul>
 * The defaults property <code>swing.boldMongol</code>, if set,
 * takes precendence over the system property of the same name. After
 * setting this defaults property you need to re-install
 * <code>MongolLookAndFeel</code>, as well as update the UI
 * of any previously created widgets. Otherwise the results are undefined.
 * The following illustrates how to do this:
 * <pre>
 *   // turn off bold fonts
 *   UIManager.put("swing.boldMongol", Boolean.FALSE);
 *
 *   // re-install the Mongol Look and Feel
 *   UIManager.setLookAndFeel(new MongolLookAndFeel());
 *
 *   // Update the ComponentUIs for all Components. This
 *   // needs to be invoked for all windows.
 *   SwingUtilities.updateComponentTreeUI(rootComponent);
 * </pre>
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans<sup><font size="-2">TM</font></sup>
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @see MongolLookAndFeel
 * @see MongolLookAndFeel#setCurrentTheme
 *
 * @version 1.31 07/12/06
 * @author Steve Wilson
 */
public class DefaultMongolTheme extends MongolTheme {

    /**
     * Whether or not fonts should be plain.  This is only used if
     * the defaults property 'swing.boldMongol' == "false".
     */
    private static final boolean PLAIN_FONTS;
    private static ColorUIResource ButtonBorder =  new ColorUIResource(0xaccddd);
    /**
     * Names of the fonts to use.
     */
    private static final String[] fontNames = {
        Font.DIALOG, Font.DIALOG, Font.DIALOG, Font.DIALOG, Font.DIALOG, Font.DIALOG
    };
    /**
     * Styles for the fonts.  This is ignored if the defaults property
     * <code>swing.boldMongol</code> is false, or PLAIN_FONTS is true.
     */
    private static final int[] fontStyles = {
        Font.BOLD, Font.PLAIN, Font.PLAIN, Font.BOLD, Font.BOLD, Font.PLAIN
    };
    /**
     * Sizes for the fonts.
     */
    private static final int[] fontSizes = {
        12, 12, 12, 12, 12, 10
    };

    // note the properties listed here can currently be used by people
    // providing runtimes to hint what fonts are good.  For example the bold 
    // dialog font looks bad on a Mac, so Apple could use this property to 
    // hint at a good font.
    //
    // However, we don't promise to support these forever.  We may move 
    // to getting these from the swing.properties file, or elsewhere.
    /**
     * System property names used to look up fonts.
     */
    private static final String[] defaultNames = {
        "swing.plaf.Mongol.controlFont",
        "swing.plaf.Mongol.systemFont",
        "swing.plaf.Mongol.userFont",
        "swing.plaf.Mongol.controlFont",
        "swing.plaf.Mongol.controlFont",
        "swing.plaf.Mongol.smallFont"
    };

    /**
     * Returns the ideal font name for the font identified by key.
     */
    static String getDefaultFontName(int key) {
        return fontNames[key];
    }

    /**
     * Returns the ideal font size for the font identified by key.
     */
    static int getDefaultFontSize(int key) {
        return fontSizes[key];
    }

    /**
     * Returns the ideal font style for the font identified by key.
     */
    static int getDefaultFontStyle(int key) {
        if (key != WINDOW_TITLE_FONT) {
            Object boldMongol = null;
            if (AppContext.getAppContext().get(
                    SwingUtilities2.LAF_STATE_KEY) != null) {
                // Only access the boldMongol key if a look and feel has
                // been loaded, otherwise we'll trigger loading the look
                // and feel.
                boldMongol = UIManager.get("swing.boldMongol");
            }
            if (boldMongol != null) {
                if (Boolean.FALSE.equals(boldMongol)) {
                    return Font.PLAIN;
                }
            } else if (PLAIN_FONTS) {
                return Font.PLAIN;
            }
        }
        return fontStyles[key];
    }

    /**
     * Returns the default used to look up the specified font.
     */
    static String getDefaultPropertyName(int key) {
        return defaultNames[key];
    }


    static {
        Object boldProperty = java.security.AccessController.doPrivileged(
                new GetPropertyAction("swing.boldMongol"));
        if (boldProperty == null || !"false".equals(boldProperty)) {
            PLAIN_FONTS = false;
        } else {
            PLAIN_FONTS = true;
        }
    }
    private static final ColorUIResource primary1 = new ColorUIResource(
            //102, 102, 153);
            102, 153, 204);
    private static final ColorUIResource primary2 = new ColorUIResource(153,
            153, 204);
    private static final ColorUIResource primary3 = primary1; //new ColorUIResource(
    //204, 204, 255);
    private static final ColorUIResource secondary1 = new ColorUIResource(
            102, 102, 102);
    private static final ColorUIResource secondary2 = new ColorUIResource(
            153, 153, 153);
    private static final ColorUIResource secondary3 = new ColorUIResource(
            204, 204, 204);
    private FontDelegate fontDelegate;

    /**
     * Returns the name of this theme. This returns {@code "Steel"}.
     *
     * @return the name of this theme.
     */
    public String getName() {
        return "Steel";
    }

    /**
     * Creates and returns an instance of {@code DefaultMongolTheme}.
     */
    public DefaultMongolTheme() {
        install();
    }

    /**
     * Returns the primary 1 color. This returns a color with rgb values
     * of 102, 102, and 153, respectively.
     *
     * @return the primary 1 color
     */
    protected ColorUIResource getPrimary1() {
        return primary1;
    }

    /**
     * Returns the primary 2 color. This returns a color with rgb values
     * of 153, 153, 204, respectively.
     *
     * @return the primary 2 color
     */
    protected ColorUIResource getPrimary2() {
        return primary2;
    }

    /**
     * Returns the primary 3 color. This returns a color with rgb values
     * 204, 204, 255, respectively.
     *
     * @return the primary 3 color
     */
    protected ColorUIResource getPrimary3() {
        return primary3;
    }

    /**
     * Returns the secondary 1 color. This returns a color with rgb values
     * 102, 102, and 102, respectively.
     *
     * @return the secondary 1 color
     */
    protected ColorUIResource getSecondary1() {
        return secondary1;
    }

    /**
     * Returns the secondary 2 color. This returns a color with rgb values
     * 153, 153, and 153, respectively.
     *
     * @return the secondary 2 color
     */
    protected ColorUIResource getSecondary2() {
        return secondary2;
    }

    /**
     * Returns the secondary 3 color. This returns a color with rgb values
     * 204, 204, and 204, respectively.
     *
     * @return the secondary 3 color
     */
    protected ColorUIResource getSecondary3() {
        return secondary3;
    }

    /**
     * Returns the control text font. This returns Dialog, 12pt. If
     * plain fonts have been enabled as described in <a href="#fontStyle">
     * font style</a>, the font style is plain. Otherwise the font style is
     * bold.
     *
     * @return the control text font
     */
    public FontUIResource getControlTextFont() {
        return getFont(CONTROL_TEXT_FONT);
    }

    /**
     * Returns the system text font. This returns Dialog, 12pt, plain.
     *
     * @return the sytem text font
     */
    public FontUIResource getSystemTextFont() {
        return getFont(SYSTEM_TEXT_FONT);
    }

    /**
     * Returns the user text font. This returns Dialog, 12pt, plain.
     *
     * @return the user text font
     */
    public FontUIResource getUserTextFont() {
        return getFont(USER_TEXT_FONT);
    }

    /**
     * Returns the menu text font. This returns Dialog, 12pt. If
     * plain fonts have been enabled as described in <a href="#fontStyle">
     * font style</a>, the font style is plain. Otherwise the font style is
     * bold.
     *
     * @return the menu text font
     */
    public FontUIResource getMenuTextFont() {
        return getFont(MENU_TEXT_FONT);
    }

    /**
     * Returns the window title font. This returns Dialog, 12pt, bold.
     *
     * @return the window title font
     */
    public FontUIResource getWindowTitleFont() {
        return getFont(WINDOW_TITLE_FONT);
    }

    /**
     * Returns the sub-text font. This returns Dialog, 10pt, plain.
     *
     * @return the sub-text font
     */
    public FontUIResource getSubTextFont() {
        return getFont(SUB_TEXT_FONT);
    }

    public ColorUIResource getButtonBorder() {
        return ButtonBorder;
    }

    private FontUIResource getFont(int key) {
        return fontDelegate.getFont(key);
    }

    void install() {
        if (MongolLookAndFeel.isWindows() &&
                MongolLookAndFeel.useSystemFonts()) {
            fontDelegate = new WindowsFontDelegate();
        } else {
            fontDelegate = new FontDelegate();
        }
    }

    /**
     * Returns true if this is a theme provided by the core platform.
     */
    boolean isSystemTheme() {
        return (getClass() == DefaultMongolTheme.class);
    }

    /**
     * FontDelegates add an extra level of indirection to obtaining fonts.
     */
    private static class FontDelegate {

        private static int[] defaultMapping = {
            CONTROL_TEXT_FONT, SYSTEM_TEXT_FONT,
            USER_TEXT_FONT, CONTROL_TEXT_FONT,
            CONTROL_TEXT_FONT, SUB_TEXT_FONT
        };
        FontUIResource fonts[];

        // menu and window are mapped to controlFont
        public FontDelegate() {
            fonts = new FontUIResource[6];
        }

        public FontUIResource getFont(int type) {
            int mappedType = defaultMapping[type];
            if (fonts[type] == null) {
                Font f = getPrivilegedFont(mappedType);

                if (f == null) {
                    f = new Font(getDefaultFontName(type),
                            getDefaultFontStyle(type),
                            getDefaultFontSize(type));
                }
                fonts[type] = new FontUIResource(f);
            }
            return fonts[type];
        }

        /**
         * This is the same as invoking
         * <code>Font.getFont(key)</code>, with the exception
         * that it is wrapped inside a <code>doPrivileged</code> call.
         */
        protected Font getPrivilegedFont(final int key) {
            return (Font) java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction() {

                        public Object run() {
                            return Font.getFont(getDefaultPropertyName(key));
                        }
                    });
        }
    }

    /**
     * The WindowsFontDelegate uses DesktopProperties to obtain fonts.
     */
    private static class WindowsFontDelegate extends FontDelegate {

        private MongolFontDesktopProperty[] props;
        private boolean[] checkedPriviledged;

        public WindowsFontDelegate() {
            props = new MongolFontDesktopProperty[6];
            checkedPriviledged = new boolean[6];
        }

        public FontUIResource getFont(int type) {
            if (fonts[type] != null) {
                return fonts[type];
            }
            if (!checkedPriviledged[type]) {
                Font f = getPrivilegedFont(type);

                checkedPriviledged[type] = true;
                if (f != null) {
                    fonts[type] = new FontUIResource(f);
                    return fonts[type];
                }
            }
            if (props[type] == null) {
                props[type] = new MongolFontDesktopProperty(type);
            }
            // While passing null may seem bad, we don't actually use
            // the table and looking it up is rather expensive.
            return (FontUIResource) props[type].createValue(null);
        }
    }
}