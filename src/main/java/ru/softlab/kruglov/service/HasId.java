package ru.softlab.kruglov.service;

/**
 * Интерфейс для объектов, имеющих id
 */
public interface HasId {
    /**
     * Возращает ID
     * @return id объект
     */
    public Long getId();

    /**
     * Устанавливает id
     * @param id новый id
     */
    public void setId(Long id);
}
