package com.ecoprinting.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecoprinting.app.exception.models.UsuarioException;
import com.ecoprinting.app.models.dto.UsuarioDTO;
import com.ecoprinting.app.models.dto.UsuarioLogadoDTO;
import com.ecoprinting.app.service.interfaces.IAutenticacaoService;
import com.ecoprinting.app.service.interfaces.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private IAutenticacaoService autenticacaoService;
    private IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IAutenticacaoService autenticacaoService, IUsuarioService usuarioService) {
        this.autenticacaoService = autenticacaoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/criar")
    public String criarUsuario(Model model) {
        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("usuario", usuarioDTO);
        return "usuario/criar";
    }

    @GetMapping("/consultarEmail")
    public ResponseEntity<Map<String, Boolean>> consultarEmail(@RequestParam String email) {
        boolean emailExiste = this.usuarioService.verificarExistenciaEmail(email);

        Map<String, Boolean> response = new HashMap<>();
        response.put("emailExiste", emailExiste);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/criar")
    public ResponseEntity<Map<String, String>> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        Map<String, String> response = new HashMap<>();

        try {
            this.usuarioService.criarUsuario(usuarioDTO);

            response.put("status", "OK");

            return ResponseEntity.ok(response);
        } catch (UsuarioException e) {
            System.out.println(e.getMessage());
            response.put("status", "ERROR");
            response.put("mensagem", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") int id, Model model) {
        UsuarioLogadoDTO usuarioLogado = autenticacaoService.retornarUsuarioLogado();
        model.addAttribute("usuarioLogado", usuarioLogado);

        UsuarioDTO usuario = this.usuarioService.buscarUsuarioPorId(id);
        model.addAttribute("usuario", usuario);

        model.addAttribute("listaEndereco", usuario.getEnderecos());

        return "usuario/editar";
    }
    
    @PostMapping("/editar")
    public ResponseEntity<Map<String, String>> editarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, String> response = new HashMap<>();

        try {
            this.usuarioService.editarUsuario(usuarioDTO);

            response.put("status", "OK");

            return ResponseEntity.ok(response);
        } catch (UsuarioException e) {
            System.out.println(e.getMessage());
            response.put("status", "ERROR");
            response.put("mensagem", e.getMessage());
            throw e;
        }
    }
}