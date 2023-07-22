package service;

// Métodos:

import dao.CarroDAO;
import model.Carro;

import java.util.ArrayList;
import java.util.List;


public class CarroService {

    private CarroDAO carroDAO;
    List<Carro> carros;
    CalculadoraEstacionamentoService calculadoraEstacionamentoService;

    public CarroService(CarroDAO carroDAO, List<Carro> carros) {

        this.carroDAO = carroDAO;
        this.carros = carros;

    }

    public void adicionarCarro(String proprietario, String marca, String placa) {

        try {

            if (carroDAO.verificarSeCarroExiste(placa)) {

                System.out.println("Carro com a placa " + placa + " já cadastrado.");

            } else{

                carroDAO.adicionar(proprietario, marca, placa);

                System.out.println("Carro cadastrado com sucesso");

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }

    }

    public List<Carro> listarCarros() {

        try {

            carros = carroDAO.obterCarros();


        } catch (Exception e) {

            throw new RuntimeException(e);

        }

        return carros;
    }

    public void consultarCarro(String placa) throws Exception {

        if(carroDAO.verificarSeCarroExiste(placa)) {

            System.out.println("Carro com a placa " + placa + " cadastrado!");

        }

        System.out.println("Carro com a placa " + placa + " não cadastrado!");

    }



}
