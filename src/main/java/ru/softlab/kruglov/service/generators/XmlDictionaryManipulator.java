package ru.softlab.kruglov.service.generators;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import ru.softlab.kruglov.service.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.Node;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URL;

/**
 * Класс являющийся фабрикой для читателей и записывателей словаря
 */
public class XmlDictionaryManipulator {
    /**
     * Класс записывателя, хранит в себе цель, куда он будет писать
     */
    public static class XmlDictionaryWriter  implements DictionaryWriter {
        private static class Target {
            File file;
            Result result;
            Writer writer;
            OutputStream stream;
            ContentHandler handler;

            Target(Result result) {
                this.result = result;
            }

            Target(Writer writer) {
                this.writer = writer;
            }

            Target(OutputStream stream) {
                this.stream = stream;
            }

            Target(ContentHandler handler) {
                this.handler = handler;
            }
        }
        private Marshaller marshaller;
        private Target target;

        private XmlDictionaryWriter(Marshaller marshaller, Target target) {
            this.marshaller = marshaller;
            this.target = target;
        }

        /**
         * Записывает словарь в указанную цель в формате xml
         * @param dictionary словарь
         * @throws DictionaryWRException исключение описывающее ошибки при записи
         */
        public void write(Dictionary dictionary) throws DictionaryWRException {
            try {
                writeUnSafe(dictionary);
            } catch (JAXBException exception) {
                throw new DictionaryWRException(exception);
            } catch (IOException exception) {
                throw new DictionaryWRException(exception);
            }
        }

        /**
         * Записывает без отлова исключений
         * @param dictionary словарь
         * @throws JAXBException исключение библиоткеи JAXB
         * @throws IOException искдючение может быть прошено при считывании из файла
         */
        public void writeUnSafe(Dictionary dictionary) throws JAXBException, IOException {
            if (target.file != null) {
                marshaller.marshal(dictionary, target.file);
            } else if (target.handler != null) {
                marshaller.marshal(dictionary, target.handler);
            } else if (target.writer != null) {
                marshaller.marshal(dictionary, target.writer);
            } else if (target.result != null) {
                marshaller.marshal(dictionary, target.result);
            } else throw new NullPointerException("XmlDictionaryWriter TARGET IS NULL");
        }
    }

    /**
     * Класс, способный считывать словарь из  указанного источника
     */
    public static class XmlDictionaryReader implements DictionaryReader {
        private static class Source {
            File file;
            URL url;
            Reader reader;
            InputStream stream;
            Node node;
            javax.xml.transform.Source source;
            InputSource input;

            Source(URL url) {
                this.url = url;
            }

            Source(Reader reader) {
                this.reader = reader;
            }

            Source(InputStream stream) {
                this.stream = stream;
            }

            Source(Node node) {
                this.node = node;
            }

            Source(javax.xml.transform.Source source) {
                this.source = source;
            }

            Source(InputSource input) {
                this.input = input;
            }
        }

        private Unmarshaller unmarshaller;
        private Source source;

        private XmlDictionaryReader(Unmarshaller unmarshaller, Source source) {
            this.unmarshaller = unmarshaller;
            this.source = source;
        }

        /**
         * Считывает словарь из источника
         * @return Считанный словарь
         * @throws DictionaryWRException исключение описывающее ошибки при чтении, в частности JAXB
         */
        public Dictionary read() throws DictionaryWRException {
            try {
                return readUnSafe();
            } catch (JAXBException exception) {
                throw new DictionaryWRException(exception);
            } catch (IOException exception) {
                throw new DictionaryWRException(exception);
            }
        }

