package com.ecoprinting.app.service.interfaces;

import com.ecoprinting.app.dto.UsuarioDTO;

public interface IUsuarioService {
    void criarUsuario(UsuarioDTO usuarioDTO);
    boolean verificarExistenciaEmail(String email);
}
