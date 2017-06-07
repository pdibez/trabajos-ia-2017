package entrega;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class CalculadorBehaviour extends TickerBehaviour{
	private static final long serialVersionUID = 1L;
	private static int MAXALEATORIO=1; 
	private static int MINALEATORIO=999;
	
	private AID destino;
	private ACLMessage mensaje;

	
	public CalculadorBehaviour(Agent a, AID destino){
		super(a,1000);
		this.destino =destino;
		this.setMensaje(new ACLMessage(ACLMessage.REQUEST));
	}
		
	protected void onTick() {
		String operacion = String.valueOf(aleatorioInt(MINALEATORIO, MAXALEATORIO))
		+ aleatorioSigno() +String.valueOf(aleatorioInt(MINALEATORIO, MAXALEATORIO));
		this.getMensaje().setContent(operacion);
		this.getMensaje().addReceiver(destino);
		System.out.println("Enviando: " + this.getMensaje().getContent());
		myAgent.send(this.getMensaje());
	}

	int aleatorioInt(int min, int max)
	{
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}
	String aleatorioSigno(){
		switch (aleatorioInt(0,3)) {
		case 0:
			return "+";
		case 1:
			return "-";
		case 2:
			return "*";
		case 3:
			return "/";
		default:
			return "+";
		}

	}
	public ACLMessage getMensaje() {
		return mensaje;
	}

	public void setMensaje(ACLMessage mensaje) {
		this.mensaje = mensaje;
	} 


}