        /**
         * Считывает без отлова исключений
         * @return считанный словарь
         * @throws JAXBException исключение библиоткеи JAXB
         * @throws IOException искдючение может быть прошено при считывании из файла
         */
        public Dictionary readUnSafe() throws JAXBException, IOException {
            if (source.file != null) {
                return (Dictionary) unmarshaller.unmarshal(source.file);
            } else if (source.url != null) {
                return (Dictionary) unmarshaller.unmarshal(source.url);
            } else if (source.node != null) {
                return (Dictionary) unmarshaller.unmarshal(source.node);
            } else if (source.reader != null) {
                return (Dictionary) unmarshaller.unmarshal(source.reader);
            } else if (source.stream != null) {
                return (Dictionary) unmarshaller.unmarshal(source.stream);
            } else if (source.source != null) {
                return (Dictionary) unmarshaller.unmarshal(source.source);
            } else if (source.input != null) {
                return (Dictionary) unmarshaller.unmarshal(source.input);
            } else throw new NullPointerException("XmlDictionaryReader SOURCE IS NULL");
        }
    }

    /**
     * Класс, который отвечает за чтение и запись в файл
     */
    public static class XmlDictionaryRW implements DictionaryReader, DictionaryWriter {
        private File file;
        private Marshaller marshaller;
        private Unmarshaller unmarshaller;

        private XmlDictionaryRW(File file, Marshaller marshaller, Unmarshaller unmarshaller) {
            this.file = file;
            this.marshaller = marshaller;
            this.unmarshaller = unmarshaller;
        }

        /**
         * Возращает тип для записи и чтения
         * @return Файл для записи и чтения
         */
        public File getFile() {
            return file;
        }

        /**
         * Устанавливает файл для записи и чтения
         * @param file Файл для записи и чтения
         */
        public void setFile(File file) {
            this.file = file;
        }

        /**
         * Считывает словарь из файла
         * @return Считанный словарь
         * @throws DictionaryWRException исключение описывающее ошибки при чтении, в частности JAXB
         */
        public Dictionary read() throws DictionaryWRException {
            try {
                if (file != null)
                    return (Dictionary)unmarshaller.unmarshal(file);
                else throw new NullPointerException("Source file is null");
            } catch (JAXBException exc) {
                throw new DictionaryWRException(exc);
            }
        }

        /**
         * Записывает словарь в указанный файл в формате xml
         * @param dictionary словарь
         * @throws DictionaryWRException исключение описывающее ошибки при записи, в частности JAXB
         */
        public void write(Dictionary dictionary) throws DictionaryWRException {
            try {
                if (file != null)
                    marshaller.marshal(dictionary, file);
                else throw new NullPointerException("Target file is null");
            } catch  (JAXBException exc) {
                throw new DictionaryWRException(exc);
            }
        }
    }

    /**
     * JAXB context for Dictionary class
     */
    protected final JAXBContext contextDict;

    /**
     * Констуирует манипулятор для JAXB
     * @throws JAXBException исключение библиотеки JAXB
     */
    public XmlDictionaryManipulator() throws JAXBException {
        contextDict = JAXBContext.newInstance(Dictionary.class);
    }

    /**
     * Возвращает объект типа XmlDictionaryWriter способный записать в заданную цель
     * @param target цель, куда объект будет писать
     * @return XmlDictionaryWriter способный записать в заданную цель
     * @throws JAXBException исключение библиотеки JAXB при создании маршаллера
     */
    public XmlDictionaryWriter get(Result target) throws JAXBException{
        return new XmlDictionaryWriter(contextDict.createMarshaller(), new XmlDictionaryWriter.Target(target));
    }

    /**
     * Возвращает объект типа XmlDictionaryWriter способный записать в заданную цель
     * @param target цель, куда объект будет писать
     * @return XmlDictionaryWriter способный записать в заданную цель
     * @throws JAXBException исключение библиотеки JAXB при создании маршаллера
     */
    public XmlDictionaryWriter get(Writer target) throws JAXBException{
        return new XmlDictionaryWriter(contextDict.createMarshaller(), new XmlDictionaryWriter.Target(target));
    }

    /**
     * Возвращает объект типа XmlDictionaryWriter способный записать в заданную цель
     * @param target цель, куда объект будет писать
     * @return XmlDictionaryWriter способный записать в заданную цель
     * @throws JAXBException исключение библиотеки JAXB при создании маршаллера
     */
    public XmlDictionaryWriter get(OutputStream target) throws JAXBException{
        return new XmlDictionaryWriter(contextDict.createMarshaller(), new XmlDictionaryWriter.Target(target));
    }

