/*
 * Created Jun 22, 2009
 */
package ltg.ps.api.phenomena;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is the representation of a generic phenomena (a
 * meta-phenomena).
 * When you need to create a new one extend this class.
 * See the "supported phenomena" section in the documentation 
 * for a detailed list of what kind of phenomena are currently supported.  
 *
 * @author Gugo
 */
public abstract class Phenomena extends Observable {

	// Logger
	protected final Logger log = LoggerFactory.getLogger(Phenomena.class);
	
	// Name of the phenomena
	protected String instanceName = null;
	// Phenomena windows
	protected List<PhenomenaWindow> phenWindows = null;
	// Is the phenomena being served?
	protected Boolean running = false;
	
	
	
	/**
	 * Private empty constructor to prevent instantiation from other classes 
	 */
	private Phenomena() {
	}

	
	
	/**
	 * Assigns the name to the phenomena instance. 
	 * Please use a meaningful names for the instances 
	 * like "[PhenomenaName]@[School/Class]".
	 * @param instanceId
	 */
	public Phenomena(String instanceId) {
		this();
		this.instanceName = instanceId;
		this.phenWindows = new ArrayList<PhenomenaWindow>();
	}
	
	
	public String getInstanceName() {
		return instanceName;
	}
	
	
	public List<PhenomenaWindow> getWindows() {
		return phenWindows;
	}
	
	
	public synchronized boolean isRunning() {
		return running;
	}
		
	
    /**
     * This method must be implemented as synchronized
     * TODO Description
     *
     */
	public abstract void start();
	
	
	/**
	 * This method must be implemented as synchronized
	 * TODO Description
	 *
	 */
	public abstract void stop();
	
	
	abstract public void configure(String configXML);
	
	
	/**
	 * Restores the state of the phenomenon according to local data.
	 * This is used when the server is booted and the state of the phenomena needs to be restored
	 * according to the persisted local data. This is dependent on the persistence
	 * method the desinger of the phenomena is using.
	 */
	abstract public void restore();

}
