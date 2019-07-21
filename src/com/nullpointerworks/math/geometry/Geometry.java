/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry;

/**
 * Abstract geometry meant to be implemented for any number of dimensions.
 * @since 1.0.0
 * @see Geometry2D
 */
public abstract class Geometry
{
	/**
	 * Indicates what dimension set this geometry belongs to.
	 * @since 1.0.0
	 */
	public enum Dimension
	{
		G2D,G3D
	}
	
	/**
	 * Returns the centroid of the geometry.
	 * @return the centroid of the geometry
	 * @since 1.0.0
	 */
	public abstract float[] center();
	
	/**
	 * Translates the geometry with the provided vector.
	 * @param v - the translation vector
	 * @since 1.0.0
	 */
	public abstract void translate(float[] v);
	
	/**
	 * Tests to see if a location falls within the boundaries of the geometry. Returns {@code true} if it's inside the geometry, {@code false} otherwise.
	 * @param v - the location to test
	 * @return {@code true} if the location {@code v} is inside the geometry boundaries
	 * @since 1.0.0
	 */
	public abstract boolean isInside(float[] v);
	
	/**
	 * Returns a reference free copy of this geometry.
	 * @return a reference free copy of this geometry
	 * @since 1.0.0
	 */
	public abstract Geometry copy();
	
	/**
	 * Returns the type of dimensional space this geometry occupies.
	 * @return the type of dimensional space this geometry occupies
	 * @since 1.0.0
	 */
	public abstract Dimension dimension();
}
