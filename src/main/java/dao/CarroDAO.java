package dao;

import model.Carro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    ConexaoDAO conexaoDAO;
    List<Carro> carros;

    public CarroDAO(ConexaoDAO conexaoDAO, List<Carro> carros) {
        this.conexaoDAO = conexaoDAO;
        this.carros = carros;
    }

    public CarroDAO(ConexaoDAO conexaoDAO) {
        this.conexaoDAO = conexaoDAO;
    }

    // Método adiciona um carro no banco de dados e cria um objeto carro setando seus atributos - testado
    public Carro adicionar(String proprietario, String marca, String placa) throws Exception {

        Carro carro = null;

        Integer id = 0;

        boolean estado = true;
        String usuario = "Meire Lopes";

        try {
            if (conexaoDAO.obterConexao() != null) {
                String sql = "INSERT INTO tb_carro (nomedono, marcacarro, placa, estado, usuario)" +
                        " values (?, ?, ?, ?, ?) RETURNING id";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setString(1, proprietario);
                statement.setString(2, marca);
                statement.setString(3, placa);
                statement.setBoolean(4, estado);
                statement.setString(5, usuario);
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


    // Método verifica se carro está cadastrado no banco de dados do estacionamento - testado
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

    // Método verifica se carro está presente no estacionamento - testado
    public Boolean buscarCarroNoEstacionamento(String placa){

        boolean estado = false;

        if (conexaoDAO.obterConexao() != null) {

            String sql = "SELECT estado FROM tb_carro WHERE placa = ?";

            try {

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setString(1, placa);
                ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()){

                        estado = resultSet.getBoolean("estado");

                    } else
                    estado = false;



            } catch (SQLException e) {

                System.out.println("Erro ao consultar carro!");
                throw new RuntimeException(e);

            }

        }

        return estado;

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

    // Método da baixa em um automóvel no estacionamento -testado
    public void baixarCarroDoSistema(String placa) {

        Boolean estado = false;

        try {
            if(conexaoDAO.obterConexao() != null) {

                String sql = "UPDATE tb_carro SET estado = ? WHERE placa = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setBoolean(1, estado);
                statement.setString(2, placa);
                statement.executeUpdate();
            }
        } catch (Exception e) {

            System.out.println("Não foi possível dar baixa no automóvel de placa ." + placa);

            throw new RuntimeException(e);

        }

    }
    public void ativarCarroNoSistema(String placa) {

        Boolean estado = true;

        try {
            if(conexaoDAO.obterConexao() != null) {

                String sql = "UPDATE tb_carro SET estado = ? WHERE placa = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setBoolean(1, estado);
                statement.setString(2, placa);
                statement.executeUpdate();
            }
        } catch (Exception e) {

            System.out.println("Não foi possível ativar entrada do automóvel de placa ." + placa);

            throw new RuntimeException(e);

        }

    }

}

