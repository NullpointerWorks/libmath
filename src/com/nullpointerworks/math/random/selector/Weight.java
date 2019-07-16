/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.random.selector;

public abstract class Weight 
{
	public float weight,cumulative = 0f;
	public Weight(float p) {weight=p;}
	public abstract void run();
}
