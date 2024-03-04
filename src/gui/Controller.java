package gui;

import domain.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import service.Service;

public class Controller {
    private Service service;
    @FXML
    private ListView<Weather> listView;

    @FXML
    private ComboBox<String> descriptionCBox;
    @FXML
    private TextField textField;

    @FXML
    private TextArea textarea;
    @FXML
    private Button updateButton;
    @FXML
    private Button computeHoursButton;
    @FXML
    private TextField descriptionTextField;
    @FXML
    void compute(ActionEvent event) {
        int totalHours=0;
        String description= descriptionTextField.getText();
        Weather selectedValue = listView.getSelectionModel().getSelectedItem();
        if(selectedValue.getDescription().contains(description)){
            totalHours+= selectedValue.getEndHour()-selectedValue.getStartHour();
        }

        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Total hours: " + totalHours);
        alert.show();
    }


    @FXML
    void onClik(MouseEvent event) {
        Weather selectedValue = listView.getSelectionModel().getSelectedItem();
        if (selectedValue != null) {
            textField.setText(String.valueOf(selectedValue.getPrecipitationProbability()));
            textarea.setText(selectedValue.getDescription().toString());// Assuming YourClass has a meaningful toString method
        } else {
            textField.clear();
            textarea.clear();
        }
    }



    @FXML
    void select(ActionEvent event) {
        String descriptionCBoxValue = descriptionCBox.getValue();

        // Filter and sort by description and starting hour
        ObservableList<Weather> filteredWeathers = FXCollections.observableArrayList(
                service.getSortedByDescriptionAndStartingHour(descriptionCBoxValue)
        );

        listView.setItems(filteredWeathers);
    }


    public Controller(Service service) {
        this.service = service;
    }

    public void initialize() {

        this.descriptionCBox.setItems(FXCollections.observableArrayList(this.service.getAllUniqueDescriptions()));
        this.listView.setItems(FXCollections.observableArrayList(this.service.getWeathersSortedByStartingHour()));
    }
    @FXML
    void update(ActionEvent event) {
        int precipitation= Integer.parseInt(textField.getText());
        String description=textarea.getText();

        Weather selectedValue = listView.getSelectionModel().getSelectedItem();
        service.updateWeather(selectedValue,precipitation,description);
        populateList();
    }
    void populateList(){
        ObservableList<Weather> geneList = FXCollections.observableArrayList(service.getWeathers());
        listView.setItems(geneList);
    }
}
