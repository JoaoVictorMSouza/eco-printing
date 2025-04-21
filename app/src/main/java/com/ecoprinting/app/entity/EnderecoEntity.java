package com.ecoprinting.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;
    private String dsCep;
    private String dsLogradouro;
    private String dsNumero;
    private String dsComplemento;
    private String dsBairro;
    private String dsCidade;
    private String dsUf;
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private UsuarioEntity usuario;

    public Integer getIdEndereco() {
        return idEndereco;
    }
    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getDsCep() {
        return dsCep;
    }
    public void setDsCep(String dsCep) {
        this.dsCep = dsCep;
    }

    public String getDsLogradouro() {
        return dsLogradouro;
    }
    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    public String getDsNumero() {
        return dsNumero;
    }
    public void setDsNumero(String dsNumero) {
        this.dsNumero = dsNumero;
    }

    public String getDsComplemento() {
        return dsComplemento;
    }
    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
    }

    public String getDsBairro() {
        return dsBairro;
    }
    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getDsCidade() {
        return dsCidade;
    }
    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    public String getDsUf() {
        return dsUf;
    }
    public void setDsUf(String dsUf) {
        this.dsUf = dsUf;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }    
}
