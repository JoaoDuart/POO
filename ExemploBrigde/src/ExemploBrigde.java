
public class ExemploBrigde {
    public static void main(String[] args) {
        Forma quadradoAzul = new Quadrado(new Azul());
        Forma circuloAzul = new Circulo(new Azul());
        Forma quadradoVermelho = new Quadrado(new Vermelho());
        Forma circuloVermelho = new Circulo(new Vermelho());

        quadradoAzul.desenhar(); 
        circuloAzul.desenhar();    
        quadradoVermelho.desenhar();
        circuloVermelho.desenhar();
    }
}
