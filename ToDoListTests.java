import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ToDoListTests {
    // Redirect System.out to capture the printed output
    @Test
    public void testShowToDoList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create test data
        String[] toDoList = {"Task 1", "Task 2 ✅", "Task 3"};
        String title = "Test ToDoList";

        // Call the method to be tested
        ToDoList.showToDoList(toDoList, title);

        // Restore the original System.out
        System.setOut(System.out);

        // Verify the printed output
        String expectedOutput = "\n\t\t\u001b[38;5;15mTest ToDoList\u001b[0m" +
                "\n\u001b[38;5;8m------------------------\u001b[0m" +
                "\n\u001b[38;5;7m1. \u001b[38;5;1mTask 1\u001b[0m" +
                "\n\u001b[38;5;7m2. \u001b[38;5;40mTask 2 ✅\u001b[0m" +
                "\n\u001b[38;5;7m3. \u001b[38;5;1mTask 3\u001b[0m" +
                "\n\nThis To Do List has 3 tasks!\n" +
                "\nThe task completion percentage is 33%!\n\n" +
                "\u001b[38;5;8m------------------------\u001b[0m";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testCreateTask() {
        // Set up the test environment
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Set up the input
        String userInput = "New Task\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // Create test data
        String[] toDoList = new String[3];

        // Call the method to be tested
        ToDoList.createTask(toDoList);

        // Restore the original System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

        // Verify the output and the updated toDoList
        String expectedOutput = "\n\u001b[38;5;15mType in the task name: \u001b[0m" + "\n\u001b[38;5;10mThe task '\u001b[38;5;15mNew Task\u001b[38;5;10m' was created!\u001b[0m";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
        // Add more assertions based on the expected behavior of createTask
        // For example, you might check if the task is added to the toDoList
    }
}
