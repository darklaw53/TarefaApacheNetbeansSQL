package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO 
{
    public PessoaFisica getPessoa(int id) 
    {
        PessoaFisica pf = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = """
            SELECT p.idPessoa , p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf
            FROM Pessoa p
            JOIN PessoaFisica pf ON p.idPessoa  = pf.idPessoa 
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
                pf = new PessoaFisica(
                    rs.getInt("idPessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
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

        return pf;
    }

    public List<PessoaFisica> getPessoas() 
    {
        List<PessoaFisica> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = """
            SELECT p.idPessoa , p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf
            FROM Pessoa p
            JOIN PessoaFisica pf ON p.idPessoa  = pf.idPessoa 
        """;

        try 
        {
            conn = ConectorBD.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) 
            {
                PessoaFisica pf = new PessoaFisica(
                    rs.getInt("idPessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                lista.add(pf);
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao listar Pessoas Físicas: " + e.getMessage());
        } 
        finally 
        {
            ConectorBD.close(rs);
            ConectorBD.close(ps);
            ConectorBD.close(conn);
        }

        return lista;
    }

    public boolean incluir(PessoaFisica pf) 
    {
        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psFisica = null;
        ResultSet rs = null;

        String sqlPessoa = """
            INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        String sqlFisica = """
            INSERT INTO PessoaFisica (idPessoa, cpf)
            VALUES (?, ?)
        """;

        try {
            conn = ConectorBD.getConnection();

            psPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            psPessoa.setString(1, pf.getNome());
            psPessoa.setString(2, pf.getLogradouro());
            psPessoa.setString(3, pf.getCidade());
            psPessoa.setString(4, pf.getEstado());
            psPessoa.setString(5, pf.getTelefone());
            psPessoa.setString(6, pf.getEmail());
            psPessoa.executeUpdate();

            rs = psPessoa.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                pf.setID(idGerado); 

                psFisica = conn.prepareStatement(sqlFisica);
                psFisica.setInt(1, idGerado);
                psFisica.setString(2, pf.getCpf());
                psFisica.executeUpdate();

                return true;
            } else {
                System.err.println("Erro ao obter o ID gerado.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao incluir Pessoa Física: " + e.getMessage());
            return false;
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psFisica);
            ConectorBD.close(conn);
        }
    }

    public boolean alterar(PessoaFisica pf) 
    {
        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psFisica = null;

        String sqlPessoa = """
            UPDATE Pessoa
            SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ?
            WHERE idPessoa  = ?
        """;

        String sqlFisica = """
            UPDATE PessoaFisica
            SET cpf = ?
            WHERE idPessoa  = ?
        """;

        try 
        {
            conn = ConectorBD.getConnection();

            psPessoa = conn.prepareStatement(sqlPessoa);
            psPessoa.setString(1, pf.getNome());
            psPessoa.setString(2, pf.getLogradouro());
            psPessoa.setString(3, pf.getCidade());
            psPessoa.setString(4, pf.getEstado());
            psPessoa.setString(5, pf.getTelefone());
            psPessoa.setString(6, pf.getEmail());
            psPessoa.setInt(7, pf.getId());
            psPessoa.executeUpdate();

            psFisica = conn.prepareStatement(sqlFisica);
            psFisica.setString(1, pf.getCpf());
            psFisica.setInt(2, pf.getId());
            psFisica.executeUpdate();

            return true;
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao alterar Pessoa Física: " + e.getMessage());
            return false;
        } 
        finally 
        {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psFisica);
            ConectorBD.close(conn);
        }
    }

    public boolean excluir(int id) 
    {
        Connection conn = null;
        PreparedStatement psFisica = null;
        PreparedStatement psPessoa = null;

        String sqlFisica = "DELETE FROM PessoaFisica WHERE idPessoa  = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa  = ?";

        try 
        {
            conn = ConectorBD.getConnection();

            psFisica = conn.prepareStatement(sqlFisica);
            psFisica.setInt(1, id);
            psFisica.executeUpdate();

            psPessoa = conn.prepareStatement(sqlPessoa);
            psPessoa.setInt(1, id);
            psPessoa.executeUpdate();

            return true;
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao excluir Pessoa Física: " + e.getMessage());
            return false;
        } 
        finally 
        {
            ConectorBD.close(psFisica);
            ConectorBD.close(psPessoa);
            ConectorBD.close(conn);
        }
    }
}