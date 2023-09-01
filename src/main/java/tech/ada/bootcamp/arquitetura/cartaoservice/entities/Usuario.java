package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;

import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    private String identificador;
    private String nome;

    public Usuario(CadastroUsuarioRequest dto){
        this.identificador = dto.getIdentificador();
        this.nome = dto.getNome();


    }

}
