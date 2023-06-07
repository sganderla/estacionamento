package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 25/04/2023
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/api/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoService veiculoService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        return veiculo == null
                ? ResponseEntity.badRequest().body("Nenhum condutor encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(veiculo);
    }

    /**
     *
     * @return
     */
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.veiculoRepository.findAll());
    }

    /**
     *
     * @return
     */
    @GetMapping("/lista/ativo")
    public ResponseEntity<?> listaCompletaAtivo(){
        return ResponseEntity.ok(this.veiculoRepository.findByAtivoTrue());
    }

    /**
     *
     * @param veiculo
     * @return
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Veiculo veiculo){
        try {
            this.veiculoService.cadastrar(veiculo);
            return ResponseEntity.ok("Registro cadastrado com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param veiculo
     * @return
     */
    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody final Veiculo veiculo
    ){
        try {
            this.veiculoRepository.save(veiculo);
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
            this.veiculoService.excluir(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
