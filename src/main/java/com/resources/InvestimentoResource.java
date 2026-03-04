package com.resources;

import com.domains.dtos.InvestimentoDTO;
import com.services.InvestimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/investimentos")
public class InvestimentoResource {

    private final InvestimentoService service;

    public InvestimentoResource(InvestimentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InvestimentoDTO>> list(
            @RequestParam(required = false) Long usuarioId) {

        List<InvestimentoDTO> body;
        if (usuarioId != null) {
            body = service.findByUsuario(usuarioId);
        } else {
            body = service.findAll();
        }
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentoDTO> findById(@PathVariable Long id) {
        InvestimentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<InvestimentoDTO> create(
            @RequestBody @Validated(InvestimentoDTO.Create.class) InvestimentoDTO dto) {

        InvestimentoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getIdInvestimento())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestimentoDTO> update(
            @PathVariable Long id,
            @RequestBody @Validated(InvestimentoDTO.Update.class) InvestimentoDTO dto) {

        dto.setIdInvestimento(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
