package com.nullpointerworks.math.random.selector;

public abstract class Weight 
{
	public float weight,cumulative = 0f;
	public Weight(float p) {weight=p;}
	public abstract void run();
}
