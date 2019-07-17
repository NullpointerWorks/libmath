/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.math.vector;

import com.nullpointerworks.math.random.Randomizer;

public class Vector3 implements Vector 
{
	public static Vector3 New() {return new Vector3();}
	
	@Override
	public float[] New(float... v)
	{
		return new float[] {v[0],v[1],v[2]};
	}
	
	@Override
	public float[] add(float[] a, float[] b)
	{
		return new float[]{	a[0]+b[0],
							a[1]+b[1],
							a[2]+b[2]};
	}
	
	@Override
	public float[] sub(float[] a, float[] b)
	{
		return new float[]{	a[0]-b[0],
							a[1]-b[1],
							a[2]-b[2]};
	}

	@Override
	public float[] mul(float[] a, float f)
	{
		return new float[]{	a[0]*f,
							a[1]*f,
							a[2]*f};
	}

	@Override
	public float div(float[] a, float[] b) 
	{
		float n = dot(a,b);
		float d = dot(b,b);
		return n / d;
	}

	@Override
	public float dot(float[] a, float[] b) 
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
	}

	@Override
	public float[] neg(float[] a) 
	{
		return new float[]{-a[0], -a[1], -a[2]};
	}

	@Override
	public float[] copy(float[] c) 
	{
		return new float[] {c[0],c[1],c[2]};
	}

	@Override
	public String print(float[] v) 
	{
		return "x:"+v[0]+" y:"+v[1]+" z:"+v[2];
	}

	@Override
	public float[] lerp(float[] a, float[] b, float i) 
	{
		float j = 1f-i;
		return new float[]{ a[0]*j + b[0]*i,
							a[1]*j + b[1]*i,
							a[2]*j + b[2]*i};
	}

	@Override
	public float[] cross(float[] a, float[] b) 
	{
		return new float[]{	a[1]*b[2]-a[2]*b[1],
							a[2]*b[0]-a[0]*b[2],
							a[0]*b[1]-a[1]*b[0]};
	}

	@Override
	public float magnitude(float[] v) 
	{
		return (float)Math.sqrt(dot(v,v));
	}

	@Override
	public float[] random(Randomizer rng) 
	{
		float x = rng._float();
		float y = rng._float();
		float z = rng._float();
		return new float[] {x, y, z};
	}

	@Override
	public float[] limit(float[] v, float f) 
	{
		float[] c = copy(v);
		float m = magnitude(c);
		if (m > f)
		{
			if (m == 0f) m = 0.0001f;
			float invm = f / m;
			c[0] = c[0]*invm;
			c[1] = c[1]*invm;
			c[2] = c[2]*invm;
		}
		return c;
	}

	@Override
	public float[] normalize(float[] a) 
	{
		float x = a[0];
		float y = a[1];
		float z = a[2];
		float m = magnitude(a);
		float invm = 1f / m;
		return new float[]{x*invm, y*invm, z*invm};
	}

	@Override
	public float[] planar(float[] O, float[] a, float[] b, float u, float v)
	{
		float x = O[0] + a[0]*u + b[0]*v;
		float y = O[1] + a[1]*u + b[1]*v;
		float z = O[2] + a[2]*u + b[2]*v;
		return new float[] {x,y,z};
	}
}
