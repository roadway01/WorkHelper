/**
 * 
 */
package com.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年12月26日
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {
	private List<Person> persons;
	
	@XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
