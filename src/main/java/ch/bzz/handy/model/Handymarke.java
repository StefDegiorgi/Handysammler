package ch.bzz.handy.model;

import java.util.Date;

/**
 * Brand from Handys
 */
public class Handymarke {
    private String handymarkeUUID;
    private String handymarkeName;
    private String herkunftsland;
    private Date gruendungsDatum;

    public Handymarke(){

    }
    public String getHandymarkeUUID() {
        return handymarkeUUID;
    }

    public void setHandymarkeUUID(String handymarkeUUID) {
        this.handymarkeUUID = handymarkeUUID;
    }

    public String getHandymarkeName() {
        return handymarkeName;
    }

    public void setHandymarkeName(String handymarkeName) {
        this.handymarkeName = handymarkeName;
    }

    public String getHerkunftsland() {
        return herkunftsland;
    }

    public void setHerkunftsland(String herkunftsland) {
        this.herkunftsland = herkunftsland;
    }

    public Date getGruendungsDatum() {
        return gruendungsDatum;
    }

    public void setGruendungsDatum(Date gruendungsDatum) {
        this.gruendungsDatum = gruendungsDatum;
    }


}

