package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarNovoCartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {
    private final CriarUsuarioService usuarioService;
    private final CriarNovoCartaoService cartaoService;

    @PostMapping(path = "", produces = "application/json")
    public CadastroUsuarioResponse cadastrarUsuario(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest) {
        return usuarioService.cadastrarUsuario(cadastroUsuarioRequest);
    }

    @PostMapping(path = "/dependentes", produces = "application/json")
    public List<CadastroUsuarioResponse> adicionarDependente(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest) {
        return cartaoService.cadastrarDependentes(cadastroUsuarioRequest);
    }

}
