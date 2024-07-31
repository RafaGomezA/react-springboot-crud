package com.rgomez.empleados.errors.exceptions;

import lombok.Getter;
@Getter
public class EntityNotFound extends RuntimeException {

    private final String entityName;

    private final String entityId;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     *
     */
    public EntityNotFound (final String entityName, final String entityId) {

        super(String.format("%s not found with id: %s", entityName.replace("Entity", ""), entityId));

        this.entityName = entityName;
        this.entityId = entityId;
    }
}
