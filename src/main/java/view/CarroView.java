package view;

import model.Carro;
import service.CarroService;


import java.util.List;
import java.util.Scanner;

public class CarroView {

    private Scanner scanner;
    private CarroService carroService;
    List<Carro> carros;

    public CarroView(Scanner scanner, CarroService carroService, List<Carro> carros) {

        this.scanner = scanner;
        this.carroService = carroService;
        this.carros = carros;

    }

    // Método adiciona carro
    public void adicionarCarro() {

        System.out.println("Informe a placa do carro:");
        String placa = scanner.nextLine();
        System.out.println("Informe a marca do carro:");
        String marca = scanner.nextLine();
        System.out.println("Informe o nome do proprietário do carro:");
        String proprietario = scanner.nextLine();
        carroService.adicionarCarro(proprietario,marca, placa);

    }

    // Método visualiza lista de carros que estão no estacionamento

    public List<Carro> listaCarros() {

        carros = carroService.listarCarros();
        return carros;
    }

    public void consultarCarro() {

        System.out.println("Informe a placa do veículo:");
        String placa = scanner.nextLine();

        try {

            carroService.consultarCarro(placa);

        } catch (Exception e) {

            System.out.println("Erro ao consultar carro " + e.getMessage());
            throw new RuntimeException(e);

        }

    }


}
