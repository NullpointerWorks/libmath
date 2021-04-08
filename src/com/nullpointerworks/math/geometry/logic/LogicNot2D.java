/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.logic;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;

/**
 * Logical NOT geometry group. Testing a location on this group will return {@code true} if none of the geometries return true on the {@code isInside(float,float)} test. <br>
 * <br>
 * The logic for the test is similar to the two input logic NOT gate. Returns true if no inputs is true.
 * <pre>
 *    | 0  1
 *  --+------
 *  0 | 1  0
 *    |
 *  1 | 0  0</pre>
 * @since 1.0.0
 */
public class LogicNot2D extends LogicGroup2D
{
	@Override
	public boolean isInside(float x, float y) 
	{
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			boolean b = g.isInside(x, y);
			if (b) return false;
		}
		return true;
	}
	
	@Override
	public Geometry2D copy()
	{
		LogicNot2D loc = new LogicNot2D();
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			loc.add(g.copy());
		}
		return loc;
	}
}
