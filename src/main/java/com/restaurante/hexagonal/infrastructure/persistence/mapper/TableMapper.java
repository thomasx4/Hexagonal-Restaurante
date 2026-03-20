package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.Table;
import com.restaurante.hexagonal.infrastructure.persistence.entity.TableEntity;

public class TableMapper {

    public static TableEntity toEntity(Table table) {
        if (table == null) return null;
        return TableEntity.builder()
                .id(table.getId())
                .number(table.getNumber().longValue())
                .capacity(table.getCapacity())
                .status(table.getStatus())
                .build();
    }

    public static Table toDomain(TableEntity entity) {
        return new Table.Builder()
                .id(entity.getId())
                .number(entity.getNumber().intValue())
                .capacity(entity.getCapacity())
                .status(entity.getStatus())
                .build();
    }
}