package view;

import java.util.Scanner;

public class MenuView {
    Scanner scanner;
    CarroView carroView;

    EstacionamentoView estacionamentoView;
    public void iniciarPrograma() throws Exception {

        int escolha = 0;

        do {

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Consultar carro");
            System.out.println("2 - Cadastrar carro");
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

    public void acionaMetodo(int escolha) throws Exception {

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

                estacionamentoView.baixarCarroDoSistema();
                break;

            case 6:
                System.out.println("Fim!");
                System.exit(0);

        }

    }

    public void outraConsulta() throws Exception {

        int escolha;

        do {

            System.out.println("Deseja realizar outra operação?");
            System.out.println("Digite 1 para sim ou 2 para não");
            escolha = scanner.nextInt();
            if (escolha != 1 && escolha != 2) {

                System.out.println("Opção inválida!");

            } else if (escolha == 1) {

                iniciarPrograma();

            } else if (escolha == 2) {
                System.out.println("Fim!");
                System.exit(0);

            }

        } while (escolha != 1 && escolha != 2);

    }

}
