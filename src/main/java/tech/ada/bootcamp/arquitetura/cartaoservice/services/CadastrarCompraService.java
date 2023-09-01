package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.NotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;

@Service
@RequiredArgsConstructor
public class CadastrarCompraService {
    private final CompraRepository compraRepository;
    private final CartaoRepository cartaoRepository;

    public CompraResponse cadastrarCompra(CompraRequest dto) {
        Cartao cartao = cartaoRepository.findById(dto.getNumeroCartao())
                .orElseThrow(() -> new NotFoundException("Cartao"));

        Compra compra = new Compra(dto);
        compra.setCartao(cartao);

        compraRepository.save(compra);

        return new CompraResponse(cartao.getNumeroCartao(), dto.getLoja(), dto.getValor(), compra.getStatusCompra().toString());
    }
}
