package ch.bzz.handy.model;

import ch.bzz.handy.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * Ein Handymodell der Handymarke
 */

public class Handymodell {
    @JsonIgnore
    private Handymarke handymarke;

    @FormParam("handymodellUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String handymodellUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min=4, max=40)
    private String handymodellName;

    @FormParam("akkulaufzeit")
    @DecimalMin(value = "5.5")
    @DecimalMax(value = "25.75")
    private double akkulaufzeit;

    @FormParam("seriennummer")
    @NotEmpty
    @Pattern(regexp = "[0-9a-fA-F]{2}-[0-9]{5}-[0-9]{5}")
    private String seriennummer;

    /**
     * hole die HandymarkeUUID über Handymarke-objekt
     * @return
     */
    public String getHandymarkeUUID() {
        if (getHandymarke()== null) return null;
        return getHandymarke().getHandymarkeUUID();
    }

    public void setHandymarkeUUID(String handymarkeUUID) {
        setHandymarke(new Handymarke());
        Handymarke handymarke = DataHandler.readHandymarkeByUUID(handymarkeUUID);
        getHandymarke().setHandymarkeUUID(handymarkeUUID);
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

    public String getHandymodellUUID() {
        return handymodellUUID;
    }

    public void setHandymodellUUID(String handymodellUUID) {
        this.handymodellUUID = handymodellUUID;
    }

    public String getHandymodellName() {
        return handymodellName;
    }

    public void setHandymodellName(String handymodellName) {
        this.handymodellName = handymodellName;
    }


    public double getAkkulaufzeit() {
        return akkulaufzeit;
    }

    public void setAkkulaufzeit(double akkulaufzeit) {
        this.akkulaufzeit = akkulaufzeit;
    }

    public String getSeriennummer() {
        return seriennummer;
    }

    public void setSeriennummer(String seriennummer) {
        this.seriennummer = seriennummer;
    }
}