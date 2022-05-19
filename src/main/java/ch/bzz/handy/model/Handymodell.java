package ch.bzz.handy.model;

/**
 * Ein Handymodell der Handymarke
 */

public class Handymodell {
    private String handymodellID;
    private String handymodellName;
    private String erscheinungsjahr;
    private double akkulaufzeit;

    public String getHandymodellID() {
        return handymodellID;
    }

    public void setHandymodellID(String handymodellID) {
        this.handymodellID = handymodellID;
    }

    public String getHandymodellName() {
        return handymodellName;
    }

    public void setHandymodellName(String handymodellName) {
        this.handymodellName = handymodellName;
    }

    public String getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(String erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public double getAkkulaufzeit() {
        return akkulaufzeit;
    }

    public void setAkkulaufzeit(double akkulaufzeit) {
        this.akkulaufzeit = akkulaufzeit;
    }
}
