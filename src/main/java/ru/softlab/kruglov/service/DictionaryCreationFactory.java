package ru.softlab.kruglov.service;

/**
 * Singleton для создания словарей(абсолютно бесполезно)
 */
public class DictionaryCreationFactory {
    private static DictionaryCreationFactory instance = new DictionaryCreationFactory();

    private DictionaryCreationFactory() {}

    /**
     * Возращает экземляр синглтона
     * @return экземпляр DictionaryCreationFactory
     */
    public static DictionaryCreationFactory getInstance() {
        return instance;
    }


    /**
     * Возвращает словарь
     * @param generator генератор, который создаст словарь
     * @return созданный словарь
     */
    public Dictionary getDictionary(DictionaryGenerator generator) {
        return generator.generate();
    }
    /**
     * Возвращает словарь
     * @param reader генератор, который создаст словарь
     * @return созданный словарь
     * @exception DictionaryWRException исключение которое может быть выброшено при чтении
     */
    public Dictionary getDictionary(DictionaryReader reader) throws DictionaryWRException {
        return reader.read();
    }
}
