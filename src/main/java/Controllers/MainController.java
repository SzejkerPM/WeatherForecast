package Controllers;

import API.weatherApi.jsonToJava.WeatherMaster;
import Repository.HistoryRepository.History;
import Repository.HistoryRepository.HistoryRepository;
import Service.WeatherService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;


public class MainController {

    private final WeatherService weatherService = new WeatherService();
    private final HistoryRepository historyRepository = new HistoryRepository();

    @FXML
    Button buttonIdSearch;

    @FXML
    ImageView imageViewIcon;

    @FXML
    Label labelCityName;

    @FXML
    Label labelWeatherInfo;

    @FXML
    Label labelDescription;

    @FXML
    Label labelTemperature;

    @FXML
    Label labelRain;

    @FXML
    Label labelSnow;

    @FXML
    TextField textFieldCityName;

    @FXML
    Button buttonCityNameSearch;

    @FXML
    ComboBox<String> comboBoxHistory;


    public void initialize() {
        buildHistoryInComboBox();
    }
//TODO
    public void getWeatherForMainCities() {
        List<WeatherMaster> cities = weatherService.getCurrentWeatherForMainCities();
    }

    public void getWeatherByIdButton() {
        WeatherMaster weather = weatherService.getCurrentWeatherWithIp();
        fillLabelsWithData(weather);
        updateHistoryInComboBox();
    }

    public void onEnter() {
        getWeatherByCityName();
    }

    public void getWeatherByCityName() {
        WeatherMaster weather = weatherService.getCurrentWeatherWithCityName(textFieldCityName.getText());
        if (weather.getStatusCode() == 200) {
            fillLabelsWithData(weather);
            updateHistoryInComboBox();
        } else {
            showErrorDialog();
        }
        textFieldCityName.setText("");

    }

    private void buildHistoryInComboBox() {
        List<History> historyDesc = historyRepository.getHistoryDesc();
        for (int i = 0; i < 5; i++) {
            comboBoxHistory.getItems().add(i, historyDesc.get(i).getCityName());
        }

    }

    private void updateHistoryInComboBox() {
        List<History> historyDesc = historyRepository.getHistoryDesc();
        for (int i = 0; i < 5; i++) {
            comboBoxHistory.getItems().set(i, historyDesc.get(i).getCityName());
        }
        comboBoxHistory.getSelectionModel().clearAndSelect(0);
    }

    public void searchCityFromComboBox() {
        textFieldCityName.setText(comboBoxHistory.getValue());
        getWeatherByCityName();
    }

    public void showInfoDialog() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("WEATHER FORECAST INFO");
        infoAlert.setHeaderText("Informacje na temat aplikacji");
        infoAlert.setContentText("Wyszukiwanie po nazwie miasta - wpisz nazwę miasta w oznaczonym polu i wciśnij" +
                " przycisk SZUKAJ lub naciśnij ENTER\n\n" +
                "Wyszukiwanie po IP - wyszukuje Twoją przybliżoną lokalizację i na jej podstawie wyświetla pogodę\n\n" +
                "Ostatnio przeglądane miasta - zapisuje 5 ostatnio przeglądanych miast i sortuje od najnowszych" +
                " pozycji. Wybranie miasta z listy spowoduje natychmiastowe wyszukanie pogody");
        infoAlert.showAndWait();
    }

    private void showErrorDialog() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("WEATHER FORECAST ERROR");
        errorAlert.setHeaderText("Napotkaliśmy problem!");
        errorAlert.setContentText("Wygląda na to, że takie miasto nie istnieje lub nie możemy go odnaleźć.");
        errorAlert.showAndWait();
    }

    private void fillLabelsWithData(WeatherMaster weather) {
        long temp = Math.round(weather.getMainWeatherInfo().getTemp());
        labelCityName.setText(weather.getCityName() + ", " + weather.getCountry().getCountry());
        labelDescription.setText(weather.getDescriptions().get(0).getDescription());
        labelTemperature.setText(temp + "\u2103");
        labelWeatherInfo.setText("Temperatura min/max: "
                + weather.getMainWeatherInfo().getTempMin() + "/" + weather.getMainWeatherInfo().getTempMax() + "\u2103"
                + "\nTemperatura odczuwalna: " + weather.getMainWeatherInfo().getFeelsLike() + "\u2103"
                + "\nWilgotność: " + weather.getMainWeatherInfo().getHumidity() + " %"
                + "\nCiśnienie: " + weather.getMainWeatherInfo().getPressure() + " hPa"
                + "\nWiatr: " + weather.getWind().getSpeed() + " km/h"
                + "\nZachmurzenie: " + weather.getClouds().getCloudy() + " %");
        labelRain.setText(getRainIfPossible(weather));
        labelSnow.setText(getSnowIfPossible(weather));
        imageViewIcon.setImage(getIcon(weather));
    }

    private String getRainIfPossible(WeatherMaster weather) {

        if (weather.toString().contains("Rain")) {
            return "Opady deszczu: " + weather.getRain().getRainPerH() + " perH";
        } else {
            return "Opady deszczu: brak";
        }
    }

    private String getSnowIfPossible(WeatherMaster weather) {

        if (weather.toString().contains("Snow")) {
            return "Opady śniegu: " + weather.getSnow().getSnowPerH() + " perH";
        } else {
            return "Opady śniegu: brak";
        }
    }

    private Image getIcon(WeatherMaster weatherMaster) {
        String icon = weatherMaster.getDescriptions().get(0).getIcon();
        return new Image("http://openweathermap.org/img/w/" + icon + ".png");
    }
}
