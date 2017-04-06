package Classes;

import java.util.Date;

/**
 * Created by Helton on 28/10/2016.
 */
public class Pessoa {

    private int Codigo;
    private String Gcm;
    private Date DataCadastro;
    private String Ativo;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getGcm() {
        return Gcm;
    }

    public void setGcm(String gcm) {
        Gcm = gcm;
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
}
