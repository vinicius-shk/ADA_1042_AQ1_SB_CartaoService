package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.NotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CriarNovoCartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class CriarUsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CriarNovoCartaoService cartaoService;

    public CadastroUsuarioResponse cadastrarUsuario(CadastroUsuarioRequest cadastroUsuarioRequest) {
        Usuario usuario = new Usuario(cadastroUsuarioRequest );
        usuarioRepository.save(usuario);

        return cartaoService.execute(cadastroUsuarioRequest);
    }
}
