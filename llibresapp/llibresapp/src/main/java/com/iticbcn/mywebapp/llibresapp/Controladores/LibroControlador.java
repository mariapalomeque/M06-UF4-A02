package com.iticbcn.mywebapp.llibresapp.Controladores;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.iticbcn.mywebapp.llibresapp.Modelos.Usuarios;
import com.iticbcn.mywebapp.llibresapp.Modelos.Libro;
import com.iticbcn.mywebapp.llibresapp.Services.LibroService;

@Controller
@RequestMapping("/libros")
public class LibroControlador {

    private final LibroService libroService;

    public LibroControlador(LibroService libroService) {
        this.libroService = libroService;
    }

    // ---------- LOGIN ----------
    @GetMapping("/")
    public String redireccionLogin() {
        return "redirect:/libros/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String usuario,
                        @RequestParam String contra,
                        Model model) {

        if ("maria".equals(usuario) && "1234".equals(contra)) {
            model.addAttribute("usuarios", new Usuarios(usuario, contra));
            return "index";
        } else {
            model.addAttribute("error", "Usuari o contrasenya incorrectes");
            return "login";
        }
    }

    // ---------- INDEX ----------
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    // ---------- CONSULTA LIBROS ----------
    @GetMapping("/consulta")
    public String consultaLibros(Model model) {
        Set<Libro> libros = libroService.obtenerTodos();
        model.addAttribute("libros", libros);
        model.addAttribute("usuarios", new Usuarios("admin", "1234")); 
        return "llistat"; 
    }

    // ---------- INSERIR LIBRO ----------
    @GetMapping("/inserir")
    public String mostrarFormulariInsercio(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("usuarios", new Usuarios("admin", "1234")); 
        return "inserir";
    }

    @PostMapping("/inserir")
    public String inserirlibro(@ModelAttribute Libro libro) {
        libroService.guardarLibro(libro);
        return "redirect:/libros/consulta";
    }

    // ---------- CERCA PER TÍTOL ----------
    @GetMapping("/cerca")
    public String cercaLibroPerTitol(@RequestParam String titulo, Model model) {
        Set<Libro> libros = libroService.buscarPorTitulo(titulo);
        model.addAttribute("libros", libros);
        return "cercaLibro";
    }

    // ---------- CERCA PER ID ----------
    @GetMapping("/cercaid")
public String cercaPerId(@RequestParam(value = "id", required = false) Long id, Model model) {
    if (id != null) {
        Optional<Libro> llibreOpt = libroService.buscarPorId(id);
        if (llibreOpt.isPresent()) {
            model.addAttribute("llibre", llibreOpt.get());
        } else {
            model.addAttribute("error", "No s'ha trobat cap llibre amb ID " + id);
        }
    }
    return "cercarId";
}



    // ---------- API POST LIBRO ----------
    @PostMapping
    @ResponseBody
    public void addLibro(@RequestBody Libro libro) {
        libroService.guardarLibro(libro);
    }
}
