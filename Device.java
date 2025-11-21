package org.example;


/**
 * Класс прибора (водителя такси)
 * Представляет водителя, который обрабатывает заказы
 */
public class Device {
    private int deviceId;
    private boolean isBusy;
    private Order currentOrder;

    public Device(int deviceId) {
        this.deviceId = deviceId;
        this.isBusy = false;
        this.currentOrder = null;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void assignOrder(Order order) {
        this.currentOrder = order;
        this.isBusy = true;

        System.out.println("Водитель " + deviceId + " принял заказ " + order.getOrderId());
    }

    public void release() {
        if (isBusy) {
            System.out.println("Водитель " + deviceId + " завершил заказ " + currentOrder.getOrderId());
            this.currentOrder = null;
            this.isBusy = false;
        }
    }

    public int getDeviceId() {
        return deviceId;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }
}
