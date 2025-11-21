package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Класс заказа такси.
 * Представляет собой заказ, созданный клиентов
 */
public class Order {
    private int orderId;
    private int sourceId;
    private PriorityType priority;
    private LocalDateTime arrivalTime;

    private static int nextId = 1;

    public Order(int sourceId, PriorityType priority) {
        this.orderId = nextId++;
        this.sourceId = sourceId;
        this.priority = priority;
        this.arrivalTime = LocalDateTime.now();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    // Строковое представление заказа для отладки
    @Override
    public String toString() {
        return String.format(
                "Order%d(S%d,%s,%s)",
                orderId,
                sourceId,
                priority,
                arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        );
    }
}
