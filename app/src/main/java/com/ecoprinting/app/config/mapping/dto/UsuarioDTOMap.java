package com.ecoprinting.app.config.mapping.dto;

import org.modelmapper.PropertyMap;

import com.ecoprinting.app.models.dto.UsuarioDTO;
import com.ecoprinting.app.models.entity.UsuarioEntity;

public class UsuarioDTOMap extends PropertyMap<UsuarioEntity, UsuarioDTO> {
    @Override
    protected void configure() {
        map().setId(source.getIdUsuario().intValue());
        map().setNome(source.getDsNome());
        map().setCpf(source.getDsCpf());
        map().setEmail(source.getDsEmail());
        map().setDataNascimento(source.getDtNascimento());
        map().setIdGeneroUsuario(source.getIdGenero());
        map().setSenha("");
        map().setConfirmacaoSenha("");
    }
}