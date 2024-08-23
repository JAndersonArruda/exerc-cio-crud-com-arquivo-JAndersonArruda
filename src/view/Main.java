package view;

import dao.PessoaDao;
import model.Pessoa;

public class Main {
    public static void main(String[] args) {
        PessoaDao pessoaDao = new PessoaDao();

        Pessoa pessoa = new Pessoa("Miguel", "miguel2@gmail.com");

        if (pessoaDao.savePessoa(pessoa)) {
            System.out.println("Salvo com suceso!");
        } else System.out.println("Falha ao salvar!");

        System.out.println(pessoaDao.getPessoas());

        if (pessoaDao.deletePessoa(pessoaDao.fetchPessoaEmail("miguel2@gmail.com"))) {
            System.out.println("Deletado com suceso!");
        } else System.out.println("Falha ao deletar!");

        System.out.println(pessoaDao.getPessoas());

        Pessoa pessoa1 = new Pessoa("Ana Beatriz", "ana@gmail.com");
        if (pessoaDao.updatePessoa(pessoa1)) {
            System.out.println("Atualização concluida!");
        } else System.out.println("Falha na atualização!");

        System.out.println(pessoaDao.getPessoas());
    }
}