package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.descontos.Desconto;
import br.com.caelum.ingresso.model.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudantes;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

import java.math.BigDecimal;

public enum TipoDeIngresso {
    INTEIRO(new SemDesconto()),
    ESTUDANTE(new DescontoParaEstudantes()),
    BANCO(new DescontoParaBancos());

    private final Desconto desconto;

    TipoDeIngresso(Desconto desconto) {
        this.desconto = desconto;
    }

    public BigDecimal aplicaDesconto(BigDecimal valor) {
        return desconto.aplicarDescontoSobre(valor);
    }

    public String getDescricao() {
        return desconto.getDescricao();
    }
}
