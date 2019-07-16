/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.complex;

import com.nullpointerworks.math.FastMath;

/**
 * Provides static methods that perform mathematical operations related to complex numbers. All operations provide a new {@code float[2]} object and do not modify the ocntent of the input. Complex numbers are represented in the format described below.
<pre>z = R + Ri
float[2] {real, imaginary}</pre>
 * 
 * @since 1.0.0
 */
public class Complex 
{
	public final static float[] ONE_R = new float[] {1f,0f};
	public final static float[] ONE_I = new float[] {0f,1f};
	
	/**
	 * Convert a complex number into polar form. 
	 * @param z - the complex number to convert as an {@code float[2]}
	 * @return a {@code float[2]} polar coordinate
	 * @since 1.0.0
	 * @see Polar
	 */
	public static float[] toPolar(float[] z)
	{
		float r = (float) modulus(z);
		float t = (float) Argument(z);
		return new float[] {r,t};
	}
	
	/**
	 * Creates a new {@code float[2]} with its components initialized with this input arguments.
	 * @param r - the real component
	 * @param i - the imaginary component
	 * @return a new {@code float[2]} {r,i}
	 * @since 1.0.0
	 */
	public static float[] New(float r, float i) 
	{
		return new float[] {r,i};
	}
	
	/**
	 * Adds the corresponding components together.
	 * @param z1 - base complex number
	 * @param z2 - additive complex number
	 * @return the result of the addition
	 * @since 1.0.0
	 */
	public static float[] add(float[] z1, float[] z2)
	{
		return new float[] {z1[0]+z2[0] , z1[1]+z2[1]};
	}
	
	/**
	 * Adds a list of complex numbers together.
	 * @param z - the list of {@code float[2]} complex numbers
	 * @return the resulting complex number
	 * @since 1.0.0
	 */
	public static float[] add(float[]...  z)
	{
		float[] res = new float[] {0f,0f};
		for (float[] x : z)
			res = add(res,x);
		return res;
	}
	
	/**
	 * Subtracts the components of z2 from z1.<br>
	 * {@code z_r = z_1 - z_2}
	 * @param z1 - the base complex number
	 * @param z2 - the complex number to subtract with
	 * @return the result of the subtraction
	 * @since 1.0.0
	 */
	public static float[] sub(float[] z1, float[] z2)
	{
		return new float[] {z1[0]-z2[0] , z1[1]-z2[1]};
	}
	
	/**
	 * Subtract a list of complex numbers from zero
	 * @param z - a list of complex numbers
	 * @return the result of subtracting all complex numbers
	 * @since 1.0.0
	 */
	public static float[] sub(float[]... z)
	{
		float[] res = new float[] {0f,0f};
		for (int i=0,l=z.length; i<l; i++)
		{
			res = sub(res,z[i]);
		}
		return res;
	}
	
	/**
	 * Multiply a complex number with a given factor.
	 * @param z - the complex number to multiply
	 * @param f - multiplication factor
	 * @return the multiplied complex number
	 * @since 1.0.0
	 */
	public static float[] mul(float[] z, float f)
	{
		return new float[] {z[0]*f, z[1]*f };
	}
	
	/**
	 * Multiplies two complex numbers.
	 * <pre>
	 * z3 = z1 * z2
	 * z3 = (r1 + i1) * (r2 + i2)
	 * z3 = r2(r1 + i1) + i2(r1 + i1)
	 * z3 = r2*r1 + r2*i1 + r1*i2 + i2*i1
	 * i^2 = -1
	 * r3 = r2*r1 - i1*i2
	 * i3 = r2*i1 + r1*i2
	 * z3 = {r3,i3}</pre>
	 * @param z1 - complex number 1
	 * @param z2 - complex number 2
	 * @return the result of the multiplication
	 * @since 1.0.0
	 */
	public static float[] mul(float[] z1, float[] z2)
	{
		float z1r = z1[0], z2r = z2[0];
		float z1i = z1[1], z2i = z2[1];
		float r = z1r * z2r - z1i * z2i;
		float i = z1r * z2i + z1i * z2r;
		return new float[] {r,i};
	}
	
