/*
 * Written by Rish Shadra.
 * There's really not much more to this license header than that.
 * 
 */
package me.rishshadra.nhstracker.warnings;

/**
 *
 * @author Rish
 */
public class Warning {

    private final int type;
    private final String content;

    public Warning(int t, String c) {
        type = t;
        content = c;
    }
    
    public String getHTML() {
        return "<div class=\"alert" + WarningTypes.ALERT_NAMES[type] + "\" role=\"alert\">" + content + "</div>";
    }
}
