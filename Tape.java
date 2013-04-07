public class Tape
{
    private Section front;
    private Section end;
    private Section header;

    public Tape()
    {
	end = null;
	front = null;
	header = null;
    }
    public Tape(String tapeStartString)
    {
	end = null;
	front = null;
	header = null;
	for(int i = 0; i < tapeStartString.length(); i++)
	    {
		addSectionAtEnd(tapeStartString.charAt(i));
	    }
    }
    public void addSectionAtEnd(char symbol)
    {
	if (header == null)
	    {
		header = new Section(symbol);
		front = header;
		end = header;
	    }
	else
	    {
		Section tempSection = end;
		end.setRight(new Section(symbol));
		end = end.getRight();
		end.setLeft(tempSection);
	    }
    }
    public void addSectionAtFront(char symbol)
    {
	if (header == null)
	    {
		header = new Section(symbol);
		front = header;
		end = header;
	    }
	else
	    {
		Section tempSection = front;
		front.setLeft(new Section(symbol));
		front = front.getLeft();
		front.setRight(tempSection);
	    }
    }
    public void addSectionAtEnd()
    {
	if(header == null)
	    {
		header = new Section();
		front = header;
		end = header;
	    }
	else
	    {
		Section tempSection = end;
		end.setRight(new Section());
		end = end.getRight();
		end.setLeft(tempSection);
	    }	
    }
    public void addSectionAtFront()
    {
	if (header == null)
	    {
		header = new Section();
		front = header;
		end = header;
	    }
	else
	    {
		Section tempSection = front;
		front.setLeft(new Section());
		front = front.getLeft();
		front.setRight(tempSection);
	    }
    }
    public String printTape()
    {
	String returnString = "";
	Section readSection = front;
	while ( readSection != end )
	    {
		returnString += readSection.readSymbol();
		readSection = readSection.getRight();
	    }
	returnString += readSection.readSymbol();
	return returnString;
    }
    public Section getHeader()
    {
	return header;
    }
    public void moveRight()
    {
	if ( header.getRight() == null )
	    addSectionAtEnd();
	
	header = header.getRight();
    }
    public void moveLeft()
    {
	if ( header.getLeft() == null )
	    addSectionAtFront();
	
	header = header.getLeft();
    }
    
}