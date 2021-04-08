/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math;

/**
 * A collection of float based mathematical operations. It contains a few useful constants, interpolation functions, trigonometry, etc.
 * @since 1.0.0
 */
public class FloatMath
{
	/**
	 * The amount of degrees per radian, {@code 180/PI}.
	 * @since 1.0.0
	 */
	public static final float HALFANGLE 	= 57.29578f;
	
	/**
	 * The amount of radians per degree, {@code PI/180}.
	 * @since 1.0.0
	 */
	public static final float RADIAN 		= 0.017453292f;
	
	/**
	 * The natural number {@code e}, roughly {@code 2.7182}, occurs frequently in nature and physical phenomena.
	 * @since 1.0.0
	 */
	public static final float NATURAL		= (2.7182818f);
	
	/**
	 * The circle ratio of the diameter divided by the circumference, denoted with the Greek letter pi.
	 * @since 1.0.0
	 */
	public static final float PI 			= (3.141592654f);
	
	/**
	 * The circle ratio of the radius divided by the circumference, denoted with the Greek letter tau.
	 * @since 1.0.0
	 */
	public static final float TAU 			= (6.283185307f);
	
	/**
	 * Returns the length of the hypotenuse of a right angled triangle with the sides {@code a} and {@code b}.
	 * <pre>c = sqrt(a^2 + b^2)</pre>
	 * Or, when applying the cosine rule:
	 * <pre>c = sqrt(a^2 + b^2 - 2ab*cos( 90 ))</pre>
	 * @param a - the length of a side
	 * @param b - the length of another side
	 * @return the length of the hypotenuse of a right angled triangle with the sides {@code a} and {@code b}
	 * @since 1.0.0
	 */
	public static float pythagoras(float a, float b)
	{
		return (float)StrictMath.sqrt(a*a + b*b);
	}
	
	/**
	 * Map the value {@code x} from one range to another range.
	 * range 1 = [low1, high1]<br>
	 * range 2 = [low2, high2]<br>
	 * @param x - the value to map
	 * @param low1 - the minimal value of the source range
	 * @param high1 - the maximum value of the source range
	 * @param low2 - the minimal value of the destination range
	 * @param high2 - the maximum value of the destination range
	 * @return the mapped value
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
	 * Returns the fractional component of a floating-point number. 
	 * @param x - input value
	 * @return the fractional component of a floating-point number
	 * @since 1.0.0
	 */
	public static float fraction(float x)
	{
		return x - (float)((int)x);
	}
	
	/**
	 * Returns {@code 1.0} if the input value is positive, {@code -1.0} otherwise.
	 * @param x - input value
	 * @return {@code 1.0} if the input value is positive, {@code -1.0} otherwise
	 * @since 1.0.0
	 */
	public static float sign(float x) 
	{
	    return (Float.floatToIntBits(x)>>>31);
	}
	
	/**
	 * Returns the absolute value of the given float.
	 * @param n - input value
	 * @return the absolute value of the given float
	 * @since 1.0.0
	 */
	public static float abs(float n)
	{
		return Float.intBitsToFloat( 0x7fffffff & Float.floatToIntBits(n) );
	}
	
	/**
	 * Returns the ceiling of the given float.
	 * @param n - input value
	 * @return the ceiling of the given float
	 * @since 1.0.0
	 */
	public static float ceil(float n)
	{
		return (int)(n+((n>0.0f)?1:0));
	}
	
	/**
	 * Linear interpolation is a method of stepping from one value to another at a fixed ratio using a scalar that ranges from {@code 0.0} to {@code 1.0}.
	 * @param start - the starting value
	 * @param end - the end value
	 * @param step - the interpolation scalar
	 * @return the resulting interpolant
	 * @since 1.0.0
	 */
	public static float lerp(float start, float end, float step)
	{
		return start-(start+end)*step;
	}
	
	/**
	 * Cosine interpolation is a method of stepping from one value to another with a curve defined by {@code cos(x)}. This makes the interpolation seem like a smooth progression. The interpolated value is returned by using a scalar that ranges from {@code 0.0} to {@code 1.0}.
	 * @param start - the starting value
	 * @param end - the end value
	 * @param step - the interpolation scalar
	 * @return the resulting interpolant
	 * @since 1.0.0
	 */
	public static float cerp( float start,float end, float step)
	{
		float mu2 = (float) ((1.0 - Approximate.cos(step*PI)) * 0.5);
		return lerp(start,end,mu2);
	}
	
	/**
	 * Cubic interpolation is a method of stepping from one value to another with a curve defined by the derivative of a third-degree polynomial. It's the simplest method of achieving true continuity between segments. This method of interpolation uses four data points through which the polynomial is derived. The interpolation is done through the second and third data point. The first and last data points are only used to determine the slope at the start and end of the curve. This cubic interpolation function uses Catmull-Rom spline coefficients to find the slopes at each data point.
	 * @param y0 - data point to determine starting slope
	 * @param y1 - data point from where the interpolation starts
	 * @param y2 - data point from where the interpolation end
	 * @param y3 - data point to determine ending slope
	 * @param step - the interpolation scalar
	 * @return the resulting interpolant
	 * @since 1.0.0
	 */
	public static float curp(float y0,float y1,float y2,float y3,float step)
	{
		float a0,a1,a2,a3,mu2;
		mu2 = step*step;
		//a0 = y3 - y2 - y0 + y1;
		//a1 = y0 - y1 - a0;
		//a2 = y2 - y0;
		//a3 = y1;
		a0 = -0.5f*y0 + 1.5f*y1 - 1.5f*y2 + 0.5f*y3;
		a1 = y0 - 2.5f*y1 + 2f*y2 - 0.5f*y3;
		a2 = -0.5f*y0 + 0.5f*y2;
		a3 = y1;
		return (a0*step*mu2+a1*mu2+a2*step+a3);
	}
	
