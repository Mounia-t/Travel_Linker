package travelLinker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Template {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
	private String nom;
	private String htmlCssContent;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getHtmlCssContent() {
		return htmlCssContent;
	}

	public void setHtmlCssContent(String htmlCssContent) {
		this.htmlCssContent = htmlCssContent;
	}

	public Long getId() {
		return id;
	}
}
