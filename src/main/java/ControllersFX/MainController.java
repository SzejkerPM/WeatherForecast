package ControllersFX;

import API.WeatherApi.WeatherProperties.WeatherMaster;
import Repository.HistoryRepository.History;
import Repository.HistoryRepository.HistoryRepository;
import Service.WeatherService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    ImageView imageViewUpdated;

    public void initialize() {
        buildHistoryInComboBox(); // + 0.3 sekundy ??adowania apki
        getWeatherForMainCities(); // + 1.5 sekundy ??adowania apki
    }

    public void refreshCurrentCity() {
        textFieldCityName.setText(historyRepository.getHistoryDesc().get(0).getCityName());
        getWeatherByCityName();
    }

    public void refreshMainCities() {
        getWeatherForMainCities();
        imageViewUpdated.setVisible(true);
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

    public void hideUpdatedImage() {
        imageViewUpdated.setVisible(false);
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
        infoAlert.setHeaderText("Informacje na temat funkcji aplikacji");
        infoAlert.setContentText("Wyszukiwanie po nazwie miasta - wpisz nazw?? miasta w oznaczonym polu i wci??nij" +
                " przycisk SZUKAJ lub naci??nij ENTER.\n\n" +
                "Wyszukiwanie po IP - wyszukuje Twoj?? przybli??on?? lokalizacj?? i na jej podstawie wy??wietla pogod??.\n\n" +
                "Ostatnio przegl??dane miasta - zapisuje 5 ostatnio przegl??danych miast i sortuje od najnowszych" +
                " pozycji. Wybranie miasta z listy spowoduje natychmiastowe wyszukanie pogody.\n\n" +
                "Przycisk OD??WIE?? w lewym g??rnym rogu pozwala od??wie??y?? ostatnio wyszukane miasto.\n\n" +
                "Przycisk OD??WIE?? w prawym dolnym rogu pozwala od??wie??y?? pogod?? dla ca??ej mapy " +
                "(mo??e to zawiesi?? program na kilka sekund w zale??no??ci od mocy komputera i pr??dko??ci internetu). " +
                "Gdy mapa sko??czy si?? aktualizowa?? - wy??wietli zielony obrazek w celu potwierdzenia. Klikni??cie na niego" +
                " sprawi, ??e zniknie.");
        infoAlert.showAndWait();
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

    // Niestety, metoda clear stwarza zbyt wiele problem??w, aby jej u??ywa??. Dlatego mamy clearAndSelect0
    // A metoda wy??ej jest po to ??eby nie wywo??ywa?? clear przy initialize
    private void correctSelectInComboBox() {
        comboBoxHistory.getSelectionModel().clearAndSelect(0);
    }

    private void showErrorDialog() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("WEATHER FORECAST ERROR");
        errorAlert.setHeaderText("Napotkali??my problem!");
        errorAlert.setContentText("Wygl??da na to, ??e takie miasto nie istnieje lub nie mo??emy go odnale????.");
        errorAlert.showAndWait();
    }

    private void fillLabelsWithData(WeatherMaster weather) {
        labelCityName.setText(weather.getCityName() + ", " + weather.getCountry().getCountry());
        labelDescription.setText(weather.getDescriptions().get(0).getDescription());
        labelTemperature.setText(Math.round(weather.getMainWeatherInfo().getTemp()) + " \u2103");
        labelWeatherInfo.setText("\nTemperatura odczuwalna: " + weather.getMainWeatherInfo().getFeelsLike() + " \u2103"
                + "\nTemperatura min/max: "
                + weather.getMainWeatherInfo().getTempMin() + "/" + weather.getMainWeatherInfo().getTempMax() + " \u2103"
                + "\nWilgotno????: " + weather.getMainWeatherInfo().getHumidity() + " %"
                + "\nCi??nienie: " + weather.getMainWeatherInfo().getPressure() + " hPa"
                + "\nWiatr: " + weather.getWind().getSpeed() + " km/h"
                + "\nZachmurzenie: " + weather.getClouds().getCloudy() + " %");
        labelRain.setText(getRainIfPossible(weather));
        labelSnow.setText(getSnowIfPossible(weather));
        imageViewIcon.setImage(getIcon(weather));
    }

    private void getWeatherForMainCities() {
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

    private String getRainIfPossible(WeatherMaster weather) {

        if (weather.toString().contains("Rain")) {
            return "Opady deszczu: " + weather.getRain().getRainPerH() + " mm";
        } else {
            return "Opady deszczu: brak";
        }
    }

    private String getSnowIfPossible(WeatherMaster weather) {

        if (weather.toString().contains("Snow")) {
            return "Opady ??niegu: " + weather.getSnow().getSnowPerH() + " mm";
        } else {
            return "Opady ??niegu: brak";
        }
    }

    private Image getIcon(WeatherMaster weatherMaster) {
        return new Image("https://openweathermap.org/img/w/" + weatherMaster.getDescriptions().get(0).getIcon() + ".png");
    }
}

//TODO w miejsca gdzie mam dane o pogodzie umie??ci?? inne informacje lub ciekawostki (doda?? te?? przycisk kt??ry je przywr??ci po wyszukaniu pogody)