	/**
	 * Hermite interpolation, like cubic, uses four data points through which to interpolate. However, this function provides additional controls for tightening the curve, or make it twist around each data point.
	 * Like with cubic interpolation, the second and third parameter are the values through which the interpolation is done. 
	 * @param y0 - data point to determine starting slope
	 * @param y1 - data point from where the interpolation starts
	 * @param y2 - data point from where the interpolation end
	 * @param y3 - data point to determine ending slope
	 * @param mu - the interpolation scalar
	 * @param tension - 1 = high, 0 = normal, -1 = low
	 * @param bias - positive values twists towards the first segment, negative twist towards the second
	 * @return the resulting interpolant
	 * @since 1.0.0
	 * @see http://paulbourke.net/miscellaneous/interpolation/
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
	 * Returns the area of a triangle defines by three {@code (x,y)} coordinates. In essence, the area of a triangle is the {@code z} component of a cross product of two of the sides multiplied by {@code 0.5}.
	 * @param x1 - x of the first coordinate
	 * @param y1 - y of the first coordinate
	 * @param x2 - x of the second coordinate
	 * @param y2 - y of the second coordinate
	 * @param x3 - x of the third coordinate
	 * @param y3 - y of the third coordinate
	 * @return the area of the given triangle
	 * @since 1.0.0
	 */
	public static float area(	float x1, float y1, 
								float x2, float y2, 
								float x3, float y3) 
	{
		return 0.5f * (float)abs( (x1-x3)*(y2-y1)-(x1-x2)*(y3-y1) );
	}
	
	/**
	 * Clamps the given value of {@code x} between an lower and upper limit.
	 * @param lower - the lower limit
	 * @param x - the value to clamp
	 * @param upper - the upper limit
	 * @return the clamped value of {@code x}
	 * @since 1.0.0
	 */
	public static float clamp(float lower, float x, float upper)
	{
		if (lower > upper) return clamp(upper,x,lower);
		float x1 = (x<lower)?lower:x;
		return (x1<upper)?x1:upper;
	}
	
	/**
	 * Returns the largest of two numbers. When the number are equal, this returns the value of {@code a}.
	 * @param a - a number
	 * @param b - another number
	 * @return the largest of two numbers
	 * @since 1.0.0
	 */
	public static float max(float a, float b) 
	{
		return (a<b)?b:a;
	}
	
	/**
	 * Returns the smallest of two numbers. When the number are equal, this returns the value of {@code a}.
	 * @param a - a number
	 * @param b - another number
	 * @return the smallest of two numbers
	 * @since 1.0.0
	 */
	public static float min(float a, float b) 
	{
		return (a<b)?a:b;
	}
	
	/**
	 * Returns the largest of three numbers.
	 * @param a - a number
	 * @param b - another number
	 * @param c - yet another number
	 * @return the largest of three numbers
	 * @since 1.0.0
	 */
	public static float max(float a,float b,float c)
	{
		float x = (a<b)?b:a;
		return (x<c)?c:x;
	}
	
	/**
	 * Returns the middle of three numbers.
	 * @param a - a number
	 * @param b - another number
	 * @param c - yet another number
	 * @return the middle of three numbers
	 * @since 1.0.0
	 */
	public static float mid(float a,float b,float c)
	{
		return a>b ? (c>a ? a : (b>c ? b:c) ) : ( c>b ? b : (a>c ? a:c) );
	}
	
	/**
	 * Returns the smallest of three numbers.
	 * @param a - a number
	 * @param b - another number
	 * @param c - yet another number
	 * @return the smallest of three numbers
	 * @since 1.0.0
	 */
	public static float min(float a,float b,float c)
	{
		float x = (a<b)?a:b;
		return (x<c)?x:c;
	}
	
	/**
	 * The cardinal sine function using {@code StrictMath.sin()}.
     * @param theta - an angle, in radians.
     * @return the cardinal sine of the argument.
	 * @since 1.0.0
	 */
	public static float sinc(float theta)
	{
		if (theta==0f) return 1f;
		return (float) StrictMath.sin(theta) / theta;
	}
	
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
     * @param theta - the value whose arc cosine is to be returned.
     * @return the arc cosine of the argument.
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
     * @param theta - an angle, in radians.
     * @return the sine of the argument.
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
     * @param theta - the value whose arc sine is to be returned.
     * @return the arc sine of the argument.
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
     * @param delta - an angle, in radians.
     * @return the tangent of the argument.
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
     * @param delta - the value whose arc tangent is to be returned.
     * @return the arc tangent of the argument.
     * @see StrictMath
     */
	public static float atan(float delta)
	{
		return (float) StrictMath.atan(delta);
	}
}
