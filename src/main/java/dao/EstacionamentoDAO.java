package dao;

import model.Carro;
import model.Estacionamento;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoDAO {

    ConexaoDAO conexaoDAO;
    CarroDAO carroDAO;

    public EstacionamentoDAO(ConexaoDAO conexaoDAO, CarroDAO carroDAO) {

        this.conexaoDAO = conexaoDAO;
        this.carroDAO = carroDAO;

    }

    // Método cria um novo registro de entrada de um carro no estacionamento
    // Registra a data de entrada, o carro_id, permanencia de uma hora e cria objeto setando os atributos
    public Estacionamento adicionaRegistro(LocalTime entrada, int carroId) throws Exception {

        Estacionamento estacionamento = null;
        Double permanencia = 1.0;

        Integer idEstacionamento = 0;

        String sql = "INSERT INTO tb_estacionamento (entrada, carroid, permanencia) VALUES (?, ?, ?) RETURNING id";

        try {
            PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
            statement.setTime(1, Time.valueOf(entrada));
            statement.setInt(2, carroId);
            statement.setDouble(3, permanencia);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                idEstacionamento = resultSet.getInt("id");
                estacionamento = new Estacionamento();
                estacionamento.setId(idEstacionamento);
                estacionamento.setDataEntrada(entrada);
                estacionamento.setPermanencia(permanencia);

            }

        } catch (SQLException e) {

            System.out.println("Erro ao adicionar registro do carro." + e.getMessage());
            throw new RuntimeException();

        }
        return estacionamento;
    }


    // Método retorna lista de carros que estão no estacionamento
    public List<Carro> obterCarros() throws Exception {

        List<Carro> carros = new ArrayList<>();

        if (conexaoDAO.obterConexao() != null) {

            String sql = "SELECT tc.id, tc.nomedono, tc.marcacarro, tc.placa " +
                    "FROM tb_carro tc " +
                    "WHERE tc.estado = true";

            try {

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String proprietario = resultSet.getString("nomedono");
                    String marca = resultSet.getString("marcacarro");
                    String placa = resultSet.getString("placa");

                    Carro carro = new Carro();
                    carro.setMarca(marca);
                    carro.setPlaca(placa);
                    carro.setEstado(true);
                    carro.setId(id);
                    carro.setProprietario(proprietario);

                    carros.add(carro);

                }

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }
        }

        return carros;

    }
    // Atualiza permanencia de veículo no estacionamento - testado
    public void atualizarPermanencia(Integer carroId, Double permanencia) {

        try {
            if(conexaoDAO.obterConexao() != null) {

                String sql = "UPDATE tb_estacionamento SET permanencia = ? WHERE carroid = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setDouble(1, permanencia);
                statement.setInt(2, carroId);
                statement.executeUpdate();
            }
        } catch (Exception e) {

            System.out.println("Não foi possível atualizar permanencia.");
            throw new RuntimeException(e);

        }

    }

    // seta saida do veiculo do estacionamento
    public void datarSaida(String placa, LocalTime saida) {

        try {
            if(conexaoDAO.obterConexao() != null) {

                String sql = "UPDATE tb_estacionamento SET saida = ? WHERE placa = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setTime(1, Time.valueOf(saida));
                statement.setString(2, placa);
                statement.executeUpdate();
            }
        } catch (Exception e) {

            System.out.println("Erro ao datar saido do veiculo " + e.getMessage());
            throw new RuntimeException(e);

        }

    }

    // Método busca entrada do carro recebendo como parâmetro a placa
    public LocalTime buscarEntrada(String placa) throws Exception {

        Integer id = carroDAO.buscarIdCarro(placa);

        LocalTime entrada = null;


        try {
            if (conexaoDAO.obterConexao() != null) {

                String sql = "SELECT entrada FROM tb_estacionamento WHERE carroid = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);

                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    Time horaEntrada =  resultSet.getTime("entrada");
                    entrada = horaEntrada.toLocalTime();
                }
            }
        } catch (SQLException e) {

            System.out.println("Erro ao buscar data de entrada!" + e.getMessage());
            throw new RuntimeException(e);

        }

        return entrada;
    }

    // Método busca entrada do carro recebendo como parâmetro a placa
    public LocalTime buscarSaida(String placa) throws Exception {

        Integer id = carroDAO.buscarIdCarro(placa);

        LocalTime saida = null;


        try {
            if (conexaoDAO.obterConexao() != null) {

                String sql = "SELECT saida FROM tb_estacionamento WHERE carroid = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);

                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    Time horaEntrada =  resultSet.getTime("saida");
                    saida = horaEntrada.toLocalTime();
                }
            }
        } catch (SQLException e) {

            System.out.println("Erro ao buscar data de saida!" + e.getMessage());
            throw new RuntimeException(e);

        }

        return saida;
    }

    public void atualizarValorTotal(String placa, Double valorPago) {

        Integer carroId = null;
        try {
            carroId = carroDAO.buscarIdCarro(placa);

        } catch (Exception e) {

            System.out.println("Não foi possível encontrar código do veículo " + e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            if(conexaoDAO.obterConexao() != null) {

                String sql = "UPDATE tb_estacionamento SET valorpago = ? WHERE carroid = ?";

                PreparedStatement statement = conexaoDAO.obterConexao().prepareStatement(sql);
                statement.setDouble(1, valorPago);
                statement.setInt(2, carroId);
                statement.executeUpdate();
            }
        } catch (Exception e) {

            System.out.println("Não foi possível atualizar valor pago.");

            throw new RuntimeException(e);

        }

    }

}


