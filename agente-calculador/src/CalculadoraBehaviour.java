package entrega;
import entrega.Operaciones;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;


public class CalculadoraBehaviour extends CyclicBehaviour{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	private ACLMessage mensaje;
	private ACLMessage respuesta;

	public CalculadoraBehaviour(Agent a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public void action() {
		
		this.setMensaje(this.getAgent().receive()); 
		
		if (this.getMensaje() != null && this.getMensaje().getPerformative() == ACLMessage.REQUEST){
			String cuerpoMsj = this.getMensaje().getContent();
			this.setRespuesta(this.getMensaje().createReply());
			this.getRespuesta().setPerformative(ACLMessage.INFORM);
			
			if (Operaciones.expresionValida(cuerpoMsj)){
				this.getRespuesta().setContent(String.valueOf(Operaciones.calcular(cuerpoMsj)));
			}
			else{
				this.getRespuesta().setContent("Expresion no valida");
			}
			myLogger.log(Logger.INFO, this.getRespuesta().getContent());
			this.getAgent().send(this.getRespuesta());				
		}
		else{
			this.block();
		}
			
	}

	public ACLMessage getMensaje() {
		return mensaje;
	}

	public void setMensaje(ACLMessage mensaje) {
		this.mensaje = mensaje;
	}

	public ACLMessage getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(ACLMessage respuesta) {
		this.respuesta = respuesta;
	}	
		
	
}
