package Controller;

import Model.GameData;
import Model.Score;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *The IOController saves and loads the scores.
 */
public class IOController {

    private ArrayList<Score> scores;
    private String fileName;

    /**
     * @param scores of the player.
     * @param fileName the name in which it will be loaded.
     */
    public IOController(ArrayList<Score> scores, String fileName) {
        this.scores = scores;
        this.fileName = fileName;
    }

    /**
     * Write the array in JSON format to the file.
     */
    public void writeToFile() {
        clearFile();
        Type array = new TypeToken<ArrayList<Score>>() { } .getType();
        try (FileWriter fileWriter = new FileWriter(fileName)) {

            Gson gson = new GsonBuilder().create();
            gson.toJson(scores, array, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data the gameData.
     */
    public void readFromFile(GameData data) {
        Type array = new TypeToken<ArrayList<Score>>() { } .getType();
        try (FileReader fileReader = new FileReader(fileName)) {
            Gson gson = new Gson();
            scores = gson.fromJson(fileReader, array);
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.setScores(scores);
    }

    /**
     * clear the file.
     */
    public void clearFile() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
    }

}
