package br.com.proinddy.models.report;

public class Config {
    private String nome;
    private String srcImagem;
    private String fileSQL;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSrcImagem() {
        return srcImagem;
    }

    public void setSrcImagem(String srcImagem) {
        this.srcImagem = srcImagem;
    }

    public String getFileSQL() {
        return fileSQL;
    }

    public void setFileSQL(String fileSQL) {
        this.fileSQL = fileSQL;
    }
}
