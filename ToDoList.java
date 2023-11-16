import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class ToDoList {
    static Scanner scan = new Scanner(System.in);
    static boolean premiumPlan = false;
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<Task> garbage = new ArrayList<>();
    static int maxTasks = 0;
    static Timestamp timestamp;

    public static void main(String[] args) throws InterruptedException {
        start();

    }


    public static void start() throws InterruptedException {

        if (!premiumPlan) {
            System.out.println("\n                                                                  \t\t\t\t\u001b[43;1m\u001b[38;5;15mIMPORTANT WARNING\u001b[0m\u001b[38;5;11m\n" + "                                                                  You are currently using the Free Plan of ToDoList!\n" + "                                                                  You can upgrade to Premium Plan in the upgrade menu!\u001b[0m");
            maxTasks = 10;
        } else {
            maxTasks = 30;
        }
        int sleep = 0;

        int userChoice;
        do {
            userChoice = -10;
            int done = 0;


            if (sleep == 0) {
                Thread.sleep(2500);
                sleep++;
            } else {
                Thread.sleep(500);
            }

            System.out.println("\n\u001b[38;5;15m                                                                  You still have \u001b[38;5;11m" + (maxTasks - tasks.size()) + "\u001b[38;5;15m free spaces on the list!\u001b[0m");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).done) {
                    done++;
                }
            }
            double percentage;
            if (tasks.size() == 0) {
                percentage = 0;
            } else {
                percentage = (done * 100) / tasks.size();
            }

            System.out.println("                                                                  Number of tasks: " + tasks.size() + "  (" + percentage + "% completed!)");

            System.out.println("\n                                                                  \u001b[38;5;15m1 - Show ToDoList\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m2 - Create task\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m3 - Mark as completed\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m4 - Remove as completed\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m5 - Edit task\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m6 - Delete task\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m7 - Organize A-Z / Z-A / \u001b[38;5;11mDone-Undone\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m8 - \u001b[38;5;11mDelete all completed tasks\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m9 - \u001b[38;5;11mRecover deleted tasks\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m10 - \u001b[38;5;11mUpdate task time\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m11 - \u001b[38;5;11m🌟Upgrade🌟 \u001b[38;5;15mToDoList Plan\u001b[0m");
            System.out.println("                                                                  \u001b[38;5;15m0 - Exit ToDoList\u001b[0m\n");
            System.out.print("                                                                  \u001b[38;5;15mChoose a option: \u001b[0m");
            try {
                userChoice = scan.nextInt();

                switch (userChoice) {
                    case 1:
                        showToDoList();
                        break;
                    case 2:
                        createTask();
                        break;
                    case 3:
                        markTaskAsCompleted();
                        break;
                    case 4:
                        unmarkTaskAsCompleted();
                        break;
                    case 5:
                        editTask();
                        break;
                    case 6:
                        deleteTask();
                        break;
                    case 7:
                        System.out.println("1-  A-Z  ||  2-  Z-A  ||  3-  Done-Undone");
                        int userChoic = scan.nextInt();
                        switch (userChoic) {
                            case 1:
                                organizeAZ();
                                break;
                            case 2:
                                organizeZA();
                                break;
                            case 3:
                                organizeDoneUndone();
                                break;
                            default:
                                System.out.println("Ahm...?");
                        }
                        break;
                    case 8:
                        deleteAllDone();
                        break;
                    case 9:
                        recover();
                        break;
                    case 10:
                        updateTime();
                        break;
                    case 11:
                        premiumPlan = upgradeToDoListPlan(premiumPlan);
                        if (premiumPlan) {
                            maxTasks = 30;
                        }
                        break;
                    case 0:
                        System.out.println("\n\u001b[38;5;9mClosing ToDoList program... Byeeee 😘\u001b[0m");
                        break;
                    default:
                        System.out.println("\n\u001b[38;5;9mInvalid option!\u001b[0m");
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n\u001b[38;5;9mSomething went wrong!\nError: \u001b[0m" + e);
                start();
                scan.next();
            }
        } while (userChoice != 0);
    }

    public static void showToDoList() {

        if (tasks.size() > 0) {
            System.out.println("\n                                                                                 \t\t\u001B[34mToDoList\u001b[0m");
            System.out.println("\u001b[38;5;8m                                                                  ------------------------------------------------\u001b[0m");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).done) {
                    System.out.println("                                                                  \u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;40m" + tasks.get(i).description + "  🏷️ " + tasks.get(i).info + "  🕓 \u001b[0m" + tasks.get(i).timestamp + " ✅ \u001b[0m");
                } else {
                    System.out.println("                                                                  \u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;1m" + tasks.get(i).description + "  🏷️ " + tasks.get(i).info + "  🕓 \u001b[0m" + tasks.get(i).timestamp + "\u001b[0m");
                }
            }
            System.out.println("                                                                  \u001b[38;5;8m------------------------------------------------\u001b[0m");
        } else {
            System.out.println("\n                                                                  \u001b[38;5;9mThe ToDoList is empty! You should create a task first.\u001b[0m");
        }
    }

    public static void createTask() throws IOException {
        Scanner scan = new Scanner(System.in);
        Task task = null;

        System.out.print("\n\u001b[38;5;15mCreate task: \u001b[0m");
        String userNewTask = scan.nextLine().trim();
        System.out.print("\n\u001b[38;5;15mAdd info: \u001b[0m");
        String userNewInfo = scan.nextLine().trim();
        timestamp = new Timestamp(System.currentTimeMillis());

        if (tasks.size() < 30) {
            task = new Task(userNewTask, userNewInfo, timestamp);
            System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + "📜 " + userNewTask + "  🏷️ " + userNewInfo + "\u001b[38;5;10m' was created!\u001b[0m");

        } else {
            System.out.println("\n\n\u001b[38;5;9mThe list is full! You don't have more space.\u001b[0m");
        }
      //  FileManager.taskToFile(task);
      //  FileManager.loadFile();
    }


    public static void markTaskAsCompleted() {
        showToDoList();
        Scanner scan = new Scanner(System.in);

        if (tasks.size() != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to mark as completed: \u001b[0m");
            int userChoiceOfTaskToMarkAsCompleted = scan.nextInt() - 1;

            if (userChoiceOfTaskToMarkAsCompleted >= 0 && userChoiceOfTaskToMarkAsCompleted < tasks.size()) {
                if (tasks.get(userChoiceOfTaskToMarkAsCompleted).done) {
                    System.out.println("\n\u001b[38;5;9mThat task is already marked as completed!\u001b[0m");
                } else {
                    tasks.get(userChoiceOfTaskToMarkAsCompleted).done = true;
                    System.out.println("\n\u001b[38;5;10mTask successfuly marked as completed!\u001b[0m");
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks!\u001b[0m");
        }
    }

    public static void unmarkTaskAsCompleted() {
        Scanner scan = new Scanner(System.in);
        showToDoList();
        int existsCompletedTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).done) {
                existsCompletedTasks++;
            }
        }

        if (existsCompletedTasks > 0) {

            System.out.print("\n\u001b[38;5;15mChoose a task to remove as completed: \u001b[0m");
            int userChoiceOfTaskToRemoveAsCompleted = scan.nextInt() - 1;

            if (userChoiceOfTaskToRemoveAsCompleted >= 0 && userChoiceOfTaskToRemoveAsCompleted < tasks.size()) {
                tasks.get(userChoiceOfTaskToRemoveAsCompleted).done = false;
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have completed tasks!\u001b[0m");
        }
    }

    public static void editTask() {
        Scanner scan = new Scanner(System.in);
        showToDoList();

        if (tasks.size() != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to edit: \u001b[0m");
            int userChoiceOfTaskToEdit = scan.nextInt() - 1;

            scan.nextLine();

            if (userChoiceOfTaskToEdit >= 0 && userChoiceOfTaskToEdit < tasks.size()) {
                System.out.println("\n\u001b[38;5;15mOld: " + tasks.get(userChoiceOfTaskToEdit).description + "\u001b[0m");
                System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                String userEditTask = scan.nextLine();
                System.out.println("\n\u001b[38;5;10mTask '\u001b[38;5;15m" + tasks.get(userChoiceOfTaskToEdit).description + "\u001b[38;5;10m' edited!");
                tasks.get(userChoiceOfTaskToEdit).description = userEditTask;

                System.out.println("\n\u001b[38;5;15mOld: " + tasks.get(userChoiceOfTaskToEdit).info + "\u001b[0m");
                System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                String userEditInfo = scan.nextLine();
                System.out.println("\n\u001b[38;5;10mInfo '\u001b[38;5;15m" + tasks.get(userChoiceOfTaskToEdit).info + "\u001b[38;5;10m' edited!");
                tasks.get(userChoiceOfTaskToEdit).info = userEditInfo;


            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks to edit!\u001b[0m");
        }
    }

    public static void deleteTask() {
        Scanner scan = new Scanner(System.in);

        showToDoList();

        if (tasks.size() > 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to delete: \u001b[0m");
            int userChoiceOfTaskToDelete = scan.nextInt() - 1;

            if (userChoiceOfTaskToDelete >= 0 && userChoiceOfTaskToDelete < tasks.size()) {
                System.out.println("\u001b[38;5;10mThe task '\u001b[38;5;15m" + tasks.get(userChoiceOfTaskToDelete).description + "\u001b[38;5;10m' was successfully deleted!\u001b[0m");
                garbage.add(tasks.get(userChoiceOfTaskToDelete));
                tasks.remove(userChoiceOfTaskToDelete);
            } else {
                System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\u001b[38;5;9mYou don't have tasks to delete!\u001b[0m");
        }
    }

    public static void organizeAZ() {
        Collections.sort(tasks, Comparator.comparing(Task::getDescription));
        System.out.println("\u001b[0m                                                                        Organized! 🥳");
    }

    public static void organizeZA() {
        organizeAZ();
        Collections.reverse(tasks);
        System.out.println("\u001b[0m                                                                        Organized! 🥳");
    }

    public static void organizeDoneUndone() throws InterruptedException {

        if (premiumPlan) {
            Collections.sort(tasks, Comparator.comparing(Task::isDone));
            Collections.reverse(tasks);
            System.out.println("\u001b[0m                                                                        Organized! 🥳");
        } else {
            System.out.println("\u001b[0m                                                                        You have no premium... sorry!");
        }
    }

    public static boolean upgradeToDoListPlan(boolean premium) {
        Scanner scan = new Scanner(System.in);
        if (!premium) {
            System.out.println("\n\u001b[38;5;15m                                                                  Do you want to buy Premium Plan?🙏 (yes or no)\u001b[0m");
            System.out.print("\u001b[38;5;15m> \u001b[0m");
            String userUpgradeOption = scan.next();

            switch (userUpgradeOption) {
                case "yes":
                    premium = true;
                    System.out.println("\n\u001b[38;5;10m                                                                  Currently plan setted to Premium! Thank you!🤌\u001b[0m");
                    break;
                default:
                    premium = false;
                    System.out.println("\n\u001b[38;5;12m                                                                  Maybe next time then...💩\u001b[0m");
                    break;
            }

            return premium;
        } else {
            System.out.println("\n\u001b[38;5;11m                                                                  Your plan is already setted to Premium! You don't need to buy it again.\u001b[0m");
        }
        return premium;
    }

    public static void deleteAllDone() {
        if (premiumPlan) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).done) {
                    garbage.add(tasks.get(i));
                }
            }
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).done) {
                    tasks.remove(i);
                    i--;
                }
            }
        } else {
            System.out.println("\u001b[0m                                                                        You have no premium... sorry!");
        }
    }

    public static void recover() {
        if (premiumPlan) {
            if (garbage.size() != 0) {
                for (int i = 0; i < garbage.size(); i++) {
                    if (garbage.get(i).done) {
                        System.out.println("                                                                  🗑️  \u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;40m" + garbage.get(i).description + "  🏷️ " + garbage.get(i).info + " ✅ \u001b[0m");
                    } else {
                        System.out.println("                                                                  🗑️  \u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;1m" + garbage.get(i).description + "  🏷️ " + garbage.get(i).info + "\u001b[0m");
                    }
                }
                System.out.print("Insert task ID:");
                int userChoiceOfTaskToEdit = scan.nextInt() - 1;
                if (userChoiceOfTaskToEdit >= 0 && userChoiceOfTaskToEdit < garbage.size()) {
                    tasks.add(garbage.get(userChoiceOfTaskToEdit));
                    garbage.remove(userChoiceOfTaskToEdit);
                    System.out.println("                                                                  Task recovered! 🥳");
                }
            } else {
                System.out.println("                                                                  You have no deleted tasks!");
            }
        } else {
            System.out.println("\u001b[0m                                                                        You have no premium... sorry!");
        }
    }

    public static void updateTime() {
        if (premiumPlan) {
            Scanner scan = new Scanner(System.in);

            showToDoList();

            if (tasks.size() > 0) {
                System.out.print("\n\u001b[38;5;15mChoose a task to update time: \u001b[0m");
                int userChoiceOfTaskToUpdate = scan.nextInt() - 1;

                if (userChoiceOfTaskToUpdate >= 0 && userChoiceOfTaskToUpdate < tasks.size()) {
                    System.out.println("\u001b[38;5;10m                                                                        " + "The time of task '\u001b[38;5;15m" + tasks.get(userChoiceOfTaskToUpdate).description + "\u001b[38;5;10m' was successfully updated!\u001b[0m");
                    tasks.get(userChoiceOfTaskToUpdate).timestamp = new Timestamp(System.currentTimeMillis());
                }
            }
        } else {
            System.out.println("\u001b[0m                                                                        You have no premium... sorry!");
        }
    }
}