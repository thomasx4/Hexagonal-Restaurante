package com.restaurante.hexagonal.infrastructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.hexagonal.application.ports.input.TableInputPort;
import com.restaurante.hexagonal.domain.model.Table;

@RestController
@RequestMapping("/tables")
public class TableController {
    private final TableInputPort tableInputPort;

    public TableController(TableInputPort tableInputPort) {
        this.tableInputPort = tableInputPort;
    }

    @PostMapping
    public Table createTable(@RequestBody Table table) {
        return tableInputPort.createTable(table);
    }

    @GetMapping
    public List<Table> getAllTables() {
        return tableInputPort.getTables();
    }

    @GetMapping("/{id}")
    public Table getTableById(@PathVariable Long id) {
        return tableInputPort.getTableById(id);
    }

    @PutMapping
    public Table updateTable(@RequestBody Table table) {
        return tableInputPort.updateTable(table);
    }

    @DeleteMapping("/{id}")
    public Table deleteTable(@PathVariable Long id) {
        return tableInputPort.deleteTable(id);
    }
}
