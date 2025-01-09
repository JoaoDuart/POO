import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora extends JFrame implements ActionListener {
    private JTextField display;
    private int primeiroNumero = 0;
    private int segundoNumero = 0;
    private char operacao;
    private boolean operacaoAtiva = false;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] botoes = {
            "7", "8", "9", "C", 
            "4", "5", "6", "/", 
            "1", "2", "3", "*", 
            "0", "+", "-", "=",
        };

        for (String i : botoes) {
            JButton botao = new JButton(i);
            botao.setFont(new Font("Arial", Font.BOLD, 30));
            botao.addActionListener(this);
            panel.add(botao);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
            if (operacaoAtiva = true) {
                display.setText(comando);
                operacaoAtiva = false;
            } else {
                display.setText(display.getText() + comando);
            }
        } 
        else if (comando.equals("C")) {
            display.setText("");
            primeiroNumero = 0;
            segundoNumero = 0;
            operacao = '\0';
        } 
        else if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            primeiroNumero = Integer.parseInt(display.getText());
            operacao = comando.charAt(0);
            operacaoAtiva = true;
        } 
        else if (comando.equals("=")) {
            segundoNumero = Integer.parseInt(display.getText());

            int resultado = 0;
            switch (operacao) {
                case '+':
                    resultado = primeiroNumero + segundoNumero;
                    break;
                case '-':
                    resultado = primeiroNumero - segundoNumero;
                    break;
                case '*':
                    resultado = primeiroNumero * segundoNumero;
                    break;
                case '/':
                    if (segundoNumero != 0) {
                        resultado = primeiroNumero / segundoNumero;
                    } else {
                        display.setText("Erro ");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(resultado));
            System.out.println("Resultado: " + resultado);

            primeiroNumero = resultado;
            operacaoAtiva = true;
        }
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
