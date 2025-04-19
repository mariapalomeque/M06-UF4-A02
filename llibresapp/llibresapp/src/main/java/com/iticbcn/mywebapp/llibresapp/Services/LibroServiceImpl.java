package com.iticbcn.mywebapp.llibresapp.Services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iticbcn.mywebapp.llibresapp.Modelos.Libro;
import com.iticbcn.mywebapp.llibresapp.Repositorios.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Set<Libro> obtenerTodos() {
        Iterator<Libro> iterator = libroRepository.findAll().iterator();
        return convertirASet(iterator);
    }

    @Override
    public Optional<Libro> buscarPorId(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Set<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public Set<Libro> buscarPorTituloYEditorial(String titulo, String editorial) {
        return libroRepository.findByTituloAndEditorial(titulo, editorial);
    }

    @Override
    public void guardarLibro(Libro libro) {
        if (esISBNValido(libro.getIsbn())) {
            libroRepository.save(libro);
        } else {
            throw new IllegalArgumentException("El ISBN proporcionado no es válido.");
        }
    }

    @Override
    public boolean esISBNValido(String isbn) {
        return isbn != null && isbn.trim().length() > 3;
    }

    private Set<Libro> convertirASet(Iterator<Libro> iterator) {
        Set<Libro> libros = new HashSet<>();
        iterator.forEachRemaining(libros::add);
        return libros;
    }
}
