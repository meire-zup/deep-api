package view;

import java.util.Scanner;

    // EM CONSTRUÇÂO
public class MenuView {
    Scanner scanner;
    CarroView carroView;

    EstacionamentoView estacionamentoView;

    public MenuView(Scanner scanner, CarroView carroView, EstacionamentoView estacionamentoView) {

        this.scanner = scanner;
        this.carroView = carroView;
        this.estacionamentoView = estacionamentoView;

    }

    public void iniciarPrograma()  {

        System.out.println("ENTRANDO iniciar programa");

        int escolha;

        do {

            System.out.println("Escolha uma opção:");
            // Método consulta se veículo deu entrada e permanece no estacionamento
            System.out.println("1 - Consultar se carro está no estacionamento");
            // Método registra entrada de veículo
            System.out.println("2 - Registrar entrada de veículo");
            System.out.println("3 - Listar carros");
            System.out.println("4 - Atualizar permanência");
            System.out.println("5 - Dar baixa em carro");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");
            escolha = scanner.nextInt();
            acionaMetodo(escolha);

        } while (escolha < 1 || escolha > 5);

        outraConsulta();

    }

    public void acionaMetodo(int escolha) {

        switch (escolha) {

            case 1:

                carroView.consultarCarro();
                break;

            case 2:

                carroView.adicionarCarro();
                break;

            case 3:

                estacionamentoView.listar();
                break;

            case 4:

                estacionamentoView.atualizarPermanencia();
                break;

            case 5:

                try {
                    estacionamentoView.baixarCarroDoSistema();
                } catch (Exception e) {

                    System.out.println("Erro ao baixar carro do sistema");

                }
                break;

            case 6:
                System.out.println("Fim!");
                System.exit(0);

        }

    }

    public void outraConsulta() {

        int escolha;

        do {

            System.out.println("Deseja realizar outra operação?");
            System.out.println("Digite 1 para sim ou 2 para não");
            escolha = scanner.nextInt();
            if (escolha != 1 && escolha != 2) {

                System.out.println("Opção inválida!");

            } else if (escolha == 1) {

                try {

                    iniciarPrograma();

                } catch (Exception e) {

                    System.out.println("Erro ao realizar operação");

                }

            } else if (escolha == 2) {
                System.out.println("Fim!");
                System.exit(0);

            }

        } while (escolha != 1 && escolha != 2);

    }

}
