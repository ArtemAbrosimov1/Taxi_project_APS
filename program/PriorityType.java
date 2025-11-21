package org.example;


/**
 * Перечисление типов приоритета заказа
 * (Имеется только БЫСТРЫЙ и ОБЫЧНЫЙ)
 * Определяет приоритет обработки заказа
 */
public enum PriorityType {
    LOW,
    HIGH;

    public int getNumericalValue() {
        return this == HIGH ? 1 : 0;
    }
}
