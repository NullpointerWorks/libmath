package exp.nullpointerworks.math.logic;

import java.util.Vector;

public class AND extends Logic
{
	public AND()
	{
		super();
	}
	
	public AND(int state)
	{
		super(state);
	}
	
	public AND(Logic... args)
	{
		super(args);
	}
	
	public AND(Vector<Logic> args)
	{
		super(args);
	}
	
	public int doOperator(int... input)
	{
		int state = 1;
		for (int i : input) 
			state*=i;
		return state;
	}
}