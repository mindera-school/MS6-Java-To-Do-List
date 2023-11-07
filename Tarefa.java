
public class Tarefa {
    boolean created;
    boolean done;
    String task;
    String info;
    String time;

    public Tarefa(String task, String info) {
        this.done = false;
        this.task = task;
        this.info = info;
        ToDoList.tarefas.add(this);
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
