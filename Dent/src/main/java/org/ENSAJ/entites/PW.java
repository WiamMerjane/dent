package org.ENSAJ.entites;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PW {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String objectif;
	private String docs;

	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<Groupe> groups;
	
	@ManyToOne
	private Tooth tooth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getDocs() {
		return docs;
	}

	public void setDocs(String docs) {
		this.docs = docs;
	}

	public List<Groupe> getGroups() {
		return groups;
	}

	public void setGroups(List<Groupe> groups) {
		this.groups = groups;
	}

	public Tooth getTooth() {
		return tooth;
	}

	public void setTooth(Tooth tooth) {
		this.tooth = tooth;
	}
}
