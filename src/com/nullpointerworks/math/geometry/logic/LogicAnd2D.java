/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.logic;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;

/**
 * Logical AND geometry group. Testing a location on this group will return {@code true} if all geometries return true on the {@code isInside(float,float)} test. <br>
 * <br>
 * The logic for the test is similar to the two input logic AND gate. Returns true if, and only if, all inputs are true.
 * <pre>
 *    | 0  1
 *  --+------
 *  0 | 0  0
 *    |
 *  1 | 0  1</pre>
 * @since 1.0.0
 */
public class LogicAnd2D extends LogicGroup2D
{
	@Override
	public boolean isInside(float x, float y) 
	{
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			boolean b = g.isInside(x, y);
			if (!b) return false;
		}
		return true;
	}
	
	@Override
	public Geometry2D copy()
	{
		LogicAnd2D loc = new LogicAnd2D();
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			loc.add(g.copy());
		}
		return loc;
	}
}
