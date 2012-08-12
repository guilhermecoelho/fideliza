package TO;


public class UsuarioPontosTO {
    public int NumeroCupom;
    public String DataCupom;
    public Float Valor;
    public String Usuario;
    public int Status;
    public String cpf;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setDataCupom(String DataCupom) {
        this.DataCupom = DataCupom;
    }

    public void setNumeroCupom(int NumeroCupom) {
        this.NumeroCupom = NumeroCupom;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public void setValor(Float Valor) {
        this.Valor = Valor;
    }

    public String getDataCupom() {
        return DataCupom;
    }

    public int getNumeroCupom() {
        return NumeroCupom;
    }

    public int getStatus() {
        return Status;
    }

    public String getUsuario() {
        return Usuario;
    }

    public Float getValor() {
        return Valor;
    }

}
