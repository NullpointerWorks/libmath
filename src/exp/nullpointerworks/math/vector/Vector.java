/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.math.vector;

import com.nullpointerworks.math.random.Randomizer;

/**
 * 
 * @since 1.0.0
 */
public interface Vector 
{
	/**
	 * Create a new vector with the given list of values.
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] New(float... v);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] add(float[] a, float[] b);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] sub(float[] a, float[] b);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] mul(float[] a, float f);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float div(float[] a, float[] b);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float dot(float[] a, float[] b);
	
	/**
	 * 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] neg(float[] a);
	
	/**
	 * 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] copy(float[] c);
	
	/**
	 * 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	String print(float[] v);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] lerp(float[] a, float[] b, float i);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] cross(float[] a, float[] b);
	
	/**
	 * 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float magnitude(float[] v);
	
	/**
	 * 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] random(Randomizer rng);
	
	/**
	 * 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] limit(float[] v, float f);
	
	/**
	 * 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] normalize(float[] a);

	/**
	 * 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] planar(float[] O, float[] a, float[] b, float u, float v);
}
