package service;

import dao.CarroDAO;
import dao.EstacionamentoDAO;
import model.Carro;

import java.time.LocalTime;
import java.util.List;

public class EstacionamentoService {

    EstacionamentoDAO estacionamentoDAO;
    CarroDAO carroDAO;
    CalculadoraEstacionamentoService calculadoraEstacionamentoService;
    List<Carro> carros;


    public EstacionamentoService(EstacionamentoDAO estacionamentoDAO, CarroDAO carroDAO,
                                 CalculadoraEstacionamentoService calculadoraEstacionamentoService, List<Carro> carros) {

        this.estacionamentoDAO = estacionamentoDAO;
        this.carroDAO = carroDAO;
        this.calculadoraEstacionamentoService = calculadoraEstacionamentoService;
        this.carros = carros;

    }

    public List<Carro> listar() {

        try {

            return estacionamentoDAO.obterCarros();

        } catch (Exception e) {

            System.out.println("Erro ao listar carros " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void atualizarPermanencia(String placa, double permanencia) {

        try {

            Integer id = carroDAO.buscarIdCarro(placa);

            if (id != null) {

                estacionamentoDAO.atualizarPermanencia(id, permanencia);

            }

        } catch (Exception e) {

            System.out.println("Código do veículo não encontrado " + e.getMessage());

            throw new RuntimeException(e);

        }

    }

    public void baixarCarroDoSistema(String placa, LocalTime saida) throws Exception {
        Integer carroId = carroDAO.buscarIdCarro(placa);
        if(carroDAO.verificarSeCarroExiste(placa)) {
            estacionamentoDAO.datarSaida(carroId, saida);
            Double permanencia = calculadoraEstacionamentoService.
                    calculaPermanencia(estacionamentoDAO.buscarEntrada(placa),
                            estacionamentoDAO.buscarSaida(placa));
           double valorPago =  calculadoraEstacionamentoService.valorPago(permanencia);
            carroDAO.baixarCarroDoSistema(placa);
            estacionamentoDAO.atualizarValorTotal(placa, valorPago);
            System.out.println("Valor total a ser pago: " + valorPago);

        }  else {

            System.out.println("Informe uma placa válida!");

        }

    }

}
