/**
 * 
 */
package com.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import com.model.Person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * @desc : TODO
 * @author: Zhu
 * @date : 2017年12月27日
 */
public class LengthOfServiceController {

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private Label infoLbl;

	private ObservableList<String> names = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		barChart.setPrefWidth(620);
	}

	public void setPersonData(List<Person> persons) {
		int[] lgth = new int[persons.size()];
		String[] name = new String[persons.size()];
		int i = 0;
		for (Person person : persons) {
			name[i] = person.getName().getValue();
			LocalDate l = LocalDate.now();
			LocalDate lStart = person.getStartDate().getValue();
			if (lStart.isAfter(l)) {
				lgth[i++] = 0;
			} else {
				Long weeks = lStart.until(l, ChronoUnit.WEEKS);
				lgth[i++] = weeks.intValue();
			}

		}
		names.addAll(Arrays.asList(name));
		xAxis.setCategories(names);

		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		series.setName("name");

		for (int j = 0; j < lgth.length; j++) {
			series.getData().add(new XYChart.Data<>(names.get(j), lgth[j]));
		}

		barChart.setCategoryGap(20);
		double width = 10 + (lgth.length - 1) * 100;
		barChart.setPrefWidth(width);
		barChart.getYAxis().setLabel("weeks");
		barChart.getData().add(series);
	}

	@FXML
	private void showData(MouseEvent e) {
		infoLbl.setTranslateX(e.getSceneX());
		infoLbl.setTranslateY(e.getSceneY());
		StringBuilder strB = new StringBuilder();
		int height = 1;
		for(XYChart.Series<String, Integer> series : barChart.getData()) {
			height = series.getData().size() > height ? series.getData().size() : height;
			series.getData().forEach((data) -> {
				strB.append(data.getXValue() + ":" + data.getYValue() + "\n");
			});
		}
		infoLbl.setPrefHeight(height * 25);
		infoLbl.setText(strB.toString());
	}
}
