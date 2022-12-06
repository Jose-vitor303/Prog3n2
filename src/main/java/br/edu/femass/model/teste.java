package br.edu.femass.model;

import br.edu.femass.dao.DaoAutor;

public class teste {

    public static void main(String[] args) {
        
    
        Autor autor = new Autor("Paulo", "Coelho", "Brasileiro");
        DaoAutor dao = new DaoAutor();

        dao.inserir(autor);
        // Livro livro = new Livro("A casa", 2010, autor);

        // DaoLivro dao = new DaoLivro();

        // dao.inserir(livro);

        
    }
    
}
