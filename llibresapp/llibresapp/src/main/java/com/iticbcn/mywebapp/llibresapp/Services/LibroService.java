package com.iticbcn.mywebapp.llibresapp.Services;

import java.util.Optional;
import java.util.Set;

import com.iticbcn.mywebapp.llibresapp.Modelos.Libro;

public interface LibroService {

    Set<Libro> obtenerTodos();

    Optional<Libro> buscarPorId(Long id);

    Set<Libro> buscarPorTitulo(String titulo);

    void guardarLibro(Libro libro);
    
    Set<Libro> buscarPorTituloYEditorial(String titulo, String editorial);

    boolean esISBNValido(String isbn);

    
}
