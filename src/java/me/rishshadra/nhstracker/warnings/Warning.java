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
    private final boolean enabled;

    public Warning(int t, String c, boolean e) {
        type = t;
        content = c;
        enabled = e;
    }

    public String getHTML() {
        if (enabled) {
            return "<div class=\"alert alert-" + WarningTypes.ALERT_NAMES[type] + "\" role=\"alert\">" + content + "</div>";
        } else {
            return "";
        }
    }
    
    public int getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
}
