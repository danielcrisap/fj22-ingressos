package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Sessao sessao;

    private BigDecimal preco;

    @ManyToOne
    private Lugar lugar;

    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;

    /**
     * @deprecated hibernate only
     */
    @Deprecated
    public Ingresso() {
    }

    public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
        this.sessao = sessao;
        this.tipoDeIngresso = tipoDeIngresso;
        this.preco = this.tipoDeIngresso.aplicaDesconto(sessao.getPreco());
        this.lugar = lugar;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public TipoDeIngresso getTipoDeIngresso() {
        return tipoDeIngresso;
    }

    public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
        this.tipoDeIngresso = tipoDeIngresso;
    }
}
