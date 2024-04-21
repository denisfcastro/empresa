package br.ueg.progweb1.empresa.controllers;

import br.ueg.progweb1.empresa.exceptions.BusinessLogicException;
import br.ueg.progweb1.empresa.exceptions.DataException;
import br.ueg.progweb1.empresa.exceptions.MandatoryException;
import br.ueg.progweb1.empresa.mappers.EmpresaMapper;
import br.ueg.progweb1.empresa.model.Empresa;
import br.ueg.progweb1.empresa.model.dtos.EmpresaDTO;
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

    @Autowired
    EmpresaMapper mapper;

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
    public ResponseEntity<Object> create(@RequestBody EmpresaDTO empresa) {
        Empresa empresaIncluir = null;
        try {
            empresaIncluir = service.incluir(mapper.toModel(empresa));

        } catch (MandatoryException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERRO:" + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro desconhecido aconteceu: " + e.getMessage());
        }

        return ResponseEntity.ok(empresaIncluir);
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Alterar empresa")
    public ResponseEntity<Object> update(@RequestBody EmpresaDTO empresa, @PathVariable("id") Long id) {
        Empresa empresaIncluir = null;
        try {
            Empresa empresaAlterar = mapper.toModel(empresa);
            empresaAlterar.setId(id);
            empresaIncluir = service.alterar(empresaAlterar);

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


    @GetMapping(path = "/{id}")
    @Operation(description = "buscar por id")
    public ResponseEntity<Object> getById(
            @PathVariable("id") Long id
    ) {
        Empresa empresa = new Empresa();
        try{
            empresa = service.validateIdSaleExists(id);

        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:"+de.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(empresa);
    }


    @DeleteMapping(path = "/{id}")
    @Operation(description = "End point para remover dados de aluno")
    public ResponseEntity<Object> remove(
            @PathVariable("id") Long id
    ) {
        Empresa studentDB =  Empresa.builder().id(0L).build();
        try{

           service.excluirEmpresaPorID(id);

        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:"+de.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(studentDB);
    }


}
