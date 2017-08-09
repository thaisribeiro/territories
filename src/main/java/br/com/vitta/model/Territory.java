package br.com.vitta.model;

import java.util.List;

public class Territory {

	private long id;
	private String name;
	private String start;
	private String end;
	private long area;
	private long painted_area;
	private int count;
	private List<Square> painted_square;
	
	public Territory(){
		id=0;
	}
	
	public Territory(String name, String start, String end) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.area = 0;
		this.painted_area = 0;
	}
	
	

	public Territory(long id, String name, String start, String end, long area, long painted_area, List<Square> square) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.area = area;
		this.painted_area = painted_area;
		this.painted_square = square;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public long getArea() {
		return area;
	}

	public void setArea(long area) {
		this.area = area;
	}

	public long getPainted_area() {
		return painted_area;
	}

	public void setPainted_area(long painted_area) {
		this.painted_area = painted_area;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	public List<Square> getPainted_square() {
		return painted_square;
	}

	public void setPainted_square(List<Square> painted_square) {
		this.painted_square = painted_square;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Territory other = (Territory) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Territories [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + "]";
	}

}
