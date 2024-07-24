public class TaskScheduler {
    private RBTree taskTree;

    public TaskScheduler() {
        taskTree = new RBTree();
    }

    public void addTask(int id, String description, int priority) {
        Task task = new Task(id, description, priority);
        taskTree.insert(task);
    }

    public void deleteTask(int id) {
        taskTree.delete(id);
    }

    public Task searchTask(int id) {
        return taskTree.search(id);
    }

    public List<Task> listTasks() {
        return taskTree.inOrderTraversal();
    }
}
