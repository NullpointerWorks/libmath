/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.logic;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;

/**
 * Logical XOR(Exclusive OR, a.k.a EXOR or EOR) geometry group. <br>
 * <br>
 * The logic with two geometries in this group is similar to the two input logic XOR gate. Returns true if, and only if, one input is true.
 * <pre>
 *    | 0  1
 *  --+------
 *  0 | 0  1
 *    |
 *  1 | 1  0</pre>
 * This is a somewhat contentious type of logical operation since Exclusive-OR is only suppose to give meaningful answers when handling two inputs. Since a geometry group can have more than two objects we enter a muddy area by breaking logical conventions. In general there are two interpretations that can be applied here. The odd-parity, or "if 1 and only 1" interpretation. <br>
 * <br>
 * The Odd-parity collects the sum of all inputs and confirms it's an odd number. If so, return {@code true}, {@code false} otherwise. 
 * <br><br>
 * The "if 1 and only 1" checks to see if the collected sum is equals 1. If so, return {@code true}, {@code false} otherwise. 
 * <br><br>
 * Both interpretations have their own applications in which they are useful. This implementation will follow the "odd-parity" interpretation by default, but can be switched if need be with the {@code setInterpretation(LogicMode)} method.
 * <br><br>
 * @since 1.0.0
 */
public class LogicXor2D extends LogicGroup2D
{
	/**
	 * XOR logic modes.
	 * @since 1.0.0
	 */
	public enum LogicMode
	{
		ODD_PARITY, ONLY_ONE
	}
	
	private LogicMode inter = LogicMode.ODD_PARITY;
	
	/**
	 * Set the XOR logic to odd-parity {@code (ODD_PARITY)} or "if 1 and only 1" {@code (ONLY_ONE)} mode.
	 * @param lm - the logic mode to apply
	 * @since 1.0.0
	 */
	public void setInterpretation(LogicMode lm)
	{
		inter = lm;
	}
	
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
		switch(inter)
		{
		case ODD_PARITY:return odd_parity(count);
		case ONLY_ONE:return only_one(count);
		default:return odd_parity(count);
		}
	}
	
	/**
	 * the odd-parity interpretation
	 * @since 1.0.0
	 */
	protected boolean odd_parity(int count)
	{
		return count%2 == 1;
	}
	
	/**
	 * "if 1 and only 1" interpretation
	 * @since 1.0.0
	 */
	protected boolean only_one(int count)
	{
		return count == 1;
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
