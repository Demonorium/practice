package ru.softlab.kruglov.service;


/**
 * Интерфейс для записывателя словарей
 */
public interface DictionaryWriter {
    /**
     * Записать словарь
     * @param dictionary словарь для записи
     * @throws DictionaryWRException исключение при записи словаря
     */
    public void write(Dictionary dictionary) throws DictionaryWRException;
}
