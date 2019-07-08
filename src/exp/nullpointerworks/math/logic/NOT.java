package exp.nullpointerworks.math.logic;

import java.util.Vector;

public class NOT extends Logic
{
	public NOT()
	{
		super();
	}
	
	public NOT(int state)
	{
		super(state);
	}
	
	public NOT(Logic... args)
	{
		super(args);
	}
	
	public NOT(Vector<Logic> args)
	{
		super(args);
	}
	
	public int doOperator(int... input)
	{
		int state = 0;
		for (int i : input) 
			state+=i;
		return (state==0)?TRUE:FALSE;
	}
}