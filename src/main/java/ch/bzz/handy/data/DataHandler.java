package ch.bzz.handy.data;

import ch.bzz.handy.model.Handymarke;
import ch.bzz.handy.model.Handymodell;
import ch.bzz.handy.service.Config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public final class DataHandler {
    private static DataHandler instance;
    private static List<Handymodell> handymodellList;
    private static List<Handymarke> handymarkeList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {

    }

    /**
     * reads all handymodells
     * @return list of handymodells
     */
    public static List<Handymodell> readAllHandymodells() {
        return getHandymodellList();
    }

    /**
     * reads a handymodell by its id
     * @param handymodellUUID
     * @return the handymodell (null=not found)
     */
    public static Handymodell readHandymodellByUUID(String handymodellUUID) {
        Handymodell handymodell = null;
        for (Handymodell entry : getHandymodellList()) {
            if (entry.getHandymodellUUID().equals(handymodellUUID)) {
                handymodell = entry;
            }
        }
        return handymodell;
    }

    /**
     * inserts a new book into the bookList
     *
     * @param handymodell the book to be saved
     */
    public static void insertHandymodell(Handymodell handymodell) {
        getHandymodellList().add(handymodell);
        writeHandymodellJSON();
    }

    public static void updateHandymodell(){
        writeHandymodellJSON();
    }


    public static boolean deleteHandymodell(String handymodellUUID){
        Handymodell handymodell = readHandymodellByUUID(handymodellUUID);
        if (handymodell != null){
            getHandymodellList().remove(handymodell);
            writeHandymodellJSON();
            return true;
        } else {
            return false;
        }

    }


    public static List<Handymarke> readAllHandymarkes() {
        return getHandymarkeList();
    }


    /**
     * reads a handymarke by its id
     * @param handymarkeUUID
     * @return the Handymarke (null=not found)
     */
    public static Handymarke readHandymarkeByUUID(String handymarkeUUID) {
        Handymarke handymarke = null;
        for (Handymarke entry : getHandymarkeList()) {
            if (entry.getHandymarkeUUID().equals(handymarkeUUID)) {
                handymarke = entry;
            }
        }
        return handymarke;
    }

    public static void insertHandymarke(Handymarke handymarke){
        getHandymarkeList().add(handymarke);
        writeHandymarkeJSON();
    }

    public static void updateHandymarke(){
        writeHandymarkeJSON();
    }

    /**
     * deletes a publisher identified by the publisherUUID
     * @param handymarkeUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteHandymarke(String handymarkeUUID) {
        Handymarke handymarke = readHandymarkeByUUID(handymarkeUUID);
        if (handymarke != null) {
            getHandymarkeList().remove(handymarke);
            writeHandymarkeJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the handymodells from the JSON-file
     */
    private static void readHandymodellJSON() {
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

    private static void writeHandymodellJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String handypath = Config.getProperty("handymodellJSON");
        try {
            fileOutputStream = new FileOutputStream(handypath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getHandymodellList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the handymarke from the JSON-file
     */
    private static void readHandymarkeJSON() {
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
     * writes the handymarkeList to the JSON-file
     */
    private static void writeHandymarkeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String handypath = Config.getProperty("handymarkeJSON");
        try {
            fileOutputStream = new FileOutputStream(handypath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getHandymodellList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets handymodellList
     *
     * @return value of handymodellList
     */
    private static List<Handymodell> getHandymodellList() {
        if (handymodellList == null){
            setHandymodellList(new ArrayList<>());
            readHandymodellJSON();
        }
        return handymodellList;
    }

    /**
     * sets handymodellList
     *
     * @param handymodellList the value to set
     */
    private static void setHandymodellList(List<Handymodell> handymodellList) {
        DataHandler.handymodellList = handymodellList;
    }

    /**
     * gets handymarkeList
     *
     * @return value of handymarkeList
     */
    private static List<Handymarke> getHandymarkeList() {
        if (handymarkeList == null){
            setHandymarkeList(new ArrayList<>());
            readHandymarkeJSON();
        }
        return handymarkeList;
    }

    /**
     * sets handymarkeList
     *
     * @param handymarkeList the value to set
     */
    private static void setHandymarkeList(List<Handymarke> handymarkeList) {
        DataHandler.handymarkeList = handymarkeList;
    }


}