public class Project
{
    public static void main(String args[])
    {
	TuringMachine machine = new TuringMachine("111011");
	machine.printConfigs();
	machine.printTape();
	machine.run("qf");
    }
}