package com.agencia.viagem.agencia_viagem_api.model;

public class Destino {
    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private double precoMedio;
    private int numeroAvaliacoes;
    private double mediaAvaliacao;
    
    public Destino() {
    }
    
    public Destino(Long id, String nome, String localizacao, String descricao, double precoMedio) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.precoMedio = precoMedio;
        this.numeroAvaliacoes = 0;
        this.mediaAvaliacao = 0.0;
    }
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getPrecoMedio() {
        return precoMedio;
    }
    
    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }
    
    public int getNumeroAvaliacoes() {
        return numeroAvaliacoes;
    }
    
    public void setNumeroAvaliacoes(int numeroAvaliacoes) {
        this.numeroAvaliacoes = numeroAvaliacoes;
    }
    
    public double getMediaAvaliacao() {
        return mediaAvaliacao;
    }
    
    public void setMediaAvaliacao(double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }
}