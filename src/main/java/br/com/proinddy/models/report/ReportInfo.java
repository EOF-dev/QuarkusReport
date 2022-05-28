package br.com.proinddy.models.report;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportInfo {
    private Long id;
    private String nome;
    private Long grupoId;
    private String configString;
    private ReportConfig config;

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

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public String getConfigString() {
        return configString;
    }

    public void setConfigString(String configString) {
        this.configString = configString;
    }

    public ReportConfig getConfig(){
        return config;
    }

    public void setConfig(String config){
        try{
            this.config = new ObjectMapper().readValue(config, ReportConfig.class);
        }catch (Exception e){
            this.config = new ReportConfig();
        }
    }
}
