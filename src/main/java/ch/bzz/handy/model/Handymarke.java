package ch.bzz.handy.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Marke von mehreren Handys
 */
public class Handymarke {
    private String handymarkeUUID;
    private String handymarkeName;
    private String herkunftsland;
    private LocalDate gründungsDatum;
    private List<Handymodell> handymodellList;

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

