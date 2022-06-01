package ch.bzz.handy.model;

import ch.bzz.handy.util.LocalDateDeserializer;
import ch.bzz.handy.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

/**
 * Brand from Handys
 */
public class Handymarke {

    private String handymarkeUUID;
    private String handymarkeName;
    private String herkunftsland;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate gruendungsDatum;

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

    public LocalDate getGruendungsDatum() {
        return gruendungsDatum;
    }

    public void setGruendungsDatum(LocalDate gruendungsDatum) {
        this.gruendungsDatum = gruendungsDatum;
    }


}

