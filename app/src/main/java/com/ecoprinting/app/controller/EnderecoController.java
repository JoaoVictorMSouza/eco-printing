package com.ecoprinting.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecoprinting.app.exception.models.EnderecoException;
import com.ecoprinting.app.models.dto.EnderecoDTO;
import com.ecoprinting.app.service.interfaces.IEnderecoService;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {
    private IEnderecoService enderecoService;

    @Autowired
    public EnderecoController( IEnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
    
    @PutMapping("/editar")
    public ResponseEntity<Map<String, String>> editarUsuario(@RequestBody EnderecoDTO enderecoDTO) {
        Map<String, String> response = new HashMap<>();

        try {
            this.enderecoService.editarEndereco(enderecoDTO);

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