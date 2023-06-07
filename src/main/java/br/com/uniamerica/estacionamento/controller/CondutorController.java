package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 25/04/2023
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/api/condutor")
public class CondutorController {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private CondutorService condutorService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor == null
                ? ResponseEntity.badRequest().body("Nenhum condutor encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(condutor);
    }

    /**
     *
     * @return
     */
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.condutorRepository.findAll());
    }

    /**
     *
     * @return
     */
    @GetMapping("/lista/ativo")
    public ResponseEntity<?> listaCompletaAtivo(){
        return ResponseEntity.ok(this.condutorRepository.findByAtivoTrue());
    }

    /**
     *
     * @param condutor
     * @return
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Condutor condutor){
        try {
            this.condutorService.cadastrar(condutor);
            return ResponseEntity.ok("Condutor cadastrado com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param condutor
     * @return
     */
    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody final Condutor condutor
    ){
        try {
            this.condutorService.editar(id, condutor);
            return ResponseEntity.ok("Condutor atualizado com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseEntity<?> excluir(
            @RequestParam("id") final Long id
    ){
        try{
            this.condutorService.excluir(id);
            return ResponseEntity.ok("Condutor excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
