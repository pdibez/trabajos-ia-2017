package entrega;
import jade.core.*;

public class Calculador extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AID destino = new AID("calculadora@192.168.1.37:1099/JADE",AID.ISGUID);
	
	protected void setup(){
		addBehaviour(new CalculadorBehaviour(this,this.destino));
		destino.addAddresses("http://192.168.1.37:7778/acc");
		
	}
	


}