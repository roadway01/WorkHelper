/**
 * 
 */
package com.controller.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年12月26日
 */
public class StringAdapter extends XmlAdapter<String, StringProperty>{
	@Override
	public StringProperty unmarshal(String v) throws Exception {
		return new SimpleStringProperty(v);
	}

	@Override
	public String marshal(StringProperty v) throws Exception {
		return v.getValue();
	}
}
