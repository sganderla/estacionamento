package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 25/04/2023
 * @since 1.0.0
 */

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    /**
     *
     * @param modelo
     */
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Modelo modelo) {

        this.modeloRepository.save(modelo);

    }

    /**
     *
     * @param modelo
     */
    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Modelo modelo) {

        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);

        if (modeloBanco == null || !modeloBanco.getId().equals(modelo.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.modeloRepository.save(modelo);
    }

    /**
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void excluir(final Long id) {

        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);

        if (modeloBanco == null || !modeloBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.modeloRepository.delete(modeloBanco);
    }
}
