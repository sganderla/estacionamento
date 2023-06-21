package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "marca", schema = "public")
@AuditTable(value = "marca_audit", schema = "audit")
public class Marca extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, unique = true, length = 50)
    @NotNull(message = "O nome da Marca não pode ser nullo.")
    @NotBlank(message = "O nome da Marca não pode estar em branco.")
    @Size(max = 50, min = 2, message = "Tamanho do nome é inválido. Minimo 2, Máximo 50.")
    private String nome;

}
