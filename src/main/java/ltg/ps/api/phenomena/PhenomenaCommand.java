/*
 * Created Jul 21, 2010
 */
package ltg.ps.api.phenomena;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ltg.ps.api.Command;


/**
 * Represents a command sent to a specific phenomena.
 *
 * @author Gugo
 */
public abstract class PhenomenaCommand implements Command{
	
	// Logger
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	// Target phenomena
	protected Phenomena target = null;
	// Origin of the command
	protected String from = null;
	
	
	/**
	 * Constructor.
	 * The phenomena passed as input is in charge of handling this command.
	 * 
	 * @param target Phenomena that will handle the command
	 * @param from The window that generated the command
	 */
	public PhenomenaCommand(Phenomena target, String from) {
		this.target = target;
		this.from = from;
	}

}
