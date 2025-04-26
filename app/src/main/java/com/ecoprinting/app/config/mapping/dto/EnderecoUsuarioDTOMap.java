package com.ecoprinting.app.config.mapping.dto;

import org.modelmapper.PropertyMap;

import com.ecoprinting.app.models.dto.EnderecoDTO;
import com.ecoprinting.app.models.entity.EnderecoEntity;

public class EnderecoUsuarioDTOMap extends PropertyMap<EnderecoEntity, EnderecoDTO> {
    @Override
    protected void configure() {
        map().setCep(source.getDsCep());
        map().setLogradouro(source.getDsLogradouro());
        map().setNumero(source.getDsNumero());
        map().setComplemento(source.getDsComplemento());
        map().setBairro(source.getDsBairro());
        map().setCidade(source.getDsCidade());
        map().setUf(source.getDsUf());
        map().setIdUsuario(source.getUsuario().getIdUsuario());
    }
}