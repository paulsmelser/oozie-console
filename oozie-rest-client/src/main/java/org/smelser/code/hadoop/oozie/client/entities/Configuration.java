package org.smelser.code.hadoop.oozie.client.entities;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("configuration")
public class Configuration {

    @XStreamImplicit(itemFieldName = "property")
    private List<Property> properties = new ArrayList<Property>();

    public List<Property> getProperties() {
	return properties;
    }

    public void setProperties(List<Property> properties) {
	this.properties = properties;
    }

    public void remove(String name) {
	List<Property> newProps = new ArrayList<Property>();
	for (Property prop : properties) {
	    if (!prop.getName().equalsIgnoreCase(name)) {
		newProps.add(prop);
	    }
	}
	this.properties = newProps;
    }

    public void add(String key, String value) {
	this.properties.add(new Property(key, value));
    }
}
