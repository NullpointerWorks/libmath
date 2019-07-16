/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

/**
 * 
 * @since 1.0.0
 */
public class FloatMath
{
	/**
	 * The amount of degrees per radian, {@code 180/PI}.
	 * @since 1.0.0
	 */
	public static final float HALFANGLE = 57.29578f;
	
	/**
	 * The amount of radians per degree, {@code PI/180}.
	 * @since 1.0.0
	 */
	public static final float RADIAN 	= 0.017453292f;
	
	/**
	 * The golden ratio, known as the Greek letter phi. A geometric ratio that is observed often in natural phenomena.
	 * @since 1.0.0
	 */
	public static final float PHI 			= (1.6180340f);
	
	/**
	 * The silver ratio, is the ratio of 
	 * @since 1.0.0
	 */
	public static final float SILVER 		= (2.4142136f);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static final float NATURAL		= (2.7182818f);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static final float PI 			= (3.141592654f);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static final float TAU 			= (6.283185307f);
	
	/**
	 * Solves for a^2 + b^2 = c^2
	 * 
	 * @since 1.0.0
	 */
	public static float pythagoras(float a, float b)
	{
		return (float)StrictMath.sqrt(a*a + b*b);
	}
	
	/**
	 * map a value x from one range to another range of values<br>
	 * range 1 = [low1, high1]<br>
	 * range 2 = [low2, high2]<br>
	 * @since 1.0.0
	 */
	public static float map(float x, float low1, float high1, float low2, float high2)
	{
		float dm = (high2-low2);
		float dw = (high1-low1);
		if (dw == 0f) return 0f;
		dm = dm / dw;
		return low2 + dm*(x-low1);
	}
	
