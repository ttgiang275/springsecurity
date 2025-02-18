package com.river.springsecurity.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return role != null ? role.getValue() : null;  // Convert to lowercase
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return dbData != null ? Role.fromString(dbData) : null;  // Convert back to enum
    }
}

