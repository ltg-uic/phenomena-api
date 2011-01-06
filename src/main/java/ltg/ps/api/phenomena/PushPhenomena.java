/*
 * Created Nov 22, 2010
 */
package ltg.ps.api.phenomena;


/**
 * TODO Description
 *
 * @author Gugo
 */
public abstract class PushPhenomena extends Phenomena {

	// Simulation updater
	private PhenomenaUpdater updater = null;


	/**
	 * @param instanceId
	 */
	public PushPhenomena(String instanceId) {
		super(instanceId);
	}



	/**
	 * Inner class whose only duty is to update the phenomena model.
	 * This class never changes because the update cycle is the same 
	 * for all the phenomena.
	 *
	 * @author Gugo
	 */
	private final class PhenomenaUpdater extends Thread {

		// Sleep time in seconds
		private int sleepTime = 5;

		/**
		 * Builds a thread with a certain name.
		 * @param thread name
		 */
		private PhenomenaUpdater(String id) {
			super(id);
		}


		/**
		 * This performs the main update cycle. 
		 * The cycle is: update, notify the observers and
		 * then finally sleep for a while.
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
				PushPhenomena.this.setChanged();
				PushPhenomena.this.notifyObservers();
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
			log.info("Started "+this.instanceName);
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
			log.info("Stopped "+this.instanceName);
		}
	}
	

	public synchronized int getSleepTime() {
		return updater.sleepTime;
	}


	/**
	 * Sets the sleep time in seconds.
	 * @param sleepTime sleep time
	 */
	public synchronized void setSleepTime(int sleepTime) {
		updater.sleepTime = sleepTime;
	}
	

	@Override
	abstract public void configure(String configXML);


	@Override
	abstract public void restore();



	/**
	 * This function is called once for update cycle and is
	 * in charge of actually performing the update of the 
	 * model (i.e. data stored in the class). 
	 */
	abstract protected void update() throws InterruptedException;


}
