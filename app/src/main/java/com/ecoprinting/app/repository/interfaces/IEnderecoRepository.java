package com.ecoprinting.app.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoprinting.app.models.entity.EnderecoEntity;
import com.ecoprinting.app.models.entity.UsuarioEntity;

public interface IEnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {
    EnderecoEntity findByIdEnderecoAndUsuario(int idEndereco, UsuarioEntity usuarioEntity);
}