	/**
	 * Returns the fractional part of the given floating-point number
	 * @since 1.0.0
	 */
	public static float fraction(float x)
	{
		return x - (float)((int)x);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static float sign(float f) 
	{
	    return (Float.floatToIntBits(f)>>>31);
	}
	
	/**
	 * fast float absolute function
	 * @since 1.0.0
	 */
	public static final float abs(float n)
	{
		return Float.intBitsToFloat( 0x7fffffff & Float.floatToIntBits(n) );
	}
	
	/**
	 * fast 32bit ceiling function
	 * @since 1.0.0
	 */
	public static final float ceil(float n)
	{
		return ((float)(int)(n + ((n>0.0f)?1:0) ));
	}
	
	/**
	 * linear interpolation function
	 * @since 1.0.0
	 */
	public static float lerp(float start, float end, float inter)
	{
		return start*(1f-inter) + end*inter;
	}
	
	/**
	 * cosine interpolation function
	 * @since 1.0.0
	 */
	public static float cerp( float y1,float y2, float inter)
	{
		float mu2 = (float) ((1f-Approximate.cos(inter*PI)) * 0.5f);
		return lerp(y1,y2,mu2);
	}
	
	/**
	 * cubic interpolation function
	 * @since 1.0.0
	 */
	public static float curp(float y0,float y1,float y2,float y3,float inter)
	{
		float a0,a1,a2,a3,mu2;
		mu2 = inter*inter;
		//a0 = y3 - y2 - y0 + y1;
		//a1 = y0 - y1 - a0;
		//a2 = y2 - y0;
		//a3 = y1;
		
		// Catmull-Rom spline coefficients
		a0 = -0.5f*y0 + 1.5f*y1 - 1.5f*y2 + 0.5f*y3;
		a1 = y0 - 2.5f*y1 + 2f*y2 - 0.5f*y3;
		a2 = -0.5f*y0 + 0.5f*y2;
		a3 = y1;
		
		return (a0*inter*mu2+a1*mu2+a2*inter+a3);
	}
	
	/*
	 * Tension: 1 = high, 0 = normal, -1 = low
	 * Bias: 0 is even,
	 *       positive is towards first segment,
	 *       negative towards the other
	 * src: http://paulbourke.net/miscellaneous/interpolation/
	 * @since 1.0.0
	 */
	public static float hermite(float y0,float y1, float y2,float y3,
							    float mu, float tension, float bias)
	{
		float m0,m1,mu2,mu3;
		float a0,a1,a2,a3;

		mu2 = mu * mu;
		mu3 = mu2 * mu;
		m0  = (y1-y0)*(1+bias)*(1-tension)/2;
		m0 += (y2-y1)*(1-bias)*(1-tension)/2;
		m1  = (y2-y1)*(1+bias)*(1-tension)/2;
		m1 += (y3-y2)*(1-bias)*(1-tension)/2;
		a0 =  2*mu3 - 3*mu2 + 1;
		a1 =    mu3 - 2*mu2 + mu;
		a2 =    mu3 -   mu2;
		a3 = -2*mu3 + 3*mu2;
		return(a0*y1+a1*m0+a2*m1+a3*y2);
	}
	
	/**
	 * fade function: f(x) = 6x^5 - 15x^4 + 10x^3
	 * @since 1.0.0
	 */
	public static float fade(float t) 
	{ 
		return t * t * t * (t * (t * 6 - 15) + 10); 
	}
	
	/*
	 * returns the area of the given triangle
	 * @since 1.0.0
	 */
	public static float area(	float x1, float y1, 
								float x2, float y2, 
								float x3, float y3) 
	{
		return 0.5f * (float)abs( (x1-x3)*(y2-y1)-(x1-x2)*(y3-y1) );
	}
	
	/**
	 * clamp a given value between two values.<br>
	 * saturation can be done with: sat(x) = clamp(0.0f, x, 1.0f)<br>
	 * @since 1.0.0
	 */
	public static float clamp(float lower, float x, float upper)
	{
		if (lower > upper) return clamp(upper,x,lower);
		float x1 = (x<lower)?lower:x;
		return (x1<upper)?x1:upper;
	}

	public static float max(float x1, float x2) 
	{
		return (x1<x2)?x2:x1;
	}
	
	public static float min(float x1, float x2) 
	{
		return (x1<x2)?x1:x2;
	}
	
	/**
	 * test if a value is strictly between the interval
	 * @since 1.0.0
	 */
	public static boolean between(float x1, float v, float x2)
	{
		return (x1 < v && v < x2);
	}
	
	/**
	 * find the largest number of three numbers
	 * @since 1.0.0
	 */
	public static float max(float x1,float x2,float x3)
	{
		float x = (x1<x2)?x2:x1;
		return (x<x3)?x3:x;
	}
	
	/**
	 * find the smallest number of three numbers
	 * @since 1.0.0
	 */
	public static float min(float x1,float x2,float x3)
	{
		float x = (x1<x2)?x1:x2;
		return (x<x3)?x:x3;
	}
	
	/**
	 * find the middle number of three numbers
	 * @since 1.0.0
	 */
	public static float mid(float a,float b,float c)
	{
		return a>b ? (c>a ? a : (b>c ? b:c) ) : ( c>b ? b : (a>c ? a:c) );
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static float sinc(float theta)
	{
		if (theta==0f) return 1f;
		return (float) StrictMath.sin(theta) / theta;
	}
	
	// ==================================================================
	//    trigonometry
	// ==================================================================
	
	/**
     * Returns the trigonometric cosine of an angle. Special cases:
     * <ul><li>If the argument is NaN or an infinity, then the
     * result is NaN.</ul>
     * @param theta - an angle, in radians.
     * @return the cosine of the argument.
     * @see StrictMath
     */
	public static float cos(float theta)
	{
		return (float) StrictMath.cos(theta);
	}
	
	/**
     * Returns the arc cosine of a value; the returned angle is in the
     * range 0.0 through <i>pi</i>.  Special case:
     * <ul><li>If the argument is NaN or its absolute value is greater
     * than 1, then the result is NaN.</ul>
     *
     * @param   theta - the value whose arc cosine is to be returned.
     * @return  the arc cosine of the argument.
     * @see StrictMath
     */
	public static float acos(float theta)
	{
		return (float) StrictMath.acos(theta);
	}
	
	/**
     * Returns the trigonometric sine of an angle. Special cases:
     * <ul><li>If the argument is NaN or an infinity, then the
     * result is NaN.
     * <li>If the argument is zero, then the result is a zero with the
     * same sign as the argument.</ul>
     *
     * @param   theta - an angle, in radians.
     * @return  the sine of the argument.
     * @see StrictMath
     */
	public static float sin(float theta)
	{
		return (float) StrictMath.sin(theta);
	}
	
	/**
     * Returns the arc sine of a value; the returned angle is in the
     * range -<i>pi</i>/2 through <i>pi</i>/2.  Special cases:
     * <ul><li>If the argument is NaN or its absolute value is greater
     * than 1, then the result is NaN.
     * <li>If the argument is zero, then the result is a zero with the
     * same sign as the argument.</ul>
     *
     * @param   theta - the value whose arc sine is to be returned.
     * @return  the arc sine of the argument.
     * @see StrictMath
     */
	public static float asin(float theta)
	{
		return (float) StrictMath.asin(theta);
	}

	/**
     * Returns the trigonometric tangent of an angle. Special cases:
     * <ul><li>If the argument is NaN or an infinity, then the result
     * is NaN.
     * <li>If the argument is zero, then the result is a zero with the
     * same sign as the argument.</ul>
     *
     * @param   delta - an angle, in radians.
     * @return  the tangent of the argument.
     * @see StrictMath
     */
	public static float tan(float delta)
	{
		return (float) StrictMath.tan(delta);
	}
	
	/**
     * Returns the arc tangent of a value; the returned angle is in the
     * range -<i>pi</i>/2 through <i>pi</i>/2.  Special cases:
     * <ul><li>If the argument is NaN, then the result is NaN.
     * <li>If the argument is zero, then the result is a zero with the
     * same sign as the argument.</ul>
     *
     * @param   delta - the value whose arc tangent is to be returned.
     * @return  the arc tangent of the argument.
     * @see StrictMath
     */
	public static float atan(float delta)
	{
		return (float) StrictMath.atan(delta);
	}
}
