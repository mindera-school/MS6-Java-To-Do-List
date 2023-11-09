import java.io.*;
import java.sql.Timestamp;
import java.util.Scanner;

public class FileManager {

    static Scanner taskScan = new Scanner("/Users/mindera/Documents/MS6-Java-To-Do-List/HistoryTxt/Tasks.txt");
    static Scanner garbageScan = new Scanner("/Users/mindera/Documents/MS6-Java-To-Do-List/HistoryTxt/Garbage.txt");


    public static void taskToFile(Task task) throws IOException {
        FileWriter taskWriter = new FileWriter("/Users/mindera/Documents/MS6-Java-To-Do-List/HistoryTxt/Tasks.txt", true);
        taskWriter.write(task.description + "|" + task.info + "|" + task.done + "|" + task.timestamp);

        taskWriter.close();
    }

    public static void loadFile() throws IOException {
        while (taskScan.hasNextLine()) {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/mindera/Documents/MS6-Java-To-Do-List/HistoryTxt/Tasks.txt"));
            String[] lineArray;
            while ((lineArray = reader.readLine().split("\\|")) != null) {

                new Task(lineArray[0], lineArray[1], Boolean.valueOf(lineArray[2]), Timestamp.valueOf(lineArray[3]));


            }
        }
    }
}
