package com.ecoprinting.app.config.mapping.dto;

import org.modelmapper.PropertyMap;
import com.ecoprinting.app.models.dto.DoacaoDTO;
import com.ecoprinting.app.models.entity.DoacaoEntity;

public class DoacaoDTOMap extends PropertyMap<DoacaoEntity, DoacaoDTO> {
    @Override
    protected void configure() {
        map().setIdDoacao(source.getIdDoacao().intValue());
        map().setDataDoacao(source.getDhDoacao());
        map().setQtdDoacao(source.getQtdDoacao());
        map().setIdUsuario(source.getUsuario().getIdUsuario());
    }
}