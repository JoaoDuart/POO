
public class Circulo extends Forma{
    public Circulo(Cor cor){
        super(cor);
    }

    public void desenhar(){
        System.out.println("Desenhando um Circulo: ");
        cor.pintar();
    }
}
