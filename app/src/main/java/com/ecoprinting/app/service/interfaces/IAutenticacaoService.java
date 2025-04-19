package com.ecoprinting.app.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.ecoprinting.app.dto.UsuarioLogadoDTO;

public interface IAutenticacaoService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UsuarioLogadoDTO retornarUsuarioLogado();
}
