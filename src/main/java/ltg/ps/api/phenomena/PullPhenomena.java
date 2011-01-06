/*
 * Created Nov 22, 2010
 */
package ltg.ps.api.phenomena;


/**
 * TODO Description
 *
 * @author Gugo
 */
public abstract class PullPhenomena extends Phenomena {

	/**
	 * @param instanceId
	 */
	public PullPhenomena(String instanceId) {
		super(instanceId);
	}
	
	
	/**
	 * Starts serving the phenomena. This means updated
	 * data will be loaded by the update function.
	 */
	public synchronized void start() {
		running = true;
	}


	/**
	 * Stops serving the phenomena. This means no updated
	 * data will be loaded after this function is called and 
	 * before start is called again.
	 */
	public synchronized void stop() {
		running = false;
	}

	
	@Override
	abstract public void configure(String configXML);
	
	
	@Override
	abstract public void restore();

	
}
