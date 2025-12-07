package Entity;

import javax.persistence.*;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(length = 50)
    private String CategoryId;
    private String CategoryName;
    
    public Category(String a , String b) {
    	this.CategoryId = a;
    	this.CategoryName = b;
    }
    
    public Category() {}
    
    public String getId() {return CategoryId;}
    public void setId(String a) {this.CategoryId = a;}
    
    public String getCategoryname() {return CategoryName;}
    public void setCategoryname(String a) {this.CategoryName = a;}
    
    public String toString() {
    	return CategoryId + ", " + CategoryName;
    }
}
