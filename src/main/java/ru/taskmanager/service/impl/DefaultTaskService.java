package ru.taskmanager.service.impl;

import ru.taskmanager.model.Task;
import ru.taskmanager.model.TaskStatus;
import ru.taskmanager.service.TaskService;

import java.util.ArrayList;

public class DefaultTaskService implements TaskService {

    private final ArrayList<Task> taskList = new ArrayList<>();

    @Override
    public void createTask(String title, String description) {
        if (title == null || title.isBlank()) {
            System.out.println("Введите название задачи");
            return;
        }

        if (description == null || description.isBlank()) {
            System.out.println("Введите описание задачи");
            return;
        }

        Task task = new Task(title, description);

        taskList.add(task);
    }

    @Override
    public void getAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("Задачи отсутствуют");
            return;
        }

        for (Task task : taskList) {
            System.out.println(task.getId() +
                    "\t" + task.getTitle() +
                    "\t" + task.getDescription() +
                    "\t" + task.getTaskStatus());
        }
    }

    @Override
    public void getTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Задача " + id + " не найдена");
            return;
        }

        System.out.println(task.getId() +
                "\t" + task.getTitle() +
                "\t" + task.getDescription() +
                "\t" + task.getTaskStatus());
    }

    @Override
    public void updateTask(int id, String title, String description) {
        Task task = findTaskById(id);

        if (title == null || title.isBlank()) {
            System.out.println("Введите название задачи");
            return;
        }

        if (description == null || description.isBlank()) {
            System.out.println("Введите описание задачи");
            return;
        }

        if (task == null) {
            System.out.println("Задача " + id + " не найдена");
            return;
        }

        task.setTitle(title);
        task.setDescription(description);

        System.out.println("Задача " + task.getId() + " успешно обновлена");

    }

    @Override
    public void startTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Задача " + id + " не найдена");
            return;
        }

        task.setTaskStatus(TaskStatus.IN_PROGRESS);

        System.out.println("Задача " + task.getId() + " начата");
    }

    @Override
    public void endTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Задача " + id + " не найдена");
            return;
        }

        task.setTaskStatus(TaskStatus.COMPLETED);

        System.out.println("Задача " + task.getId() + " завершена");
    }

    @Override
    public void deleteTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Задача " + id + " не найдена");
            return;
        }

        taskList.remove(task);

        System.out.println("Задача " + id + " успешно удалена");
    }

    @Override
    public void filterTasks(TaskStatus status) {
        int taskCount = 0;

        for (Task task : taskList) {
            if (status.equals(task.getTaskStatus())) {
                System.out.println(task.getId() +
                        "\t" + task.getTitle() +
                        "\t" + task.getDescription() +
                        "\t" + task.getTaskStatus());

                taskCount++;
            }
        }

        if (taskCount == 0) {
            System.out.println("Задач с таким статусом нет");
        }
    }

    @Override
    public void getStatistics() {
        int newTaskCount = 0;
        int inProgressTaskCount = 0;
        int completedTaskCount = 0;

        int taskCount = taskList.size();


        for (Task task : taskList) {
            if (task.getTaskStatus().equals(TaskStatus.NEW)) {
                newTaskCount++;
            }

            if (task.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
                inProgressTaskCount++;
            }

            if (task.getTaskStatus().equals(TaskStatus.COMPLETED)) {
                completedTaskCount++;
            }
        }

        System.out.println("Всего задач: " + taskCount);
        System.out.println("Новые: " + newTaskCount);
        System.out.println("В процессе выполнения: " + inProgressTaskCount);
        System.out.println("Завершенные: " + completedTaskCount);
    }

    @Override
    public boolean isTaskExists(int id) {
        Task task = findTaskById(id);

        return task != null;
    }

    private Task findTaskById(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }
}
