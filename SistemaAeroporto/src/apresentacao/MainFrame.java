package apresentacao;

import negocio.Sistema;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Sistema sistema;

    public MainFrame(Sistema sistema) {
        this.sistema = sistema;
        initialize();
    }

    private void initialize() {
        setTitle("Sistema de Aviação");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Aeroporto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(3, 1));

        JButton btnGerenciarAvioes = new JButton("Gerenciar Aviões");
        btnGerenciarAvioes.addActionListener(e -> {
            new AviaoFrame(sistema, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnGerenciarEmpregados = new JButton("Gerenciar Empregados");
        btnGerenciarEmpregados.addActionListener(e -> {
            new EmpregadoFrame(sistema, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnGerenciarTestes = new JButton("Gerenciar Testes");
        btnGerenciarTestes.addActionListener(e -> {
            new TesteFrame(sistema,this).setVisible(true);
            this.setVisible(false);
        });
        
        panelCentro.add(btnGerenciarAvioes);
        panelCentro.add(btnGerenciarEmpregados);
        panelCentro.add(btnGerenciarTestes);

        add(panelCentro, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        MainFrame mainFrame = new MainFrame(sistema);
        mainFrame.setVisible(true);
    }
}
