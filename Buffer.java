package org.example;

import java.util.ArrayList;
import java.util.List;


/**
 * Класс буфера для хранения заказов в очереди
 * Реализует логику управления очередью заказов
 */
public class Buffer {
    private int capacity;
    private List<Order> orders;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.orders = new ArrayList<>();
    }

    public boolean isFull() {
        return orders.size() >= capacity;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void addOrder(Order order) {
        if (!isFull()) {
            orders.add(order);

            System.out.println("Заказ" + order.getOrderId() + " добавлен в буфер");
        }
    }

    public void removeOrder(Order order) {
        if (orders.remove(order)) {
            System.out.println("Заказ " + order.getOrderId() + " удален из буфера");
        }
    }

    public Order findOrderToRemove() {
        if (orders.isEmpty()) {
            return null;
        }

        Order toRemove = orders.get(orders.size() - 1);

        for (int i = orders.size() - 1; i >= 0; i--) {
            Order current = orders.get(i);

            if (current.getPriority().getNumericalValue() < toRemove.getPriority().getNumericalValue()) {
                toRemove = current;
            }
            else if (current.getPriority() == toRemove.getPriority()) {
                if (current.getArrivalTime().isAfter(toRemove.getArrivalTime())) {
                    toRemove = current;
                }
            }
        }

        return toRemove;

    }

    public void shiftOrders() {
        if (!orders.isEmpty()) {
            System.out.println("Сдвиг заказов в буфере");

            if (orders.size() > 1) {
                orders.remove(0);
            }
        }
    }



    public Order selectOrderByPriority() {
        if (orders.isEmpty()) {
            return null;
        }

        Order selected = orders.get(0);
        for (Order order : orders) {
            if (order.getPriority().getNumericalValue() > selected.getPriority().getNumericalValue()) {
                selected = order;
            }
            else if (order.getPriority() == selected.getPriority()) {
                if (order.getArrivalTime().isAfter(selected.getArrivalTime())) {
                    selected = order;
                }
            }
        }

        return selected;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

     public int getSize() {
        return orders.size();
     }

     public int getCapacity() {
        return capacity;
     }
}


