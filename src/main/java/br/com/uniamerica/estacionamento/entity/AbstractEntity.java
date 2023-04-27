package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 20/03/2023
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column(name = "cadastro", nullable = false)
    private LocalDateTime cadastro;
    @Getter @Setter
    @Column(name = "atualizacao")
    private LocalDateTime atualizacao;
    @Getter @Setter
    @Column(name = "ativo", nullable = false)
    private boolean ativo;


    /**
     *
     */
    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }

    /**
     *
     */
    @PreUpdate
    private void preUpdate(){
        this.atualizacao = LocalDateTime.now();
    }
}