
public class Tarefa {
    boolean created;
    boolean done;
    boolean deleted;
    String task;
    String info;

    public Tarefa(String task, String info) {
        this.created = true;
        this.done = false;
        this.deleted = false;
        this.task = task;
        this.info = info;
        ToDoList.tarefas.add(this);
        ToDoList.taskCount++;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
