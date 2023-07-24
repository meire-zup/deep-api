package view;

import model.Carro;
import service.EstacionamentoService;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class EstacionamentoView {

    EstacionamentoService estacionamentoService;
    Scanner scanner;

    public EstacionamentoView(EstacionamentoService estacionamentoService, Scanner scanner) {
        this.estacionamentoService = estacionamentoService;
        this.scanner = scanner;
    }

    public List<Carro> listar() {


        return estacionamentoService.listar();

    }

    public void atualizarPermanencia() {

        System.out.println("Informe a placa do veículo:");
        String placa =scanner.nextLine();
        System.out.println("Informe os minutos de permanencia:");
        double permanencia = scanner.nextDouble();

        estacionamentoService.atualizarPermanencia(placa,permanencia);

    }
    public void baixarCarroDoSistema() throws Exception {

        System.out.println("Informe a placa do veículo:");
        String placa =scanner.nextLine();
        LocalTime saida = LocalTime.now();

        estacionamentoService.baixarCarroDoSistema(placa, saida);

    }

    }
