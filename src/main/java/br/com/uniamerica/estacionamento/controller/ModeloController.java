package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
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

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        return modelo == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(modelo);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") final Long id){
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        return modelo == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
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
     * @param modelo
     * @return
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Modelo modelo){
        try {
            this.modeloRepository.save(modelo);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
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
            final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);

            if (modeloBanco == null || !modeloBanco.getId().equals(modelo.getId())){
                throw new RuntimeException("Não foi possivel identificar o registro informado.");
            }

            this.modeloRepository.save(modelo);
            return ResponseEntity.ok("Registro atualizado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
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
        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);

        this.modeloRepository.delete(modeloBanco);
        return ResponseEntity.ok("Registro Excluido com Sucesso");
    }
}






