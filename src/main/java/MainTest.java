import dao.CarroDAO;
import dao.ConexaoDAO;
import dao.EstacionamentoDAO;
import model.Carro;
import model.Estacionamento;

import java.time.LocalTime;

public class MainTest {

    public static void main(String[] args) throws Exception {

        ConexaoDAO conexaoDAO = new ConexaoDAO();

        conexaoDAO.obterConexao();

        CarroDAO carroDAO = new CarroDAO(conexaoDAO);
        EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO(conexaoDAO);
        /*Carro carro = carroDAO.adicionar("Meire", "Fiat", "1234ABC");
        System.out.println(carro.getId()+" "+ carro.getEstado());
        System.out.println(carro.getMarca()+" "+ carro.getPlaca());
        System.out.println(carro.getProprietario());

       Estacionamento registro = estacionamentoDAO.adicionaRegistro(LocalTime.now(),1);
        System.out.println(registro.getEntrada());
        System.out.println(registro.getPermanencia());
        System.out.println(registro.getId());*/

        System.out.println(estacionamentoDAO.obterCarros());



    }

}
