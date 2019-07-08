package exp.nullpointerworks.math.logic;

import java.util.Vector;

public class OR extends Logic
{
	public OR()
	{
		super();
	}
	
	public OR(int state)
	{
		super(state);
	}
	
	public OR(Logic... args)
	{
		super(args);
	}
	
	public OR(Vector<Logic> args)
	{
		super(args);
	}
	
	public int doOperator(int... input)
	{
		for (int i : input) 
		{
			if (i==1) return TRUE;
		}
		return FALSE;
	}
}