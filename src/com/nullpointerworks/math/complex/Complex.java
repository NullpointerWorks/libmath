/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.complex;

import com.nullpointerworks.math.FastMath;

/**
 * z = R + Ri<br>
 * float[2] = {r,i}
 */
public class Complex 
{
	public final static float[] ONE_R = new float[] {1f,0f};
	public final static float[] ONE_I = new float[] {0f,1f};
	
	/**
	 * convert a complex number into polar form
	 */
	public static float[] toPolar(float[] z)
	{
		float r = (float) modulus(z);
		float t = (float) Argument(z);
		return new float[] {r,t};
	}
	
	/**
	 * 
	 */
	public static float[] New(float r, float i) 
	{
		return new float[] {r,i};
	}
	
	/**
	 * add two complex numbers
	 */
	public static float[] add(float[] c1, float[] c2)
	{
		return new float[] {c1[0]+c2[0] , c1[1]+c2[1]};
	}
	
	/**
	 * add a list of complex numbers together
	 */
	public static float[] add(float[]... c)
	{
		float[] res = new float[] {0f,0f};
		for (float[] x : c)
			res = add(res,x);
		return res;
	}
	
	/**
	 * subtract complex 2 from complex 1
	 */
	public static float[] sub(float[] c1, float[] c2)
	{
		return new float[] {c1[0]-c2[0] , c1[1]-c2[1]};
	}
	
	/**
	 * subtract a list of complex numbers from each other
	 */
	public static float[] sub(float[]... c)
	{
		float[] res = c[0];
		for (int i=1,l=c.length; i<l; i++)
		{
			float[] x = c[i];
			res = sub(res,x);
		}
		return res;
	}
	
	/**
	 * multiply a complex number with a given factor
	 */
	public static float[] mul(float[] z, float f)
	{
		return new float[] {z[0]*f, z[1]*f };
	}
	
	/**
	 * multiply two complex numbers
	 */
	public static float[] mul(float[] c1, float[] c2)
	{
		float c1r = c1[0], c2r = c2[0];
		float c1i = c1[1], c2i = c2[1];
		float r = c1r * c2r - c1i * c2i; // i^2 = -1
		float i = c1r * c2i + c1i * c2r;
		return new float[] {r,i};
	}
	
	/**
	 * multiply a list of complex numbers together
	 */
	public static float[] mul(float[]... c)
	{
		float[] res = c[0];
		for (int i=1,l=c.length; i<l; i++)
		{
			float[] x = c[i];
			res = mul(res,x);
		}
		return res;
	}
	
	/**
	 * divide two complex numbers from each other.<br>
	 * n = numerator<br>
	 * d = denominator
	 */
	public static float[] div(float[] n, float[] d)
	{
		float[] conj = new float[] {d[0], -d[1]};
		float[] num = mul(n,conj);
		float[] den = mul(d,conj);
		
		float r = num[0] / den[0];
		float i = num[1] / den[0];
		
		return new float[] {r,i};
	}
	
	/**
	 * divide a list of complex numbers with each other.<br>
	 * 1st element is the numerator, and all following numbers are dividers.<br>
	 * example:<br>
	 * div(c1,c2,c3,c4,c5) = div(c1, mul(c2,c3,c4,c5) )
	 */
	public static float[] div(float[]... c)
	{
		float[] n = c[0];
		float[] d = c[1];
		
		for (int i=2,l=c.length; i<l; i++)
		{
			float[] x = c[i];
			d = mul(d,x);
		}
		
		return div(n,d);
	}
	
	/**
	 * returns the exponent complex number of the given base(z) and the power(a).<br>
	 * the result is given with the term 2*k*PI, where k = 0<br>
	 * z^a = e^(a ln(z))<br>
	 */
	public static float[] pow(float[] z, float[] a)
	{
		float[] exp = mul(a, ln(z));
		float r 	= (float)Math.pow(Math.E, exp[0] );
		float[] p 	= cis(exp[1] );
		return mul(p,r);
	}
	
	/**
	 * returns the natural log of a complex number, as a complex number<br>
	 * ln(z) = ln(|z|) + i arg(z)
	 */
	public static float[] ln(float[] z)
	{
		float r = (float)Math.log( modulus(z) );
		float i = (float)Argument(z);
		return new float[] {r,i};
	}
	
	/**
	 * returns the complex number with radius 1 from the given angle.<br>
	 * cis(x) = cos(x) + i sin(x)
	 */
	public static float[] cis(float x)
	{
		float r = (float)FastMath.cos( x );
		float i = (float)FastMath.sin( x );
		return new float[] {r,i};
	}
	
	/**
	 * returns the complex natural log of the given complex number
	 */
	public static float[] log(float[] z)
	{
		return new float[] { (float)Math.log( modulus(z) ) , (float)Argument(z) };
	}
	
	/**
	 * returns the modulus(aka length or magnitude) of the given complex number<br>
	 * mod(z) = |z| = sqrt(r*r + i*i)
	 */
	public static double modulus(float[] z)
	{
		return Math.sqrt( z[0]*z[0] + z[1]*z[1] );
	}
	
	/**
	 * Arg(z) = angle in radians of the given complex number with the Real axes<br>
	 * The argument is given in the range of -pi < Arg(z) < pi
	 */
	public static double Argument(float[] z)
	{
		float[] a = abs(z);
		double arg = Math.atan( a[1] / a[0] );
		if (z[0] < 0f) arg = Math.PI - arg;
		if (z[1] < 0f) arg = -arg;
		return arg;
	}
	
	/**
	 * The argument is returned in the range of 0 < arg(z) < 2*pi
	 */
	public static double argument(float[] z)
	{
		float[] a = abs(z);
		double arg = Math.atan( a[1] / a[0] );
		if (z[0] < 0f) arg = Math.PI - arg;
		if (z[1] < 0f) arg = 2d*Math.PI - arg;
		return arg;
	}
	
	/**
	 * returns the square of the given complex number
	 */
	public static float[] square(float[] c)
	{
		return mul(c,c);
	}
	
	/**
	 * converts the complex number into absolute values
	 */
	public static float[] abs(float[] c)
	{
		float r=c[0], i=c[1];
		return new float[] { (r<0f)?-r:r , (i<0f)?-i:i };
	}
	
	/**
	 * 
	 */
	public static String toString(float[] z)
	{
		return toString(z,"complex");
	}

	/**
	 * 
	 */
	public static String toString(float[] z, String name)
	{
		return name+": "+z[0]+((z[1]<0.0f)?"":"+")+z[1]+"i";
	}
}
