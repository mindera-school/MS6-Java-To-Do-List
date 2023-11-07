import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean premiumPlan = false;
        String[] toDoList;
        ArrayList<String> deletedTasks = new ArrayList<>();
        if (!premiumPlan) {
            System.out.println("\n\t\t\t\t\u001b[43;1m\u001b[38;5;15mIMPORTANT WARNING\u001b[0m\u001b[38;5;11m\nYou are currently using the Free Plan of ToDoList!\nYou can upgrade to Premium Plan in the upgrade menu!\u001b[0m");
            toDoList = new String[10];
        } else {
            toDoList = new String[30];
        }

        String userChoice;

        do {

            int count = 0;
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    count++;
                }
            }

            System.out.println("\n\u001b[38;5;15mYou still have \u001b[38;5;11m" + count + "\u001b[38;5;15m free spaces on the list!\u001b[0m");

//            System.out.println("\n\u001b[38;5;15m " + Arrays.toString(toDoList) + "\u001b[0m");

            System.out.println("\u001b[38;5;15m0 - Exit ToDoList\u001b[0m");
            System.out.println("\u001b[38;5;15m1 - Show ToDoList\u001b[0m");
            System.out.println("\u001b[38;5;15m2 - Create task\u001b[0m");
            System.out.println("\u001b[38;5;15m3 - Mark as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m4 - Remove as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m5 - Edit task\u001b[0m");
            System.out.println("\u001b[38;5;15m6 - Delete task\u001b[0m");
            System.out.println("\u001b[38;5;15m7 - Organize alphabetically\u001b[0m");
            System.out.println("\u001b[38;5;15m8 - Upgrade ToDoList Plan\u001b[0m");
            System.out.println("\u001b[38;5;15m9 - Organize by completed and uncompleted\u001b[0m");
            System.out.println("\u001b[38;5;15m10 - Remove all tasks set as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m11 - Recover deleted tasks\u001b[0m");
            System.out.print("\u001b[38;5;15mChoose a option: \u001b[0m");
            userChoice = scan.next();

            scan.nextLine();


            switch (userChoice) {
                case "1":
                    showToDoList(toDoList);
                    break;
                case "2":
                    createTask(toDoList);
                    break;
                case "3":
                    markTaskAsCompleted(toDoList);
                    break;
                case "4":
                    removeTaskAsCompleted(toDoList);
                    break;
                case "5":
                    editTask(toDoList);
                    break;
                case "6":
                    deleteTask(toDoList, deletedTasks);
                    break;
                case "7":
                    organizeAlphabetically(toDoList);
                    break;
                case "8":
                    premiumPlan = upgradeToDoListPlan(toDoList, premiumPlan);
                    if (premiumPlan) {
                        String[] tempToDoList = new String[30];

                        for (int i = 0; i < toDoList.length; i++) {
                            if (i < tempToDoList.length) {
                                tempToDoList[i] = toDoList[i];
                            } else {
                                break;
                            }
                        }

                        toDoList = tempToDoList;
                    }
                    break;
                case "9":
                    organizeByDoneAndUndone(toDoList);
                    break;
                case "10":
                    removeAllTasksSetAsCompleted(toDoList, deletedTasks);
                    break;
                case "11":
                    recoverDeletedTasks(toDoList, deletedTasks);
                    break;
                case "0":
                    System.out.println("\n\u001b[38;5;9mClosing ToDoList program...\u001b[0m");
                    break;
                default:
                    System.out.println("\n\u001b[38;5;9mInvalid option!\u001b[0m");
                    break;
            }

        } while (!userChoice.equals("0"));
    }

    public static void showToDoList(String[] toDoList) {

        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }

        if (count > 0) {
            System.out.println("\n\t\t\u001b[38;5;15mToDoList\u001b[0m");
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] != null) {
                    if (toDoList[i].contains(" ✅")) {
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;40m" + toDoList[i] + "\u001b[0m");
                    } else {
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;1m" + toDoList[i] + "\u001b[0m");
                    }
                }
            }
            System.out.println("This To Do List has " + taskCountDisplay(toDoList) + " tasks");
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
        } else {
            System.out.println("\n\u001b[38;5;9mThe ToDoList is empty! You should create a task first.\u001b[0m");
        }
    }

    public static void createTask(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\u001b[38;5;15mCreate task: \u001b[0m");
        String userNewTask = scan.nextLine().trim();

        if (!userNewTask.isEmpty()) {
            boolean added = false;

            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    toDoList[i] = userNewTask;
                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + userNewTask + "\u001b[38;5;10m' was created!\u001b[0m");
                    added = true;
                    break;
                }
            }

            if (!added) {
                System.out.println("\n\n\u001b[38;5;9mThe list is full! You don't have more space.\u001b[0m");
            }
        } else {
            System.out.println("\n\n\u001b[38;5;9mTask name cannot be empty.\u001b[0m");
        }
    }

    public static void markTaskAsCompleted(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to mark as completed: \u001b[0m");
            int userChoiceOfTaskToMarkAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToMarkAsCompleted] != null) {
                if (toDoList[userChoiceOfTaskToMarkAsCompleted].contains(" ✅")) {
                    System.out.println("\n\u001b[38;5;9mThat task is already marked as completed!\u001b[0m");
                } else {
                    toDoList[userChoiceOfTaskToMarkAsCompleted] = toDoList[userChoiceOfTaskToMarkAsCompleted].concat(" ✅");
                    System.out.println("\n\u001b[38;5;10mTask successfuly marked as completed!\u001b[0m");
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks!\u001b[0m");
        }
    }

    public static void removeTaskAsCompleted(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int existsCompletedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                System.out.println(i + " - " + toDoList[i]);
                existsCompletedTasks++;
            }
        }

        if (existsCompletedTasks > 0) {

            System.out.print("\n\u001b[38;5;15mChoose a task to remove as completed: \u001b[0m");
            int userChoiceOfTaskToRemoveAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToRemoveAsCompleted] != null) {
                toDoList[userChoiceOfTaskToRemoveAsCompleted] = toDoList[userChoiceOfTaskToRemoveAsCompleted].replace(" ✅", "");
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have completed tasks!\u001b[0m");
        }
    }

    public static void editTask(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to edit: \u001b[0m");
            int userChoiceOfTaskToEdit = scan.nextInt();

            scan.nextLine();

            if (toDoList[userChoiceOfTaskToEdit] != null) {

                if (toDoList[userChoiceOfTaskToEdit].contains(" ✅")) {
                    toDoList[userChoiceOfTaskToEdit] = toDoList[userChoiceOfTaskToEdit].replace(" ✅", "");
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoiceOfTaskToEdit] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    String userEditTask = scan.nextLine();

                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToEdit] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");
                    userEditTask = userEditTask.concat(" ✅");
                    toDoList[userChoiceOfTaskToEdit] = userEditTask;
                } else {
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoiceOfTaskToEdit] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    String userEditTask = scan.nextLine();

                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToEdit] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");
                    toDoList[userChoiceOfTaskToEdit] = userEditTask;
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks to edit!\u001b[0m");
        }
    }

    public static void deleteTask(String[] toDoList, ArrayList<String> deletedTasks) {
        Scanner scan = new Scanner(System.in);

        int existedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                existedTasks++;
            }
        }

        if (existedTasks > 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to delete: \u001b[0m");
            int userChoiceOfTaskToDelete = scan.nextInt();

            if (toDoList[userChoiceOfTaskToDelete] != null) {
                System.out.println("\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToDelete] + "\u001b[38;5;10m' was successfully deleted!\u001b[0m");
                deletedTasks.add(toDoList[userChoiceOfTaskToDelete]);
                toDoList[userChoiceOfTaskToDelete] = null;
            } else {
                System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\u001b[38;5;9mYou don't have tasks to delete!\u001b[0m");
        }
    }

    public static void organizeAlphabetically(String[] toDoList) {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }
        Arrays.sort(toDoList, 0, count);
    }

    public static boolean upgradeToDoListPlan(String[] toDoList, boolean premium) {
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

    public static void organizeByDoneAndUndone(String[] toDoList) {
        String[] toDoListCopy1 = new String[toDoList.length];
        String[] toDoListCopy2 = new String[toDoList.length];
        System.arraycopy(toDoList, 0, toDoListCopy1, 0, toDoListCopy1.length);
        System.arraycopy(toDoList, 0, toDoListCopy2, 0, toDoListCopy1.length);
        int counter = 0;
        System.out.println("\nHere is the list organized:");
        for (int i = 0; i < toDoListCopy1.length; i++) {
            if (toDoListCopy1[i] != null) {
                if (toDoListCopy1[i].contains(" ✅")) {
                    System.out.println(toDoListCopy1[i]);
                    toDoList[counter] = toDoListCopy1[i];
                    counter++;
                }
            }
        }

        for (int i = 0; i < toDoListCopy2.length; i++) {
            if (toDoListCopy2[i] != null) {
                if (!toDoListCopy2[i].contains(" ✅")) {
                    System.out.println(toDoListCopy2[i]);
                    toDoList[counter] = toDoListCopy2[i];
                    counter++;
                }
            }
        }

        System.out.println("This To Do List has " + taskCountDisplay(toDoList) + " tasks");
    }

    public static void removeAllTasksSetAsCompleted(String[] toDoList, ArrayList<String> deletedTasks) {
        int counter = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                if (toDoList[i].contains(" ✅")) {
                    deletedTasks.add(toDoList[i]);
                    toDoList[i] = null;
                    counter++;
                }
            }
        }
        if (counter < 1) {
            System.out.println("There wasn't any task set as completed to remove!");
        } else {
            System.out.println("All tasks set as completed were removed!");
        }
    }

    public static void recoverDeletedTasks(String[] toDoList, ArrayList<String> deletedTasks) {
        System.out.println("\nHere are the deleted tasks!");
        for (int i = 0; i < deletedTasks.size(); i++) {
            System.out.println(deletedTasks.get(i));
        }
        System.out.println("\nChoose the one you want to recover");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        if (userInput >= 0 && userInput < deletedTasks.size()) {
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    toDoList[i] = deletedTasks.get(userInput);
                    deletedTasks.remove(userInput);
                    break;
                }
            }
        }
        showToDoList(toDoList);
    }

    public static int taskCountDisplay(String[] toDoList) {
        int counter = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                counter++;
            }
        }
        return counter;
    }
}