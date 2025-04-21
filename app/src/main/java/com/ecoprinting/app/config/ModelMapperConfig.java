package com.ecoprinting.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecoprinting.app.config.mapping.dto.*;
import com.ecoprinting.app.config.mapping.entity.*;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new UsuarioDTOMap());
        modelMapper.addMappings(new UsuarioEntityMap());

        modelMapper.addMappings(new EnderecoUsuarioDTOMap());
        modelMapper.addMappings(new EnderecoEntityMap());

        return modelMapper;
    }
}