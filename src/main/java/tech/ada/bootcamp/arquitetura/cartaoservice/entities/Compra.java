package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "compra")
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime dataCompra;
    private String loja;

    public BigDecimal valor;


    @ManyToOne
    @JoinColumn(name = "numeroCartao")
    public Cartao cartao;

    public StatusCompra statusCompra;

    public Compra(CompraRequest dto) {
        this.dataCompra = LocalDateTime.now();
        this.loja = dto.getLoja();
        this.valor = BigDecimal.valueOf(dto.getValor());
        this.statusCompra = StatusCompra.PENDENTE;
    }

}