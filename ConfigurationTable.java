import java.util.Vector;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.*;

public class ConfigurationTable
{
    private Vector table;
    private int length;
    public ConfigurationTable()
    {
	table = new Vector(10,5);
    }
    public void addConfiguration(Configuration c)
    {
	table.addElement(c);
    }
    public Configuration getConfiguration(String config, char symbol)
    {
	Enumeration e = table.elements();
	boolean found = false;
	Configuration configurationToReturn = new Configuration();
	while (e.hasMoreElements() && !found)
	    {
		
		configurationToReturn = (Configuration)e.nextElement();
		found = configurationToReturn.isConfigToRun(config, symbol);
	    }
	if ( found )
	    return configurationToReturn;
	else
	    {
		System.out.println("configuration does not exist");
		return configurationToReturn;
	    }
    }
    public void printConfigurations()
    {
	Enumeration e = table.elements();
	while (e.hasMoreElements())
	    {
		((Configuration)e.nextElement()).printConfigDetails();
	    }
    }
    public void printConfigurations(JTable t)
    {
	Enumeration e = table.elements();
	while (e.hasMoreElements())
	    {
		((Configuration)e.nextElement()).printConfigDetails(t);
	    }
    }

}
