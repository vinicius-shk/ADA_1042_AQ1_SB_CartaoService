package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.NotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CriarNovoCartaoService {
    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;
    private static Random random;

    public CadastroUsuarioResponse execute(CadastroUsuarioRequest cadastroRequest) {

        Usuario usuario = usuarioRepository.findById(cadastroRequest.getIdentificador())
            .orElseThrow(() -> new NotFoundException("Usuario"));
        Cartao cartao = new Cartao(cadastroRequest.getDadosCartao(), gerarNumeroAleatorio(3),
            gerarNumeroAleatorio(12), usuario);
        cartaoRepository.save(cartao);
        return new CadastroUsuarioResponse(cartao.getNumeroCartao(), cartao.getNomeTitular(),
            cartao.getTipoCartao(), usuario.getNome());
    }

    public List<CadastroUsuarioResponse> cadastrarDependentes(CadastroUsuarioRequest cadastroRequest) {
        Usuario usuario = usuarioRepository.findById(cadastroRequest.getIdentificador())
                .orElseThrow(() -> new NotFoundException("Usuario"));

        List<CadastroUsuarioResponse> listaResposta = new ArrayList<>();

        for (String dependente : cadastroRequest.getDependentes()) {
            Cartao cartao = new Cartao(cadastroRequest.getDadosCartao(), gerarNumeroAleatorio(3),
                    gerarNumeroAleatorio(12), usuario);
            cartao.setDependente(true);
            cartao.setTipoCartao(TipoCartao.PRATA);
            cartao.setNomeTitular(dependente);
            cartaoRepository.save(cartao);
            listaResposta.add(new CadastroUsuarioResponse(cartao.getNumeroCartao(), cartao.getNomeTitular(),
                    cartao.getTipoCartao(), usuario.getNome()));
        }
        return listaResposta;
    }

    private String gerarNumeroAleatorio(int length) {

        final int tamanho = length <= 0 ? 1 : length;
        IntStream stream = getRandom().ints(tamanho, 0, 9);
        StringBuilder builder = new StringBuilder();
        stream.forEachOrdered(builder::append);
        return builder.toString();
    }

    private static Random getRandom() {
        if (Objects.isNull(random)) {
            random = new Random();
        }
        return random;
    }
}
