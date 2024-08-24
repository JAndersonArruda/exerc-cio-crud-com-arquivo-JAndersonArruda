package view;

import dao.PessoaDao;
import model.Pessoa;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void menuOptions() {
        System.out.println("\n---------- Menu ----------");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Imprimir");
        System.out.println("3 - Buscar");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Excluir");
        System.out.println("0 - Sair\n");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        PessoaDao pessoaDao = new PessoaDao();

        int control;

        do {
            menuOptions();

            System.out.printf("Informe o ID da operação desejada: ");
            control = scan.nextInt();
            scan.nextLine();

            switch (control) {
                case 1:
                    System.out.println("\nCadastrando pessoa...");
                    System.out.printf("Informe o nome da pessoa a ser cadastrada: ");
                    String name = scan.nextLine();

                    System.out.printf("Informe o email da pessoa a ser cadastrada: ");
                    String email = scan.nextLine();

                    Pessoa pessAdd = new Pessoa(name, email);
                    if (pessoaDao.savePessoa(pessAdd)) {
                        System.out.println("\nOperação de cadastro realizada com sucesso!");
                    } else System.out.println("\nFalha na operação de cadastro!");
                    System.out.printf("Press Enter to continue... ");
                    scan.nextLine();
                    break;
                case 2:
                    System.out.println("\nImprimindo as pessoas cadastradas...");
                    Set<Pessoa> pessoas = pessoaDao.getPessoas();

                    for (Pessoa pess : pessoas) {
                        System.out.println(pess);
                    }
                    System.out.printf("\nOperação de impresão concluida!\nPress Enter to continue...");
                    scan.nextLine();
                    break;
                case 3:
                    System.out.println("\nBuscando pessoa...");
                    System.out.printf("Informe o email da pessoa que deseja buscar: ");

                    Pessoa pessFetch = pessoaDao.fetchPessoaEmail(scan.nextLine());
                    if (pessFetch != null) {
                        System.out.println("Pessoa encontrada!\n");
                        System.out.println(pessFetch);
                        System.out.println("\nOperação de busca concluida com sucesso!");
                    } else System.out.println("\nFalha na operção de busca!");
                    System.out.printf("Press Enter to continue... ");
                    scan.nextLine();
                    break;
                case 4:
                    System.out.println("\nAtualizando dados da pessoa...");
                    System.out.printf("Informe o email da pessoa que deseja atualizar: ");

                    Pessoa pessFethUpdate = pessoaDao.fetchPessoaEmail(scan.nextLine());
                    if (pessFethUpdate != null) {
                        System.out.println("\nATENÇÃO: Não é possível atualizar o email!");
                        System.out.printf("Informe o novo nome: ");

                        Pessoa pessUpdate = new Pessoa(scan.nextLine(), pessFethUpdate.getEmail());
                        if (pessoaDao.updatePessoa(pessUpdate)) {
                            System.out.println("\nOperação de atualização concluida com sucesso!");
                        } else System.out.println("\nFalha na operação de atualização!");
                    } else System.out.println("\nInstancia não existe!");
                    System.out.printf("Press Enter to continue... ");
                    scan.nextLine();
                    break;
                case 5:
                    System.out.println("\nExcluindo pessoa...");
                    System.out.printf("Informe o email da pessoa cuja queira remover: ");

                    Pessoa pessDelete = pessoaDao.fetchPessoaEmail(scan.nextLine());
                    if (pessDelete != null) {
                        if (pessoaDao.deletePessoa(pessDelete)) {
                            System.out.println("\nOperação de remoção concluida com sucesso!");
                        } else System.out.println("\nFalha na operção de remoção!");
                    } else System.out.println("\nInstancia não existe!");
                    System.out.printf("Press Enter to continue... ");
                    scan.nextLine();
                    break;
                case 0:
                    System.out.println("\nSaída realizada com sucesso!");
                    System.exit(0);
                default:
                    System.out.printf("\nInforme um ID valido!\nPress Enter to continue... ");
                    scan.nextLine();
                    break;
            }
        } while (true);
    }
}