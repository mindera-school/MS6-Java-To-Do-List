import java.util.*;

public class ToDoList {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    static Scanner scan = new Scanner(System.in);
    static boolean premiumPlan = false;
    static ArrayList<Tarefa> tarefas = new ArrayList<>();
    static ArrayList<Tarefa> garbage = new ArrayList<>();
    static int maxTasks = 0;

    public static void main(String[] args) throws InterruptedException {

        int userChoice;

        if (!premiumPlan) {
            System.out.println("\n\t\t\t\t\u001b[43;1m\u001b[38;5;15mIMPORTANT WARNING\u001b[0m\u001b[38;5;11m\n" +
                    "You are currently using the Free Plan of ToDoList!\nYou can upgrade to Premium Plan in the upgrade menu!\u001b[0m");
            maxTasks = 10;
        } else {
            maxTasks = 30;
        }

        do {
            int done = 0;
            Thread.sleep(2500);

            System.out.println("\n\u001b[38;5;15mYou still have \u001b[38;5;11m" + (maxTasks - tarefas.size()) + "\u001b[38;5;15m free spaces on the list!\u001b[0m");
            for (int i = 0; i < tarefas.size(); i++) {
                if (tarefas.get(i).done) {
                    done++;
                }
            }
            double percentage;
            if(tarefas.size()==0){
                percentage=0;
            }else {
                percentage = (done * 100) / tarefas.size();
            }
            System.out.println("Number of tasks: " + tarefas.size() + "  (" + percentage +"% completed!)");

            System.out.println("\n\u001b[38;5;15m1 - Show ToDoList\u001b[0m");
            System.out.println("\u001b[38;5;15m2 - Create task\u001b[0m");
            System.out.println("\u001b[38;5;15m3 - Mark as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m4 - Remove as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m5 - Edit task\u001b[0m");
            System.out.println("\u001b[38;5;15m6 - Delete task\u001b[0m");
            System.out.println("\u001b[38;5;15m7 - Organize A-Z / Z-A / \u001b[38;5;11mDone-Undone\u001b[0m");
            System.out.println("\u001b[38;5;15m8 - \u001b[38;5;11mDelete all completed tasks\u001b[0m");
            System.out.println("\u001b[38;5;15m9 - \u001b[38;5;11mRecover deleted tasks\u001b[0m");
            System.out.println("\u001b[38;5;15m10 - \u001b[38;5;11m🌟Upgrade🌟 \u001b[38;5;15mToDoList Plan\u001b[0m");
            System.out.println("\u001b[38;5;15m0 - Exit ToDoList\u001b[0m\n");
            System.out.print("\u001b[38;5;15mChoose a option: \u001b[0m");
            userChoice = scan.nextInt();

            scan.nextLine();

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
                    premiumPlan = upgradeToDoListPlan(tarefas, premiumPlan);
                    if (premiumPlan) {
                        maxTasks = 30;
                    }
                    break;
                //////   V.2    //////
                case 9:
                    deleteAllDone();
                    break;
                case 10:
                    recover();
                    break;
                case 0:
                    System.out.println("\n\u001b[38;5;9mClosing ToDoList program... Byeeee 😘\u001b[0m");
                    break;
                default:
                    System.out.println("\n\u001b[38;5;9mInvalid option!\u001b[0m");
                    break;
            }

        } while (userChoice != 0);
    }

    public static void showToDoList() {

        if (tarefas.size() > 0) {
            System.out.println("\n\t\t\u001b[38;5;15mToDoList\u001b[0m");
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
            for (int i = 0; i < tarefas.size(); i++) {
                if (tarefas.get(i).done) {
                    System.out.println("\u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;40m" + tarefas.get(i).task +
                            "  🏷️ " + tarefas.get(i).info + " ✅ \u001b[0m");
                } else {
                    System.out.println("\u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;1m" + tarefas.get(i).task +
                            "  🏷️ " + tarefas.get(i).info + "\u001b[0m");
                }
            }
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
        } else {
            System.out.println("\n\u001b[38;5;9mThe ToDoList is empty! You should create a task first.\u001b[0m");
        }
    }

    public static void createTask() {
        Scanner scan = new Scanner(System.in);
        boolean full = true;

        System.out.print("\n\u001b[38;5;15mCreate task: \u001b[0m");
        String userNewTask = scan.nextLine().trim();
        System.out.print("\n\u001b[38;5;15mAdd info: \u001b[0m");
        String userNewInfo = scan.nextLine().trim();


        if (tarefas.size() < 30) {
            new Tarefa(userNewTask, userNewInfo);
            System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + "📜 " + userNewTask +
                    "  🏷️ " + userNewInfo + "\u001b[38;5;10m' was created!\u001b[0m");
            full = false;
        }

        if (full) {
            System.out.println("\n\n\u001b[38;5;9mThe list is full! You don't have more space.\u001b[0m");
        }
    }


    public static void markTaskAsCompleted() {
        showToDoList();
        Scanner scan = new Scanner(System.in);

        if (tarefas.size() != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to mark as completed: \u001b[0m");
            int userChoiceOfTaskToMarkAsCompleted = scan.nextInt() - 1;

            if (userChoiceOfTaskToMarkAsCompleted >= 0 && userChoiceOfTaskToMarkAsCompleted < tarefas.size()) {
                if (tarefas.get(userChoiceOfTaskToMarkAsCompleted).done) {
                    System.out.println("\n\u001b[38;5;9mThat task is already marked as completed!\u001b[0m");
                } else {
                    tarefas.get(userChoiceOfTaskToMarkAsCompleted).done = true;
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
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).done) {
                existsCompletedTasks++;
            }
        }

        if (existsCompletedTasks > 0) {

            System.out.print("\n\u001b[38;5;15mChoose a task to remove as completed: \u001b[0m");
            int userChoiceOfTaskToRemoveAsCompleted = scan.nextInt() - 1;

            if (userChoiceOfTaskToRemoveAsCompleted >= 0 && userChoiceOfTaskToRemoveAsCompleted < tarefas.size()) {
                tarefas.get(userChoiceOfTaskToRemoveAsCompleted).done = false;
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

        if (tarefas.size() != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to edit: \u001b[0m");
            int userChoiceOfTaskToEdit = scan.nextInt() - 1;

            scan.nextLine();

            if (userChoiceOfTaskToEdit >= 0 && userChoiceOfTaskToEdit < tarefas.size()) {
                System.out.println("\n\u001b[38;5;15mOld: " + tarefas.get(userChoiceOfTaskToEdit).task + "\u001b[0m");
                System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                String userEditTask = scan.nextLine();
                System.out.println("\n\u001b[38;5;10mTask '\u001b[38;5;15m" + tarefas.get(userChoiceOfTaskToEdit).task + "\u001b[38;5;10m' edited!");
                tarefas.get(userChoiceOfTaskToEdit).task = userEditTask;

                System.out.println("\n\u001b[38;5;15mOld: " + tarefas.get(userChoiceOfTaskToEdit).info + "\u001b[0m");
                System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                String userEditInfo = scan.nextLine();
                System.out.println("\n\u001b[38;5;10mInfo '\u001b[38;5;15m" + tarefas.get(userChoiceOfTaskToEdit).info + "\u001b[38;5;10m' edited!");
                tarefas.get(userChoiceOfTaskToEdit).info = userEditInfo;


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

        if (tarefas.size() > 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to delete: \u001b[0m");
            int userChoiceOfTaskToDelete = scan.nextInt() - 1;

            if (userChoiceOfTaskToDelete >= 0 && userChoiceOfTaskToDelete < tarefas.size()) {
                System.out.println("\u001b[38;5;10mThe task '\u001b[38;5;15m" + tarefas.get(userChoiceOfTaskToDelete).task + "\u001b[38;5;10m' was successfully deleted!\u001b[0m");
                garbage.add(tarefas.get(userChoiceOfTaskToDelete));
                tarefas.remove(userChoiceOfTaskToDelete);
            } else {
                System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\u001b[38;5;9mYou don't have tasks to delete!\u001b[0m");
        }
    }

    public static void organizeAZ() {
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getTask));
    }

    public static void organizeZA() {
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getTask));
        Collections.reverse(tarefas);
    }

    public static void organizeDoneUndone() throws InterruptedException {

        if(premiumPlan) {
            Collections.sort(tarefas, new Comparator<Tarefa>() {
                @Override
                public int compare(Tarefa abc1, Tarefa abc2) {
                    return Boolean.compare(abc2.done, abc1.done);
                }
            });
        }else{
            System.out.println("\u001b[0mYou have no premium... sorry!");
        }
    }

    public static boolean upgradeToDoListPlan(ArrayList toDoList, boolean premium) {
        Scanner scan = new Scanner(System.in);
        if (!premium) {
            System.out.println("\n\u001b[38;5;15mDo you want to buy Premium Plan? (yes or no)\u001b[0m");
            System.out.print("\u001b[38;5;15m> \u001b[0m");
            String userUpgradeOption = scan.next();

            switch (userUpgradeOption) {
                case "yes":
                    premium = true;
                    System.out.println("\n\u001b[38;5;10mCurrently plan setted to Premium! Thank you!\u001b[0m");
                    break;
                default:
                    premium = false;
                    System.out.println("\n\u001b[38;5;12mMaybe next time then...\u001b[0m");
                    break;
            }

            return premium;
        } else {
            System.out.println("\n\u001b[38;5;11mYour plan is already setted to Premium! You don't need to buy it again.\u001b[0m");
        }
        return premium;
    }

    public static void deleteAllDone() {
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).done) {
                garbage.add(tarefas.get(i));
            }
        }
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).done) {
                tarefas.remove(i);
                i--;
            }
        }

    }

    public static void recover() {
        if (garbage.size() != 0) {
            for (int i = 0; i < garbage.size(); i++) {
                if (garbage.get(i).done) {
                    System.out.println("🗑️  \u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;40m" + garbage.get(i).task +
                            "  🏷️ " + garbage.get(i).info + " ✅ \u001b[0m");
                } else {
                    System.out.println("🗑️  \u001b[38;5;7m" + (i + 1) + ".  📜 \u001b[38;5;1m" + garbage.get(i).task +
                            "  🏷️ " + garbage.get(i).info + "\u001b[0m");
                }
            }
            System.out.print("Insert task ID:");
            int userChoiceOfTaskToEdit = scan.nextInt() - 1;
            if (userChoiceOfTaskToEdit >= 0 && userChoiceOfTaskToEdit < garbage.size()) {
                tarefas.add(garbage.get(userChoiceOfTaskToEdit));
                garbage.remove(userChoiceOfTaskToEdit);
                System.out.println("Task recovered! 🥳");
            }
        } else {
            System.out.println("You have no deleted tasks!");
        }

    }
}