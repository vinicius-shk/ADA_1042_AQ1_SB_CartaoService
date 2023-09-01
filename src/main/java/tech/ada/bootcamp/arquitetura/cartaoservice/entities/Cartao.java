package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CriarNovoCartaoRequest;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "cartao")
@NoArgsConstructor
public class Cartao {
    @Id
    private String numeroCartao;
    private String nomeTitular;
    private LocalDate vencimentoCartao;

    private String codigoSeguranca;

    private TipoCartao tipoCartao;

    private String idContaBanco;

    private Boolean dependente = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "usuarioIdentificador")
    private Usuario usuario;

    public Cartao(CriarNovoCartaoRequest dto, String codigoSeguranca, String numeroCartao, Usuario user) {
        LocalDate dataAtual = LocalDate.now();
        this.nomeTitular = dto.getNomeTitular();
        this.tipoCartao = dto.getTipoCartao();
        this.usuario = user;
        this.idContaBanco = UUID.randomUUID().toString();
        this.vencimentoCartao = dataAtual.plusYears(5);
        this.codigoSeguranca = codigoSeguranca;
        this.numeroCartao = numeroCartao;

    }
}