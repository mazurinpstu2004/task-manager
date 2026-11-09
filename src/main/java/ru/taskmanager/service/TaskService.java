package ru.taskmanager.service;

import ru.taskmanager.model.TaskStatus;

public interface TaskService {

    void createTask(String title, String description);

    void getAllTasks();

    void getTask(int id);

    void updateTask(int id, String title, String description);

    void startTask(int id);

    void endTask(int id);

    void deleteTask(int id);

    void filterTasks(TaskStatus status);

    void getStatistics();

    boolean isTaskExists(int id);
}
