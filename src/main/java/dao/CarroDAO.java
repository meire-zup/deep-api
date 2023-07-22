package dao;

import model.Carro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarroDAO {

    ConexaoDAO conexaoDAO;

    public CarroDAO(ConexaoDAO conexaoDAO) {

        this.conexaoDAO = conexaoDAO;

    }

    // Método adiciona um carro no banco de dados e cria um objeto carro setando seus atributos
    public Carro adicionar(String proprietario, String marca, String placa) throws Exception {

        Carro carro = null;

        Integer id = 0;

        boolean estado = true;

        try {
            if (conexaoDAO.obterConexao() != null) {
                String sql = "INSERT INTO tb_carro (nomedono, marcacarro, placa, estado)" +
                        " values (?, ?, ?, ?) RETURNING id";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setString(1, proprietario);
                statement.setString(2, marca);
                statement.setString(3, placa);
                statement.setBoolean(4, estado);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    id = resultSet.getInt("id");

                    carro = new Carro();

                    carro.setProprietario(proprietario);
                    carro.setId(id);
                    carro.setPlaca(placa);
                    carro.setEstado(estado);
                    carro.setMarca(marca);

                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar carro." + e.getMessage());
            throw new RuntimeException(e);
        }
        return carro;
    }

    public List<Carro> buscarTodos() {


        return null;
    }



    // Método verifica se carro existe no banco de dados

    public boolean verificarSeCarroExiste(String placa) throws Exception {

        boolean existe = false;

        if (conexaoDAO.obterConexao() != null) {

            String sql = "SELECT id FROM tb_carro WHERE placa = ? LIMIT 1";

            try {
                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);

                statement.setString(1, placa);

                ResultSet resultSet = statement.executeQuery();

                existe = resultSet.next();

            } catch (SQLException e) {

                System.out.println(e.getMessage());

                throw new RuntimeException(e);

            }

        }

        return existe;

    }

    // Método busca id do carro recebendo como parâmetro a placa
    public Integer buscarIdCarro(String placa) throws Exception {

        Integer id = null;

        try {
            if (conexaoDAO.obterConexao() != null) {

                String sql = "SELECT id FROM tb_carro WHERE placa = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);

                statement.setString(1, placa);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    id = resultSet.getInt("id");

                }
            }
        } catch (SQLException e) {

            System.out.println("Erro ao buscar carro" + e.getMessage());
            throw new RuntimeException(e);

        }

        return id;
    }

}

