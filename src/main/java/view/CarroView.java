package view;

import dao.CarroDAO;
import model.Carro;
import service.CarroService;


import java.util.List;
import java.util.Scanner;

public class CarroView {

    private Scanner scanner;
    private CarroService carroService;
    private CarroDAO carroDAO;
    List<Carro> carros;

    public CarroView(Scanner scanner, CarroService carroService, CarroDAO carroDAO, List<Carro> carros) {
        this.scanner = scanner;
        this.carroService = carroService;
        this.carroDAO = carroDAO;
        this.carros = carros;
    }

    // Método adiciona carro
    public void adicionarCarro() {

        System.out.println("Informe a placa do carro:");
        scanner.nextLine();
        String placa = scanner.nextLine();


        try {
            if (carroDAO.verificarSeCarroExiste(placa)){
                System.out.println("Carro existe");


            } else if (carroDAO.verificarSeCarroExiste(placa) == false) {

                System.out.println("Informe a marca do carro:");
                String marca = scanner.nextLine();
                System.out.println("Informe o nome do proprietário do carro:");
                String proprietario = scanner.nextLine();
                carroService.adicionarCarro(proprietario,marca, placa);

            }


        } catch (Exception e) {

            throw new RuntimeException(e);
        }


    }

    // Método visualiza lista de carros que estão no estacionamento

    public List<Carro> listaCarros() {

        carros = carroService.listarCarros();
        return carros;
    }

    // Método consulta se carro está no estacionamento informando a placa do veículo
    public void consultarCarro() {

        System.out.println("Informe a placa do veículo:");
        scanner.nextLine();

        String placa = scanner.nextLine();

        try {

            carroService.consultarCarro(placa);

        } catch (Exception e) {

            System.out.println("Erro ao consultar carro " + e.getMessage());

            throw new RuntimeException(e);

        }

    }


}
