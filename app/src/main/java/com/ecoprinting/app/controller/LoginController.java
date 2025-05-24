package com.ecoprinting.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecoprinting.app.models.dto.UsuarioLogadoDTO;
import com.ecoprinting.app.service.interfaces.IAutenticacaoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private IAutenticacaoService autenticacaoService;

    @Autowired
    public LoginController(IAutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "/login/login";
    }

    @PostMapping("/logout")
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().invalidate();

        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);
        
        return "/";
    }
}