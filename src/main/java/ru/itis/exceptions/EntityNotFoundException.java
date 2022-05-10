package ru.itis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    protected String entity;

    protected Map<String, Object> attributes;

    public EntityNotFoundException(String entity) {
        this.entity = entity;
    }

    public EntityNotFoundException(String entity, Map<String, Object> attribute) {
        this.entity = entity;
        this.attributes = attribute;
    }

    @Override
    public String getMessage() {
        if (attributes != null) {

            StringBuilder builder = new StringBuilder();
            builder.append(entity).append(" with ");

            for (String key : attributes.keySet()) {
                builder.append(key).append(" = ").append(attributes.get(key));
            }
            return builder.toString();
        }
        return entity + " not found!";
    }
}
