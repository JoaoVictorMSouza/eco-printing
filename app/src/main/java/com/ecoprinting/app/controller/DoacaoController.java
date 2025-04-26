package com.ecoprinting.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecoprinting.app.models.dto.UsuarioLogadoDTO;
import com.ecoprinting.app.service.interfaces.IAutenticacaoService;

@Controller
public class DoacaoController {
    private IAutenticacaoService autenticacaoService;

    public DoacaoController(IAutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @GetMapping("/doacao")
    public String login(Model model) {
        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "/doacao/index";
    }
}