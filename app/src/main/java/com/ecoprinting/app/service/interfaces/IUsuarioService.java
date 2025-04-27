package com.ecoprinting.app.service.interfaces;

import com.ecoprinting.app.models.dto.UsuarioDTO;

public interface IUsuarioService {
    void criarUsuario(UsuarioDTO usuarioDTO);
    boolean verificarExistenciaEmail(String email);
    UsuarioDTO buscarUsuarioPorId(int idUsuario);
    void editarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO consultarUsuarioByCpf(String cpf);
}
