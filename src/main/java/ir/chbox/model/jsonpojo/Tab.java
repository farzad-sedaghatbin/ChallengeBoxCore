package ir.chbox.model.jsonpojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"id",
"title",
"items"
})
public class Tab {

@JsonProperty("id")
private long id;
@JsonProperty("title")
private String title;
@JsonProperty("items")
private List<Item> items = new ArrayList<Item>();
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Tab() {
}

/**
* 
* @param id
* @param title
* @param items
*/
public Tab(long id, String title, List<Item> items) {
this.id = id;
this.title = title;
this.items = items;
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
* The title
*/
@JsonProperty("title")
public String getTitle() {
return title;
}

/**
* 
* @param title
* The title
*/
@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

/**
* 
* @return
* The items
*/
@JsonProperty("items")
public List<Item> getItems() {
return items;
}

/**
* 
* @param items
* The items
*/
@JsonProperty("items")
public void setItems(List<Item> items) {
this.items = items;
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