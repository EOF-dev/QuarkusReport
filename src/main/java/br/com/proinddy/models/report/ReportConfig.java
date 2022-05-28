package br.com.proinddy.models.report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportConfig {
    @JsonProperty("configuracao")
    private Config configuracao;

    @JsonProperty("parametros")
    private Parametros parametros;

    @JsonProperty("tela")
    private Tela tela;

    public Config getConfiguracoes() {
        return configuracao;
    }

    public void setConfiguracoes(Config configuracoes) {
        this.configuracao = configuracoes;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }

    public boolean hasParametros(){
        return this.parametros.getParametro() != null;
    }
}
