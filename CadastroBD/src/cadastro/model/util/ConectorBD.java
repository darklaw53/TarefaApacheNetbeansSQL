package cadastro.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectorBD 
{
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "loja";
    private static final String SENHA = "loja";

    public static Connection getConnection() 
    {
        try 
        {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public static PreparedStatement getPrepared(String sql) 
    {
        try 
        {
            Connection conn = getConnection();
            if (conn != null) 
            {
                return conn.prepareStatement(sql);
            } 
            else 
            {
                throw new SQLException("Conexão nula. Verifique o banco de dados.");
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao preparar statement: " + e.getMessage());
            return null;
        }
    }

    public static ResultSet getSelect(String sql) 
    {
        try 
        {
            PreparedStatement ps = getPrepared(sql);
            if (ps != null) 
            {
                return ps.executeQuery();
            } 
            else 
            {
                throw new SQLException("PreparedStatement nulo. Verifique o SQL.");
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao executar select: " + e.getMessage());
            return null;
        }
    }

    public static void close(ResultSet rs) 
    {
        if (rs != null) 
        {
            try 
            {
                rs.close();
            } 
            catch (SQLException e) 
            {
                System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
    }

    public static void close(Statement st) 
    {
        if (st != null) 
        {
            try 
            {
                st.close();
            } 
            catch (SQLException e) 
            {
                System.err.println("Erro ao fechar Statement: " + e.getMessage());
            }
        }
    }

    public static void close(Connection conn) 
    {
        if (conn != null) 
        {
            try 
            {
                conn.close();
            } 
            catch (SQLException e) 
            {
                System.err.println("Erro ao fechar Connection: " + e.getMessage());
            }
        }
    }
}