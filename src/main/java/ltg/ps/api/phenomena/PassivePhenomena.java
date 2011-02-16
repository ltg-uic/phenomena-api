/*
 * Created Nov 22, 2010
 */
package ltg.ps.api.phenomena;


/**
 * This kind of phenomena just stores its state which can be updated 
 * either by using the web portal or, in a limited way, by the clients.
 *
 * @author Gugo
 */
public abstract class PassivePhenomena extends Phenomena {

	/**
	 * Assigns a unique ID to the phenomenon. 
	 * Please use a meaningful names for the instances 
	 * like "[PhenomenaName]_[School/Class]".
	 * @param instanceId
	 */
	public PassivePhenomena(String instanceId) {
		super(instanceId);
	}
	
	
	
	/**
	 * Starts serving the phenomena.
	 */
	public synchronized void start() {
		running = true;
	}

	

	/**
	 * Stops serving the phenomena.
	 */
	public synchronized void stop() {
		running = false;
	}

	
	
	@Override
	abstract public void configure(String configXML);
	
	
	
	@Override
	abstract public void restore();

	
}
