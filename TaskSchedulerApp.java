import java.util.List;
import java.util.Scanner;

public class TaskSchedulerApp {
    private static TaskScheduler taskScheduler = new TaskScheduler();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Task Scheduler Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Search Task");
            System.out.println("4. List Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task priority: ");
                    int priority = scanner.nextInt();
                    taskScheduler.addTask(id, description, priority);
                    System.out.println("Task added successfully.");
                    break;

                case 2:
                    System.out.print("Enter task ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    taskScheduler.deleteTask(idToDelete);
                    System.out.println("Task deleted successfully.");
                    break;

                case 3:
                    System.out.print("Enter task ID to search: ");
                    int idToSearch = scanner.nextInt();
                    Task task = taskScheduler.searchTask(idToSearch);
                    if (task != null) {
                        System.out.println("Task found: " + task.getId() + ", " + task.getDescription() + ", " + task.getPriority());
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case 4:
                    List<Task> tasks = taskScheduler.listTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks in the scheduler.");
                    } else {
                        System.out.println("Tasks in the scheduler:");
                        for (Task t : tasks) {
                            System.out.println(t.getId() + ", " + t.getDescription() + ", " + t.getPriority());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
