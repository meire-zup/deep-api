package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    public Connection obterConexao() throws Exception {

        Connection conexao = null;

        try {

            conexao = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/deep-api", "postgres", "puc@2015");


        if (conexao != null) {

            System.out.println("Conexao realizada com sucesso!");

        }

        } catch (SQLException e) {

                throw new Exception("Erro ao se conectar ao banco de dados" + e.getMessage());
            }

            return conexao;

        }

    }



