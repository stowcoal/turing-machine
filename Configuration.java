public class Configuration
{
    private String startConfig;
    private char readSymbol;
    private char direction;
    private char writeSymbol;
    private String finalConfig;
    public Configuration(String start, char read, char dir, char write, String fin)
    {
	startConfig = start;
	readSymbol = read;
	direction = dir;
	writeSymbol = write;
	finalConfig = fin;
    }
    public Configuration()
    {
	// error configuration
	startConfig = "";
	readSymbol = 'a';
	direction = 'r';
	writeSymbol = 'e';
	finalConfig = "qf";
    }
    public boolean isConfigToRun(String config, char symbol)
    {
	return ((symbol == readSymbol) && (config.equals(startConfig)));
    }
    public char symbolToWrite()
    {
	return writeSymbol;
    }
    public String nextConfiguration()
    {
	return finalConfig;
    }
    public void printConfigDetails()
    {
	String stringToPrint = startConfig + ", " + readSymbol + ", " + direction + ", " + writeSymbol + ", " + finalConfig;
	System.out.println(stringToPrint);
    }
    public char moveDirection()
    {
	return direction;
    }
}