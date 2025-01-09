
public class Quadrado extends Forma {
    public Quadrado(Cor cor){
        super(cor);
    }

    public void desenhar(){
        System.out.println("Desenhando um quadrado: ");
        cor.pintar();
    }
}
