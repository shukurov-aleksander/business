package com.ku.business.controller;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@Tag(name = "Documents", description = "Documents information")
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService documentService;

    @GetMapping("{id}")
    @Operation(summary = "Find document by id")
    public DocumentDto findById(
        @Parameter(description = "Detail id", required = true, example = "1")
        @PathVariable Long id
    ) {
        return documentService.findById(id).get();
    }

    @GetMapping
    @Operation(summary = "Find documents")
    public List<DocumentListDto> findAll() {
        return documentService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save document")
    public void save(DocumentSaveDto document) {
        documentService.save(document);
    }

    @PutMapping
    @Operation(summary = "Update document")
    public void update(DocumentSaveDto document) {
        documentService.update(document);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete document by id")
    public void delete(
        @Parameter(description = "Document id", required = true, example = "1")
        @PathVariable Long id
    ) {
        documentService.delete(id);
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}