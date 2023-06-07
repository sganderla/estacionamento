package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
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
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    /**
     *
     * @param condutor
     */
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Condutor condutor) {

        this.condutorRepository.save(condutor);

    }

    /**
     *
     * @param condutor
     */
    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Condutor condutor) {

        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);

        if (condutorBanco == null || !condutorBanco.getId().equals(condutor.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.condutorRepository.save(condutor);
    }

    /**
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void excluir(final Long id) {

        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);

        if (condutorBanco == null || !condutorBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.condutorRepository.delete(condutorBanco);
    }
}
