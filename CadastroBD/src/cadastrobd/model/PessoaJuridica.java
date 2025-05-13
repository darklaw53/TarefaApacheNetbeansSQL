package cadastrobd.model;

public class PessoaJuridica extends Pessoa 
{
    private String cnpj;

    public PessoaJuridica() 
    {
        super(); 
    }

    public PessoaJuridica(String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) 
    {
        super(nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) 
    {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    public String getCnpj()
    {
        return cnpj;
    }
    
    @Override
    public void exibir() 
    {
        super.exibir(); 
        System.out.println("CNPJ: " + cnpj);
    }
    
    @Override
    public void setID(int newID)
    {
        super.setID(newID);
    }
    
    @Override
    public int getId()
    {
        return super.getId();
    }
}