package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Главный класс диспетчера
 * Управляет всей логикой распределения заказов между
 * буфером и водителями
 */
public class Dispatcher {
    private Buffer buffer;
    private List<Device> devices;
    private List<String> eventLog;

    public Dispatcher(int bufferCapacity, int deviceCount) {
        this.buffer = new Buffer(bufferCapacity);
        this.devices = new ArrayList<>();
        this.eventLog = new ArrayList<>();

        for (int i = 1; i <= deviceCount; i++) {
            devices.add(new Device(i));
        }
    }

    private Order selectOrderByPriority() {
        Order selected = buffer.selectOrderByPriority();

        if (selected != null) {
            logEvent("Выбран заказ по приоритету: " + selected);
        }

        return selected;
    }

    private Device selectDevice() {
        for (Device device : devices) {
            if (!device.isBusy()) {
                logEvent("Выбран свободный водитель: " + device.getDeviceId());
                return device;
            }
        }

        logEvent("Нет свободных водителей");
        return null;
    }

    private void logEvent(String event) {
        eventLog.add(event);
        System.out.println(event);
    }

    public void displaySystemState() {
        System.out.println("\n--- ТЕКУЩЕЕ СОСТОЯНИЕ СИСТЕМЫ ---");

        System.out.println("Буфер (" + buffer.getSize() + "/" + buffer.getCapacity() + "):");
        for (Order order : buffer.getOrders()) {
            System.out.println("   - " + order);
        }

        System.out.println("Водители: ");
        for (Device device: devices) {
            String status = device.isBusy() ?
                    "Занят заказом " + device.getCurrentOrder().getOrderId() : "Свободен";

            System.out.println("   - Водитель " + device.getDeviceId() + ": " + status);
        }

        System.out.println("------------------------------\n");
    }

    public void displayEventCalendar() {
        System.out.println("\n --- КАЛЕНДАРЬ СОБЫТИЙ ---");

        for (int i = 0; i < eventLog.size(); i++) {
            System.out.println((i + 1) + ". " + eventLog.get(i));
        }

        System.out.println("------------------------------\n");
    }

    public void handleOrderArrival(Order order) {
        logEvent("Поступление заказа: " + order);

        if (buffer.isFull()) {
            Order orderToRemove = buffer.findOrderToRemove();

            if (orderToRemove != null) {
                buffer.removeOrder(orderToRemove);
                logEvent("Выбивание заказа из буфера: " + orderToRemove + "(наименее приоритетный)");
            }
        }

        buffer.addOrder(order);
        displaySystemState();
    }

    public void processOrdersFromBuffer() {
        logEvent("Проверка буфера на наличие заказов");

        if (!buffer.isEmpty()) {
            Order order = selectOrderByPriority();

            if (order != null) {
                Device device = selectDevice();

                if (device != null) {
                    device.assignOrder(order);
                    buffer.removeOrder(order);
                    logEvent("Заказ отправлен на обработку: " + order + " -> Водитель " + device.getDeviceId());
                }
                else {
                    logEvent("Нет свободных водителей для заказа: " + order);
                }
            }
        }
        else {
            logEvent("Буфер пуст - ожидание заказов");
        }

        displaySystemState();
    }

    public void releaseRandomDevice() {
        List<Device> busyDevices = new ArrayList<>();

        for (Device device : devices) {
            if (device.isBusy()) {
                busyDevices.add(device);
            }
        }

        if (!busyDevices.isEmpty()) {
            Device deviceToRelease = busyDevices.get(new Random().nextInt(busyDevices.size()));

            deviceToRelease.release();
            logEvent("Водитель освободился: " + deviceToRelease.getDeviceId());
        }
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public List<Device> getDevice() {
        return devices;
    }

}
