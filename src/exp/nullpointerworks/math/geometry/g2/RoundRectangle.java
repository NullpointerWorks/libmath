package exp.nullpointerworks.math.geometry.g2;

import com.nullpointerworks.math.geometry.g2d.Circle;
import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Rectangle;

public class RoundRectangle extends Geometry2D
{
	private Rectangle bounding;
	private Circle circ1;
	private Circle circ2;
	private Circle circ3;
	private Circle circ4;
	
	public float x=0,y=0,w=0,h=0,r=0;
	
	public RoundRectangle() 
	{
		this(0,0,0,0,0);
	}
	
	public RoundRectangle(float x, float y, float w, float h, float r)
	{
		r = (r<0f)?0f:r;
		float sm = ((w<h)?w:h) * 0.5f;
		this.r = (r < sm)? r : sm;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		recalculate();
	}
	
	private void recalculate()
	{
		bounding = new Rectangle(x,y,w,h);
		circ1 = new Circle(	x+r,		y+r, 		r);
		circ2 = new Circle(	(x+w-1f)-r,	y+r, 		r);
		circ3 = new Circle(	(x+w-1f)-r,	(y+h-1f)-r, r);
		circ4 = new Circle(	x+r,		(y+h-1f)-r, r);
	}

	@Override
	public Type type() 
	{
		return Type.Group;
	}

	@Override
	public boolean isInside(float px, float py)
	{
		if (!bounding.isInside(px, py)) return false;
		
		if ( (py-y) < r)
		{
			// check top-left corner
			if ( (px-x) < r)
			{
				if (!circ1.isInside(px, py)) return false;
			}
			
			// check top-right corner
			if ( ((x+w-1f)-px) < r)
			{
				if (!circ2.isInside(px, py)) return false;
			}
		}
		
		if ( ((y+h-1f)-py) < r)
		{
			// check bottom-right corner
			if ( ((x+w-1f)-px) < r)
			{
				if (!circ3.isInside(px, py)) return false;
			}
			
			// check bottom-left corner
			if ( (px-x) < r)
			{
				if (!circ4.isInside(px, py)) return false;
			}
		}
		
		return true;
	}
	
	@Override
	public float[] center()
	{
		return new float[] {x+(w*0.5f), y+(h*0.5f)};
	}
	
	@Override
	public float area()
	{
		return w*h;
	}
	
	@Override
	public void offset(float x, float y)
	{
		this.x += x;
		this.y += y;
		bounding.offset(x,y);
		circ1.offset(x,y);
		circ2.offset(x,y);
		circ3.offset(x,y);
		circ4.offset(x,y);
	}
	
	@Override
	public Geometry2D copy()
	{
		return new RoundRectangle(x,y,w,h,r);
	}
	
	@Override
	public Rectangle getBoundingBox() 
	{
		return bounding;
	}
}
