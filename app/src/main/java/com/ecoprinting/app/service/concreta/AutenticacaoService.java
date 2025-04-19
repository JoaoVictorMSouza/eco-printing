package com.ecoprinting.app.service.concreta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ecoprinting.app.dto.UsuarioLogadoDTO;
import com.ecoprinting.app.entity.UsuarioEntity;
import com.ecoprinting.app.repository.interfaces.IUsuarioRepository;
import com.ecoprinting.app.service.interfaces.IAutenticacaoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Service
public class AutenticacaoService implements IAutenticacaoService {
    private IUsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacaoService(IUsuarioRepository iUsuarioRepository) {
        this.usuarioRepository = iUsuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuario = this.usuarioRepository.findByDsEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
        }

        return User.builder()
                .username(usuario.getDsEmail())
                .password(usuario.getDsSenha())
                .build();
    }

    public UsuarioLogadoDTO retornarUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails usuario = (UserDetails) authentication.getPrincipal();

            UsuarioEntity usuarioEntity = this.usuarioRepository.findByDsEmail(usuario.getUsername());
            
            return new UsuarioLogadoDTO(usuario, usuarioEntity);
        }

        return null;
    }
}