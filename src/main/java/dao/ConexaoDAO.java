package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    public Connection obterConexao() {

        Connection conexao = null;

        try {

            conexao = DriverManager.getConnection
                    ("jdbc:postgresql://estacionamento-aws.cek2nmi2tmwz.sa-east-1.rds.amazonaws.com/estacionamentodeep", "postgres", "root1234");


        if (conexao != null) {

            System.out.println("Conexao realizada com sucesso!");

        }

        } catch (SQLException e) {

            System.out.println("Erro ao se conectar ao banco de dados" + e.getMessage());

            }

            return conexao;

        }

    }



