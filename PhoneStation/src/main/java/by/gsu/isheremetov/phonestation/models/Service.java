package by.gsu.isheremetov.phonestation.models;

public class Service {
    private int id;
    private String name;
    private String description;
    private int price;
    private Boolean active;

    public Service(int id, String name, String description, int price,
	    Boolean active) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.price = price;
	this.active = active;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

}
