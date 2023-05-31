package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Modelo;
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

    /**
     *
     * @param modelo
     */
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Modelo modelo) {


    }
}
