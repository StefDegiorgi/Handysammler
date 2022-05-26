package ch.bzz.handy.model;

import ch.bzz.handy.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Ein Handymodell der Handymarke
 */

public class Handymodell {

    @JsonIgnore
    private Handymarke handymarke;

    private String handymodellID;
    private String handymodellName;
    private String erscheinungsjahr;
    private double akkulaufzeit;

    public String getHandymarkeID() {
        if (getHandymarke()== null) return null;
        return getHandymarke().getHandymarkeID();
    }

    public void setHandymarkeID(String handymarkeID) {
        setHandymarke(new Handymarke());
        Handymarke handymarke = DataHandler.readHandymarkeByID(handymarkeID);
        getHandymarke().setHandymarkeID(handymarkeID);
        getHandymarke().setHandymarkeName(handymarke.getHandymarkeName());
        getHandymarke().setHerkunftsland((handymarke.getHerkunftsland()));
        getHandymarke().setGruendungsDatum(handymarke.getGruendungsDatum());
    }
    public Handymarke getHandymarke(){
        return handymarke;
    }
    public void setHandymarke(Handymarke handymarke) {
        this.handymarke = handymarke;
    }

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