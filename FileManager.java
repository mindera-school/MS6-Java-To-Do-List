import java.io.*;
import java.sql.Timestamp;

public class FileManager {


    public static void taskToFile(Task task) throws IOException {
        FileWriter taskWriter = new FileWriter("/Users/mindera/Documents/MS6-Java-To-Do-List/HistoryTxt/Tasks.txt", true);
        taskWriter.write(task.description + "|" + task.info + "|" + task.done + "|" + task.timestamp + "\n");
        taskWriter.close();
    }

    public static void loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/mindera/Documents/MS6-Java-To-Do-List/HistoryTxt/Tasks.txt"));
        String[] lineArray = reader.readLine().split("\\|");
        new Task(lineArray[0], lineArray[1], Boolean.valueOf(lineArray[2]), Timestamp.valueOf(lineArray[3]));
    }
}
