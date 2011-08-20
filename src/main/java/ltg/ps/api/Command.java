/*
 * Created Oct 29, 2010
 */
package ltg.ps.api;

import org.dom4j.Element;

/**
 * Represents a generic command.
 * This interface can be implemented to create more specific command types.
 *
 * @author Gugo
 */
public interface Command {
	
	public void execute();
	public void parse(Element xml);

}
