package ru.softlab.kruglov.service;

/**
 * Интерфейс для генератора словарей
 */
public interface DictionaryGenerator {
    /**
     * Создаёт произвольный словарь
     * @return созданный словарь
     */
    public Dictionary generate();
}
