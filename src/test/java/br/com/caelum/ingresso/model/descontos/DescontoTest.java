package br.com.caelum.ingresso.model.descontos;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class DescontoTest {

    private BigDecimal filmeValor;
    private BigDecimal salaValor;
    private Sala sala;
    private Filme filme;
    private Sessao sessao;

    public static double getRandomDoubleBetweenRange() {
        double min = 5.00;
        double max = 100.00;
        return (Math.random() * ((max - min) + 1)) + min;
    }

    @Before
    public void preparaSessoes() {
        this.filmeValor = new BigDecimal(getRandomDoubleBetweenRange()).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.salaValor = new BigDecimal(getRandomDoubleBetweenRange()).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.sala = new Sala("Eldorado - IMAX", this.salaValor);
        this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", this.filmeValor);
        this.sessao = new Sessao(LocalTime.parse("10:00:00"), this.filme, this.sala);
    }

    @Test
    public void naoDeveConcederDescontoParaIngressoNormal() {
        Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
        BigDecimal precoEsperado = filmeValor.add(salaValor);
        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }

    @Test
    public void ConcederDescontoParaIngressoaBanco() {
        Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());
        BigDecimal precoEsperado = filmeValor.add(salaValor).multiply(new BigDecimal("0.7")).setScale(2, BigDecimal.ROUND_HALF_UP);
        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }
    @Test
    public void ConcederDescontoParaIngressoAEstudante() {
        Ingresso ingresso = new Ingresso(sessao, new DescontoParaEstudantes());
        BigDecimal precoEsperado = filmeValor.add(salaValor).multiply(new BigDecimal("0.5")).setScale(2, BigDecimal.ROUND_HALF_UP);
        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }
}