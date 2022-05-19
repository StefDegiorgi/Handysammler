package ch.bzz.handy.model;

import java.util.Date;
import java.util.List;

/**
 * Brand from Handys
 */
public class Handymarke {
    private String handymarkeID;
    private String handymarkeName;
    private String herkunftsland;
    private Date gruendungsDatum;
    private List<Handymodell> handymodellList;

    public String getHandymarkeID() {
        return handymarkeID;
    }

    public void setHandymarkeID(String handymarkeID) {
        this.handymarkeID = handymarkeID;
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

    public List<Handymodell> getHandymodellList() {
        return handymodellList;
    }

    public void setHandymodellList(List<Handymodell> handymodellList) {
        this.handymodellList = handymodellList;
    }
}

