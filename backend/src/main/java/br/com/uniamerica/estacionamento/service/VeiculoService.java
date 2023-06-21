package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
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
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    /**
     *
     * @param veiculo
     */
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Veiculo veiculo) {

        this.veiculoRepository.save(veiculo);

    }

    /**
     *
     * @param veiculo
     */
    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Veiculo veiculo) {

        final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);

        if (veiculoBanco == null || !veiculoBanco.getId().equals(veiculo.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.veiculoRepository.save(veiculo);
    }

    /**
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void excluir(final Long id) {

        final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);

        if (veiculoBanco == null || !veiculoBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.veiculoRepository.delete(veiculoBanco);
    }
}
