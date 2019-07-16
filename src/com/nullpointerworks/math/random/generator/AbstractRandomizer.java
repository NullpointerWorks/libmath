package com.nullpointerworks.math.random.generator;

abstract class AbstractRandomizer implements Randomizer 
{
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#_double()
	 */
	@Override
	public abstract double _double();
	
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#_float()
	 */
	@Override
	public abstract float _float();
	
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#_boolean()
	 */
	@Override
	public abstract boolean _boolean();
	
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#_double(double, double)
	 */
	@Override
	public abstract double _double(double low, double high);
	
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#_float(float, float)
	 */
	@Override
	public abstract float _float(float low, float high);
	
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#_integer(int, int)
	 */
	@Override
	public abstract int _integer(int low, int high);
	
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#seed()
	 */
	@Override
	public long seed() {return 0;}
	
	/* (non-Javadoc)
	 * @see com.nullpointerworks.math.random.generator.abstracts.IRandomizer#seed(long)
	 */
	@Override
	public void seed(long s) {}
}
