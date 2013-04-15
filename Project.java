import java.awt.*;
import javax.swing.*;

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
        content.add(new JButton("Button 1"));
        content.add(new JButton("Button 2"));
        content.add(new JButton("Button 3"));
        f.setVisible(true);
	machine.printConfigs();
	machine.printTape();
	machine.run("qf");
    }
}
