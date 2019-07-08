package exp.nullpointerworks.math.logic;

import java.util.HashMap;
import java.util.Vector;

public class LogicFactory
{
	public static Logic parse(String expression, Logic... params)
	{
		// clean expression of spaces
		expression=expression.replace(" ", "");
		int l = expression.length();
		
		// expression substitutions
		HashMap<String,String> subs = new HashMap<String,String>();
		HashMap<String,Logic> refs = new HashMap<String,Logic>();
		
		// from a to z
		int getRef=0;
		for(int i=97; i<123; i++)
		{
			String character = (""+(char)i);
			if (expression.contains( character ))
			{
				out( character +" > " + params[getRef]);
				refs.put(character, params[getRef++]);
			}
		}
		
		// get rid of all ()
		String toBeRemoved="";
		int subCount=0;
		boolean record=false;
		out("got: "+expression);
		while(expression.contains("("))
		{
			l = expression.length();
			for(int i=0; i<l; i++)
			{
				char c = expression.charAt(i);
				
				switch(c)
				{
				case '(':
					toBeRemoved="";
					record=true;
					break;
				case ')':
					record=false;
					break;
				}
				
				if (record && c!='(')
					toBeRemoved+=c;
			}
			
			String subString = getSubName(subCount++);
			subs.put(subString, toBeRemoved );
			expression = expression.replace( "("+toBeRemoved+")" , subString);
			
			out("got: "+expression);
		}
		
		// construct logic circuit per term
		return makeORLogic(subs,refs,expression);
	}
	
	public static void setDebug(boolean d)
	{
		debugOut = d;
	}
	
	private static Logic makeORLogic(HashMap<String,String> subs, 
									HashMap<String,Logic> refs, 
									String expression)
	{
		Vector<Logic> args 	= new Vector<Logic>();
		String[] terms 		= expression.split("\\+");
		for (String term : terms)
		{
			out("building: "+term);
			args.add( makeANDLogic(subs,refs,term) );
		}
		return new OR(args);
	}
	
	private static Logic makeANDLogic(	HashMap<String,String> subs, 
										HashMap<String,Logic> refs, 
										String expression)
	{
		Vector<Logic> args 		= new Vector<Logic>();
		
		// get factored expressions
		while(expression.contains("@"))
		{
			String f = getSub(expression);
			args.add ( makeORLogic(subs,refs, subs.get(f) ) );
			expression = expression.replace(f, "");
		}
		
		// get factors
		String[] factor = expression.split("");
		if (factor.length>0)
		for(String f : factor)
		{
			if (!f.equals(""))
				args.add( refs.get(f) );
		}
		
		return new AND(args);
	}
	
	private static String getSub(String term)
	{
		String[] s = term.split("");
		String sub = "";
		boolean read=false;
		for (String t : s)
		{
			if (t.equals("@"))
			{
				if (read)
					return sub+"@";
				read=true;
			}
			
			if (read) sub+=t;
		}
		return "";
	}

	private static boolean debugOut = false;
	private static void out(String msg)
	{
		if (debugOut) System.out.println(msg);
	}
	
	private static String getSubName(int subCount)
	{
		return "@"+String.format("%02d", subCount)+"@";
	}
}
