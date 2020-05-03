package ru.softlab.kruglov.service;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс описывающий словарь
 */
@XmlRootElement(name = "dictionary")
public class Dictionary implements HasId {
    private Long id;
    private User user;
    private LanguageType languageType;

    @XmlElement(name = "word")
    private List<Word> words = new LinkedList<Word>();

    /**
     * Пустой словарь, содержит поле words по умолчанию
     */
    public Dictionary() {}

    /**
     * Создание словаря с заданным id и языком
     * @param id id словаря
     * @param languageType язык словаря
     */
    public Dictionary(Long id, LanguageType languageType) {
        this.id = id;
        this.languageType = languageType;
    }

    /**
     * Создание словаря с заданным  языком
     * @param languageType язык словаря
     */
    public Dictionary(LanguageType languageType) {
        this.languageType = languageType;

    }

    /**
     * Позволяет задать все поля словаря разом
     * @param id id словаря
     * @param user пользователь владеющий словарём
     * @param languageType язык словаря
     * @param words список слов в словаре
     */
    public Dictionary(Long id, User user, LanguageType languageType, List<Word> words) {
        this.id = id;
        this.user = user;
        this.languageType = languageType;
        this.words.addAll(words);
    }

    /**
     * Id словаря
     * @return id словаря
     */
    @XmlAttribute
    public Long getId() {
        return id;
    }

    /**
     * устанавливает id словаря
     * @param id id словаря
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * устаналивает словарю пользователя
     * @return создатель словаря
     */
    @XmlElement(name = "user")
    public User getUser() {
        return user;
    }

    /**
     * Добавляет слово в словарь
     * @param word слово для добавления
     */
    public void addWord(Word word) {
        words.add(word);
    }

    /**
     * Добавляет несколько слов в словарь
     * @param word слово для добавления
     */
    public void addWords(Word ... word) {
        Collections.addAll(words, word);
    }

    /**
     * устаналивает словарю пользователя
     * @param user пользовтель словаря
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Возвращает тип язык словаря
     * @return язык словаря
     */
    @XmlAttribute
    public LanguageType getLanguageType() {
        return languageType;
    }


    /**
     * Возращает массив слов, хранымых в словаре
     * @return список слов в словаре
     */
    public List<Word> getWords() {
        return words;
    }
}
