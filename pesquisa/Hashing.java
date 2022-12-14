package pesquisa;

import java.util.LinkedList;

import Av1C2.Conta;
import java.util.NoSuchElementException;

public class Hashing extends Metodo {

    private LinkedList<Conta>[] lista;

    @SuppressWarnings("unchecked")
    public Hashing(int tamanho) {
        int aux;
        switch (tamanho) {
            case 1000:
                aux = 1103;
                break;
            case 5000:
                aux = 5501;
                break;
            case 10000:
                aux = 11001;
                break;
            case 50000:
                aux = 55001;
                break;
            default:
                aux = 557;
        }
        lista = new LinkedList[aux];
    }

    public Hashing() {
        this(500);
    }

    @Override
    public void insere(Conta conta) {
        int index = getIndex(conta.getNome());
        boolean inserido;
        do {
            try {
                inserido = lista[index].getFirst().getNome().equals(conta.getNome());
            } catch (NoSuchElementException e) {
                inserido = true;
            } catch (NullPointerException e) {
                lista[index] = new LinkedList<>();
                inserido = true;
            }
            if (inserido) {
                lista[index].add(conta);
            } else {
                index = (index + 1) % lista.length;
            }
        } while (!inserido);

    }

    @Override
    public LinkedList<Conta> pesquisa(String nome) {
        int indexInicial = getIndex(nome);
        int index = indexInicial;
        boolean inserido;
        do {
            try {
                inserido = lista[index].getFirst().getNome().equals(nome);
            } catch (NullPointerException e) {
                return null;
            }
            if (inserido) {
                return lista[index];
            } else {
                index = (index + 1) % lista.length;
                if (index == indexInicial) {
                    return null;
                }
            }
        } while (!inserido);
        return null;
    }

    private int getIndex(String str) {
        return Math.abs(str.hashCode() % lista.length);
    }
}
