package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
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
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    /**
     *
     * @param marca
     */
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Marca marca) {

        this.marcaRepository.save(marca);

    }

    /**
     *
     * @param marca
     */
    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Marca marca) {

        final Marca marcaBanco = this.marcaRepository.findById(id).orElse(null);

        if (marcaBanco == null || !marcaBanco.getId().equals(marca.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.marcaRepository.save(marca);
    }

    /**
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void excluir(final Long id) {

        final Marca marcaBanco = this.marcaRepository.findById(id).orElse(null);

        if (marcaBanco == null || !marcaBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.marcaRepository.delete(marcaBanco);
    }
}
