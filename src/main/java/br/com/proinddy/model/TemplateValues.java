package br.com.proinddy.model;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.List;

public class TemplateValues {
    private String col_nome;
    private Long col_codigo;
    private String col_futuro;

    public TemplateValues(String col_nome, Long col_codigo, String col_futuro) {
        this.col_nome = col_nome;
        this.col_codigo = col_codigo;
        this.col_futuro = col_futuro;
    }

    public String getCol_nome() {
        return col_nome;
    }

    public Long getCol_codigo() {
        return col_codigo;
    }

    public String getCol_futuro() {
        return col_futuro;
    }
}
