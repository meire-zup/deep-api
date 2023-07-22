import dao.CarroDAO;
import dao.ConexaoDAO;
import dao.EstacionamentoDAO;
import model.Carro;
import service.CarroService;
import view.CarroView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        try {


            ConexaoDAO conexaoDAO = new ConexaoDAO();
            conexaoDAO.obterConexao();
            Scanner scanner = new Scanner(System.in);

            List<Carro> carros = new ArrayList<>();

            CarroDAO carroDAO = new CarroDAO(conexaoDAO);
            CarroService carroService = new CarroService(carroDAO, carros);

            EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO(conexaoDAO, carroDAO);
            CarroView carroView = new CarroView(scanner, carroService, carros);
        /*Carro carro = carroDAO.adicionar("Meire", "Fiat", "1234ABC");
        System.out.println(carro.getId()+" "+ carro.getEstado());
        System.out.println(carro.getMarca()+" "+ carro.getPlaca());
        System.out.println(carro.getProprietario());

       Estacionamento registro = estacionamentoDAO.adicionaRegistro(LocalTime.now(),1);
        System.out.println(registro.getEntrada());
        System.out.println(registro.getPermanencia());
        System.out.println(registro.getId());*/

            //System.out.println(carroDAO.obterCarros());
            //System.out.println(carroDAO.buscarIdCarro("1234ABC"));
            //estacionamentoDAO.atualizarPermanencia(1, 14.0);
            //carroDAO.baixarCarroDoSistema("1234ABC");
            // CalculadoraEstacionamentoService calculadoraEstacionamentoService = new CalculadoraEstacionamentoService();
            // double valor = calculadoraEstacionamentoService.valorPago(35);
            // System.out.println(valor);
       /* carroView.adicionarCarro();
             List<Carro> carros2 =   estacionamentoDAO.obterCarros();
             carros2.toString();
        for (Carro c: carros2) {
            System.out.println("proprierar "+c.getProprietario());

        }
        System.out.println(estacionamentoDAO.obterCarros().toString());*/
            System.out.println(estacionamentoDAO.buscarEntrada("1234ABC"));
            System.out.println(estacionamentoDAO.buscarSaida("1234ABC"));

        } catch (Exception ignored) {


        }

    }
}
