package ir.chbox.model.jsonpojo;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"id",
"name",
"description",
"rating",
"cost",
"img"
})
public class Item {

@JsonProperty("id")
private long id;
@JsonProperty("name")
private String name;
@JsonProperty("description")
private String description;
@JsonProperty("rating")
private float rating;
@JsonProperty("cost")
private String cost;
@JsonProperty("img")
private String img;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Item() {
}

/**
* 
* @param id
* @param description
* @param name
* @param img
* @param rating
* @param cost
*/
public Item(long id, String name, String description, float rating, String cost, String img) {
this.id = id;
this.name = name;
this.description = description;
this.rating = rating;
this.cost = cost;
this.img = img;
}

/**
* 
* @return
* The id
*/
@JsonProperty("id")
public long getId() {
return id;
}

/**
* 
* @param id
* The id
*/
@JsonProperty("id")
public void setId(long id) {
this.id = id;
}

/**
* 
* @return
* The name
*/
@JsonProperty("name")
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The description
*/
@JsonProperty("description")
public String getDescription() {
return description;
}

/**
* 
* @param description
* The description
*/
@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

/**
* 
* @return
* The rating
*/
@JsonProperty("rating")
public float getRating() {
return rating;
}

/**
* 
* @param rating
* The rating
*/
@JsonProperty("rating")
public void setRating(float rating) {
this.rating = rating;
}

/**
* 
* @return
* The cost
*/
@JsonProperty("cost")
public String getCost() {
return cost;
}

/**
* 
* @param cost
* The cost
*/
@JsonProperty("cost")
public void setCost(String cost) {
this.cost = cost;
}

/**
* 
* @return
* The img
*/
@JsonProperty("img")
public String getImg() {
return img;
}

/**
* 
* @param img
* The img
*/
@JsonProperty("img")
public void setImg(String img) {
this.img = img;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}