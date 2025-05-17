package com.ecoprinting.app.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoprinting.app.models.entity.DoacaoEntity;
import com.ecoprinting.app.models.entity.UsuarioEntity;

public interface IDoacaoRepository extends JpaRepository<DoacaoEntity, Integer> {
    DoacaoEntity findByIdDoacaoAndUsuario(int idDoacao, UsuarioEntity usuarioEntity);
}
