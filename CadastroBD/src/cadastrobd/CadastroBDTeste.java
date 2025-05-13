package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaDAO pfDao = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDao = new PessoaJuridicaDAO();
        try
        {
            String nome = "João";
            String logradouro = "Rua Qualquer";
            String cidade = "Cidade";
            String estado = "Estado";
            String telefone = "999999999";
            String email = "email@exemplo.com";
            String cpf = "999999999";

            PessoaFisica Pf = criarPessoaFisica(scanner, nome, logradouro, cidade, estado, telefone, email, cpf);
            pfDao.incluir(Pf);
            
            nome = "João Lucas";
            logradouro = "Rua Qualquer";
            cidade = "Cidade";
            estado = "Estado";
            telefone = "999999999";
            email = "email@exemplo.com";
            cpf = "999999999";
            
            Pf = criarPessoaFisica(scanner, nome, logradouro, cidade, estado, telefone, email, cpf, Pf.getId());
            pfDao.alterar(Pf);
            
            exibirCabecalho("F");
            for (PessoaFisica pf : pfDao.getPessoas()) 
            {
                System.out.printf("%-5d %-20s %-20s %-15s %-10s %-15s %-25s %-14s%n",
                    pf.getId(),
                    pf.getNome(),
                    pf.getLogradouro(),
                    pf.getCidade(),
                    pf.getEstado(),
                    pf.getTelefone(),
                    pf.getEmail(),
                    pf.getCpf());
            }
            
            pfDao.excluir(Pf.getId());
            
            nome = "Empresa X";
            logradouro = "Rua Qualquer";
            cidade = "Cidade";
            estado = "Estado";
            telefone = "999999999";
            email = "emailempresa@exemplo.com";
            String cnpj = "999999999";

            PessoaJuridica Pj = criarPessoaJuridica(scanner, nome, logradouro, cidade, estado, telefone, email, cnpj);
            pjDao.incluir(Pj);
            
            nome = "Empresa Y";
            logradouro = "Rua Qualquer";
            cidade = "Cidade";
            estado = "Estado";
            telefone = "999999999";
            email = "emailempresa@exemplo.com";
            cnpj = "999999999";
            
            Pj = criarPessoaJuridica(scanner, nome, logradouro, cidade, estado, telefone, email, cnpj, Pj.getId());
            pjDao.alterar(Pj);
            
            exibirCabecalho("J");
            for (PessoaJuridica pj : pjDao.getPessoas()) 
            {
                System.out.printf("%-5d %-20s %-20s %-15s %-10s %-15s %-25s %-14s%n",
                    pj.getId(),
                    pj.getNome(),
                    pj.getLogradouro(),
                    pj.getCidade(),
                    pj.getEstado(),
                    pj.getTelefone(),
                    pj.getEmail(),
                    pj.getCnpj());
            }
            
            pjDao.excluir(Pj.getId());
        } 
        catch (Exception e) 
        {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void exibirCabecalho(String tipo) 
    {
        System.out.printf("%-5s %-20s %-20s %-15s %-10s %-15s %-25s %-14s%n", 
            "ID", "Nome", "Logradouro", "Cidade", "Estado", "Telefone", "Email", tipo.equals("F") ? "CPF" : "CNPJ");
        System.out.println("=".repeat(130));
    }

    public static void exibirPessoasFisicas(List<PessoaFisica> lista) 
    {
        for (PessoaFisica pf : lista) {
            System.out.printf("%-5d %-20s %-20s %-15s %-10s %-15s %-25s %-14s%n",
                pf.getId(),
                pf.getNome(),
                pf.getLogradouro(),
                pf.getCidade(),
                pf.getEstado(),
                pf.getTelefone(),
                pf.getEmail(),
                pf.getCpf());
        }
    }

    public static void exibirPessoasJuridicas(List<PessoaJuridica> lista) 
    {
        for (PessoaJuridica pj : lista) {
            System.out.printf("%-5d %-20s %-20s %-15s %-10s %-15s %-25s %-14s%n",
                pj.getId(),
                pj.getNome(),
                pj.getLogradouro(),
                pj.getCidade(),
                pj.getEstado(),
                pj.getTelefone(),
                pj.getEmail(),
                pj.getCnpj());
        }
    }
    
    public static PessoaJuridica criarPessoaJuridica(Scanner scanner, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) 
    {
        return new PessoaJuridica(nome, logradouro, cidade, estado, telefone, email, cnpj);
    }
    
    public static PessoaFisica criarPessoaFisica(Scanner scanner, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) 
    {
        return new PessoaFisica(nome, logradouro, cidade, estado, telefone, email, cpf);
    }
    
    public static PessoaJuridica criarPessoaJuridica(Scanner scanner, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj, int id) 
    {
        return new PessoaJuridica(id, nome, logradouro, cidade, estado, telefone, email, cnpj);
    }
    
    public static PessoaFisica criarPessoaFisica(Scanner scanner, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf, int id) 
    {
        return new PessoaFisica(id, nome, logradouro, cidade, estado, telefone, email, cpf);
    }
}