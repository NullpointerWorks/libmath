/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.random.selector;

import java.util.ArrayList;

import com.nullpointerworks.math.Random;
import com.nullpointerworks.math.random.generator.Randomizer;

public class Selector extends ArrayList<Weight>
{
	private static final long serialVersionUID = 7239246354561552487L;
	private Randomizer rand = Random.getRandomizer();

	public Selector(){}
	public Selector(Randomizer rng) {rand = rng;}

	public void add(Weight... w) 
	{
		for (int i=0,l=w.length; i<l;i++)
		{
			this.add(w[i]);
		}
		probability();
	}
	
	public Weight random() 
	{
		float rng = (float)rand._double();
		
		for (Weight w : this)
		{
			if (rng < w.cumulative)
			{
				return w;
			}
		}
		
		/*
		 * return "null" weight
		 */
		return new Weight(0f) 
		{
			@Override
			public void run(){}
		};
	}

	public void roll() 
	{
		float rng = (float)rand._double();
		
		for (Weight w : this)
		{
			if (rng < w.cumulative)
			{
				w.run();
				return;
			}
		}
	}
	
	// =============================================
	
	private void probability()
	{
		float c = 0f;
		for (Weight w : this)
		{
			w.cumulative = w.weight + c;
			c += w.weight;
		}
		
		c = 1f/c;
		for (Weight w : this)
		{
			w.cumulative *= c;
		}
	}
}
