package com.ecoprinting.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecoprinting.app.exception.models.EnderecoException;
import com.ecoprinting.app.models.dto.DoacaoDTO;
import com.ecoprinting.app.models.dto.UsuarioLogadoDTO;
import com.ecoprinting.app.service.interfaces.IAutenticacaoService;
import com.ecoprinting.app.service.interfaces.IDoacaoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class DoacaoController {
    private IAutenticacaoService autenticacaoService;
    private IDoacaoService doacaoService;

    public DoacaoController(IAutenticacaoService autenticacaoService, IDoacaoService doacaoService) {
        this.autenticacaoService = autenticacaoService;
        this.doacaoService = doacaoService;
    }

    @GetMapping("/doacao")
    public String login(Model model) {
        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "/doacao/index";
    }

    @PostMapping("/doacao")
    public ResponseEntity<Map<String, String>> efetuarDoacao(@RequestBody DoacaoDTO doacaoDTO) {
        Map<String, String> response = new HashMap<>();

        try {
            this.doacaoService.efetuarDoacao(doacaoDTO);

            response.put("status", "OK");

            return ResponseEntity.ok(response);
        } catch (EnderecoException e) {
            System.out.println(e.getMessage());
            response.put("status", "ERROR");
            response.put("mensagem", e.getMessage());
            throw e;
        }
    }
    
    @DeleteMapping("/doacao")
    public ResponseEntity<Map<String, String>> deletarDoacao(@RequestBody DoacaoDTO doacaoDTO) {
        Map<String, String> response = new HashMap<>();

        try {
            this.doacaoService.deletarDoacao(doacaoDTO);

            response.put("status", "OK");

            return ResponseEntity.ok(response);
        } catch (EnderecoException e) {
            System.out.println(e.getMessage());
            response.put("status", "ERROR");
            response.put("mensagem", e.getMessage());
            throw e;
        }
    }
}