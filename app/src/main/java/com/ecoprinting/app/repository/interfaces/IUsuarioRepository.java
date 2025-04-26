package com.ecoprinting.app.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoprinting.app.models.entity.UsuarioEntity;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findByDsEmail(String email);
    UsuarioEntity findByIdUsuario(int id);
}