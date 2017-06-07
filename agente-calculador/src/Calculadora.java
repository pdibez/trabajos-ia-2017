package entrega;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;


public class Calculadora extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  CalculadoraBehaviour behaviour;
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	
	protected void setup(){
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Calculadora");
		sd.setName(getName());
		sd.setOwnership("Los4M^2.P.S");
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			this.setBehavior(new CalculadoraBehaviour(this));
			this.addBehaviour(this.getBehavior());
			DFService.register(this, dfd);
		} 	
		catch (FIPAException e) {
			myLogger.log(Logger.SEVERE, "Agent "+getLocalName()+" - Cannot register with DF", e);
		}

		
	}
	
	public void setBehavior(CalculadoraBehaviour behavior){
		this.behaviour=behavior;
	}
	
	public CalculadoraBehaviour getBehavior(){
		return this.behaviour;
	}

}