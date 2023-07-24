import dao.CarroDAO;
import dao.ConexaoDAO;
import dao.EstacionamentoDAO;
import model.Carro;
import service.CalculadoraEstacionamentoService;
import service.CarroService;
import service.EstacionamentoService;
import view.CarroView;
import view.EstacionamentoView;
import view.MenuView;

import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws Exception {

            ConexaoDAO conexaoDAO = new ConexaoDAO();
            conexaoDAO.obterConexao();

            List<Carro> carros = new ArrayList<>();

            CarroDAO carroDAO = new CarroDAO(conexaoDAO);
            EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO(conexaoDAO, carroDAO);

            CalculadoraEstacionamentoService calculadoraEstacionamentoService = new CalculadoraEstacionamentoService();

            // MÉTODOS TESTADOS
            // Método adiciona novo entrada de veículo no sistema - testado
            carroDAO.adicionar("Pedro", "Ferrari", "232433h");

            //Método verifica se placa do automóvel já está cadastrado no banco de dados - testado
            System.out.println(carroDAO.verificarSeCarroExiste("232433h"));

            // Método consulta se veículo deu entrada e permanece no estacionamento - testado
           System.out.println(carroDAO.buscarCarroNoEstacionamento("1232guf"));

            // Método busca id do carro recebendo como parâmetro a placa - testado
            System.out.println(carroDAO.buscarIdCarro("232433h"));

            // Método da baixa em um automóvel no estacionamento -testado
            carroDAO.baixarCarroDoSistema("232433h");

            // Método ativa com true estado carro
            carroDAO.ativarCarroNoSistema("232433h");

            // Método cria um novo registro de entrada de um carro no estacionamento
            estacionamentoDAO.adicionaRegistro(LocalTime.now(), 5);

            // Método lista carros que deram entrada no estacionamento - testado
               carros = estacionamentoDAO.obterCarros();
            for (Carro carro : carros) {

                    System.out.println("Placa: " + carro.getPlaca());
                    System.out.println("Marca: " + carro.getMarca());
                    System.out.println("Proprietário: " + carro.getProprietario());
                    System.out.println("----------------------------------");

            }

            // Método atualiza permanência - testado
            estacionamentoDAO.atualizarPermanencia(4, 23.0);

            // Método data saída de carro do estacionamento
            estacionamentoDAO.datarSaida(4, LocalTime.now());

            // Método busca entrada no sistema
            LocalTime entrada = estacionamentoDAO.buscarEntrada("232433h");

            // Método busca saída no sistema
            LocalTime saida = estacionamentoDAO.buscarSaida("232433h");

            // Calcula tempo de permanencia no estacionamnto
            Double permanencia = calculadoraEstacionamentoService.calculaPermanencia(entrada, saida);

            // Calcula valor total a ser pago pela pemanência no estacionamento
            Double valorPago = (calculadoraEstacionamentoService.valorPago(permanencia));

            // Método atualiza a coluna valorpago no banco de dados
            estacionamentoDAO.atualizarValorTotal("232433h", valorPago);

    }


}
