package apresentacao;

import dados.*;
import negocio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaFinancas {
    private static Login login;
    private static Sistema sistema;
    private static JFrame frame;

    public static void main(String[] args) {
        inicializarDados();

        frame = new JFrame("Sistema Financeiro");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel saldoLabel = new JLabel("Saldo: ");
        JTextField saldoField = new JTextField(10);
        saldoField.setEditable(false);

        JButton adicionarGastoButton = new JButton("Adicionar Gasto");
        JButton adicionarReceitaButton = new JButton("Adicionar Receita");
        JButton removerGastoButton = new JButton("Remover Gasto");
        JButton removerReceitaButton = new JButton("Remover Receita");
        JButton visualizarTransacoesButton = new JButton("Visualizar Transações");

        panel.add(saldoLabel);
        panel.add(saldoField);
        panel.add(adicionarGastoButton);
        panel.add(adicionarReceitaButton);
        panel.add(removerGastoButton);
        panel.add(removerReceitaButton);
        panel.add(visualizarTransacoesButton);

        frame.add(panel);
        frame.setVisible(false);

        fazerLogin();
        atualizarSaldo(saldoField);

        adicionarGastoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarGasto();
                atualizarSaldo(saldoField);
            }
        });

        adicionarReceitaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarReceita();
                atualizarSaldo(saldoField);
            }
        });

        removerGastoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerGasto();
                atualizarSaldo(saldoField);
            }
        });

        removerReceitaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerReceita();
                atualizarSaldo(saldoField);
            }
        });

        visualizarTransacoesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                visualizarTransacoes();
            }
        });
    }

    private static void fazerLogin() {
        login = new Login();

        String nomeUsuario = JOptionPane.showInputDialog(frame, "Digite o nome de usuário:");
        String senha = JOptionPane.showInputDialog(frame, "Digite a senha:");

        if (login.verificarCredenciais(nomeUsuario, senha)) {
            JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciais inválidas. Tente novamente.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            fazerLogin();
        }
    }

    private static void adicionarGasto() {
        String nome = JOptionPane.showInputDialog(frame, "Digite o nome do gasto:");
        String descricao = JOptionPane.showInputDialog(frame, "Digite a descrição do gasto:");
        double valor = 0;
        try {
            valor = Double.parseDouble(JOptionPane.showInputDialog(frame, "Digite o valor do gasto:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Valor inválido. Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Gasto gasto = new Gasto(nome, descricao, descricao, descricao, valor, null);
        sistema.adicionaGasto(gasto);
    }

    private static void adicionarReceita() {
        String nome = JOptionPane.showInputDialog(frame, "Digite o nome da receita:");
        String descricao = JOptionPane.showInputDialog(frame, "Digite a descrição da receita:");
        double valor = 0;
        try {
            valor = Double.parseDouble(JOptionPane.showInputDialog(frame, "Digite o valor da receita:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Valor inválido. Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Receita receita = new Receita(nome, descricao, valor, null);
        sistema.adicionaReceita(receita);
    }

    private static void removerGasto() {
        String nome = JOptionPane.showInputDialog(frame, "Digite o nome do gasto a ser removido:");
        boolean sucesso = sistema.removeGasto(nome);
        if (sucesso) {
            JOptionPane.showMessageDialog(frame, "Gasto removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Gasto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removerReceita() {
        String nome = JOptionPane.showInputDialog(frame, "Digite o nome da receita a ser removida:");
        boolean sucesso = sistema.removeReceita(nome);
        if (sucesso) {
            JOptionPane.showMessageDialog(frame, "Receita removida com sucesso!");
        } else {
            JOptionPane.showMessageDialog(frame, "Receita não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void visualizarTransacoes() {
        java.util.List<Object> transacoes = sistema.getTransacoes();
        StringBuilder mensagem = new StringBuilder("Transações cadastradas:\n");
        for (Object transacao : transacoes) {
            if (transacao instanceof Gasto) {
                Gasto gasto = (Gasto) transacao;
                mensagem.append("Gasto: ").append(gasto.getNome()).append(": ").append(gasto.getDescricao()).append(" - R$").append(gasto.getValor()).append("\n");
            } else if (transacao instanceof Receita) {
                Receita receita = (Receita) transacao;
                mensagem.append("Receita: ").append(receita.getNome()).append(": ").append(receita.getDescricao()).append(" - R$").append(receita.getValor()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(frame, mensagem.toString());
    }

    private static void inicializarDados() {
        login = new Login();
        sistema = new Sistema();
    }

    private static void atualizarSaldo(JTextField saldoField) {
        double saldo = sistema.calculaTotal();
        saldoField.setText(String.valueOf(saldo));
    }
}