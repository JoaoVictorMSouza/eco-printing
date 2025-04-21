package com.ecoprinting.app.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String dsNome;
    private String dsCpf;
    private String dsEmail;
    private String dsSenha;
    private boolean isAtivo;
    private int idGrupo;
    private Date dtNascimento;
    private int idGenero;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnderecoEntity> enderecos;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DoacaoEntity> doacoes;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getDsNome() {
        return dsNome;
    }
    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsCpf() {
        return dsCpf;
    }
    public void setDsCpf(String dsCpf) {
        this.dsCpf = dsCpf;
    }

    public String getDsEmail() {
        return dsEmail;
    }
    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsSenha() {
        return dsSenha;
    }
    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public boolean isAtivo() {
        return isAtivo;
    }
    public void setAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }
    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public int getIdGenero() {
        return idGenero;
    }
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public List<EnderecoEntity> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<EnderecoEntity> enderecos) {
        this.enderecos = enderecos;
    }  
}
