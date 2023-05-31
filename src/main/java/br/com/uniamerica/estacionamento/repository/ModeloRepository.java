package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 23/03/2023
 * @since 1.0.0
 */
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}








