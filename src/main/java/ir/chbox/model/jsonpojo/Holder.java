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
"Status",
"Error",
"tabs"
})
public class Holder {

@JsonProperty("Status")
private boolean Status;
@JsonProperty("Error")
private String Error;
@JsonProperty("tabs")
private List<Tab> tabs = new ArrayList<Tab>();
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Holder() {
}

/**
* 
* @param Status
* @param Error
* @param tabs
*/
public Holder(boolean Status, String Error, List<Tab> tabs) {
this.Status = Status;
this.Error = Error;
this.tabs = tabs;
}

/**
* 
* @return
* The Status
*/
@JsonProperty("Status")
public boolean isStatus() {
return Status;
}

/**
* 
* @param Status
* The Status
*/
@JsonProperty("Status")
public void setStatus(boolean Status) {
this.Status = Status;
}

/**
* 
* @return
* The Error
*/
@JsonProperty("Error")
public String getError() {
return Error;
}

/**
* 
* @param Error
* The Error
*/
@JsonProperty("Error")
public void setError(String Error) {
this.Error = Error;
}

/**
* 
* @return
* The tabs
*/
@JsonProperty("tabs")
public List<Tab> getTabs() {
return tabs;
}

/**
* 
* @param tabs
* The tabs
*/
@JsonProperty("tabs")
public void setTabs(List<Tab> tabs) {
this.tabs = tabs;
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
