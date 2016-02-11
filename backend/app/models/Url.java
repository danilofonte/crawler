package models;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;

@Entity
public class Url extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -231435704042193272L;
	
	public String link;

	public Url() {
		super();
	}

	public Url(String link) {
		super();
		this.link = link;
	}

}
