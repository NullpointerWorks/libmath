package exp.nullpointerworks.math.logic;

import java.util.Vector;

public class EOR extends Logic
{
	public EOR()
	{
		super();
	}
	
	public EOR(int state)
	{
		super(state);
	}
	
	public EOR(Logic... args)
	{
		super(args);
	}
	
	public EOR(Vector<Logic> args)
	{
		super(args);
	}
	
	public int doOperator(int... input)
	{
		int state = 0;
		for (int i : input)
			state += i;
		return (state%2 == 0)?FALSE:TRUE;
	}
}