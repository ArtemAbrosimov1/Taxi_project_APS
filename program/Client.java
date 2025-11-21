package org.example;

import java.util.Random;


/**
 * Класс клиента (источника заказов)
 * Генерирует новые заказы такси
 */
public class Client {
    private int sourceId;
    private PriorityType priority;
    private Random random;

    public Client(int sourceId) {
        this.sourceId = sourceId;
        this.random = new Random();
        this.priority = random.nextBoolean() ? PriorityType.HIGH : PriorityType.LOW;
    }

    public Order generateOrder() {
        Order order = new Order(sourceId, priority);

        System.out.println(
                        "Клиент S" + sourceId + " создал " +
                        (priority == PriorityType.HIGH ? "Быстрый" : "Обычный") +
                        " заказ: " + order.getOrderId()
        );

        return order;
    }

    public int getSourceId() {
        return sourceId;
    }

    public PriorityType getPriority() {
        return priority;
    }
}
