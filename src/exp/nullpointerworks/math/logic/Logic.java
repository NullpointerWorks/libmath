package exp.nullpointerworks.math.logic;

import java.util.Vector;

public class Logic extends State
{
	private Vector<Logic> gates;
	
	public Logic()
	{
		gates = new Vector<Logic>();
	}
	
	public Logic(int state)
	{
		gates = new Vector<Logic>();
		this.state = state;
	}
	
	public Logic(Logic... args)
	{
		gates = new Vector<Logic>();
		for (Logic l : args)
		{
			gates.addElement(l);
		}
	}
	
	public Logic(Vector<Logic> args)
	{
		gates = args;
	}
	
	public void addSource(Logic l)
	{
		gates.addElement(l);
	}
	
	public int doOperator(int... input)
	{
		int state=this.state;
		for (int i : input) 
			state+=i;
		return state;
	}

	private int state = FALSE;
	public int getState()
	{
		int[] states = new int[gates.size()];
		int i=0;
		for (Logic l : gates)
			states[i++]=l.getState();
		state = doOperator(states);
		if (state!=0) state=TRUE;
		if (state!=1) state=FALSE;
		return state;
	}
}
