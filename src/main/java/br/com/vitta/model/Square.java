package br.com.vitta.model;

public class Square {

	private long x;
	private long y;
	private boolean painted;

	public Square() {
	}

	public Square(long x, long y, boolean painted) {
		super();
		this.x = x;
		this.y = y;
		this.painted = painted;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public boolean isPainted() {
		return painted;
	}

	public void setPainted(boolean painted) {
		this.painted = painted;
	}

	

	@Override
	public String toString() {
		return "Square [x=" + x + ", y=" + y + ", painted=" + painted + "]";
	}

}
