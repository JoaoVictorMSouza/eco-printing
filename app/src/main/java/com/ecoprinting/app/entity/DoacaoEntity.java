package com.ecoprinting.app.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_doacao")
public class DoacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoacao;
    private Double QtdDoacao;
    private Date dtDoacao;
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private UsuarioEntity usuario;

    public Integer getIdDoacao() {
        return idDoacao;
    }
    public void setIdDoacao(Integer idDoacao) {
        this.idDoacao = idDoacao;
    }

    public Double getQtdDoacao() {
        return QtdDoacao;
    }
    public void setQtdDoacao(Double qtdDoacao) {
        QtdDoacao = qtdDoacao;
    }
    
    public Date getDtDoacao() {
        return dtDoacao;
    }
    public void setDtDoacao(Date dtDoacao) {
        this.dtDoacao = dtDoacao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }    
}
