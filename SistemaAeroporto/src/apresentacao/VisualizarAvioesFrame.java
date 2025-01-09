package apresentacao;

import dados.Aviao;
import dados.Modelo;
import negocio.Sistema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizarAvioesFrame extends JFrame {

    private Sistema sistema;
    private DefaultTableModel model;
    private JTable tabela;
    private JComboBox<String> comboBoxModelosFiltro;

    public VisualizarAvioesFrame(Sistema sistema) {
        this.sistema = sistema;
        initialize();
    }

    private void initialize() {
        setTitle("Visualizar Aviões");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel(new GridLayout(1, 2));
        JLabel lblModeloFiltro = new JLabel("Modelo:");
        comboBoxModelosFiltro = new JComboBox<>();
        comboBoxModelosFiltro.addItem("Todos");

        for (Modelo modelo : sistema.getModelos()) {
            comboBoxModelosFiltro.addItem(modelo.getNome());
        }

        filterPanel.add(lblModeloFiltro);
        filterPanel.add(comboBoxModelosFiltro);

        add(filterPanel, BorderLayout.NORTH);

        comboBoxModelosFiltro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                visualizarAvioes();
            }
        });

        model = new DefaultTableModel(new String[]{"ID", "Modelo", "Número Registro"}, 0);
        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        visualizarAvioes();
    }

    private void visualizarAvioes() {
        model.setRowCount(0); // Limpar a tabela

        String modeloFiltro = (String) comboBoxModelosFiltro.getSelectedItem();
        for (Aviao aviao : sistema.getAvioes()) {
            if (modeloFiltro.equals("Todos") || aviao.getModelo().equals(modeloFiltro)) {
                model.addRow(new Object[]{aviao.getId(), aviao.getModelo(), aviao.getNumeroRegistro()});
            }
        }

        revalidate();
        repaint();
    }
}
