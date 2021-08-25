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

    @FXML
    Label labelBialystok;

    @FXML
    ImageView imageViewBialystok;

    @FXML
    Label labelBydgoszcz;

    @FXML
    ImageView imageViewBydgoszcz;

    @FXML
    Label labelGdansk;

    @FXML
    ImageView imageViewGdansk;

    @FXML
    Label labelGorzowWlkp;

    @FXML
    ImageView imageViewGorzowWlkp;

    @FXML
    Label labelKatowice;

    @FXML
    ImageView imageViewKatowice;

    @FXML
    Label labelKielce;

    @FXML
    ImageView imageViewKielce;

    @FXML
    Label labelKrakow;

    @FXML
    ImageView imageViewKrakow;

    @FXML
    Label labelTorun;

    @FXML
    ImageView imageViewTorun;

    @FXML
    Label labelLublin;

    @FXML
    ImageView imageViewLublin;

    @FXML
    Label labelLodz;

    @FXML
    ImageView imageViewLodz;

    @FXML
    Label labelOlsztyn;

    @FXML
    ImageView imageViewOlsztyn;

    @FXML
    Label labelOpole;

    @FXML
    ImageView imageViewOpole;

    @FXML
    Label labelPoznan;

    @FXML
    ImageView imageViewPoznan;

    @FXML
    Label labelRzeszow;

    @FXML
    ImageView imageViewRzeszow;

    @FXML
    Label labelSzczecin;

    @FXML
    ImageView imageViewSzczecin;

    @FXML
    Label labelWarszawa;

    @FXML
    ImageView imageViewWarszawa;

    @FXML
    Label labelWroclaw;

    @FXML
    ImageView imageViewWroclaw;

    @FXML
    Label labelZielonaGora;

    @FXML
    ImageView imageViewZielonaGora;



    public void initialize() {
        buildHistoryInComboBox();
        getWeatherForMainCities();
    }

    public void refreshCurrentCity() {
        textFieldCityName.setText(historyRepository.getHistoryDesc().get(0).getCityName());
        getWeatherByCityName();
    }

    public void refreshMainCities() {
        getWeatherForMainCities();
    }

    public void getWeatherForMainCities() {
        List<WeatherMaster> cities = weatherService.getCurrentWeatherForMainCities();

        labelBialystok.setText(Math.round(cities.get(0).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewBialystok.setImage(getIcon(cities.get(0)));
        labelBydgoszcz.setText(Math.round(cities.get(1).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewBydgoszcz.setImage(getIcon(cities.get(1)));
        labelGdansk.setText(Math.round(cities.get(2).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewGdansk.setImage(getIcon(cities.get(2)));
        labelGorzowWlkp.setText(Math.round(cities.get(3).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewGorzowWlkp.setImage(getIcon(cities.get(3)));
        labelKatowice.setText(Math.round(cities.get(4).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewKatowice.setImage(getIcon(cities.get(4)));
        labelKielce.setText(Math.round(cities.get(5).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewKielce.setImage(getIcon(cities.get(5)));
        labelKrakow.setText(Math.round(cities.get(6).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewKrakow.setImage(getIcon(cities.get(6)));
        labelLublin.setText(Math.round(cities.get(7).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewLublin.setImage(getIcon(cities.get(7)));
        labelLodz.setText(Math.round(cities.get(8).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewLodz.setImage(getIcon(cities.get(8)));
        labelOlsztyn.setText(Math.round(cities.get(9).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewOlsztyn.setImage(getIcon(cities.get(9)));
        labelOpole.setText(Math.round(cities.get(10).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewOpole.setImage(getIcon(cities.get(10)));
        labelPoznan.setText(Math.round(cities.get(11).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewPoznan.setImage(getIcon(cities.get(11)));
        labelRzeszow.setText(Math.round(cities.get(12).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewRzeszow.setImage(getIcon(cities.get(12)));
        labelSzczecin.setText(Math.round(cities.get(13).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewSzczecin.setImage(getIcon(cities.get(13)));
        labelTorun.setText(Math.round(cities.get(14).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewTorun.setImage(getIcon(cities.get(14)));
        labelWarszawa.setText(Math.round(cities.get(15).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewWarszawa.setImage(getIcon(cities.get(15)));
        labelWroclaw.setText(Math.round(cities.get(16).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewWroclaw.setImage(getIcon(cities.get(16)));
        labelZielonaGora.setText(Math.round(cities.get(17).getMainWeatherInfo().getTemp()) + " \u2103");
        imageViewZielonaGora.setImage(getIcon(cities.get(17)));
    }

    public void getWeatherByIdButton() {
        WeatherMaster weather = weatherService.getCurrentWeatherWithIp();
        fillLabelsWithData(weather);
        updateAndCorrectHistoryInComboBox();
    }

    public void onEnter() {
        getWeatherByCityName();
    }

    public void getWeatherByCityName() {
        WeatherMaster weather = weatherService.getCurrentWeatherWithCityName(textFieldCityName.getText());
        if (weather.getStatusCode() == 200) {
            fillLabelsWithData(weather);
            updateAndCorrectHistoryInComboBox();
        } else {
            showErrorDialog();
        }
        textFieldCityName.setText("");

    }

    private void buildHistoryInComboBox() {
        for (int i = 0; i < 5; i++) {
            comboBoxHistory.getItems().add(i, "Puste miejsce");
        }
        updateHistoryInComboBox();
    }

    private void updateHistoryInComboBox() {
        List<History> historyDesc = historyRepository.getHistoryDesc();
        for (int i = 0; i < historyRepository.getActualSizeOfCityHistory(); i++) {
            comboBoxHistory.getItems().set(i, historyDesc.get(i).getCityName());
        }
    }

    private void updateAndCorrectHistoryInComboBox() {
        updateHistoryInComboBox();
        correctSelectInComboBox();
    }

    // Niestety, metoda clear stwarza zbyt wiele problemów, aby jej używać. Dlatego mamy clearAndSelect0
    private void correctSelectInComboBox() {
        comboBoxHistory.getSelectionModel().clearAndSelect(0);
    }

    public void searchCityFromComboBox() {
        String value = comboBoxHistory.getValue();
        if (!value.equalsIgnoreCase("Puste miejsce")) {
            textFieldCityName.setText(value);
            getWeatherByCityName();
        }
    }

    public void showInfoDialog() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("WEATHER FORECAST INFO");
        infoAlert.setHeaderText("Informacje na temat aplikacji");
        infoAlert.setContentText("Wyszukiwanie po nazwie miasta - wpisz nazwę miasta w oznaczonym polu i wciśnij" +
                " przycisk SZUKAJ lub naciśnij ENTER\n\n" +
                "Wyszukiwanie po IP - wyszukuje Twoją przybliżoną lokalizację i na jej podstawie wyświetla pogodę\n\n" +
                "Ostatnio przeglądane miasta - zapisuje 5 ostatnio przeglądanych miast i sortuje od najnowszych" +
                " pozycji. Wybranie miasta z listy spowoduje natychmiastowe wyszukanie pogody\n\n" +
                "Przycisk ODŚWIEŻ w lewym górnym rogu pozwala odświeżyć ostatnio wyszukane miasto\n\n" +
                "Przycisk ODŚWIEŻ w prawym dolnym rogu pozwala odświeżyć pogodę dla całej mapy " +
                "(może to zawiesić program na kilka sekund w zależności od mocy komputera i prędkości internetu)");
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
        labelCityName.setText(weather.getCityName() + ", " + weather.getCountry().getCountry());
        labelDescription.setText(weather.getDescriptions().get(0).getDescription());
        labelTemperature.setText(Math.round(weather.getMainWeatherInfo().getTemp()) + " \u2103");
        labelWeatherInfo.setText("Temperatura min/max: "
                + weather.getMainWeatherInfo().getTempMin() + "/" + weather.getMainWeatherInfo().getTempMax() + " \u2103"
                + "\nTemperatura odczuwalna: " + weather.getMainWeatherInfo().getFeelsLike() + " \u2103"
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
            return "Opady deszczu: " + weather.getRain().getRainPerH() + " mm";
        } else {
            return "Opady deszczu: brak";
        }
    }

    private String getSnowIfPossible(WeatherMaster weather) {

        if (weather.toString().contains("Snow")) {
            return "Opady śniegu: " + weather.getSnow().getSnowPerH() + " mm";
        } else {
            return "Opady śniegu: brak";
        }
    }

    private Image getIcon(WeatherMaster weatherMaster) {
        return new Image("https://openweathermap.org/img/w/" + weatherMaster.getDescriptions().get(0).getIcon() + ".png");
    }
}

//TODO przycisk "odśwież" dla aktualnego miasta (może też dla wojewódzkich)
//TODO w miejsca gdzie mam dane o pogodzie umieścić inne informacje lub ciekawostki (dodać też przycisk który je przywróci po wyszukaniu pogody)
