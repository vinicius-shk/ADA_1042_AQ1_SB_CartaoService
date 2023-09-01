package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.NotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CriarNovoCartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class CriarUsuarioService {
    private final UsuarioRepository usuarioRepository;

    public CadastroUsuarioResponse execute(CadastroUsuarioRequest cadastroUsuarioRequest) {
        Usuario usuario = new Usuario(cadastroUsuarioRequest );
//to do

        usuarioRepository.save(usuario);
        return new CadastroUsuarioResponse();
    }
}
