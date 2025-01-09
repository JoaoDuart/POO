package apresentacao;

import dados.Aviao;
import dados.Modelo;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AviaoFrame extends JFrame {

    private Sistema sistema;
    private JFrame parent;

    public AviaoFrame(Sistema sistema, JFrame parent) {
        this.sistema = sistema;
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciar Aviões");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton btnAdicionarAviao = new JButton("Adicionar Avião");
        btnAdicionarAviao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogAdicionarAviao();
            }
        });

        JButton btnRemoverAviao = new JButton("Remover Avião");
        btnRemoverAviao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogRemoverAviao();
            }
        });

        JButton btnVisualizarAviao = new JButton("Visualizar Aviões");
        btnVisualizarAviao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VisualizarAvioesFrame(sistema).setVisible(true);
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });

        panel.add(btnAdicionarAviao);
        panel.add(btnRemoverAviao);
        panel.add(btnVisualizarAviao);
        panel.add(btnVoltar);

        add(panel, BorderLayout.CENTER);
    }

    private void abrirDialogAdicionarAviao() {
        JDialog dialog = new JDialog(this, "Adicionar Avião", true);
        dialog.setLayout(new GridLayout(4, 2));

        JLabel lblModelo = new JLabel("Modelo:");
        JComboBox<String> comboBoxModelos = new JComboBox<>();
        for (Modelo modelo : sistema.getModelos()) {
            comboBoxModelos.addItem(modelo.getNome());
        }

        JLabel lblNumeroRegistro = new JLabel("Número Registro:");
        JTextField campoNumeroRegistro = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(System.currentTimeMillis());
                String modelo = (String) comboBoxModelos.getSelectedItem();
                String numeroRegistro = campoNumeroRegistro.getText();

                Aviao aviao = new Aviao(id, modelo, numeroRegistro);
                sistema.adicionarAviao(aviao);
                JOptionPane.showMessageDialog(null, "Avião adicionado com sucesso!");
                dialog.dispose();
            }
        });

        dialog.add(lblModelo);
        dialog.add(comboBoxModelos);
        dialog.add(lblNumeroRegistro);
        dialog.add(campoNumeroRegistro);
        dialog.add(new JLabel());
        dialog.add(btnSalvar);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void abrirDialogRemoverAviao() {
        JDialog dialog = new JDialog(this, "Remover Avião", true);
        dialog.setLayout(new GridLayout(2, 2));

        JLabel lblId = new JLabel("ID do Avião:");
        JTextField campoId = new JTextField();

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroRegistro = campoId.getText();
                sistema.removerAviao(numeroRegistro);
                JOptionPane.showMessageDialog(null, "Avião removido com sucesso!");
                dialog.dispose();
            }
        });

        dialog.add(lblId);
        dialog.add(campoId);
        dialog.add(new JLabel());
        dialog.add(btnRemover);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
