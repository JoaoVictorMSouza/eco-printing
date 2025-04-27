package com.ecoprinting.app.models.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DoacaoDTO {
    private int idDoacao;
    private Double qtdDoacao;
    private int idUsuario;
    private Date dataDoacao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDoacaoFront;

    public int getIdDoacao() {
        return idDoacao;
    }
    public void setIdDoacao(int idDoacao) {
        this.idDoacao = idDoacao;
    }

    public Double getQtdDoacao() {
        return qtdDoacao;
    }
    public void setQtdDoacao(Double qtdDoacao) {
        this.qtdDoacao = qtdDoacao;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }
    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
        this.dataDoacaoFront = dataDoacao;
    }

    public Date getDataDoacaoFront() {
        return dataDoacaoFront;
    }
}
