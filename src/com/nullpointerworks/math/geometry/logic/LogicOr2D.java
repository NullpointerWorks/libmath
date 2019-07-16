/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.logic;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;

public class LogicOr2D extends LogicGroup2D
{
	
	@Override
	public boolean isInside(float x, float y) 
	{
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			boolean b = g.isInside(x, y);
			if (b) return true;
		}
		return false;
	}
	
	@Override
	public Geometry2D copy()
	{
		LogicOr2D loc = new LogicOr2D();
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			loc.add(g.copy());
		}
		return loc;
	}
}
