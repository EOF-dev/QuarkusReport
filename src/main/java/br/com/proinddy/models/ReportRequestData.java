package br.com.proinddy.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReportRequestData {
    @NotBlank
    @NotNull
    private String relatorioName;

    @NotBlank
    @NotNull

    private String base;

    @NotNull
    @NotBlank
    private String empresa;


    public ReportRequestData() {
    }

    public ReportRequestData(String relatorioName, String base, String empresa) {
        this.relatorioName = relatorioName;
        this.base = base;
        this.empresa = empresa;
    }

    public String getRelatorioName() {
        return relatorioName;
    }

    public String getBase() {
        return base;
    }

    public String getEmpresa() {
        return empresa;
    }
}
