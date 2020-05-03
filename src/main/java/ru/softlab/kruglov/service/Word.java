package ru.softlab.kruglov.service;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Описывает отдельно взятое слово, имеет ID
 */
public class Word implements HasId {
    private Long id;
    private String aNative;
    private String translation;
    private String transcription;

    /**
     * Конструктор поумолчанию
     */
    public Word(){}

    /**
     * Конструктор, который позволяет задать поля
     * @param aNative слово на родном языке
     * @param translation перевод на русский
     * @param transcription транскрипция
     */
    public Word(String aNative, String translation, String transcription) {
        this.aNative = aNative;
        this.translation = translation;
        this.transcription = transcription;
    }

    /**
     * Возращает id
     * @return id
     */
    @XmlAttribute
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает id
     * @param id новый id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает слово на родном языке
     * @return слово на родном языке
     */
    @XmlAttribute
    public String getNative() {
        return aNative;
    }

    /**
     * Устанавливает слово на родном языке
     * @param aNative новое слово
     */
    public void setNative(String aNative) {
        this.aNative = aNative;
    }

    /**
     * Возвращает перевод
     * @return перевод
     */
    @XmlAttribute
    public String getTranslation() {
        return translation;
    }

    /**
     * Устанавливает новый перевод
     * @param translation новый перевод
     */
    public void setTranslation(String translation) {
        this.translation = translation;
    }

    /**
     * Возвращает транскрипцию
     * @return новая транскрипция
     */
    @XmlAttribute
    public String getTranscription() {
        return transcription;
    }

    /**
     * Устанавливает транскрипцию
     * @param transcription новая транскрипция
     */
    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    /**
     * Сравнение двух объектов
     * @param o объект для сравнения
     * @return true или false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (!aNative.equals(word.aNative)) return false;
        if (!translation.equals(word.translation)) return false;
        return transcription.equals(word.transcription);
    }

    /**
     * Хэширование по aNative и translation, transcription
     * @return Хэширование по aNative и translation, transcription
     */
    @Override
    public int hashCode() {
        int result = aNative.hashCode();
        result = 31 * result + translation.hashCode();
        result = 31 * result + transcription.hashCode();
        return result;
    }
}
