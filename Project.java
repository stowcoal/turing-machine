import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.Action.*;
import java.awt.Color;

public class Project
{
    public static void main(String args[])
    {
	TuringMachine machine = new TuringMachine("111011");
	JTable tape = new JTable(1,20);
	tape.setDefaultRenderer(Object.class, new CustomRenderer());
	JFrame f = new JFrame("This is a test");
        f.setSize(400, 150);
        Container content = f.getContentPane();
        content.setBackground(Color.white);
        content.setLayout(new FlowLayout());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	TableColumnModel colModel = tape.getColumnModel();
	for ( int col = 0; col < tape.getColumnCount(); col++ )
	    {
		colModel.getColumn(col).setPreferredWidth(10);
	    }
	TableModel tapeModel = tape.getModel();
	machine.printTape(tape);
	content.add(tape);
	content.add(new JLabel("\n"));
	JButton buttonStep = new JButton("Step");
	buttonStep.addActionListener(new StepButtonListener(machine, tape));
	content.add(buttonStep);
	f.setVisible(true);
	
    }
    private static class StepButtonListener implements ActionListener
    {
	private TuringMachine m;
	private JTable t;
	public StepButtonListener(TuringMachine machine, JTable tape)
	{
	    this.m = machine;
	    this.t = tape;
	}
	public void actionPerformed(ActionEvent e)
	{
	    m.nextStep(t);
	}
    }
    private static class CustomRenderer extends DefaultTableCellRenderer 
    {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
	    
	    if(column == 11)
		{
		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		    c.setBackground(Color.gray);
		    return c;
		}
	    else
		{
		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		    c.setBackground(Color.white);
		    return c;
		}

	}
    }
}
