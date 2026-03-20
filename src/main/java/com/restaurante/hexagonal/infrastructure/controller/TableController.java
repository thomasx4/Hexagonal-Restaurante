package com.restaurante.hexagonal.infrastructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.restaurante.hexagonal.application.ports.input.TableInputPort;
import com.restaurante.hexagonal.domain.model.Table;
import com.restaurante.hexagonal.infrastructure.controller.dto.TableRequestDTO;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableInputPort tableInputPort;

    public TableController(TableInputPort tableInputPort) {
        this.tableInputPort = tableInputPort;
    }

    @PostMapping
    public Table createTable(@RequestBody TableRequestDTO dto) {
        Table table = Table.create(
                dto.id,
                dto.number,
                dto.capacity,
                dto.status
        );

        return tableInputPort.createTable(table);
    }

    @GetMapping
    public List<Table> getAll() {
        return tableInputPort.getTables();
    }

    @GetMapping("/{id}")
    public Table getById(@PathVariable Long id) {
        return tableInputPort.getTableById(id);
    }

    @PutMapping("/{id}")
    public Table update(@PathVariable Long id, @RequestBody TableRequestDTO dto) {
        Table table = new Table.Builder()
                .number(dto.number)
                .capacity(dto.capacity)
                .status(dto.status)
                .build();

        return tableInputPort.updateTable(id, table);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tableInputPort.deleteTable(id);
    }
}