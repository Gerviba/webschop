package hu.gerviba.webschop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Indexed
@NoArgsConstructor
@Table(name = "items")
@SuppressWarnings("serial")
public class ItemEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field(termVector = TermVector.YES)
    @Column
    private String name;
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private CircleEntity circle;

    @Lob
    @Field(termVector = TermVector.YES)
    @Column
    private String description;

    @Lob
    @Column
    private String ingredients;

    @Lob
    @Column
    private String detailsConfigJson;

    @JsonIgnore
    @Field(termVector = TermVector.YES)
    @Column
    private String keywords;
    
    @Column
    private int price; 
    
    @Column
    private boolean orderable;

    @Column
    private boolean visible;

    @Column
    private boolean service;
    
    @Column
    private boolean visibleInAll;
    
    @Column
    private boolean personallyOrderable;
    
    @Column
    private String imageName;

    @Column
    private int flag;
    
	public ItemEntity(String name, CircleEntity circle, String description, String ingredients, 
	        String keywords, String detailsConfigJson, int price, boolean orderable, boolean visible, 
	        boolean service, boolean visibleInAll, boolean personallyOrderable,
	        String imageName) {
		this.name = name;
		this.circle = circle;
		this.description = description;
		this.ingredients = ingredients;
		this.keywords = keywords;
		this.detailsConfigJson = detailsConfigJson;
		this.price = price;
		this.orderable = orderable;
		this.visible = visible;
		this.service = service;
		this.visibleInAll = visibleInAll;
		this.personallyOrderable = personallyOrderable;
		this.imageName = imageName;
		this.flag = 0;
	}

    public ItemEntity(ItemEntity copy) {
        this.id = copy.getId();
        this.name = copy.getName();
        this.description = copy.getDescription();
        this.ingredients = copy.getIngredients();
        this.detailsConfigJson = copy.getDetailsConfigJson();
        this.keywords = copy.getKeywords();
        this.price = copy.getPrice();
        this.orderable = copy.isOrderable();
        this.visible = copy.isVisible();
        this.imageName = copy.getImageName();
    }
    
}
