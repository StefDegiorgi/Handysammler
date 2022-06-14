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
 * schreibt und liest die Daten in die JSON-Files
 */
public final class DataHandler {
    private static DataHandler instance;
    private static List<Handymodell> handymodellList;
    private static List<Handymarke> handymarkeList;

    /**
     * privater konstruktor
     */
    private DataHandler() {

    }

    /**
     * inizialisiert die Listen mit Daten
     */
    public static void initLists() {
        DataHandler.setHandymarkeList(null);
        DataHandler.setHandymodellList(null);
    }
    /**
     * liest alle handymodells
     * @return Liste von handymodells
     */
    public static List<Handymodell> readAllHandymodells() {
        return getHandymodellList();
    }

    /**
     * liest ein handymodell über der ID
     * @param handymodellUUID
     * @return das handymodell (null=not found)
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
     * schreibt ein neues Handymodell in der HandymodellListe
     * @param handymodell das handymodell wird gespeichert
     */
    public static void insertHandymodell(Handymodell handymodell) {
        getHandymodellList().add(handymodell);
        writeHandymodellJSON();
    }

    /**
     * update der HandymodellListe
     */
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


    /**
     * liest alle Handymarken
     * @return Liste von allen Handymodells
     */
    public static List<Handymarke> readAllHandymarkes() {
        return getHandymarkeList();
    }


    /**
     * liest eine handymarke über der id
     * @param handymarkeUUID
     * @return die Handymarke (null=not found)
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

    /**
     * schreibt eine neue Handymarke in der HandymodellListe
     * @param handymarke die Handymarke wird gespeichert
     */
    public static void insertHandymarke(Handymarke handymarke){
        getHandymarkeList().add(handymarke);
        writeHandymarkeJSON();
    }

    /**
     * update der HandymarkeListe
     */
    public static void updateHandymarke(){
        writeHandymarkeJSON();
    }

    /**
     * löscht alle Handymaken von der HandymarkeUUID
     * @param handymarkeUUID  der Schlüssel
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
     * liest die Handymodells über der JSON-file
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

    /**
     * schreibt die HandymodellListe zur JSON-file
     */
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
     * liest die Handymarke über der the JSON-file
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
     * schreibt die HandymarkeList zur JSON-file
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
            objectWriter.writeValue(fileWriter, getHandymarkeList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * hole die HandymodellListe
     *
     * @return Wert der HandymodellListe
     */
    private static List<Handymodell> getHandymodellList() {
        if (handymodellList == null){
            setHandymodellList(new ArrayList<>());
            readHandymodellJSON();
        }
        return handymodellList;
    }

    /**
     * setzte die HandymodellListe
     *
     * @param handymodellList der Wert zu setzten
     */
    private static void setHandymodellList(List<Handymodell> handymodellList) {
        DataHandler.handymodellList = handymodellList;
    }

    /**
     * hole die HandymarkeListe
     *
     * @return der Wert von der HandymarkeListe
     */
    private static List<Handymarke> getHandymarkeList() {
        if (handymarkeList == null){
            setHandymarkeList(new ArrayList<>());
            readHandymarkeJSON();
        }
        return handymarkeList;
    }

    /**
     * setzte die HandymarkeListe
     *
     * @param handymarkeList der Wert zu setzte
     */
    private static void setHandymarkeList(List<Handymarke> handymarkeList) {
        DataHandler.handymarkeList = handymarkeList;
    }


}