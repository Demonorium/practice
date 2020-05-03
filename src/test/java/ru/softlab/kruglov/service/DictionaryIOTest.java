package ru.softlab.kruglov.service;

import org.junit.Assert;
import org.junit.Test;
import ru.softlab.kruglov.service.generators.RandomDictionaryGenerator;
import ru.softlab.kruglov.service.generators.XmlDictionaryManipulator;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public class DictionaryIOTest {
    public static final String ENG_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String RU_ALPHABET  = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    Dictionary dict = new RandomDictionaryGenerator(ENG_ALPHABET, RU_ALPHABET, LanguageType.ENGLISH).generate();
    XmlDictionaryManipulator manipulator;

    public DictionaryIOTest() throws JAXBException{
        manipulator = new XmlDictionaryManipulator();
    }

    @Test
    public void SimpleWrite() throws JAXBException, IOException, DictionaryWRException {
        DictionaryWriter writer = manipulator.get(new File("src/test/Output.xml"));
        try {
            writer.write(dict);
        } catch (DictionaryWRException exc) {
            if (exc.isIOException())
                throw exc.getIOException();
            throw exc;
        }

    }

    @Test
    public void SimpleRead() throws JAXBException, IOException, DictionaryWRException {
        File file = new File("src/test/Dict.xml");
        Assert.assertTrue("Ошибка в тесте. Файл не найден: " + file.getAbsolutePath(), file.exists());
        XmlDictionaryManipulator.XmlDictionaryRW io = manipulator.get(file);
        try {
            dict = io.read();
        } catch (DictionaryWRException exc) {
            if (exc.isIOException())
                throw exc.getIOException();
            throw exc;
        }

        Assert.assertNotNull("Словарь не был считан (dict == null)", dict);
        boolean wHello = false;
        boolean wWorld = false;
        boolean wRemove = false;
        boolean wAdd = false;
        boolean wDelete = false;

        for (Word w: dict.getWords()) {
            if (w.getNative().equals("hello")) {
                wHello = true;
            }
            else if (w.getNative().equals("world")) {
                wWorld = true;
            }
            else if (w.getNative().equals("remove")) {
                wRemove = true;
            }
            else if (w.getNative().equals("add to")) {
                wAdd = true;
            } else if (w.getNative().equals("delete")) {
                wDelete = true;
            }
        }

        Assert.assertTrue(wHello);
        Assert.assertTrue(wWorld);
        Assert.assertTrue(wRemove);
        Assert.assertTrue(wAdd);
        Assert.assertTrue(wDelete);

    }

//    @Test
//    public void SimpleRW() throws JAXBException, IOException, DictionaryWRException {
//        File file = new File("src/test/OUT.xml");
//
//        DictionaryWriter writer = manipulator.get(file);
//        try {
//            writer.write(dict);
//        } catch (DictionaryWRException exc) {
//            if (exc.isIOException())
//                throw exc.getIOException();
//            throw exc;
//        }
//
//
//
//        Assert.assertTrue("Ошибка в тесте. Файл не найден: " + file.getAbsolutePath(), file.exists());
//        XmlDictionaryManipulator.XmlDictionaryRW io = manipulator.get(file);
//        try {
//            dict = io.read();
//        } catch (DictionaryWRException exc) {
//            if (exc.isIOException())
//                throw exc.getIOException();
//            throw exc;
//        }
//
//        Assert.assertNotNull("Словарь не был считан (dict == null)", dict);
//        boolean wHello = false;
//        boolean wWorld = false;
//        boolean wRemove = false;
//        boolean wAdd = false;
//        boolean wDelete = false;
//
//        for (Word w: dict.getWords()) {
//            if (w.getNative().equals("hello")) {
//                wHello = true;
//            }
//            else if (w.getNative().equals("world")) {
//                wWorld = true;
//            }
//            else if (w.getNative().equals("remove")) {
//                wRemove = true;
//            }
//            else if (w.getNative().equals("add to")) {
//                wAdd = true;
//            } else if (w.getNative().equals("delete")) {
//                wDelete = true;
//            }
//        }
//
//        Assert.assertTrue(wHello);
//        Assert.assertTrue(wWorld);
//        Assert.assertTrue(wRemove);
//        Assert.assertTrue(wAdd);
//        Assert.assertTrue(wDelete);

//    }

}
