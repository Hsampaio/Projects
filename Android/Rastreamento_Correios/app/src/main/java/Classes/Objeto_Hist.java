package Classes;

import java.util.Date;

/**
 * Created by Helton on 28/10/2016.
 */
public class Objeto_Hist {
    private int Codigo;
    private int Codigo_objeto;
    private String Datahora = "";
    private String Descricao= "";
    private String Situacao= "";
    private String Local= "";



    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public int getCodigo_objeto() {
        return Codigo_objeto;
    }

    public void setCodigo_objeto(int codigo_objeto) {
        Codigo_objeto = codigo_objeto;
    }

    public String getDatahora() {
        return Datahora;
    }

    public void setDatahora(String datahora) {
        Datahora = datahora;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String situacao) {
        Situacao = situacao;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }
}
