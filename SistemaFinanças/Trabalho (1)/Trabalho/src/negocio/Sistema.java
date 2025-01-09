package negocio;

import dados.Gasto;
import dados.Receita;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Gasto> gastos;
    private List<Receita> receitas;

    public Sistema() {
        this.gastos = new ArrayList<>();
        this.receitas = new ArrayList<>();
    }

    public void adicionaGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public boolean removeGasto(String nome) {
        for (int i = 0; i < gastos.size(); i++) {
            if (gastos.get(i).getNome().equals(nome)) {
                gastos.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean removeReceita(String nome) {
        for (int i = 0; i < receitas.size(); i++) {
            if (receitas.get(i).getNome().equals(nome)) {
                receitas.remove(i);
                return true;
            }
        }
        return false;
    }

    public void adicionaReceita(Receita receita) {
        receitas.add(receita);
    }

    public List<Gasto> getGastos() {
        return new ArrayList<>(gastos);
    }

    public List<Receita> getReceitas() {
        return new ArrayList<>(receitas);
    }
    public List<Object> getTransacoes() {
        List<Object> transacoes = new ArrayList<>();
        transacoes.addAll(gastos);
        transacoes.addAll(receitas);
        return transacoes;
    }

    public double calculaTotal() {
        double total = 0.0;

        for (Gasto gasto : gastos) {
            total -= gasto.getValor();
        }

        for (Receita receita : receitas) {
            total += receita.getValor();
        }

        return total;
    }
}
