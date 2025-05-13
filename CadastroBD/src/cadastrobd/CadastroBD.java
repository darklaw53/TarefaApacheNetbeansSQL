package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CadastroBD {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaDAO pfDao = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDao = new PessoaJuridicaDAO();
        int opcao = 0;

        do 
        {
            try 
            {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Incluir");
                System.out.println("2 - Alterar");
                System.out.println("3 - Excluir");
                System.out.println("4 - Exibir por ID");
                System.out.println("5 - Exibir todos");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) 
                {
                    case 1:
                    {
                        System.out.print("Tipo (F para Física / J para Jurídica): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        
                        if (tipo.equals("F")) 
                        {    
                            PessoaFisica pf = inputPessoasFisicas(scanner);
                            pfDao.incluir(pf);
                            
                            exibirCabecalho("F");
                            exibirPessoasFisicas(Collections.singletonList(pf));
                        } 
                        else if (tipo.equals("J")) 
                        {
                            PessoaJuridica pj = inputPessoasJuridicas(scanner);
                            pjDao.incluir(pj);
                            
                            exibirCabecalho("J");
                            exibirPessoasJuridicas(Collections.singletonList(pj));
                        }
                        break;
                    }
                    case 2: 
                    {
                        System.out.print("Tipo (F para Física / J para Jurídica): ");
                        String tipo = scanner.nextLine().toUpperCase();

                        if (tipo.equals("F")) 
                        {
                            System.out.print("ID da pessoa a ser alterada: ");
                            int targetId = Integer.parseInt(scanner.nextLine());

                            PessoaFisica pessoaCheckF = pfDao.getPessoa(targetId); 
                            if (pessoaCheckF != null) 
                            {
                                exibirCabecalho("F");
                                exibirPessoasFisicas(Collections.singletonList(pessoaCheckF));
                                
                                PessoaFisica pf = inputPessoasFisicas(scanner);
                                pf.setID(targetId);
                                pfDao.alterar(pf);
                               
                                exibirCabecalho("F");
                                exibirPessoasFisicas(Collections.singletonList(pf));
                            }
                            else 
                            {
                                System.out.println("Pessoa Física não encontrada.");
                            }

                        } 
                        else if (tipo.equals("J")) 
                        {
                            System.out.print("ID da pessoa a ser alterada: ");
                            int targetId = Integer.parseInt(scanner.nextLine());

                            PessoaJuridica pessoaCheckJ = pjDao.getPessoa(targetId);
                            if (pessoaCheckJ != null) 
                            {
                                exibirCabecalho("J");
                                exibirPessoasJuridicas(Collections.singletonList(pessoaCheckJ));
                                
                                PessoaJuridica pj = inputPessoasJuridicas(scanner);
                                pj.setID(targetId);
                                pjDao.alterar(pj);
                                
                                exibirCabecalho("J");
                                exibirPessoasJuridicas(Collections.singletonList(pj));
                            }
                            else 
                            {
                                System.out.println("Pessoa Jurídica não encontrada.");
                            }
                        }
                        break;
                    }

                    case 3:
                    {
                        System.out.print("Tipo (F para Física / J para Jurídica): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        if (tipo.equals("F")) 
                        {
                            System.out.print("ID: ");
                            int idExc = Integer.parseInt(scanner.nextLine());
                            PessoaFisica pessoaCheckF = pfDao.getPessoa(idExc);
                            
                            if (pessoaCheckF != null)
                            {
                                exibirCabecalho("F");
                                exibirPessoasFisicas(Collections.singletonList(pessoaCheckF));

                                System.out.printf("Tem certeza que deseja excluir? s/n");
                                String confirm = scanner.nextLine();
                                if ("s".equals(confirm) || "S".equals(confirm))
                                {
                                    pfDao.excluir(idExc);
                                    System.out.print("Pessoa excluida com successo");
                                }
                                else
                                {
                                    System.out.print("Cancelado");
                                }
                            }
                            else
                            {
                                System.out.print("Pessoa Fisica não encontrada.");
                            }
                        } 
                        else if (tipo.equals("J")) 
                        {
                            System.out.print("ID: ");
                            int idExc = Integer.parseInt(scanner.nextLine());
                            PessoaJuridica pessoaCheckJ = pjDao.getPessoa(idExc);
                            
                            if (pessoaCheckJ != null)
                            {
                                exibirCabecalho("J");
                                exibirPessoasJuridicas(Collections.singletonList(pessoaCheckJ));

                                System.out.printf("Tem certeza que deseja excluir? s/n");
                                String confirm = scanner.nextLine();
                                if ("s".equals(confirm) || "S".equals(confirm))
                                {
                                    pjDao.excluir(idExc);
                                    System.out.print("Pessoa excluida com successo");
                                }
                                else
                                {
                                    System.out.print("Cancelado");
                                }
                            }
                            else
                            {
                                System.out.print("Pessoa Jurídica não encontrada.");
                            }
                        }
                        break;
                    }

                    case 4:
                    {
                        System.out.print("Tipo (F para Física / J para Jurídica): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        System.out.print("ID: ");
                        int idExibir = Integer.parseInt(scanner.nextLine());
                        if (tipo.equals("F")) 
                        {
                            PessoaFisica pf = pfDao.getPessoa(idExibir);
                            if (pf != null)
                            {
                                exibirCabecalho("F");
                                exibirPessoasFisicas(Collections.singletonList(pf));
                            }
                            else
                            {
                                System.out.println("Pessoa Física não encontrada.");
                            }
                        } 
                        else if (tipo.equals("J")) 
                        {
                            PessoaJuridica pj = pjDao.getPessoa(idExibir);
                            if (pj != null)
                            {
                                exibirCabecalho("J");
                                exibirPessoasJuridicas(Collections.singletonList(pj));
                            }
                            else
                            {
                                System.out.println("Pessoa Jurídica não encontrada.");
                            }
                        }
                        break;
                    }

                    case 5: 
                    {
                        System.out.print("Tipo (F para Física / J para Jurídica): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        if (tipo.equals("F")) 
                        {
                            exibirCabecalho("F");
                            exibirPessoasFisicas(pfDao.getPessoas());
                        } 
                        else if (tipo.equals("J")) 
                        {
                            exibirCabecalho("J");
                            exibirPessoasJuridicas(pjDao.getPessoas());
                        }
                        break;
                    }

                    case 0:
                    {
                        System.out.println("Encerrando o sistema...");
                        break;
                    }

                    default:
                    {
                        System.out.println("Opção inválida.");
                        break;
                    }
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        } 
        while (opcao != 0);

        scanner.close();
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
    
    public static PessoaFisica inputPessoasFisicas(Scanner scanner) 
    {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
                            
        return new PessoaFisica(nome, logradouro, cidade, estado, telefone, email, cpf);
    }
    
    public static PessoaJuridica inputPessoasJuridicas(Scanner scanner) 
    {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        return new PessoaJuridica(nome, logradouro, cidade, estado, telefone, email, cnpj);
    }
}