
package badrecipebook2;

import java.io.Serializable;

class Instruction implements Serializable
{
	private String instruction;
	public Instruction(String instr)
	{
		instruction = instr;
	}
	public String getInstruction()
	{
		return instruction;
	}
}
