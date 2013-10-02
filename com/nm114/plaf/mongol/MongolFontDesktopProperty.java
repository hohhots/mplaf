/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nm114.plaf.mongol;

import java.awt.*;
import java.beans.*;
import javax.swing.*;

/**
 * DesktopProperty that only uses font height in configuring font. This
 * is only used on Windows.
 *
 * @version @(#)MongolFontDesktopProperty.java	1.5 05/11/17
 */
class MongolFontDesktopProperty extends com.sun.java.swing.plaf.windows.DesktopProperty {
    /**
     * Maps from Mongol font theme type as defined in MongolTheme
     * to the corresponding desktop property name.
     */
    private static final String[] propertyMapping = {
        "win.ansiVar.font.height",
        "win.tooltip.font.height",
        "win.ansiVar.font.height",
        "win.menu.font.height",
        "win.frame.captionFont.height",
        "win.menu.font.height"
    };

    /**
     * Corresponds to a MongolTheme font type.
     */
    private int type;


    /**
     * Creates a MongolFontDesktopProperty. The key used to lookup the
     * desktop property is determined from the type of font.
     *
     * @param type MongolTheme font type.
     */
    MongolFontDesktopProperty(int type) {
        this(propertyMapping[type], Toolkit.getDefaultToolkit(), type);
    }

    /**
     * Creates a MongolFontDesktopProperty.
     *
     * @param key Key used in looking up desktop value.
     * @param toolkit Toolkit used to fetch property from, can be null
     *        in which default will be used.
     * @param type Type of font being used, corresponds to MongolTheme font
     *        type.
     */
    MongolFontDesktopProperty(String key, Toolkit kit, int type) {
        super(key, null, kit);
        this.type = type;
    }

    /**
     * Overriden to create a Font with the size coming from the desktop
     * and the style and name coming from DefaultMongolTheme.
     */
    protected Object configureValue(Object value) {
        if (value instanceof Integer) {
            value = new Font(DefaultMongolTheme.getDefaultFontName(type),
                             DefaultMongolTheme.getDefaultFontStyle(type),
                             ((Integer)value).intValue());
        }
        return super.configureValue(value);
    }

    /**
     * Returns the default font.
     */
    protected Object getDefaultValue() {
        return new Font(DefaultMongolTheme.getDefaultFontName(type),
                        DefaultMongolTheme.getDefaultFontStyle(type),
                        DefaultMongolTheme.getDefaultFontSize(type));
    }
}