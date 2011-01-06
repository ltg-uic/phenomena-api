/*
 * Created Oct 29, 2010
 */
package ltg.ps.api;

import org.dom4j.Element;

/**
 * TODO Description
 *
 * @author Gugo
 */
public interface Command {
	public void execute();
	public void parse(Element xml);
	public String toXML();

}
