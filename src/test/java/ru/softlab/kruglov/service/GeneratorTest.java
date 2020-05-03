package ru.softlab.kruglov.service;

import org.junit.Assert;
import org.junit.Test;
import ru.softlab.kruglov.service.generators.RandomDictionaryGenerator;

import java.util.List;
import java.util.ListIterator;

public class GeneratorTest {
    public static final String ENG_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String RU_ALPHABET  = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    @Test
    public void SeedTest() {
        RandomDictionaryGenerator generator1 = new RandomDictionaryGenerator(RU_ALPHABET, ENG_ALPHABET, LanguageType.ENGLISH, 666);
        RandomDictionaryGenerator generator2 = new RandomDictionaryGenerator(RU_ALPHABET, ENG_ALPHABET, LanguageType.ENGLISH, 666);

        Dictionary dict1 = generator1.generate();
        Dictionary dict2 = generator2.generate();

        List<Word> w1 = dict1.getWords();
        List<Word> w2 = dict2.getWords();

        Assert.assertEquals("Длины сгенерированных массивов не равны", w1.size(), w2.size());
        for (ListIterator<Word> i = w1.listIterator(), j = w2.listIterator(); i.hasNext() && j.hasNext();) {
            Assert.assertEquals("Сгенерированные массивы содержат разные слова", i.next(), j.next());
        }
    }

    @Test
    public void GeneratorFactoryTest() {
        RandomDictionaryGenerator generator = new RandomDictionaryGenerator(RU_ALPHABET, ENG_ALPHABET, LanguageType.ENGLISH);
        Dictionary dict = DictionaryCreationFactory.getInstance().getDictionary(generator);

        Assert.assertNotNull(dict);
    }
}
