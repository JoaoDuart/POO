package apresentacao;

import dados.Teste;
import dados.Tecnico;
import dados.Aviao;
import negocio.Sistema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TesteFrame extends JFrame {

    private Sistema sistema;
    private JTable tabela;
    private DefaultTableModel model;
    private JFrame parent;

    public TesteFrame(Sistema sistema , JFrame parent) {
        this.sistema = sistema;
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciar Testes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 1));

        JButton btnAdicionarTeste = new JButton("Adicionar Teste");
        btnAdicionarTeste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogAdicionarTeste();
            }
        });

        JButton btnVisualizarTestes = new JButton("Visualizar Testes");
        btnVisualizarTestes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogVisualizarTestes();
            }
        });

        JButton btnAdicionarInfoTeste = new JButton("Adicionar Informações ao Teste");
        btnAdicionarInfoTeste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogAdicionarInfoTeste();
            }
        });

        JButton btnRemoverTeste = new JButton("Remover Teste");
        btnRemoverTeste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerTeste();
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });

        panel.add(btnAdicionarTeste);
        panel.add(btnVisualizarTestes);
        panel.add(btnAdicionarInfoTeste);
        panel.add(btnRemoverTeste);
        panel.add(btnVoltar);

        add(panel, BorderLayout.NORTH);
    }

    private void abrirDialogAdicionarTeste() {
        JDialog dialog = new JDialog(this, "Adicionar Teste", true);
        dialog.setLayout(new GridLayout(4, 2));

        JLabel lblNumeroANAC = new JLabel("Número ANAC:");
        JTextField campoNumeroANAC = new JTextField();

        JLabel lblNumeroTeste = new JLabel("Número do Teste:");
        JTextField campoNumeroTeste = new JTextField();

        JLabel lblAviao = new JLabel("Avião:");
        JComboBox<Aviao> campoAviao = new JComboBox<>();
        for (Aviao aviao : sistema.getAvioes()) {
            campoAviao.addItem(aviao);
        }

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(System.currentTimeMillis());
                String numeroANAC = campoNumeroANAC.getText();
                String numeroTeste = campoNumeroTeste.getText();
                Aviao aviao = (Aviao) campoAviao.getSelectedItem();

                Teste teste = new Teste(id, numeroANAC, numeroTeste, aviao);
                sistema.adicionarTeste(teste);
                JOptionPane.showMessageDialog(null, "Teste adicionado com sucesso!");
                dialog.dispose();
            }
        });

        dialog.add(lblNumeroANAC);
        dialog.add(campoNumeroANAC);
        dialog.add(lblNumeroTeste);
        dialog.add(campoNumeroTeste);
        dialog.add(lblAviao);
        dialog.add(campoAviao);
        dialog.add(new JLabel());
        dialog.add(btnSalvar);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void abrirDialogAdicionarInfoTeste() {
        JDialog dialog = new JDialog(this, "Adicionar Informações ao Teste", true);
        dialog.setLayout(new GridLayout(5, 2));

        JLabel lblId = new JLabel("ID do Teste:");
        JTextField campoId = new JTextField();

        JLabel lblDataRealizacao = new JLabel("Data de Realização:");
        JTextField campoDataRealizacao = new JTextField();

        JLabel lblHorasGastas = new JLabel("Número de Horas Gastas:");
        JTextField campoHorasGastas = new JTextField();

        JLabel lblTecnicos = new JLabel("Técnico(s):");
        JPanel tecnicosPanel = new JPanel(new GridLayout(0, 1));
        List<Tecnico> tecnicos = sistema.getTecnicos();
        for (Tecnico tecnico : tecnicos) {
            JCheckBox checkBox = new JCheckBox(tecnico.getNome());
            checkBox.setActionCommand(tecnico.getId());
            tecnicosPanel.add(checkBox);
        }

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = campoId.getText();
                String dataRealizacao = campoDataRealizacao.getText();
                double horasGastas = Double.parseDouble(campoHorasGastas.getText());
                List<String> tecnicosSelecionados = new ArrayList<>();
                for (Component comp : tecnicosPanel.getComponents()) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.isSelected()) {
                        tecnicosSelecionados.add(checkBox.getActionCommand());
                    }
                }

                sistema.adicionarInformacoesTeste(id, dataRealizacao, horasGastas, tecnicosSelecionados);
                JOptionPane.showMessageDialog(null, "Informações adicionadas com sucesso!");
                dialog.dispose();
            }
        });

        dialog.add(lblId);
        dialog.add(campoId);
        dialog.add(lblDataRealizacao);
        dialog.add(campoDataRealizacao);
        dialog.add(lblHorasGastas);
        dialog.add(campoHorasGastas);
        dialog.add(lblTecnicos);
        dialog.add(new JScrollPane(tecnicosPanel));
        dialog.add(new JLabel());
        dialog.add(btnSalvar);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void abrirDialogVisualizarTestes() {
        JDialog dialog = new JDialog(this, "Visualizar Testes", true);
        dialog.setLayout(new BorderLayout());

        JComboBox<String> comboBoxFiltro = new JComboBox<>(new String[]{"Todos", "Por Técnico", "Por Data"});
        comboBoxFiltro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                visualizarTestes(comboBoxFiltro, dialog);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(comboBoxFiltro, BorderLayout.NORTH);

        dialog.add(panel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Número ANAC", "Número do Teste", "Avião", "Técnico(s)", "Data de Realização", "Horas Gastas"}, 0);
        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        dialog.add(scrollPane, BorderLayout.CENTER);

        visualizarTestes(comboBoxFiltro, dialog);

        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void visualizarTestes(JComboBox<String> comboBoxFiltro, JDialog dialog) {
        model.setRowCount(0); // Limpar a tabela

        String filtro = (String) comboBoxFiltro.getSelectedItem();

        for (Teste teste : sistema.getTestes()) {
            if (filtro.equals("Todos")) {
                model.addRow(new Object[]{teste.getId(), teste.getNumeroANAC(), teste.getNumeroTeste(), teste.getAviao().getModelo(), String.join(", ", teste.getTecnicos()), teste.getDataRealizacao(), teste.getHorasGastas()});
            } else if (filtro.equals("Por Técnico") && teste.getTecnicos() != null && !teste.getTecnicos().isEmpty()) {
                model.addRow(new Object[]{teste.getId(), teste.getNumeroANAC(), teste.getNumeroTeste(), teste.getAviao().getModelo(), String.join(", ", teste.getTecnicos()), teste.getDataRealizacao(), teste.getHorasGastas()});
            } else if (filtro.equals("Por Data") && teste.getDataRealizacao() != null && !teste.getDataRealizacao().isEmpty()) {
                model.addRow(new Object[]{teste.getId(), teste.getNumeroANAC(), teste.getNumeroTeste(), teste.getAviao().getModelo(), String.join(", ", teste.getTecnicos()), teste.getDataRealizacao(), teste.getHorasGastas()});
            }
        }

        dialog.revalidate();
        dialog.repaint();
    }

    private void removerTeste() {
        JDialog dialog = new JDialog(this, "Remover Avião", true);
        dialog.setLayout(new GridLayout(2, 2));

        JLabel lblId = new JLabel("Numero do Teste:");
        JTextField campoId = new JTextField();

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroTeste = campoId.getText();
                sistema.removerTeste(numeroTeste);
                JOptionPane.showMessageDialog(null, "Teste removido com sucesso!");
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
