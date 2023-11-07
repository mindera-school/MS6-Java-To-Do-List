import java.sql.Timestamp;

public class Task {
    boolean done;
    String description;
    String info;
    Timestamp timestamp;


    public Task(String description, String info, Timestamp timestamp) {
        this.done = false;
        this.description = description;
        this.info = info;
        this.timestamp = timestamp;
        ToDoList.tasks.add(this);
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
