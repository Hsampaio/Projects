package Classes;

import java.util.Date;

/**
 * Created by Helton on 20/07/2016.
 */
public class Objeto {
    private int Codigo_id;
    private int Codigo_pessoa;
    private String Codigo_rast;
    private Date DataCadastro;
    private String Ativo;
    private String Notificar;
    private String Descricao;


    public Objeto(int iCodigo_id, int iCodigo_pessoa, String iCodigo_rast, Date dDataCadastro, String sAtivo, String sNotificar, String sDescricao){
        Codigo_id = iCodigo_id;
        Codigo_pessoa = iCodigo_pessoa;
        Codigo_rast = iCodigo_rast;
        DataCadastro = dDataCadastro;
        Ativo = sAtivo;
        Notificar = sNotificar;
        Descricao = sDescricao;
    }


    public int getCodigo_id() {
        return Codigo_id;
    }

    public void setCodigo_id(int codigo_id) {
        Codigo_id = codigo_id;
    }

    public int getCodigo_pessoa() {
        return Codigo_pessoa;
    }

    public void setCodigo_pessoa(int codigo_pessoa) {
        Codigo_pessoa = codigo_pessoa;
    }

    public String getCodigo_rast() {
        return Codigo_rast;
    }

    public void setCodigo_rast(String codigo_rast) {
        Codigo_rast = codigo_rast;
    }

    public Date getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        DataCadastro = dataCadastro;
    }

    public String getAtivo() {
        return Ativo;
    }

    public void setAtivo(String ativo) {
        Ativo = ativo;
    }

    public String getNotificar() {
        return Notificar;
    }

    public void setNotificar(String notificar) {
        Notificar = notificar;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}