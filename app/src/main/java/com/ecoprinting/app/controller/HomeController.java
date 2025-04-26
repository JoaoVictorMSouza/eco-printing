package com.ecoprinting.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.ecoprinting.app.models.dto.UsuarioLogadoDTO;
import com.ecoprinting.app.service.interfaces.IAutenticacaoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    private IAutenticacaoService autenticacaoService;

    @Autowired
    public HomeController(IAutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "index";
    }
}
