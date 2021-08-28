*** Witaj w projekcie WeatherForecast realizowanym na rzecz kursu "Java od podstaw" w Software Development Academy ***

- możliwość uruchomienia programu w konsoli przez klasę "LauncherConsole"
- możliwość uruchomienia programu z GUI przez klasę "LauncherFX" (rekomendowane)

Instrukcja:

1) Utwórz pustą bazę danych MySQL, następnie uzupełnij swoimi danymi plik persistence.xml znajdujący się w resources -> META-INF
2) Przejdź do pakietu "BuildCityDatabase" i uruchom program z klasy "BuildDatabase". Baza zapełni się danymi z pliku "city.list.json" (może to chwilę potrwać)
3) Prawdopodobnie przy uruchomieniu aplikacji z klasy "LauncherFX" wyskoczy błąd "runtime components are missing(...)". Rozwiązanie, które sam stosuje w tej sytuacji, znajduje się w poniższym linku:
https://stackoverflow.com/questions/51478675/error-javafx-runtime-components-are-missing-and-are-required-to-run-this-appli

Upewnij się, że uruchamiasz projekt na Javie 11.* i JavaFX 11.*!

O projekcie:
Projekt był realizowany samodzielnie w trakcie kursu, w momencie gdzie nie mieliśmy jeszcze zajęć z frontendu czy springa. "Pogodynka" to prosty program, który korzysta z API OpenWeatherMap do wyszukiwania pogody po: nazwie, id (z użyciem bazy danych miast) oraz przy pomocy współrzędnych uzyskiwanych z IP-API. Interfejs graficzny JavaFX dodałem dodatkowo, aby poćwiczyć tworzenie aplikacji okienkowych. Krótki opis funkcji programu znajduje się również w samej aplikacji pod ikonką "i" w prawym górnym rogu.
Prace nad projektem nadal trwają. Pozostało napisać testy i zająć się stylowaniem CSS + ewentualne pomysły na nowe funkcje.

Twórca aplikacji, Maciej Wilga

