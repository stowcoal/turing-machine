import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

public class TuringMachine{
    private Tape tape;
    private ConfigurationTable configs;
    private String state; 
    public TuringMachine()
    {
	tape = new Tape();
	configs = new ConfigurationTable();
	state = "q0";
    }
    public TuringMachine(String tapeStartString)
    {
	tape = new Tape(tapeStartString);
	configs = new ConfigurationTable();
	state = "q0";
	String fileLocation = System.getProperty("user.dir") + "/instructions.in";
	try
	    {
		String currentLine;
		BufferedReader br = new BufferedReader( new FileReader( fileLocation ));
			while( (currentLine = br.readLine()) != null )
			    {
				String[] configArray = currentLine.split(" ");
				String s = configArray[0];
				char r = configArray[1].charAt(0);
				char d = configArray[2].charAt(0);
				char w = configArray[3].charAt(0);
				String f = configArray[4];
				
				Configuration configToAdd = new Configuration(s, r, d, w, f);
				configs.addConfiguration(configToAdd);
			    }
	    }
	catch ( IOException e )
	    {
		System.out.println("error: file not found!");
	    }
    }
    public void nextStep()
    {
	Configuration c = configs.getConfiguration(state, read());
	printTape();
	//	c.printConfigDetails();
	//	System.out.println(state);
	//	System.out.println(tape.getHeader().readSymbol());
	write( c.symbolToWrite() );
	if ( c.moveDirection() == 'r' )
	    moveHeaderRight();
	else if ( c.moveDirection() == 'l' )
	    moveHeaderLeft();
	else 
	    System.out.println("invalid direction");
	state = c.nextConfiguration();
    }
    public void addConfiguration(Configuration c)
    {
	configs.addConfiguration(c);
    }
    public void run(String finalState)
    {
	while( !state.equals(finalState) )
	    {
		nextStep();
	    }
	printTape();
    }
    public void printConfigs()
    {
	configs.printConfigurations();
    }
    public void printTape()
    {
	System.out.println(tape.printTape());
    }
    public void moveHeaderRight()
    {
	tape.moveRight();
    }
    public void moveHeaderLeft()
    {
	tape.moveLeft();
    }
    public void addSectionAtFront(char symbol)
    {
	tape.addSectionAtFront(symbol);
    }
    public void addSectionAtFront()
    {
	tape.addSectionAtFront();
    }
    public void addSectionAtEnd(char symbol)
    {
	tape.addSectionAtEnd(symbol);
    }
    public void addSectionAtEnd()
    {
	tape.addSectionAtEnd();
    }
    public void write(char symbolToWrite)
    {
	tape.getHeader().writeSymbol(symbolToWrite);
    }
    public char read()
    {
	return tape.getHeader().readSymbol();
    }
}