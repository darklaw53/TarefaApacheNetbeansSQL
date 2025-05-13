package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager 
{
    public static int getValue(String sequenceName) 
    {
        int valor = -1;

        String sql = "UPDATE sequences SET valor = valor + 1 WHERE nome = ?";
        String selectSql = "SELECT valor FROM sequences WHERE nome = ?";

        Connection conn = null;
        PreparedStatement updateStmt = null;
        PreparedStatement selectStmt = null;
        ResultSet rs = null;

        try 
        {
            conn = ConectorBD.getConnection();

            updateStmt = conn.prepareStatement(sql);
            updateStmt.setString(1, sequenceName);
            int affected = updateStmt.executeUpdate();

            if (affected == 0) 
            {
                throw new SQLException("Sequência '" + sequenceName + "' não encontrada.");
            }

            selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setString(1, sequenceName);
            rs = selectStmt.executeQuery();

            if (rs.next()) 
            {
                valor = rs.getInt("valor");
            }

        } 
        catch (SQLException e) 
        {
            System.err.println("Erro ao obter valor da sequência: " + e.getMessage());
        } 
        finally 
        {
            ConectorBD.close(rs);
            ConectorBD.close(updateStmt);
            ConectorBD.close(selectStmt);
            ConectorBD.close(conn);
        }

        return valor;
    }
}