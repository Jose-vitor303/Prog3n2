package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Livro;

public class DaoLivro extends Dao<Livro>{
   

    public List<Livro> buscarTodos(){

        return em.createQuery("select L from Livro L").getResultList(); 
    
    }

    public List<Livro> buscarTodosPorId() {
        return em.createQuery("select L from Livro L order by L.id").getResultList();
    }
    
}   
