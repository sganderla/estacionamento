package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

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
    @Column(name = "placa", nullable = false, unique = true, length = 10)
    @NotNull(message = "A placa do Veículo não pode ser nullo.")
    @NotBlank(message = "A placa do Veículo não pode estar em branco.")
    @Length(max = 10, min = 8, message = "Tamanho do nome é inválido. Minimo 8, Máximo 10.")
    private String placa;

    @Getter @Setter
    @Column(name = "ano", nullable = false)
    @Size(min = 1900, message = "O ano terá que estar entre 1900 a Ano atual.")
    private int ano;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo_id", nullable = false)
    @NotNull(message = "O Modelo do Veículo não foi informado.")
    private Modelo modelo;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "cor", nullable = false, length = 20)
    @NotNull(message = "A Cor do Veículo não foi informado.")
    private Cor cor;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 6)
    @NotNull(message = "O Tipo do Veículo não foi informado.")
    private Tipo tipo;

}