	/**
	 * Multiplies a list of complex numbers together.
	 * @param z - the list of complex numbers
	 * @return the result of the multiplication
	 * @since 1.0.0
	 */
	public static float[] mul(float[]... z)
	{
		float[] res = z[0];
		for (int i=1,l=z.length; i<l; i++)
		{
			res = mul(res,z[i]);
		}
		return res;
	}
	
	/**
	 * Divides two complex numbers from each other using the conjugate of the denominator.
	 * <pre>
	 * c = {dr, -di}
	 * N = n*c
	 * D = d*c
	 * 
	 * zr = Nr / Dr
	 * zi = Ni / Dr
	 * z = {zr, ri}</pre>
	 * @param n - the numerator
	 * @param d - the denominator
	 * @return the result of the division
	 * @since 1.0.0
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
	 * Divide a list of complex numbers with from the first in the list. The first number is the numerator, and all following numbers are dividers. <br>For example:
	 * {@code div(c1,c2,c3,c4,c5) = div(c1, mul(c2,c3,c4,c5) )}
	 * @param c - the list of complex numbers
	 * @return the result of the division
	 * @since 1.0.0
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
	 * Returns the exponential complex number of the given base({@code z}) and the exponent({@code a}).<br>
	 * the result is given with the term {@code 2*k*PI, where k = 0}
	 * <br><br>
	 * {@code z^a = e^(a ln(z))}
	 * @param z - the base number
	 * @param a - the exponential value
	 * @return {@code z} to the power of {@code a}
	 * @since 1.0.0
	 */
	public static float[] pow(float[] z, float[] a)
	{
		float[] exp = mul(a, ln(z));
		float r 	= (float)Math.pow(Math.E, exp[0]);
		float[] p 	= cis(exp[1]);
		return mul(p,r);
	}
	
	/**
	 * Returns the natural log of a complex number.
	 * <br><br>
	 * {@code ln(z) = ln(|z|) + i arg(z)}
	 * @param z - the input complex number
	 * @return the natural log of {@code z}
	 * @since 1.0.0
	 */
	public static float[] ln(float[] z)
	{
		float r = (float)Math.log( modulus(z) );
		float i = (float)Argument(z);
		return new float[] {r,i};
	}
	
	/**
	 * Returns a complex number with radius {@code 1.0} directed at the given angle.
	 * <br><br>
	 * {@code cis(x) = cos(x) + i sin(x)}
	 * @param x - the angle in radians
	 * @return a complex number with radius {@code 1.0} directed at the given angle
	 * @since 1.0.0
	 */
	public static float[] cis(float x)
	{
		float r = (float)FastMath.cos( x );
		float i = (float)FastMath.sin( x );
		return new float[] {r,i};
	}
	
	/**
	 * Returns the modulus(a.k.a. the length or magnitude) of the given complex number.
	 * <br><br>
	 * {@code mod(z) = |z| = sqrt(r*r + i*i)}
	 * @param z - the input complex number
	 * @return the modulus of {@code z}
	 * @since 1.0.0
	 */
	public static double modulus(float[] z)
	{
		return Math.sqrt( z[0]*z[0] + z[1]*z[1] );
	}
	
	/**
	 * Returns the angle in radians of the complex number. The argument is given in the range of {@code -pi < Arg(z) < pi}
	 * @param z - the input complex number
	 * @return the angle in radians
	 * @since 1.0.0
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
	 * @param 
	 * @return 
	 * @since 1.0.0
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
	 * Returns the square of the given complex number
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	public static float[] square(float[] c)
	{
		return mul(c,c);
	}
	
	/**
	 * Converts the complex number into absolute values
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	public static float[] abs(float[] c)
	{
		float r=c[0], i=c[1];
		return new float[] { (r<0f)?-r:r , (i<0f)?-i:i };
	}
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	public static String toString(float[] z)
	{
		return toString(z,"complex");
	}

	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	public static String toString(float[] z, String name)
	{
		return name+": "+z[0]+((z[1]<0.0f)?"":"+")+z[1]+"i";
	}
}
