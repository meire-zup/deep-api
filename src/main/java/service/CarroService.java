package service;

// Métodos:

import dao.CarroDAO;
import dao.EstacionamentoDAO;
import model.Carro;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class CarroService {

    private CarroDAO carroDAO;
    List<Carro> carros;
    EstacionamentoDAO estacionamentoDAO;

    public CarroService(CarroDAO carroDAO, List<Carro> carros, EstacionamentoDAO estacionamentoDAO) {

        this.carroDAO = carroDAO;
        this.carros = carros;
        this.estacionamentoDAO = estacionamentoDAO;

    }

    public void adicionarCarro(String proprietario, String marca, String placa) {

        try {

                carroDAO.adicionar(proprietario, marca, placa);
                //Integer carroId = carroDAO.buscarIdCarro(placa);
               // LocalTime entrada = LocalTime.now();
                //estacionamentoDAO.adicionaRegistro(entrada, carroId);


        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }

    }
    public void registrarCarroExistente(String proprietario, String marca, String placa) {

        try {
             System.out.println("Entrou em placa existe");
                System.out.println("Carro com a placa " + placa + " já cadastrado.");
                Integer carroId = carroDAO.buscarIdCarro(placa);
                LocalTime entrada = LocalTime.now();
                estacionamentoDAO.adicionaRegistro(entrada, carroId);


        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }

    }


    public List<Carro> listarCarros() {

        try {
                carros = estacionamentoDAO.obterCarros();
            for (Carro carro : carros) {
                System.out.println("Placa: ");
                System.out.println("Marca: ");
                System.out.println("Proprietário: ");
                System.out.println("----------------------------------");

            }
        } catch (Exception e) {

            throw new RuntimeException(e);

        }

        return carros;
    }

    // Método consulta se carro está no estacionamento informnando a placa do veículo
    public void consultarCarro(String placa) throws Exception {

        if(carroDAO.buscarCarroNoEstacionamento(placa)) {

            System.out.println("Carro com a placa " + placa + " encontrado. Se encontra no estacionamento!");

        } else System.out.println("Carro com a placa " + placa + " não encontrado. Não se encontra no estacionamento!");

    }



}
