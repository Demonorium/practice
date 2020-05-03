package ru.softlab.kruglov.service;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Описывает владельца словаря
 */
public class User implements HasId {
    private Long id;
    private String login;
    @XmlTransient
    private String password;
    private String surname;
    private String firstName;

    /**
     * Конктруктор пользователя, который пустой внутри
     */
    public User() {}

    /**
     * Конструктор для задания всех полей
     * @param login логин пользователя
     * @param password пароль пользователя
     * @param surname  фамилия пользователя
     * @param firstName имя пользователя
     */
    public User(String login, String password, String surname, String firstName) {
        this.login      = login;
        this.password   = password;
        this.surname    = surname;
        this.firstName  = firstName;
    }

    /**
     * Устаналивает id
     * @param id новый id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает id
     * @return id пользователя
     */
    @XmlAttribute
    public Long getId() {
        return id;
    }


    /**
     * Логин пользователя
     * @return логин пользователя
     */
    @XmlAttribute
    public String getLogin() {
        return login;
    }


    /**
     * Возвращает фамилию
     * @return фамилия пользователя
     */
    @XmlAttribute
    public String getSurname() {
        return surname;
    }

    /**
     * Возвращает имя пользователя
     * @return имя пользователя
     */
    @XmlAttribute
    public String getFirstName() {
        return firstName;
    }

    /**
     * Устанавливает новый логин
     * @param login новый логин
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Сменяет пароль
     * @param password новый пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Устанавливает фамилию
     * @param surname новая фамилия
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Устанавливает имя
     * @param firstName новое имя
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
