package com.nullpointerworks.math.geometry.logic;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;

public class LogicXor2D extends LogicGroup2D
{
	
	@Override
	public boolean isInside(float x, float y) 
	{
		int count = 0;
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			boolean b = g.isInside(x, y);
			if (b) count++;
		}
		
		/*
		 * the odd-party interpretation
		 */
		return count%2 == 1;
		
		/*
		 * "if 1 and only 1" interpretation
		 */
		//return count == 1;
	}
	
	@Override
	public Geometry2D copy()
	{
		LogicXor2D loc = new LogicXor2D();
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			loc.add(g.copy());
		}
		return loc;
	}
}
