package com.ecoprinting.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecoprinting.app.dto.UsuarioDTO;
import com.ecoprinting.app.dto.UsuarioLogadoDTO;
import com.ecoprinting.app.service.interfaces.IAutenticacaoService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private IAutenticacaoService autenticacaoService;

    @Autowired
    public UsuarioController(IAutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @GetMapping("/criar")
    public String criarUsuario(Model model) {
        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("usuario", usuarioDTO);
        return "usuario/criar";
    }
}