package com.ecoprinting.app.config.mapping.entity;

import org.modelmapper.PropertyMap;

import com.ecoprinting.app.models.dto.UsuarioDTO;
import com.ecoprinting.app.models.entity.UsuarioEntity;

public class UsuarioEntityMap extends PropertyMap<UsuarioDTO, UsuarioEntity> {
    @Override
    protected void configure() {
        map().setDsNome(source.getNome());
        map().setDsCpf(source.getCpf());
        map().setDsEmail(source.getEmail());
        map().setDsSenha(source.getSenha());
        map().setDtNascimento(source.getDataNascimento());
        map().setIdGenero(source.getIdGeneroUsuario());
    }
}