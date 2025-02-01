/**
 * 
 */
package com.view;

import com.controller.MainApp;
import com.model.Person;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @desc : TODO
 * @author: Zhu
 * @date : 2017年12月20日
 */
public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> nameColumn;
	@FXML
	private Label nameLabel;
	@FXML
	private Label skillLabel;
	@FXML
	private ChoiceBox<String> statusChB;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;

	private MainApp mainApp;

	public PersonOverviewController() {

	}

	private void showPersonDetails(Person person) {
		if (person != null) {
			nameLabel.setText(person.getName().getValue());
			skillLabel.setText(person.getSkill().getValue());
			statusChB.setValue(person.getStatus().getValue());
			startDate.setValue(person.getStartDate().getValue());
			endDate.setValue(person.getEndDate().getValue());
		} else {
			nameLabel.setText("");
			skillLabel.setText("");
			statusChB.setValue(null);
			startDate.setValue(null);
			endDate.setValue(null);
		}
	}

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		
		statusChB.setItems(FXCollections.observableArrayList("工作", "释放"));

		showPersonDetails(null);		

		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}
	
	@FXML
	private void handleDeletePerson() {
	    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        personTable.getItems().remove(selectedIndex);
	    } else {
	        // Nothing selected.
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("错误");
	    	alert.setContentText("请选择需删除的人员！");
	    	alert.showAndWait();
	    }
	}
	
	@FXML
	private void handleNewPerson() {
	    Person tempPerson = new Person();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPerson, "新增");
	    if (okClicked) {
	        mainApp.getPersonData().add(tempPerson);
	    }
	}
	
	@FXML
	private void handleEditPerson() {
	    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson, "编辑");
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	    	// Nothing selected.
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("错误");
	    	alert.setContentText("请选择需编辑的人员！");
	    	alert.showAndWait();
	    }
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the table
		personTable.setItems(mainApp.getPersonData());
	}
}
