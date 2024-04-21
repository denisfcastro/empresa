package br.ueg.progweb1.empresa.controllers;

import br.ueg.progweb1.empresa.exceptions.BusinessLogicException;
import br.ueg.progweb1.empresa.exceptions.MandatoryException;
import br.ueg.progweb1.empresa.model.Empresa;
import br.ueg.progweb1.empresa.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${api.version}/controllers")
public class EmpresaController {
    @Autowired
    EmpresaService service;

    @GetMapping
    @Operation(description = "Listar todas as empresas")
    public ResponseEntity<List<Empresa>> listall() {
        List<Empresa> empresaList = new ArrayList<>();
        try {
            empresaList = service.listAll();

        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.of(Optional.ofNullable(empresaList));
    }

    @PostMapping
    @Operation(description = "incluir empresa")
    public ResponseEntity<Object> create(@RequestBody Empresa empresa) {
        Empresa empresaIncluir = null;
        try {
            empresaIncluir = service.incluir(empresa);

        } catch (MandatoryException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERRO:" + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro desconhecido aconteceu: " + e.getMessage());
        }

        return ResponseEntity.ok(empresaIncluir);
    }

    @PutMapping
    @Operation(description = "Alterar empresa")
    public ResponseEntity<Object> update(@RequestBody Empresa empresa) {
        Empresa empresaIncluir = null;
        try {
            empresaIncluir = service.alterar(empresa);

        } catch (MandatoryException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("ERRO:" + e.getMessage());

        } catch (BusinessLogicException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED)
                    .body("ERRO: " + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro desconhecido aconteceu: " + e.getMessage());
        }

        return ResponseEntity.ok(empresaIncluir);
    }


}
