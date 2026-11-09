package ru.taskmanager.model;

public class Task {

    private final int id;
    private String title;
    private String description;
    private TaskStatus taskStatus;

    private static int taskCount = 0;

    {
        taskCount++;
    }

    public Task(String title, String description) {
        this.id = taskCount;
        this.title = title;
        this.description = description;
        taskStatus = TaskStatus.NEW;

        System.out.println("Задача " + id + " успешно создана");
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
