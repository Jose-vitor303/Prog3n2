package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;

public class DaoAluno extends Dao<Aluno>{

    public List<Aluno> buscarTodos(){

        return em.createQuery("select a from Aluno a").getResultList(); 
    
    }

    public List<Aluno> buscarTodosPorId() {
        return em.createQuery("select a from Aluno a order by a.id").getResultList();
    }
    
}
