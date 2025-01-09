public class Triangulo extends Forma{
    public Triangulo(Cor cor){
        super(cor);
    }

    public void desenhar(){
        System.out.println("Desenhando um triangulo: ");
        cor.pintar();
    }
}
