package com.restaurante.hexagonal.domain.model;

import java.util.Objects;

public class Category {

    private final Long id;
    private final String name;
    private final String description;

    private Category (Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        validate();
    }

      public static class Builder {
        private Long id;
        private String name;
        private String description;
        
        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        
        public Category build() {
            return new Category(this);
        }
    }

    public Long getId(){ return id;}
    public String getName (){return name;}
    public String getDescription(){return description;}

    private void validate(){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
            
        }

        if (name.length()>100) {
            throw new IllegalArgumentException("El nombre no puede exceder de 100 caracteres");
            
        }

        if (description.length()>500) {
            throw new IllegalArgumentException("La descripcion no puede exceder de 500 caracteres");
        }
    }

    public Category update(String newName, String newDescription){
        return new Builder()
        .id(this.id)
        .name(newName)
        .description(newDescription)
        .build();
    }

     
    public Category withName(String newName) {
        return new Builder()
                .id(this.id)
                .name(newName)
                .description(this.description)
                .build();
    }
    
    public Category withDescription(String newDescription) {
        return new Builder()
                .id(this.id)
                .name(this.name)
                .description(newDescription)
                .build();
    }

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

        public static Category create(String name, String description) {
        return new Builder()
                .name(name)
                .description(description)
                .build();
    }
    
    public static Category reconstruct(Long id, String name, String description) {
        return new Builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

    
}
