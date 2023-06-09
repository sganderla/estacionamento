package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.service.ConfiguracaoService;
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
@RequestMapping(value = "/api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private ConfiguracaoService configuracaoService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
        return configuracao == null
                ? ResponseEntity.badRequest().body("Nenhum condutor encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(configuracao);
    }

    /**
     *
     * @param configuracao
     * @return
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Configuracao configuracao){
        try {
            this.configuracaoService.cadastrar(configuracao);
            return ResponseEntity.ok("Configuração cadastrada com sucesso.");
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
     * @param configuracao
     * @return
     */
    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody final Configuracao configuracao
    ){
        try {
            this.configuracaoService.editar(id, configuracao);
            return ResponseEntity.ok("Configuração atualizada com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
