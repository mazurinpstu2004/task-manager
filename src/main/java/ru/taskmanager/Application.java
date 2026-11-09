package ru.taskmanager;

import ru.taskmanager.model.TaskStatus;
import ru.taskmanager.service.TaskService;
import ru.taskmanager.service.impl.DefaultTaskService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        TaskService taskService = new DefaultTaskService();

        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Найти задачу по ID");
            System.out.println("4. Изменить задачу");
            System.out.println("5. Начать задачу");
            System.out.println("6. Завершить задачу");
            System.out.println("7. Удалить задачу");
            System.out.println("8. Показать задачи по статусу");
            System.out.println("9. Показать статистику");
            System.out.println("0. Выход");

            System.out.print("Пункт меню: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Введите корректный номер меню");
                scanner.nextLine();
                continue;
            }

            scanner.nextLine();

            String name = null;
            String description = null;
            int id = 0;
            int status = 0;

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите имя задачи: ");
                    name = scanner.nextLine();
                    System.out.print("Введите описание задачи: ");
                    description = scanner.nextLine();
                    taskService.createTask(name, description);
                }
                case 2 -> taskService.getAllTasks();
                case 3 -> {
                    System.out.print("Введите ID задачи: ");
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                    } else {
                        System.out.println("Введите корректный ID");
                        scanner.nextLine();
                        continue;
                    }
                    scanner.nextLine();
                    taskService.getTask(id);
                }
                case 4 -> {
                    System.out.print("Введите ID задачи: ");
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                    } else {
                        System.out.println("Введите корректный ID");
                        scanner.nextLine();
                        continue;
                    }
                    scanner.nextLine();
                    if (taskService.isTaskExists(id)) {
                        System.out.print("Введите новое имя задачи: ");
                        name = scanner.nextLine();
                        System.out.print("Введите новое описание задачи: ");
                        description = scanner.nextLine();
                        taskService.updateTask(id, name, description);
                    } else {
                        System.out.println("Задача " + id + " не найдена");
                    }
                }
                case 5 -> {
                    System.out.print("Введите ID задачи: ");
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                    } else {
                        System.out.println("Введите корректный ID");
                        scanner.nextLine();
                        continue;
                    }
                    scanner.nextLine();
                    taskService.startTask(id);
                }
                case 6 -> {
                    System.out.print("Введите ID задачи: ");
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                    } else {
                        System.out.println("Введите корректный ID");
                        scanner.nextLine();
                        continue;
                    }
                    scanner.nextLine();
                    taskService.endTask(id);
                }
                case 7 -> {
                    System.out.print("Введите ID задачи: ");
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                    } else {
                        System.out.println("Введите корректный ID");
                        scanner.nextLine();
                        continue;
                    }
                    scanner.nextLine();
                    taskService.deleteTask(id);
                }
                case 8 -> {
                    System.out.print("Введите статус задачи: 1 - NEW, 2 - IN_PROGRESS, 3 - COMPLETED: ");
                    if (scanner.hasNextInt()) {
                        status = scanner.nextInt();
                    } else {
                        System.out.println("Введите корректный статус");
                        scanner.nextLine();
                        continue;
                    }

                    scanner.nextLine();

                    if (status == 1) {
                        taskService.filterTasks(TaskStatus.NEW);
                    }

                    if (status == 2) {
                        taskService.filterTasks(TaskStatus.IN_PROGRESS);
                    }

                    if (status == 3) {
                        taskService.filterTasks(TaskStatus.COMPLETED);
                    }

                    if (status != 1 && status != 2 && status != 3) {
                        System.out.println("Такого статуса нет");
                    }
                }
                case 9 -> taskService.getStatistics();
                case 0 -> System.out.println("Завершение работы");
                default -> System.out.println("Такого пункта в меню нет");
            }
        }
    }
}
