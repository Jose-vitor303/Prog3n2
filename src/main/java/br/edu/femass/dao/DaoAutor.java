package br.edu.femass.dao;
import br.edu.femass.model.Autor;
import java.util.List;


public class DaoAutor extends Dao<Autor>{


    public List<Autor> buscarTodos(){

        return em.createQuery("select a from Autor a").getResultList(); 
    
    }

    public List<Autor> buscarTodosPorId() {
        return em.createQuery("select a from Autor a order by a.id").getResultList();
    }
    
    
}