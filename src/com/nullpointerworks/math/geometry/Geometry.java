/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry;

public abstract class Geometry
{
	public enum Dimension
	{
		G2D,G3D
	}
	
	public abstract float[] center();
	public abstract void offset(float[] v);
	public abstract boolean isInside(float[] v);
	public abstract Geometry copy();
	public abstract Dimension dimension();
}
