package ru.softlab.kruglov.service;

/**
 * Интерфейс для считывателя словарей
 */
public interface DictionaryReader {

    /**
     * Считывает словарь
     * @return считанный словарь
     * @exception DictionaryWRException исключение, которое может быть выброшено при чтении
     */
    public Dictionary read() throws DictionaryWRException;
}
