package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO 
{
    public PessoaJuridica getPessoa(int id) 
    {
        PessoaJuridica pj = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = """
            SELECT p.idPessoa , p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cnpj
            FROM Pessoa p
            JOIN PessoaJuridica pf ON p.idPessoa  = pf.idPessoa 
            WHERE p.idPessoa  = ?
        """;

        try 
        {
            conn = ConectorBD.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) 
            {
                pj = new PessoaJuridica(
                    rs.getInt("idPessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao buscar Pessoa Física: " + e.getMessage());
        } 
        finally 
        {
            ConectorBD.close(rs);
            ConectorBD.close(ps);
            ConectorBD.close(conn);
        }

        return pj;
    }

    public List<PessoaJuridica> getPessoas() 
    {
        List<PessoaJuridica> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = """
            SELECT p.idPessoa , p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cnpj
            FROM Pessoa p
            JOIN PessoaJuridica pf ON p.idPessoa  = pf.idPessoa 
        """;

        try 
        {
            conn = ConectorBD.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) 
            {
                PessoaJuridica pf = new PessoaJuridica(
                    rs.getInt("idPessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
                lista.add(pf);
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao listar Pessoa Juridica: " + e.getMessage());
        } 
        finally 
        {
            ConectorBD.close(rs);
            ConectorBD.close(ps);
            ConectorBD.close(conn);
        }

        return lista;
    }
    
    public boolean incluir(PessoaJuridica pj) 
    {
        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psJuridica = null;
        ResultSet rs = null;

        String sqlPessoa = """
            INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        String sqlJuridica = """
            INSERT INTO PessoaJuridica (idPessoa, cnpj)
            VALUES (?, ?)
        """;

        try {
            conn = ConectorBD.getConnection();

            psPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            psPessoa.setString(1, pj.getNome());
            psPessoa.setString(2, pj.getLogradouro());
            psPessoa.setString(3, pj.getCidade());
            psPessoa.setString(4, pj.getEstado());
            psPessoa.setString(5, pj.getTelefone());
            psPessoa.setString(6, pj.getEmail());
            psPessoa.executeUpdate();

            rs = psPessoa.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                pj.setID(idGerado); 

                psJuridica = conn.prepareStatement(sqlJuridica);
                psJuridica.setInt(1, idGerado);
                psJuridica.setString(2, pj.getCnpj());
                psJuridica.executeUpdate();

                return true;
            } else {
                System.err.println("Erro ao obter o ID gerado.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao incluir Pessoa Juridica: " + e.getMessage());
            return false;
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psJuridica);
            ConectorBD.close(conn);
        }
    }
    
    public boolean alterar(PessoaJuridica pj) 
    {
        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psJuridica = null;

        String sqlPessoa = """
            UPDATE Pessoa
            SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ?
            WHERE idPessoa  = ?
        """;

        String sqlJuridica = """
            UPDATE PessoaJuridica
            SET cnpj = ?
            WHERE idPessoa  = ?
        """;

        try 
        {
            conn = ConectorBD.getConnection();

            psPessoa = conn.prepareStatement(sqlPessoa);
            psPessoa.setString(1, pj.getNome());
            psPessoa.setString(2, pj.getLogradouro());
            psPessoa.setString(3, pj.getCidade());
            psPessoa.setString(4, pj.getEstado());
            psPessoa.setString(5, pj.getTelefone());
            psPessoa.setString(6, pj.getEmail());
            psPessoa.setInt(7, pj.getId());
            psPessoa.executeUpdate();

            psJuridica = conn.prepareStatement(sqlJuridica);
            psJuridica.setString(1, pj.getCnpj());
            psJuridica.setInt(2, pj.getId());
            psJuridica.executeUpdate();

            return true;
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao alterar Pessoa Juridica: " + e.getMessage());
            return false;
        } 
        finally 
        {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psJuridica);
            ConectorBD.close(conn);
        }
    }

    public boolean excluir(int id) 
    {
        Connection conn = null;
        PreparedStatement psJuridica = null;
        PreparedStatement psPessoa = null;

        String sqlJuridica = "DELETE FROM PessoaJuridica WHERE idPessoa  = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa  = ?";

        try 
        {
            conn = ConectorBD.getConnection();

            psJuridica = conn.prepareStatement(sqlJuridica);
            psJuridica.setInt(1, id);
            psJuridica.executeUpdate();

            psPessoa = conn.prepareStatement(sqlPessoa);
            psPessoa.setInt(1, id);
            psPessoa.executeUpdate();

            return true;
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao excluir Pessoa Juridica: " + e.getMessage());
            return false;
        } 
        finally 
        {
            ConectorBD.close(psJuridica);
            ConectorBD.close(psPessoa);
            ConectorBD.close(conn);
        }
    }
}