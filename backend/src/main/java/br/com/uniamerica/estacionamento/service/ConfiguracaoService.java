package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
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
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    /**
     *
     * @param configuracao
     */
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Configuracao configuracao) {

        this.configuracaoRepository.save(configuracao);

    }

    /**
     *
     * @param configuracao
     */
    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Configuracao configuracao) {

        final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);

        if (configuracaoBanco == null || !configuracaoBanco.getId().equals(configuracao.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.configuracaoRepository.save(configuracao);

    }

    /**
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void excluir(final Long id) {

        final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);

        if (configuracaoBanco == null || !configuracaoBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.configuracaoRepository.delete(configuracaoBanco);
    }
}
