package ch.bzz.handy.data;

import ch.bzz.handy.model.Handymarke;
import ch.bzz.handy.model.Handymodell;
import ch.bzz.handy.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Handymodell> handymodellList;
    private List<Handymarke> handymarkeList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setHandymarkeList(new ArrayList<>());
        readHandymarkeJSON();
        setHandymodellList(new ArrayList<>());
        readHandymodellJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all handymodells
     * @return list of handymodells
     */
    public List<Handymodell> readAllHandymodells() {
        return getHandymodellList();
    }

    /**
     * reads a handymodell by its id
     * @param handymodellID
     * @return the Handymodell (null=not found)
     */
    public Handymodell readHandymodellByID(String handymodellID) {
        Handymodell handymodell = null;
        for (Handymodell entry : getHandymodellList()) {
            if (entry.getHandymodellID().equals(handymodellID)) {
                handymodell = entry;
            }
        }
        return handymodell;
    }

    /**
     * reads all Handymarke
     * @return list of handymarkes
     */
    public List<Handymarke> readAllHandymarke() {

        return getHandymarkeList();
    }

    /**
     * reads a handymarke by its id
     * @param handymarkeID
     * @return the Handymarke (null=not found)
     */
    public Handymarke readHandymarkeByID(String handymarkeID) {
        Handymarke handymarke = null;
        for (Handymarke entry : getHandymarkeList()) {
            if (entry.getHandymarkeID().equals(handymarkeID)) {
                handymarke = entry;
            }
        }
        return handymarke;
    }

    /**
     * reads the handymodells from the JSON-file
     */
    private void readHandymodellJSON() {
        try {
            String path = Config.getProperty("handymodellJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Handymodell[] handymodells = objectMapper.readValue(jsonData, Handymodell[].class);
            for (Handymodell handymodell : handymodells) {
                getHandymodellList().add(handymodell);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the handymarke from the JSON-file
     */
    private void readHandymarkeJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("handymarkeJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Handymarke[] handymarkes = objectMapper.readValue(jsonData, Handymarke[].class);
            for (Handymarke handymarke : handymarkes) {
                getHandymarkeList().add(handymarke);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets handymodellList
     *
     * @return value of handymodellList
     */
    private List<Handymodell> getHandymodellList() {
        return handymodellList;
    }

    /**
     * sets handymodellList
     *
     * @param handymodellList the value to set
     */
    private void setHandymodellList(List<Handymodell> handymodellList) {
        this.handymodellList = handymodellList;
    }

    /**
     * gets handymarkeList
     *
     * @return value of handymarkeList
     */
    private List<Handymarke> getHandymarkeList() {
        return handymarkeList;
    }

    /**
     * sets handymarkeList
     *
     * @param handymarkeList the value to set
     */
    private void setHandymarkeList(List<Handymarke> handymarkeList) {
        this.handymarkeList = handymarkeList;
    }


}