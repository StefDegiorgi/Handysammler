package ch.bzz.handy.model;

import ch.bzz.handy.util.LocalDateDeserializer;
import ch.bzz.handy.util.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.time.LocalDate;

/**
 * Brand from Handys
 */
public class Handymarke {

    @FormParam("handymarkeUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String handymarkeUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min=3, max=20)
    private String handymarkeName;

    @FormParam("herkunftsland")
    @NotEmpty
    @Size(min = 4, max = 56)
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

    @JsonIgnore
    public void setGruendungsDatum(String gruendungsDatum){
        this.gruendungsDatum = LocalDate.parse(gruendungsDatum);
    }

}

