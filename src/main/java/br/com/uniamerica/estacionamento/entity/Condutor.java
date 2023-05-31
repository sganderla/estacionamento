package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalTime;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 20/03/2023
 * @since 1.0.0
 */
@Entity
@Audited
@Table(name = "condutores", schema = "public")
@AuditTable(value = "condutores_audit", schema = "audit")
public class Condutor extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 100)
    @NotNull(message = "O nome do Condutor não pode ser nullo.")
    @NotBlank(message = "O nome do Condutor não pode estar em branco.")
    @Size(max = 100, min = 4, message = "Tamanho do nome é inválido. Minimo 4, Máximo 100.")
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    @CPF(message = "O CPF do Condutor está invalido")
    @NotNull(message = "O CPF do Condutor não pode ser nullo.")
    @NotBlank(message = "O CPF do Condutor não pode estar em branco.")
    @Size(min = 14, max = 14, message = "Tamanho do CPF é inválido. Minimo 14, Máximo 14.")
    private String cpf;

    @Getter @Setter
    @Column(name = "telefone", nullable = false, length = 17)
    @NotNull(message = "O telefone do Condutor não pode ser nullo.")
    @NotBlank(message = "O telefone do Condutor não pode estar em branco.")
    @Size(min = 17, max = 17, message = "Tamanho do telefone é inválido. Minimo 17, Máximo 17.")
    private String telefone;

    @Getter @Setter
    @Column(name = "tempo_gasto")
    private int tempoGasto;

    /**
     *
     */
    @PrePersist
    public void prePersist(){
        this.tempoGasto = 0;
    }
}











