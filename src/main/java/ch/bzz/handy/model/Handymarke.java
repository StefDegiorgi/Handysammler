package ch.bzz.handy.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Marke von mehreren Handys
 */
public class Handymarke {
    private String handymarkeID;
    private String handymarkeName;
    private String herkunftsland;
    private LocalDate gründungsDatum;
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

    public LocalDate getGründungsDatum() {
        return gründungsDatum;
    }

    public void setGründungsDatum(LocalDate gründungsDatum) {
        this.gründungsDatum = gründungsDatum;
    }

    public List<Handymodell> getHandymodellList() {
        return handymodellList;
    }

    public void setHandymodellList(List<Handymodell> handymodellList) {
        this.handymodellList = handymodellList;
    }
}

