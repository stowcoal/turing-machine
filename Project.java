import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.Action.*;
import java.awt.Color;
import javax.swing.text.*;

public class Project
{
    public static void main(String args[])
    {
	
	TuringMachine machine = new TuringMachine("11110111111");
	JTable tape = new JTable(1,20);
	tape.setDefaultRenderer(Object.class, new TapeRenderer());
	JFrame f = new JFrame("Turing Machine");
        f.setSize(415, 150);
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
	JButton backButton = new JButton("<");
	JButton forwardButton = new JButton(">");
	backButton.addActionListener(new BackButtonListener(machine, tape));
	forwardButton.addActionListener(new ForwardButtonListener(machine, tape));
	content.add(backButton);
	content.add(tape);
	content.add(forwardButton);
	JLabel stateLabel = new JLabel("q0");
	content.add(stateLabel);
	JButton buttonStep = new JButton("Step");
	JButton buttonWriteCell = new JButton("Write");
	JTextField writeText = new JTextField(1);
	buttonWriteCell.addActionListener(new WriteButtonListener(machine, tape, writeText));
	content.add(writeText);
	content.add(buttonWriteCell);
      	buttonStep.addActionListener(new StepButtonListener(machine, tape, buttonStep, stateLabel, backButton, forwardButton));
	content.add(buttonStep);
	DefaultTableModel instructionTableModel = new DefaultTableModel();
	JTable instructionTable = new JTable(instructionTableModel);
	
	
	instructionTableModel.addColumn("Start State");
	instructionTableModel.addColumn("Read Symbol");
	instructionTableModel.addColumn("Direction");
	instructionTableModel.addColumn("Write Symbol");
	instructionTableModel.addColumn("End State");
	instructionTableModel.addRow(new Object[]{"Start State", "Read Symbol", "Direction", "Write Symbol", "End State"});
	TableColumnModel instructionTCM = instructionTable.getColumnModel();
	for ( int col = 0; col < instructionTable.getColumnCount(); col++ )
	    {
		String header = (String)instructionTableModel.getValueAt(0, col);
		instructionTCM.getColumn(col).setPreferredWidth(8*header.length());
	    }
	
	
	instructionTable.setDefaultRenderer(Object.class, new InstructionTableRenderer());
	content.add(instructionTable);
	f.setVisible(true);
	
    }
    private static class WriteButtonListener implements ActionListener
    {
	private TuringMachine machine;
	private JTable tape;
	private JTextField cellWrite;
	
	public WriteButtonListener(TuringMachine m, JTable t, JTextField tf)
	{
	    this.machine = m;
	    this.tape = t;
	    this.cellWrite = tf;
	}
	public void actionPerformed(ActionEvent e)
	{
	    if (cellWrite.getText().equals(""))
		{
		    machine.write('*');
		    machine.printTape(tape);
		}
	    else
		{
		    machine.write(cellWrite.getText().charAt(0));
		    machine.printTape(tape);
		}
     	}
    }
    
    private static class ForwardButtonListener implements ActionListener
    {
	private TuringMachine machine;
	private JTable tape;
	public ForwardButtonListener(TuringMachine m, JTable t)
	{
	    this.machine = m;
	    this.tape = t;
	}
	public void actionPerformed(ActionEvent e)
	{
	    machine.moveHeaderRight();
	    machine.printTape(tape);
	}
	
    }
    private static class BackButtonListener implements ActionListener
    {
	private TuringMachine machine;
	private JTable tape;
	public BackButtonListener(TuringMachine m, JTable t)
	{
	    this.machine = m;
	    this.tape = t;
	}
	public void actionPerformed(ActionEvent e)
	{
	    machine.moveHeaderLeft();
	    machine.printTape(tape);
	}
	
    }    
	
    private static class StepButtonListener implements ActionListener
    {
	private TuringMachine machine;
	private JTable tape;
	private JButton stepButton;
	private JButton forwardButton;
	private JButton backButton;
	private JLabel stateLabel;
	public StepButtonListener(TuringMachine m, JTable t, JButton b, JLabel l, JButton back, JButton forward)
	{
	    this.machine = m;
	    this.tape = t;
	    this.stepButton = b;
	    this.stateLabel = l;
	    this.backButton = back;
	    this.forwardButton = forward;
	}
	public void actionPerformed(ActionEvent e)
	{
	    backButton.setEnabled(false);
	    forwardButton.setEnabled(false);
	    machine.nextStep(tape);
	    stateLabel.setText(machine.getState());
	    if (machine.getState().equals("qf"))
		{
		    stepButton.setEnabled(false);
		    backButton.setEnabled(true);
		    forwardButton.setEnabled(true);
		}
	}
    }
    private static class TapeRenderer extends DefaultTableCellRenderer 
    {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
	    
	    if(column == 10)
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
    private static class InstructionTableRenderer extends DefaultTableCellRenderer 
    {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
	    
	    if(row == 0)
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
