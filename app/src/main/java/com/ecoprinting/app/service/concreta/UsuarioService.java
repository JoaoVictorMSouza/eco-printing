package com.ecoprinting.app.service.concreta;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecoprinting.app.enums.UsuarioGrupoEnum;
import com.ecoprinting.app.exception.models.UsuarioException;
import com.ecoprinting.app.models.dto.UsuarioDTO;
import com.ecoprinting.app.models.entity.EnderecoEntity;
import com.ecoprinting.app.models.entity.UsuarioEntity;
import com.ecoprinting.app.repository.interfaces.IUsuarioRepository;
import com.ecoprinting.app.service.interfaces.IUsuarioService;
import com.ecoprinting.app.utils.CpfUtils;

@Service
public class UsuarioService implements IUsuarioService {
    private IUsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder senhaEncoder;

    @Autowired
    public UsuarioService(IUsuarioRepository iUsuarioRepository, ModelMapper modelMapper, PasswordEncoder senhaEncoder) {
        this.usuarioRepository = iUsuarioRepository;
        this.modelMapper = modelMapper;
        this.senhaEncoder = senhaEncoder;
    }

    public void criarUsuario(UsuarioDTO usuarioDTO) {
        usuarioDTO.setGeneroEnum();

        this.validarSenha(usuarioDTO.getSenha(), usuarioDTO.getConfirmacaoSenha());

        if (this.verificarExistenciaEmail(usuarioDTO.getEmail())) {
            throw new UsuarioException("E-mail já cadastrado");
        }

        if (!CpfUtils.validarCPF(usuarioDTO.getCpf())) {
            throw new UsuarioException("CPF inválido");
        }

        if (usuarioDTO.getDataNascimento().after(new Date())) {
            throw new UsuarioException("Data de nascimento inválida");
        }

        // Encripta a senha
        String senhaEncriptada = senhaEncoder.encode(usuarioDTO.getSenha());
        usuarioDTO.setSenha(senhaEncriptada);

        // Mapeia o DTO para a entidade
        UsuarioEntity usuarioEntity = this.mapearUsuarioDTOParaUsuarioEntity(usuarioDTO);

        UsuarioGrupoEnum usuarioGrupoEnum = UsuarioGrupoEnum.COMUM;
        usuarioEntity.setIdGrupo(usuarioGrupoEnum.getId());

        usuarioEntity.setAtivo(true);

        for (EnderecoEntity endereco : usuarioEntity.getEnderecos()) {
           endereco.setUsuario(usuarioEntity);
           endereco.setIdEndereco(null);
        }

        UsuarioEntity usuarioCadastrado = usuarioRepository.save(usuarioEntity);
    }

    private UsuarioDTO mapearUsuarioEntityParaUsuarioDTO(UsuarioEntity usuarioEntity) {
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }

    private UsuarioEntity mapearUsuarioDTOParaUsuarioEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, UsuarioEntity.class);
    }

    public boolean verificarExistenciaEmail(String email) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByDsEmail(email);

        if (usuarioEntity != null) {
            return true;
        };

        return false;
    }

    private void validarSenha(String senha, String confirmacaoSenha) {
        if (!senha.equals(confirmacaoSenha)) {
            throw new UsuarioException("As senhas não conferem");
        }
    }

    public UsuarioDTO buscarUsuarioPorId(int id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(id);

        if (usuarioEntity == null) {
            throw new UsuarioException("Usuário não encontrado");
        }

        return this.mapearUsuarioEntityParaUsuarioDTO(usuarioEntity);
    }

    public void editarUsuario(UsuarioDTO usuarioDTO){
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(usuarioDTO.getId());

        if (usuarioEntity == null) {
            throw new UsuarioException("Usuário não encontrado");
        }

        if ((usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().trim().isEmpty()) || 
            (usuarioDTO.getConfirmacaoSenha() != null && !usuarioDTO.getConfirmacaoSenha().trim().isEmpty())) {

            this.validarSenha(usuarioDTO.getSenha(), usuarioDTO.getConfirmacaoSenha());

            // Encripta a senha
            String senhaEncriptada = senhaEncoder.encode(usuarioDTO.getSenha());
            usuarioDTO.setSenha(senhaEncriptada);
            usuarioEntity.setDsSenha(usuarioDTO.getSenha());
        }

        if (usuarioDTO.getNome() != null && 
            !usuarioDTO.getNome().trim().isEmpty() && 
            !usuarioEntity.getDsNome().equals(usuarioDTO.getNome())) {

            usuarioEntity.setDsNome(usuarioDTO.getNome()); 
        }

        if (usuarioDTO.getIdGeneroUsuario() > 0 && 
            usuarioDTO.getIdGeneroUsuario() != usuarioEntity.getIdGenero()) {

            usuarioEntity.setIdGenero(usuarioDTO.getIdGeneroUsuario());     
        }

        if (usuarioDTO.getDataNascimento() != null && 
            !usuarioDTO.getDataNascimento().equals(usuarioEntity.getDtNascimento())) {

            usuarioEntity.setDtNascimento(usuarioDTO.getDataNascimento());     
    }

        usuarioRepository.save(usuarioEntity);
    }
}