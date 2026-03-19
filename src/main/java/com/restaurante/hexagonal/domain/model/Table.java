package com.restaurante.hexagonal.domain.model;

import java.util.Objects;

public class Table {

    private final Long id;
    private final Long number;
    private final Integer capacity;
    private final Boolean status;

    private Table(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.capacity = builder.capacity;
        this.status = builder.status;
        this.validate();
    }

    // Getters
    public Long getId() { return id; }
    public Long getNumber() { return number; }
    public Integer getCapacity() { return capacity; }
    public Boolean getStatus() { return status; }

    // Validaciones de negocio
    private void validate() {
        if (number == null || number <= 0) {
            throw new IllegalArgumentException("Table number must be greater than 0");
        }

        if (capacity == null || capacity <= 0) {
            throw new IllegalArgumentException("Table capacity must be greater than 0");
        }
    }

    // Método de negocio (actualizar)
    public Table update(Long number, Integer capacity, Boolean status) {
        return new Builder()
                .id(this.id)
                .number(number)
                .capacity(capacity)
                .status(status)
                .build();
    }

    // Método de negocio (ocupar mesa)
    public Table occupy() {
        if (Boolean.TRUE.equals(this.status)) {
            throw new RuntimeException("Table is already occupied");
        }

        return new Builder()
                .id(this.id)
                .number(this.number)
                .capacity(this.capacity)
                .status(true)
                .build();
    }

    // Método de negocio (liberar mesa)
    public Table release() {
        if (Boolean.FALSE.equals(this.status)) {
            throw new RuntimeException("Table is already free");
        }

        return new Builder()
                .id(this.id)
                .number(this.number)
                .capacity(this.capacity)
                .status(false)
                .build();
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Builder
    public static class Builder {
        private Long id;
        private Long number;
        private Integer capacity;
        private Boolean status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder number(Long number) {
            this.number = number;
            return this;
        }

        public Builder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder status(Boolean status) {
            this.status = status;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }

    // Factory method
    public static Table create(Long number, Integer capacity, Boolean status) {
        return new Builder()
                .number(number)
                .capacity(capacity)
                .status(status)
                .build();
    }
}