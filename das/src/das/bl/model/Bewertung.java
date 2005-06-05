package das.bl.model;

/**
 * Domain klasse Bewertung.
 */
public class Bewertung {
	
	private Long id;
	private Integer rating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
