package cadastrobd.model;

public class Pessoa
{
    private int id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;

    public Pessoa() {}

    public Pessoa(String nome, String logradouro, String cidade, String estado, String telefone, String email) 
    {
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }
    
    public Pessoa(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) 
    {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    public void exibir() 
    {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Logradouro: " + logradouro);
        System.out.println("Cidade: " + cidade);
        System.out.println("Estado: " + estado);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setID(int newID)
    {
        id = newID;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public String getLogradouro()
    {
        return logradouro;
    }
    
    public String getCidade()
    {
        return cidade;
    }
    
    public String getEstado()
    {
        return estado;
    }
    
    public String getTelefone()
    {
        return telefone;
    }
    
    public String getEmail()
    {
        return email;
    }
}