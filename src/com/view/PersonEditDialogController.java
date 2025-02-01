/**
 * 
 */
package com.view;

import java.time.LocalDate;

import com.model.Person;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年12月21日
 */
public class PersonEditDialogController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField skillField;
	@FXML
	private ChoiceBox<String> statusChB;	
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	
	private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    
    @FXML
    private void initialize() {
    	statusChB.setItems(FXCollections.observableArrayList("工作", "释放"));
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setPerson(Person person) {
        this.person = person;
        
        if(person != null) {
        	nameField.setText(person.getName().getValue());
        	skillField.setText(person.getSkill().getValue());
			statusChB.setValue(person.getStatus().getValue());
			startDate.setValue(person.getStartDate().getValue());
			endDate.setValue(person.getEndDate().getValue());
        }
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setName(new SimpleStringProperty(nameField.getText()));
            person.setSkill(new SimpleStringProperty(skillField.getText()));
            person.setStatus(new SimpleStringProperty(statusChB.getValue()));
            person.setStartDate(new SimpleObjectProperty<LocalDate>(startDate.getValue()));
            person.setEndDate(new SimpleObjectProperty<LocalDate>(endDate.getValue()));

            okClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "请输入姓名!\n"; 
        }
        if (skillField.getText() == null || skillField.getText().length() == 0) {
            errorMessage += "请输入技能!\n"; 
        }
        if (statusChB.getValue() == null || statusChB.getValue().length() == 0) {
            errorMessage += "请选择状态!\n"; 
        }
        
        if (startDate.getValue() == null) {
            errorMessage += "请选择开始时间!\n"; 
        }
        
        if (endDate.getValue() == null) {
            errorMessage += "请选择结束时间!\n"; 
        }

        if(startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().isAfter(endDate.getValue())) {
        	errorMessage += "开始时间不能晚于结束时间!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
        	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("错误");
	    	alert.setContentText(errorMessage);
	    	alert.showAndWait();
        	return false;
        }
    }
}
