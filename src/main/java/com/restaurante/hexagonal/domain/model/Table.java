package com.restaurante.hexagonal.domain.model;

import java.util.Objects;


public class Table {

    private final Long id;
    private final Integer number;
    private final Integer capacity;
    private final Boolean status;

    private Table(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.capacity = builder.capacity;
        this.status = builder.status;
        validate();
    }

    private void validate() {

        if (number == null) {
            throw new IllegalArgumentException("Table number is required");
        }

        if (capacity == null || capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }
    }

    // Getters
    public Long getId() { return id; }
    public Integer getNumber() { return number; }
    public Integer getCapacity() { return capacity; }
    public Boolean getStatus() { return status; }

    // Update
    public Table update(Integer number, Integer capacity, Boolean status) {
        return new Builder()
                .id(this.id)
                .number(number)
                .capacity(capacity)
                .status(status)
                .build();
    }

    // Builder
    public static class Builder {
        private Long id;
        private Integer number;
        private Integer capacity;
        private Boolean status;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder number(Integer number) { this.number = number; return this; }
        public Builder capacity(Integer capacity) { this.capacity = capacity; return this; }
        public Builder status(Boolean status) { this.status = status; return this; }

        public Table build() {
            return new Table(this);
        }
    }

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

    public static Table create(Long id2, Integer number2, Integer capacity2, Boolean status2) {
        return new Builder()
                .id(id2)
                .number(number2)
                .capacity(capacity2)
                .status(status2)
                .build();
        }
}