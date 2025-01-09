package apresentacao;

import dados.Empregado;
import dados.Tecnico;
import dados.Controlador;
import negocio.Sistema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizarEmpregadosFrame extends JFrame {

    private Sistema sistema;
    private DefaultTableModel model;
    private JTable tabela;
    private JComboBox<String> comboBoxTipoFiltro;

    public VisualizarEmpregadosFrame(Sistema sistema) {
        this.sistema = sistema;
        initialize();
    }

    private void initialize() {
        setTitle("Visualizar Empregados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel(new GridLayout(1, 2));
        JLabel lblTipoFiltro = new JLabel("Tipo:");
        comboBoxTipoFiltro = new JComboBox<>();
        comboBoxTipoFiltro.addItem("Todos");
        comboBoxTipoFiltro.addItem("Tecnico");
        comboBoxTipoFiltro.addItem("Controlador");
        comboBoxTipoFiltro.addItem("Outros");

        filterPanel.add(lblTipoFiltro);
        filterPanel.add(comboBoxTipoFiltro);

        add(filterPanel, BorderLayout.NORTH);

        comboBoxTipoFiltro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                visualizarEmpregados();
            }
        });

        model = new DefaultTableModel(new String[]{"ID", "Número Matrícula", "Nome", "Tipo"}, 0);
        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        visualizarEmpregados();
    }

    private void visualizarEmpregados() {
        model.setRowCount(0); // Limpar a tabela

        String tipoFiltro = (String) comboBoxTipoFiltro.getSelectedItem();
        if (tipoFiltro.equals("Todos")) {
            model.setColumnIdentifiers(new String[]{"ID", "Número Matrícula", "Nome", "Tipo"});
        } else if (tipoFiltro.equals("Tecnico")) {
            model.setColumnIdentifiers(new String[]{"ID", "Número Matrícula", "Nome", "Tipo", "Endereço", "Telefone", "Salário", "Modelos"});
        } else if (tipoFiltro.equals("Controlador")) {
            model.setColumnIdentifiers(new String[]{"ID", "Número Matrícula", "Nome", "Tipo", "Data de Exame"});
        } else {
            model.setColumnIdentifiers(new String[]{"ID", "Número Matrícula", "Nome", "Tipo", "Detalhes"});
        }

        for (Empregado empregado : sistema.getEmpregados()) {
            if (tipoFiltro.equals("Todos")) {
                model.addRow(new Object[]{empregado.getId(), empregado.getNumeroMatricula(), empregado.getNome(), empregado.getTipo()});
            } else if (empregado instanceof Tecnico && tipoFiltro.equals("Tecnico")) {
                Tecnico tecnico = (Tecnico) empregado;
                model.addRow(new Object[]{tecnico.getId(), tecnico.getNumeroMatricula(), tecnico.getNome(),"Tecnico",tecnico.getEndereco(), tecnico.getTelefone(), tecnico.getSalario(), String.join(", ", tecnico.getModelosPeritos())});
            } else if (empregado instanceof Controlador && tipoFiltro.equals("Controlador")) {
                Controlador controlador = (Controlador) empregado;
                model.addRow(new Object[]{controlador.getId(), controlador.getNumeroMatricula(), controlador.getNome(),"Controlador", controlador.getDataExame()});
            } else if (tipoFiltro.equals(empregado.getTipo())) {
                model.addRow(new Object[]{empregado.getId(), empregado.getNumeroMatricula(), empregado.getNome(), empregado.getTipo()});
            }
        }

        revalidate();
        repaint();
    }
}
