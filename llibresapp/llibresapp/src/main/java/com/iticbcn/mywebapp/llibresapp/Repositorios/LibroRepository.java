package com.iticbcn.mywebapp.llibresapp.Repositorios;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iticbcn.mywebapp.llibresapp.Modelos.Libro;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Long> {
    
    Set<Libro> findAll();  

    Set<Libro> findByTituloContainingIgnoreCase(String titulo);  

    Set<Libro> findByTituloAndEditorial(String titulo, String editorial); 
}