    /**
     * Возвращает объект типа XmlDictionaryWriter способный записать в заданную цель
     * @param target цель, куда объект будет писать
     * @return XmlDictionaryWriter способный записать в заданную цель
     * @throws JAXBException исключение библиотеки JAXB при создании маршаллера
     */
    public XmlDictionaryWriter get(ContentHandler target) throws JAXBException{
        return new XmlDictionaryWriter(contextDict.createMarshaller(), new XmlDictionaryWriter.Target(target));
    }

    /**
     * Возвращает объект типа XmlDictionaryRW способный считать объект из файла или записать его
     * @param file файл для чтения и записи
     * @return новый экземляр XmlDictionaryRW
     * @throws JAXBException исключение библиотеки JAXB при создании анмаршаллера или маршаллера
     */
    public XmlDictionaryRW get(File file) throws JAXBException{
        return new XmlDictionaryRW(file,
                contextDict.createMarshaller(),
                contextDict.createUnmarshaller());
    }

    /**
     * Возвращает объект типа XmlDictionaryReader способный считать объект из заданной цели
     * @param source источник, откуда будет считан объект
     * @return XmlDictionaryReader способный считать объект
     * @throws JAXBException исключение библиотеки JAXB при создании анмаршаллера
     */
    public XmlDictionaryReader get(URL source) throws JAXBException{
        return new XmlDictionaryReader(contextDict.createUnmarshaller(), new XmlDictionaryReader.Source(source));
    }

    /**
     * Возвращает объект типа XmlDictionaryReader способный считать объект из заданной цели
     * @param source источник, откуда будет считан объект
     * @return XmlDictionaryReader способный считать объект
     * @throws JAXBException исключение библиотеки JAXB при создании анмаршаллера
     */
    public XmlDictionaryReader get(Reader source) throws JAXBException{
        return new XmlDictionaryReader(contextDict.createUnmarshaller(), new XmlDictionaryReader.Source(source));
    }

    /**
     * Возвращает объект типа XmlDictionaryReader способный считать объект из заданной цели
     * @param source источник, откуда будет считан объект
     * @return XmlDictionaryReader способный считать объект
     * @throws JAXBException исключение библиотеки JAXB при создании анмаршаллера
     */
    public XmlDictionaryReader get(InputStream source) throws JAXBException{
        return new XmlDictionaryReader(contextDict.createUnmarshaller(), new XmlDictionaryReader.Source(source));
    }

    /**
     * Возвращает объект типа XmlDictionaryReader способный считать объект из заданной цели
     * @param source источник, откуда будет считан объект
     * @return XmlDictionaryReader способный считать объект
     * @throws JAXBException исключение библиотеки JAXB при создании анмаршаллера
     */
    public XmlDictionaryReader get(Node source) throws JAXBException{
        return new XmlDictionaryReader(contextDict.createUnmarshaller(), new XmlDictionaryReader.Source(source));
    }

    /**
     * Возвращает объект типа XmlDictionaryReader способный считать объект из заданной цели
     * @param source источник, откуда будет считан объект
     * @return XmlDictionaryReader способный считать объект
     * @throws JAXBException исключение библиотеки JAXB при создании анмаршаллера
     */
    public XmlDictionaryReader get(Source source) throws JAXBException{
        return new XmlDictionaryReader(contextDict.createUnmarshaller(), new XmlDictionaryReader.Source(source));
    }


    /**
     * Возвращает объект типа XmlDictionaryReader способный считать объект из заданной цели
     * @param source источник, откуда будет считан объект
     * @return XmlDictionaryReader способный считать объект
     * @throws JAXBException исключение библиотеки JAXB при создании анмаршаллера
     */
    public XmlDictionaryReader get(InputSource source) throws JAXBException{
        return new XmlDictionaryReader(contextDict.createUnmarshaller(), new XmlDictionaryReader.Source(source));
    }
}
