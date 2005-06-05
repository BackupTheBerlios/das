package das.bl.model;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Domain klasse Rezept.
 */
public class Rezept {
	
	private Long id;
	private String name;
	private String anleitung;
	private Integer avgRating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnleitung() {
		return anleitung;
	}

	public void setAnleitung(String anleitung) {
		this.anleitung = anleitung;
	}

	public Integer getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Integer avgRating) {
		this.avgRating = avgRating;
	}
		
	public int summeKalorien(){
		throw new RuntimeException("todo");
	}
	
	public double summeFett(){
		throw new RuntimeException("todo");
	}
	
	public double summeZucker(){
		throw new RuntimeException("todo");
	}
}
