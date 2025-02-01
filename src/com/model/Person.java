/**
 * 
 */
package com.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.controller.utils.DateUtil;
import com.controller.utils.LocalDateAdapter;
import com.controller.utils.StringAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年12月19日
 */
@XmlType(propOrder = {"name", "skill", "status", "startDate", "endDate"})
public class Person {
	private StringProperty name;
	private StringProperty skill;
	private StringProperty status;
	private ObjectProperty<LocalDate> startDate;
	private ObjectProperty<LocalDate> endDate;
	
	public Person() {
		this(null, null, null, null, null);
	}
	
	public Person(String name, String skill, String status, String startDate, String endDate) {
		this.name = new SimpleStringProperty(name);
		this.skill = new SimpleStringProperty(skill);
		this.status = new SimpleStringProperty(status);
		
		this.startDate = new SimpleObjectProperty<LocalDate>(startDate == null ? null : DateUtil.parse(startDate));
		this.endDate = new SimpleObjectProperty<LocalDate>(endDate == null ? null : DateUtil.parse(endDate));
	}

	/**
	 * 
	 */
	@XmlJavaTypeAdapter(StringAdapter.class)
	public StringProperty getName() {
		return name;
	}

	/**
	 * 
	 */
	@XmlJavaTypeAdapter(StringAdapter.class)
	public StringProperty getSkill() {
		return skill;
	}

	/**
	 * 
	 */
	@XmlJavaTypeAdapter(StringAdapter.class)
	public StringProperty getStatus() {
		return status;
	}

	/**
	 * 
	 */
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public ObjectProperty<LocalDate> getStartDate() {
		return startDate;
	}

	/**
	 * 
	 */
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public ObjectProperty<LocalDate> getEndDate() {
		return endDate;
	}

	/**
	 * 
	 */
	public void setName(StringProperty name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public void setSkill(StringProperty skill) {
		this.skill = skill;
	}

	/**
	 * 
	 */
	public void setStatus(StringProperty status) {
		this.status = status;
	}

	/**
	 * 
	 */
	public void setStartDate(ObjectProperty<LocalDate> startDate) {
		this.startDate = startDate;
	}

	/**
	 * 
	 */
	public void setEndDate(ObjectProperty<LocalDate> endDate) {
		this.endDate = endDate;
	}
	
}
