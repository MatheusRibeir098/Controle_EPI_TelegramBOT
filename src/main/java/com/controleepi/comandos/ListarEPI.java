package com.controleepi.comandos;

import com.controleepi.database.Conexao;
import com.controleepi.model.EPI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListarEPI {

    public StringBuilder listarEPIs() {
        ArrayList<EPI> lista = new ArrayList<>();
        String sql = "SELECT * FROM epi";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EPI epi = new EPI(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("validade")
                );
                lista.add(epi);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar EPIs: " + e.getMessage());
        }
        StringBuilder sb = new StringBuilder("📋 EPIs cadastrados:\n");
        for (EPI epi : lista) {
            sb.append("🆔 ").append(epi.getId())
                    .append(" | 🧤 ").append(epi.getNome())
                    .append(" | 📅 ").append(epi.getValidade()).append("\n");
        }
        return sb;
    }
}

