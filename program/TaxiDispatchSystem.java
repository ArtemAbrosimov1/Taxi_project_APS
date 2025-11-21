package org.example;

import java.util.Scanner;


/**
 * Главный класс системы диспетчеризации такси
 * Управляет взаимодействием с пользователем и координацией работы системы
 */
public class TaxiDispatchSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Dispatcher dispatcher = new Dispatcher(5, 3);

        System.out.println("СИСТЕМА ДИСПЕТЧЕРИЗАЦИИ ТАКСИ");
        System.out.println("------------------------------\n");

        boolean running = true;
        int clientCounter = 1;

        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Новый заказ от клиента");
            System.out.println("2 - Обработать заказы из буфера");
            System.out.println("3 - Освободить случайного водителя");
            System.out.println("4 - Показать календарь событий");
            System.out.println("5 - Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Client client = new Client(clientCounter++);
                    Order order = client.generateOrder();
                    dispatcher.handleOrderArrival(order);
                    break;

                case 2:
                    dispatcher.processOrdersFromBuffer();
                    break;

                case 3:
                    dispatcher.releaseRandomDevice();
                    dispatcher.displaySystemState();
                    break;

                case 4:
                    dispatcher.displayEventCalendar();
                    break;

                case 5:
                    running = false;
                    System.out.println("Завершение работы системы...");
                    break;

                default:
                    System.out.println("Неверный выбор!");
            }
        }

        scanner.close();
    }
}
