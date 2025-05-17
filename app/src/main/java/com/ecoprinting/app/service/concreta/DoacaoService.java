package com.ecoprinting.app.service.concreta;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecoprinting.app.exception.models.DoacaoException;
import com.ecoprinting.app.models.dto.DoacaoDTO;
import com.ecoprinting.app.models.entity.DoacaoEntity;
import com.ecoprinting.app.models.entity.UsuarioEntity;
import com.ecoprinting.app.repository.interfaces.IDoacaoRepository;
import com.ecoprinting.app.repository.interfaces.IUsuarioRepository;
import com.ecoprinting.app.service.interfaces.IDoacaoService;

@Service
public class DoacaoService implements IDoacaoService {
    private IDoacaoRepository doacaoRepository;
    private IUsuarioRepository usuarioRepository;

    @Autowired
    public DoacaoService(IDoacaoRepository iDoacaoRepository, IUsuarioRepository iUsuarioRepository) {
        this.doacaoRepository = iDoacaoRepository;
        this.usuarioRepository = iUsuarioRepository;
    }

    public void efetuarDoacao(DoacaoDTO doacaoDTO) {
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(doacaoDTO.getIdUsuario());

        if (usuario == null) {
            throw new DoacaoException("Usuário não encontrado.");
        }

        if (doacaoDTO.getQtdDoacao() <= 0) {
            throw new DoacaoException("Quantidade de doação inválida.");
        }

        DoacaoEntity doacaoEntity = new DoacaoEntity();
        doacaoEntity.setUsuario(usuario);
        doacaoEntity.setQtdDoacao(doacaoDTO.getQtdDoacao());
        doacaoEntity.setDhDoacao(new Date());

        doacaoRepository.save(doacaoEntity);
    }

    public void deletarDoacao(DoacaoDTO doacaoDTO) {
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(doacaoDTO.getIdUsuario());
        if (usuario == null) {
            throw new DoacaoException("Usuário não encontrado.");
        }

        DoacaoEntity doacao = doacaoRepository.findByIdDoacaoAndUsuario(doacaoDTO.getIdDoacao(), usuario);

        if (doacao == null) {
            throw new DoacaoException("Doação não encontrada.");
        }

        doacaoRepository.delete(doacao);
    }
}
