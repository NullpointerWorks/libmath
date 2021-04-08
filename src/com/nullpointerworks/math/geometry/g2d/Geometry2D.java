/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d;

import com.nullpointerworks.math.geometry.Geometry;

/**
 * Defines the abstract method layout for a 2D geometry. 
 * @since 1.0.0
 */
public abstract class Geometry2D extends Geometry
{
	/**
	 * Available primitives to be used in the geometry API.
	 * @since 1.0.0
	 */
	public enum Type 
	{
		Point, Circle, Triangle, Rectangle,
		Group, Null
	}
	
	@Override
	public abstract float[] center();
	
	/**
	 * Returns the area of the geometry.
	 * @return the area of the geometry
	 * @since 1.0.0
	 */
	public abstract float area();
	
	@Override
	public abstract Geometry2D copy();
	
	/**
	 * Compute and return a bounding box of the type {@code Rectangle} that fits exactly around the geometry.
	 * @return a {@code Rectangle} that fits exactly around the geometry
	 * @since 1.0.0
	 */
	public abstract Rectangle getBoundingBox();
	
	/**
	 * Returns the 2D primitive type.
	 * @return the 2D primitive type
	 * @since 1.0.0
	 */
	public abstract Type type();
	
	/**
	 * Move the geometry in 2D along the x and y axes.
	 * @param x - to translate along the x axes
	 * @param y - to translate along the y axes
	 * @since 1.0.0
	 */
	public abstract void translate(float x, float y);

	@Override
	public final void translate(float[] vec)
	{
		translate(vec[0], vec[1]);
	}
	
	/**
	 * Test to see if a location is found inside of the geometry.
	 * @param px - x location to test
	 * @param py - y location to test
	 * @return {@code true} if the location is inside the geometry
	 * @since 1.0.0
	 */
	public abstract boolean isInside(float px, float py);

	@Override
	public final boolean isInside(float[] vec)
	{
		return isInside(vec[0], vec[1]);
	}

	@Override
	public Dimension dimension()
	{
		return Dimension.G2D;
	}
}
