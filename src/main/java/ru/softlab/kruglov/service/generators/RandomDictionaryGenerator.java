package ru.softlab.kruglov.service.generators;

import ru.softlab.kruglov.service.*;

import java.util.Date;
import java.util.Random;

/**
 * Генератор словарей на основе ГПСЧ java.lang.Random
 */
public class RandomDictionaryGenerator implements DictionaryGenerator {
    private Random random = new Random();
    private final long seed;
    private String baseLetters;
    private String outLetters;
    private LanguageType languageType;

    private long globalID;


    public static final int DEFAULT_WORD_COUNT = 7;
    public static final int DEFAULT_WORD_LENGTH = 10;
    public static final int DEFAULT_TRANSLATION_LENGTH = 10;
    public static final int DEFAULT_TRANSCRIPTION_LENGTH = 7;

    public static final String ENG_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String RU_ALPHABET  = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    /**
     * Создаёт новый массив с заданным зерном и заданными строками, ID не повторяются
     * @return Возращает созданный массив
     */
    public Dictionary generate() {
        globalID = 0L;
        random.setSeed(seed);
        Dictionary dict = new Dictionary(languageType);

        dict.setId(getId());
        dict.setUser(getUser());

        for (int i = 0; i < DEFAULT_WORD_COUNT; ++i) {
            dict.addWord(generateWord());
        }

        return dict;
    }

    /**
     * Задаёт новый генератор словарей
     * @param baseLetters символы "родного" языка и транслитерации
     * @param outLetters символы перевода
     * @param languageType язык словаря
     * @param seed зерно гпсч для словаря
     */
    public RandomDictionaryGenerator(String baseLetters, String outLetters, LanguageType languageType, long seed) {
        this.seed = seed;
        this.baseLetters = baseLetters;
        this.outLetters = outLetters;
        this.languageType = languageType;
    }

    /**
     * Задаёт новый генератор словарей со случайным зерном
     * @param baseLetters символы "родного" языка и транслитерации
     * @param outLetters символы перевода
     * @param languageType язык словаря
     */
    public RandomDictionaryGenerator(String baseLetters, String outLetters, LanguageType languageType) {
        this.seed = random.nextLong();
        this.baseLetters = baseLetters;
        this.outLetters = outLetters;
        this.languageType = languageType;
    }

    private String generateString(int length, String letters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            builder.append(letters.charAt(random.nextInt(letters.length())));
        }
        return builder.toString();
    }

    private long getId() {
        return globalID++;
    }

    private User getUser() {
        User user = new User(
                generateString(DEFAULT_WORD_LENGTH, ENG_ALPHABET),
                generateString(DEFAULT_WORD_LENGTH, ENG_ALPHABET),
                generateString(DEFAULT_WORD_LENGTH, RU_ALPHABET),
                generateString(DEFAULT_WORD_LENGTH, RU_ALPHABET));
        user.setId(getId());
        return user;
    }

    private Word generateWord() {
        Word word = new Word(
                generateString(DEFAULT_WORD_LENGTH, baseLetters),
                generateString(DEFAULT_TRANSLATION_LENGTH, outLetters),
                generateString(DEFAULT_TRANSCRIPTION_LENGTH, baseLetters)
        );
        word.setId(getId());
        return word;
    }

}