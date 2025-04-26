package com.ecoprinting.app.config.mapping.entity;

import org.modelmapper.PropertyMap;

import com.ecoprinting.app.models.dto.EnderecoDTO;
import com.ecoprinting.app.models.entity.EnderecoEntity;

public class EnderecoEntityMap extends PropertyMap<EnderecoDTO, EnderecoEntity> {
    @Override
    protected void configure() {
        map().setDsCep(source.getCep());
        map().setDsLogradouro(source.getLogradouro());
        map().setDsNumero(source.getNumero());
        map().setDsComplemento(source.getComplemento());
        map().setDsBairro(source.getBairro());
        map().setDsCidade(source.getCidade());
        map().setDsUf(source.getUf());
    }
}