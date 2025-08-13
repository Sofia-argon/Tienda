package com.tienda.controller;

import com.tienda.domain.Usuario;
import com.tienda.service.RegistroService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final RegistroService registroService;

    @Autowired
    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/registro/nuevo";
    }

    @GetMapping("/recordar")
    public String mostrarFormularioRecordar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/registro/recordar";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            model = registroService.crearUsuario(model, usuario);
            return "/registro/salida";
        } catch (MessagingException e) {
            model.addAttribute("error", "Error al enviar el correo de confirmación");
            return "/registro/error";
        }
    }

    @GetMapping("/activacion/{usuario}/{id}")
    public String activarCuenta(
            @PathVariable String usuario,
            @PathVariable String id,
            Model model) {
        
        model = registroService.activar(model, usuario, id);
        
        if (model.containsAttribute("usuario")) {
            return "/registro/activa";
        } else {
            model.addAttribute("error", "Enlace de activación inválido o expirado");
            return "/registro/error";
        }
    }

    @PostMapping("/activar")
    public String completarActivacion(
            @ModelAttribute Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        registroService.activar(usuario, imagenFile);
        return "redirect:/login?activacion=exito";
    }

    @PostMapping("/recordarUsuario")
    public String recordarCredenciales(@ModelAttribute Usuario usuario, Model model) {
        try {
            model = registroService.recordarUsuario(model, usuario);
            return "/registro/salida";
        } catch (MessagingException e) {
            model.addAttribute("error", "Error al enviar el correo de recuperación");
            return "/registro/error";
        }
    }

    @ExceptionHandler(MessagingException.class)
    public String manejarErrorCorreo(Model model) {
        model.addAttribute("error", "Error en el servicio de correo electrónico");
        return "/registro/error";
    }
}

