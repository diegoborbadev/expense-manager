package com.diegoborbadev.expensemanager.controller;

import com.diegoborbadev.expensemanager.model.BaseModel;
import com.diegoborbadev.expensemanager.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class BaseController<S extends CrudService<T, Long>, DTO, T extends BaseModel<Long>> {

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected S service;

    @Operation(summary = "Get all elements")
    @GetMapping("/all")
    @Transactional
    public ResponseEntity<List<DTO>> getAllElements() {
        List<T> elements = service.findAll();
        if (!CollectionUtils.isEmpty(elements)) {
            return ResponseEntity.ok(convertToListDto(elements));
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all elements")
    @GetMapping("/all/pagination")
    @Transactional
    public ResponseEntity<Page<DTO>> getAllElements(Pageable pageable) {
        Page<T> pages = service.findAll(pageable);
        if (pages != null && pages.hasContent()) {
            List<DTO> dtoList = convertToListDto(pages.getContent());
            return ResponseEntity.ok(new PageImpl<>(dtoList, pageable, pages.getTotalElements()));
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get element by id")
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DTO> getElementById(@PathVariable(value = "id") Long elementId) {
        Optional<T> element = service.findById(elementId);
        return element.map(t -> ResponseEntity.ok(convertToDetailDto(t))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Operation(summary = "Create a new element")
    @PostMapping("/")
    @Transactional
    public ResponseEntity<DTO> createElement(@Valid @RequestBody DTO element) {
        T converted = convertToModel(element);
        T elementCreated = service.createElement(converted);
        if (elementCreated != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDetailDto(elementCreated));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update a element")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTO> updateElement(@PathVariable(value = "id") Long elementId, @Valid @RequestBody DTO element) {
        T converted = convertToModel(element);
        Optional<T> elementUpdated = service.updateElement(elementId, converted);
        return elementUpdated.map(t -> ResponseEntity.ok(convertToDetailDto(t))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a element")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable(value = "id") Long elementId) {
        boolean success = service.deleteElement(elementId);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    protected abstract List<DTO> convertToListDto(List<T> elements);

    protected abstract DTO convertToDetailDto(T element);

    protected abstract T convertToModel(DTO dto);
}