package apresentacao;

import dados.Empregado;
import dados.Tecnico;
import dados.Controlador;
import dados.Modelo;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmpregadoFrame extends JFrame {

    private Sistema sistema;
    private JFrame parent;

    public EmpregadoFrame(Sistema sistema, JFrame parent) {
        this.sistema = sistema;
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciar Empregados");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton btnAdicionarEmpregado = new JButton("Adicionar Empregado");
        btnAdicionarEmpregado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogAdicionarEmpregado();
            }
        });

        JButton btnRemoverEmpregado = new JButton("Remover Empregado");
        btnRemoverEmpregado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDialogRemoverEmpregado();
            }
        });

        JButton btnVisualizarEmpregados = new JButton("Visualizar Empregados");
        btnVisualizarEmpregados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VisualizarEmpregadosFrame(sistema).setVisible(true);
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });

        panel.add(btnAdicionarEmpregado);
        panel.add(btnRemoverEmpregado);
        panel.add(btnVisualizarEmpregados);
        panel.add(btnVoltar);

        add(panel, BorderLayout.CENTER);
    }

    private void abrirDialogAdicionarEmpregado() {
        JDialog dialog = new JDialog(this, "Adicionar Empregado", true);
        dialog.setLayout(new GridLayout(10, 1));

        JLabel lblNumeroMatricula = new JLabel("Número Matrícula:");
        JTextField campoNumeroMatricula = new JTextField();

        JLabel lblNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField();

        JLabel lblTipo = new JLabel("Tipo:");
        JComboBox<String> comboBoxTipo = new JComboBox<>(new String[]{"Outros", "Controlador", "Técnico"});
        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField campoEndereco = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField campoTelefone = new JTextField();

        JLabel lblSalario = new JLabel("Salário:");
        JTextField campoSalario = new JTextField();

        JLabel lblModelos = new JLabel("Modelos:");
        JPanel panelModelos = new JPanel();
        panelModelos.setLayout(new BoxLayout(panelModelos, BoxLayout.Y_AXIS));
        List<JCheckBox> checkboxesModelos = new ArrayList<>();
        for (Modelo modelo : sistema.getModelos()) {
            JCheckBox checkbox = new JCheckBox(modelo.getNome());
            checkboxesModelos.add(checkbox);
            panelModelos.add(checkbox);
        }

        JLabel lblDataExame = new JLabel("Data do Exame:");
        JTextField campoDataExame = new JTextField();

        // Inicialmente, escondemos os campos específicos para Técnico e Controlador
        lblEndereco.setVisible(false);
        campoEndereco.setVisible(false);
        lblTelefone.setVisible(false);
        campoTelefone.setVisible(false);
        lblSalario.setVisible(false);
        campoSalario.setVisible(false);
        lblModelos.setVisible(false);
        panelModelos.setVisible(false);
        lblDataExame.setVisible(false);
        campoDataExame.setVisible(false);

        comboBoxTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboBoxTipo.getSelectedItem();
                if (tipo.equals("Técnico")) {
                    lblEndereco.setVisible(true);
                    campoEndereco.setVisible(true);
                    lblTelefone.setVisible(true);
                    campoTelefone.setVisible(true);
                    lblSalario.setVisible(true);
                    campoSalario.setVisible(true);
                    lblModelos.setVisible(true);
                    panelModelos.setVisible(true);
                    lblDataExame.setVisible(false);
                    campoDataExame.setVisible(false);
                } else if (tipo.equals("Controlador")) {
                    lblEndereco.setVisible(false);
                    campoEndereco.setVisible(false);
                    lblTelefone.setVisible(false);
                    campoTelefone.setVisible(false);
                    lblSalario.setVisible(false);
                    campoSalario.setVisible(false);
                    lblModelos.setVisible(false);
                    panelModelos.setVisible(false);
                    lblDataExame.setVisible(true);
                    campoDataExame.setVisible(true);
                } else {
                    lblEndereco.setVisible(false);
                    campoEndereco.setVisible(false);
                    lblTelefone.setVisible(false);
                    campoTelefone.setVisible(false);
                    lblSalario.setVisible(false);
                    campoSalario.setVisible(false);
                    lblModelos.setVisible(false);
                    panelModelos.setVisible(false);
                    lblDataExame.setVisible(false);
                    campoDataExame.setVisible(false);
                }
            }
        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(System.currentTimeMillis());
                String numeroMatricula = campoNumeroMatricula.getText();
                String nome = campoNome.getText();
                String tipo = (String) comboBoxTipo.getSelectedItem();

                Empregado empregado = null;
                if (tipo.equals("Técnico")) {
                    String endereco = campoEndereco.getText();
                    String telefone = campoTelefone.getText();
                    double salario = Double.parseDouble(campoSalario.getText());
                    List<String> modelos = new ArrayList<>();
                    for (JCheckBox checkbox : checkboxesModelos) {
                        if (checkbox.isSelected()) {
                            modelos.add(checkbox.getText());
                        }
                    }
                    empregado = new Tecnico(id, numeroMatricula, nome, endereco,telefone, salario, modelos);
                } else if (tipo.equals("Controlador")) {
                    String dataExame = campoDataExame.getText();
                    empregado = new Controlador(id, numeroMatricula, nome, dataExame);
                } else {
                    empregado = new Empregado(id, numeroMatricula, nome, tipo);
                }

                sistema.adicionarEmpregado(empregado);
                JOptionPane.showMessageDialog(null, "Empregado adicionado com sucesso!");
                dialog.dispose();
            }
        });

        dialog.add(lblNumeroMatricula);
        dialog.add(campoNumeroMatricula);
        dialog.add(lblNome);
        dialog.add(campoNome);
        dialog.add(lblTipo);
        dialog.add(comboBoxTipo);
        dialog.add(lblEndereco);
        dialog.add(campoEndereco);
        dialog.add(lblTelefone);
        dialog.add(campoTelefone);
        dialog.add(lblSalario);
        dialog.add(campoSalario);
        dialog.add(lblModelos);
        dialog.add(panelModelos);
        dialog.add(lblDataExame);
        dialog.add(campoDataExame);
        dialog.add(new JLabel());
        dialog.add(btnSalvar);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void abrirDialogRemoverEmpregado() {
        JDialog dialog = new JDialog(this, "Remover Empregado", true);
        dialog.setLayout(new GridLayout(2, 2));

        JLabel lblId = new JLabel("ID do Empregado:");
        JTextField campoId = new JTextField();

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = campoId.getText();
                sistema.removerEmpregado(id);
                JOptionPane.showMessageDialog(null, "Empregado removido com sucesso!");
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
