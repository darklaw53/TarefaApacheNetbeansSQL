package cadastrobd.model;

public class PessoaFisica extends Pessoa 
{
    private String cpf;

    public PessoaFisica() 
    {
        super(); 
    }

    public PessoaFisica(String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) 
    {
        super(nome, logradouro, cidade, estado, telefone, email); 
        this.cpf = cpf;
    }

    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) 
    {
        super(id, nome, logradouro, cidade, estado, telefone, email); 
        this.cpf = cpf;
    }
    
    public String getCpf()
    {
        return cpf;
    }
    
    @Override
    public void exibir() 
    {
        super.exibir(); 
        System.out.println("CPF: " + cpf);
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