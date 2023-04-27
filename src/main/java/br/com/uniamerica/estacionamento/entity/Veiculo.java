package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 20/03/2023
 * @since 1.0.0
 */
@Entity
@Audited
@Table(name = "veiculo", schema = "public")
@AuditTable(value = "veiculo_audit", schema = "audit")
public class Veiculo extends AbstractEntity{
    @Getter @Setter
    @Column(name = "placa",nullable = false,unique = true, length = 10)
    private String placa;
    @Getter @Setter
    @Column(name = "ano",nullable = false)
    private int ano;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "cor", nullable = false, length = 20)
    private Cor cor;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "tipo", nullable = false, length = 6)
    private Tipo tipo;

}
