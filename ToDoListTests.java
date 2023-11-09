import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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
                // Include expected output for task completion percentage and separators if needed
                "\u001b[38;5;8m------------------------\u001b[0m";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }
}
