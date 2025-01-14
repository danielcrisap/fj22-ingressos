package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessaoDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(Sessao sessao) {
        manager.persist(sessao);
    }

    public List<Sessao> buscaSessoesDaSala(Sala sala) {
        return manager.createQuery("SELECT s FROM Sessao s WHERE s.sala = :sala", Sessao.class).setParameter("sala", sala).getResultList();
    }

    public List<Sessao> buscaSessoesDoFilme(Filme filme) {
        return manager.createQuery("SELECT s FROM Sessao s WHERE s.filme = :filme", Sessao.class).setParameter("filme", filme).getResultList();
    }

    public Sessao findOne(Integer id) {
        return manager.find(Sessao.class, id);
    }
}
