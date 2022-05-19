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
     * reads a book by its uuid
     * @param handymodellID
     * @return the Book (null=not found)
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
     * reads all Publishers
     * @return list of publishers
     */
    public List<Handymarke> readAllHandymarke() {

        return getHandymarkeList();
    }

    /**
     * reads a publisher by its uuid
     * @param handymarkeID
     * @return the Publisher (null=not found)
     */
    public Handymarke readPublisherByUUID(String handymarkeID) {
        Handymarke handymarke = null;
        for (Handymarke entry : getHandymarkeList()) {
            if (entry.getHandymarkeID().equals(handymarkeID)) {
                handymarke = entry;
            }
        }
        return handymarke;
    }

    /**
     * reads the books from the JSON-file
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
     * reads the publishers from the JSON-file
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
     * gets bookList
     *
     * @return value of bookList
     */
    private List<Handymodell> getHandymodellList() {
        return handymodellList;
    }

    /**
     * sets bookList
     *
     * @param handymodellList the value to set
     */
    private void setHandymodellList(List<Handymodell> handymodellList) {
        this.handymodellList = handymodellList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Handymarke> getHandymarkeList() {
        return handymarkeList;
    }

    /**
     * sets publisherList
     *
     * @param handymarkeList the value to set
     */
    private void setHandymarkeList(List<Handymarke> handymarkeList) {
        this.handymarkeList = handymarkeList;
    }


}