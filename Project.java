import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Project
{
    public static void main(String args[])
    {
	TuringMachine machine = new TuringMachine("111011");
	JFrame f = new JFrame("This is a test");
        f.setSize(400, 150);
        Container content = f.getContentPane();
        content.setBackground(Color.white);
        content.setLayout(new FlowLayout());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JTable tape = new JTable(1,10);
	TableColumnModel colModel = tape.getColumnModel();
	TableModel tapeModel = tape.getModel();
	for ( int col = 0; col < colModel.getColumnCount(); col++)
	    {
		tapeModel.setValueAt(1,0,col);
		colModel.getColumn(col).setPreferredWidth(10);
	    }
	
	content.add(tape);
	f.setVisible(true);
	print(tapeModel);
	machine.printConfigs();
	machine.printTape();
	machine.run("qf");
    }
}
