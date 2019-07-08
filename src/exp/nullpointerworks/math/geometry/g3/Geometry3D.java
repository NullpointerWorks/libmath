package exp.nullpointerworks.math.geometry.g3;

import com.nullpointerworks.math.geometry.Geometry;

public abstract class Geometry3D extends Geometry
{
	public enum Type 
	{
		Point, Sphere, Tetra, Box,
		Group, Null
	}
	
	public abstract float[] center();
	public abstract float volume();
	public abstract Geometry3D copy();
	public abstract Box getBoundingBox();
	public abstract Type type();
	
	public abstract void offset(float x, float y, float z);
	public void offset(float[] vec)
	{
		offset(vec[0], vec[1], vec[2]);
	}

	public abstract boolean isInside(float x, float y, float z);
	public boolean isInside(float[] vec)
	{
		return isInside(vec[0], vec[1], vec[2]);
	}
	
	public Dimension dimension()
	{
		return Dimension.G3D;
	}
}
