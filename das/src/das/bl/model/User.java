package das.bl.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class User {
	
	private String login;
	private String name;
	private String password;
	private String userGroup;
	private String email;
	private Map<Long,Bewertung> bewertungen = new LinkedHashMap<Long,Bewertung>();
	private Map<Long,Allergie> allergien = new LinkedHashMap<Long,Allergie>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addBewertung(Bewertung b){
		bewertungen.put(b.getId(), b);
	}
	
	public void removeBewertung(Long bewertungId){
		bewertungen.remove(bewertungId);
	}
	
	public Collection<Bewertung> getBewertungen(){
		return bewertungen.values();
	}
	
	public void addAllergie(Allergie a){
		allergien.put(a.getId(), a);
	}
	
	public void removeAllergie(Long allergieId){
		allergien.remove(allergieId);
	}
	
	public Collection<Allergie> getAllergien(){
		return allergien.values();
	}
}
