package com.rgomez.empleados.service;

import java.util.List;
import java.util.UUID;

public interface GenericService <T,ID> { //T es lo que devuelve, ID la clave primaria

    List<T> getAll ();

    T getByID (ID id);

    T save (T entity);

    T update (ID id, T entity);

    void delete (ID id);

}
