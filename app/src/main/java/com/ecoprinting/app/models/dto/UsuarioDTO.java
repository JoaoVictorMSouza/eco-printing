package com.ecoprinting.app.models.dto;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ecoprinting.app.enums.GeneroEnum;

import java.util.ArrayList;
import java.util.Date;

public class UsuarioDTO {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String confirmacaoSenha;
    private int idGeneroUsuario;
    private GeneroEnum generoEnum;
    private List<EnderecoDTO> enderecos;
    private List<DoacaoDTO> doacoes;
    private Date dataNascimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimentoFront;

    public UsuarioDTO() {
        this.enderecos = new ArrayList<>();
        this.doacoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }
    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public int getIdGeneroUsuario() {
        return idGeneroUsuario;
    }
    public void setIdGeneroUsuario(int idGeneroUsuario) {
        this.idGeneroUsuario = idGeneroUsuario;
        this.setGeneroEnumById(idGeneroUsuario);
    }

    public GeneroEnum getGeneroEnum() {
        return generoEnum;
    }
    private void setGeneroEnumById(int id) {
        this.generoEnum = GeneroEnum.fromId(this.idGeneroUsuario);;
    }
    public void setGeneroEnum() {
        this.generoEnum = GeneroEnum.fromId(this.idGeneroUsuario);
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public List<DoacaoDTO> getDoacoes() {
        return doacoes;
    }
    public void setDoacoes(List<DoacaoDTO> doacoes) {
        this.doacoes = doacoes;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.dataNascimentoFront = dataNascimento;
    }

    public Date getDataNascimentoFront() {
        return dataNascimentoFront;
    }
}
