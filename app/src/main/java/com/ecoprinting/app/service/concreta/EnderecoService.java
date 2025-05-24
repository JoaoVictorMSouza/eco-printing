package com.ecoprinting.app.service.concreta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecoprinting.app.exception.models.EnderecoException;
import com.ecoprinting.app.models.dto.EnderecoDTO;
import com.ecoprinting.app.models.entity.EnderecoEntity;
import com.ecoprinting.app.models.entity.UsuarioEntity;
import com.ecoprinting.app.repository.interfaces.IEnderecoRepository;
import com.ecoprinting.app.repository.interfaces.IUsuarioRepository;
import com.ecoprinting.app.service.interfaces.IEnderecoService;

@Service
public class EnderecoService implements IEnderecoService {
    private IEnderecoRepository enderecoRepository;
    private IUsuarioRepository usuarioRepository;

    @Autowired
    public EnderecoService(IEnderecoRepository iEnderecoRepository, IUsuarioRepository iUsuarioRepository) {
        this.enderecoRepository = iEnderecoRepository;
        this.usuarioRepository = iUsuarioRepository;
    }

    public void editarEndereco(EnderecoDTO enderecoDTO){
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(enderecoDTO.getIdUsuario());

        if (usuarioEntity == null) {
            throw new EnderecoException("Usuário não encontrado");
        }

        EnderecoEntity enderecoEntity = enderecoRepository.findByIdEnderecoAndUsuario(enderecoDTO.getIdEndereco(), usuarioEntity);

        if (enderecoEntity == null) {
            throw new EnderecoException("Endereço não encontrado");
        }

        boolean isAlterado = false;

        if (enderecoDTO.getCep() != null && 
            !enderecoDTO.getCep().trim().isEmpty() && 
            !enderecoEntity.getDsCep().equals(enderecoDTO.getCep())) {

            enderecoEntity.setDsCep(enderecoDTO.getCep()); 
            enderecoEntity.setDsLogradouro(enderecoDTO.getLogradouro()); 
            enderecoEntity.setDsNumero(enderecoDTO.getNumero());
            enderecoEntity.setDsBairro(enderecoDTO.getBairro()); 
            enderecoEntity.setDsCidade(enderecoDTO.getCidade()); 
            enderecoEntity.setDsUf(enderecoDTO.getUf()); 

            isAlterado = true;
        }

        if (enderecoDTO.getComplemento() != null && 
            !enderecoDTO.getComplemento().trim().isEmpty() && 
            !enderecoEntity.getDsComplemento().equals(enderecoDTO.getComplemento())) {

            enderecoEntity.setDsComplemento(enderecoDTO.getComplemento()); 
            isAlterado = true;
        }

        if (isAlterado) {
           enderecoRepository.save(enderecoEntity);
        }
    }
}