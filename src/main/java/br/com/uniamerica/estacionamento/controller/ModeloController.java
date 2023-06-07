package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 25/04/2023
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/api/modelo")
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private ModeloService modeloService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        return modelo == null
                ? ResponseEntity.badRequest().body("Nenhum condutor encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(modelo);
    }

    /**
     *
     * @return
     */
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.modeloRepository.findAll());
    }

    /**
     *
     * @return
     */
    @GetMapping("/lista/ativo")
    public ResponseEntity<?> listaCompletaAtivo(){
        return ResponseEntity.ok(this.modeloRepository.findByAtivoTrue());
    }

    /**
     *
     * @param modelo
     * @return
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Modelo modelo){
        try {
            this.modeloService.cadastrar(modelo);
            return ResponseEntity.ok("Registro cadastrado com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param modelo
     * @return
     */
    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody final Modelo modelo
    ){
        try {
            this.modeloService.editar(id, modelo);
            return ResponseEntity.ok("Registro atualizado com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
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
    public ResponseEntity<?> delete(
            @RequestParam("id") final Long id
    ){
        try {
            this.modeloService.excluir(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}






