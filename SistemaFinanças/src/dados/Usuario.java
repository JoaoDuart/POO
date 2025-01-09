package dados;

public class Usuario {
    private String nome;
    private String telefone;
    private String cpf;
    private Endereco endereco;
    private Data dataNascimento;
    private String senha;
    private TipoUsuario tipousuario;

    public Usuario(String nome, String telefone, String cpf, Endereco endereco, Data dataNascimento, String senha,
            TipoUsuario tipousuario) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.tipousuario = tipousuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipoUsuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String toString() {
        return "Nome: " + nome +
                ", Telefone: " + telefone +
                ", Cpf: " + cpf;
    }
}