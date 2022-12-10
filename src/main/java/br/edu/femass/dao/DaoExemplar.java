package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Exemplar;

public class DaoExemplar extends Dao<Exemplar>{

       
    public List<Exemplar> buscarTodos(){

        return em.createQuery("select e from Exemplar e").getResultList(); 
    
    }

    public List<Exemplar> buscarTodosPorId() {
        return em.createQuery("select e from Exemplar e order by e.codigo").getResultList();
    }
    
}
