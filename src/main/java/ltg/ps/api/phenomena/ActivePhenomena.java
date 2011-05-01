/*
 * Created Nov 22, 2010
 */
package ltg.ps.api.phenomena;


/**
 * This kind of phenomena updates its state by itself with a 
 * regular interval, in addition to changes coming from the web
 * portal and the clients. 
 *
 * @author Gugo
 */
public abstract class ActivePhenomena extends Phenomena {

	// State updater
	private PhenomenaUpdater updater = null;
	// Sleep time in seconds
	private int sleepTime = 5;


	
	/**
	 * Assigns a unique ID to the phenomenon. 
	 * Please use a meaningful names for the instances 
	 * like "[PhenomenaName]_[School/Class]".
	 * @param instanceId
	 */
	public ActivePhenomena(String instanceId) {
		super(instanceId);
	}



	/**
	 * Inner class whose only duty is to update the phenomenon state.
	 * This class never changes because the update cycle is the same 
	 * for all the phenomena.
	 *
	 * @author Gugo
	 */
	private final class PhenomenaUpdater extends Thread {

		/**
		 * Private constructor: only ActivePhenomena can instantiate this class.
		 * @param thread name
		 */
		private PhenomenaUpdater(String id) {
			super(id);
		}


		/**
		 * This performs the main update cycle. 
		 * The cycle is: update, notify the observers, sleep for a while.
		 */
		public void run() {
			while(running) {
				// Updates the phenomena
				try {
					update();
				} catch (InterruptedException e) {
					break;
				}
				// Notifies the observers (if any)
				ActivePhenomena.this.setChanged();
				ActivePhenomena.this.notifyObservers();
				// Sleeps for a while
				try {
					sleep(sleepTime*1000);
				} catch (InterruptedException e) {
					break;
				}
			}
		}

	}

	

	/**
	 * Starts serving the phenomena. This means updated
	 * data will be loaded by the update function.
	 */
	public synchronized void start() {
		if (updater == null || !updater.isAlive()) {
			this.updater = new PhenomenaUpdater(instanceName);
			running = true;
			updater.start();
		}
	}


	/**
	 * Stops serving the phenomena. This means no updated
	 * data will be loaded after this function is called and 
	 * before start is called again.
	 */
	public synchronized void stop() {
		if (updater!=null && updater.isAlive()) {
			running = false;
			updater.interrupt();
			updater = null;
		}
	}
	

	
	/**
	 * Returns the sleepTime of the phenomenon in seconds.
	 * This is the interval (in seconds) the phenomenon
	 * is inactive between self updates. 
	 *
	 * @return sleep time in seconds
	 */
	public synchronized int getSleepTime() {
		return sleepTime;
	}


	
	/**
	 * Sets the sleep time of the phenomenon in seconds.
	 * This is the interval (in seconds) the phenomenon
	 * is inactive between self updates.
	 * 
	 * @param sleepTime sleep time in seconds
	 */
	public synchronized void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	

	
	@Override
	abstract public void configure(String configXML);

	

	@Override
	abstract public void restore();



	/**
	 * This function is called once for every update cycle and is
	 * in charge of actually performing the update of the 
	 * state of the phenomenon (i.e. data stored in the class). 
	 */
	abstract protected void update() throws InterruptedException;


}
