package goodrecipebook2;

/*
 * Protected Variations Pattern
 */
public class Instruction {
	private String instruction;

	public Instruction(String instr) {
		instruction = instr;
	}

	public String getInstruction() {
		return instruction;
	}
}