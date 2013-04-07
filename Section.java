public class Section{
    private char symbol;
    private Section right;
    private Section left;
    public Section(char s)
    {
	symbol = s;
	right = null;
	left = null;
    }
    public Section()
    {
	symbol = '*';
	right = null;
	left = null;
    }
    public Section getRight()
    {
	return right;
    }
    public Section getLeft()
    {
	return left;
    }
    public void setRight(Section s)
    {
	right = s;
    }
    public void setLeft(Section s)
    {
	left = s;
    }
    public char readSymbol()
    {
	return symbol;
    }
    public void writeSymbol(char symbolToWrite)
    {
	symbol = symbolToWrite;
    }    
}