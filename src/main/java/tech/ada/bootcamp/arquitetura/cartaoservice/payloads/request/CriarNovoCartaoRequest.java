package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import lombok.Getter;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;


@Getter
@Setter
public class CriarNovoCartaoRequest {
    private String nomeTitular;
    private TipoCartao tipoCartao;
    private CadastroUsuarioRequest usuario;

}
