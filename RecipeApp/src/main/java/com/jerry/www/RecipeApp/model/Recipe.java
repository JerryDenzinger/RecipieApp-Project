package com.jerry.www.RecipeApp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Data;

@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String description;
 
	@Min(1)
	@Max(999)
	private Integer prepTime;
	
	@Min(1)
	@Max(999)
	private Integer cookTime;
	
	@Min(1)
	@Max(999)
	private Integer servings;
	private String source;
	
	@URL
	private String url;
	
	@NotBlank
	@Lob
	private String directions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	@Lob
	private Byte[] image;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	@ManyToMany
	@JoinTable(name = "recipe_category",
	joinColumns = @JoinColumn(name = "recipe_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public void setNotes(Notes notes) {
		if (notes != null) {
			this.notes = notes;
			notes.setRecipe(this);
		}
	}
	
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
