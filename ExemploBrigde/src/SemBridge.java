public class SemBridge {
    public static void main(String[] args) {
        QuadradoAzul quadradoAzul = new QuadradoAzul();
        CirculoAzul circuloAzul = new CirculoAzul();
        QuadradoVermelho quadradoVermelho = new QuadradoVermelho();
        CirculoVermelho circuloVermelho = new CirculoVermelho();

        quadradoAzul.desenhar();   
        circuloAzul.desenhar();    
        quadradoVermelho.desenhar(); 
        circuloVermelho.desenhar(); 
    }
}
