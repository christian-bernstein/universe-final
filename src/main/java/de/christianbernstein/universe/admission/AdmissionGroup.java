package de.christianbernstein.universe.admission;

public class AdmissionGroup {

    private AdmissionContainer container;

    private String systemName;

    private String displayName;

    private String colorCode;

    private int priority;

    private boolean defaultGroup;

    public AdmissionGroup(final AdmissionContainer container, final String systemName,
                          final String displayName, final String colorCode, final int priority,
                          final boolean defaultGroup){
        this.container = container;
        this.systemName = systemName;
        this.displayName = displayName;
        this.colorCode = colorCode;
        this.priority = priority;
        this.defaultGroup = defaultGroup;
    }

    public String getColoredName(){
        return this.colorCode + this.displayName;
    }

    public AdmissionContainer getContainer() {
        return container;
    }

    public void setContainer(AdmissionContainer container) {
        this.container = container;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setDefaultGroup(boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public boolean isDefaultGroup() {
        return defaultGroup;
    }
}
