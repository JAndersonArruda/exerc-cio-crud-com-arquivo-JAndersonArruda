package dao;

import model.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDao {
    private File arquivo;

    public PessoaDao() {
        arquivo = new File("pessoas.ser");

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo!");
            }
        }
    }

    public Set<Pessoa> getPessoas() {
        if (arquivo.length() > 0) {
            try (FileInputStream inputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                return (Set<Pessoa>) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado!");
            } catch (IOException e) {
                System.out.println("Falha ao ler arquivo!");
            } catch (ClassNotFoundException e) {
                System.out.println("Falha ao ler objeto em objeto!");
            }
        }
        return new HashSet<>();
    }

    public boolean manipulateFile(Set<Pessoa> pessoas, File file) {
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(pessoas);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            System.out.println("Falha ao escrever no arquivo!");
        } return false;
    }

    public boolean savePessoa(Pessoa pessoa) {
        Set<Pessoa> pessoas = getPessoas();
        if (pessoas.add(pessoa)) {
            return manipulateFile(pessoas, arquivo);
        }
        return false;
    }

    public boolean deletePessoa(Pessoa pessoa) {
        Set<Pessoa> pessoas = getPessoas();
        if (pessoas.remove(pessoa)) {
            return manipulateFile(pessoas, arquivo);
        }
        return false;
    }

    public boolean updatePessoa(Pessoa pessoa) {
        return deletePessoa(pessoa) && savePessoa(pessoa);
    }

    public Pessoa fetchPessoaEmail(String email) {
        Set<Pessoa> pessoas = getPessoas();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getEmail().equals(email)) return pessoa;
        }
        return null;
    }
}
