/**
 * 
 */
package com.controller.utils;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @desc : TODO
 * @author: Zhu
 * @date : 2017年12月26日
 */
public class LocalDateAdapter extends XmlAdapter<String, ObjectProperty<LocalDate>> {
	@Override
	public ObjectProperty<LocalDate> unmarshal(String v) throws Exception {
		return new SimpleObjectProperty<LocalDate>(v == null ? null : DateUtil.parse(v));
	}

	@Override
	public String marshal(ObjectProperty<LocalDate> v) throws Exception {
		return DateUtil.format(v.getValue());
	}
}
