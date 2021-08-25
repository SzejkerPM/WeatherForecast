*** Witaj w projekcie WeatherForecast realizowanym na rzecz kursu "Java od podstaw" w Software Development Academy ***

** Aplikacja jest w tym momencie przystosowana do uruchomienia w trybie okienkowym JavaFX. **

Uruchomienie programu w konsoli:

Jeśli chcesz skorzystać z aplikacji w konsoli, musisz zajrzeć do klasy "Launcher" i usunąć dziedziczenie po Application, usunąć zawartość "main" i metodę "start". Następnie utworzyć obiekt WeatherService i na nim testować gotowe funkcje, z których korzysta również JavaFX *

Instrukcja:

1) Utwórz pustą bazę danych MySQL, następnie uzupełnij swoimi danymi plik persistence.xml znajdujący się w resources -> META-INF
2) Przejdź do pakietu "BuildCityDatabase" i uruchom program z klasy "BuildDatabase". Baza zapełni się danymi z pliku "city.list.json" (może to chwilę potrwać)
3) Prawdopodobnie przy uruchomieniu aplikacji z klasy "Launcher" wyskoczy błąd "runtime components are missing(...)". Rozwiązanie, które sam stosuje w tej sytuacji znajduje się w poniższym linku:
https://stackoverflow.com/questions/51478675/error-javafx-runtime-components-are-missing-and-are-required-to-run-this-appli

Upewnij się, że uruchamiasz projekt na Javie 11.* i JavaFX 11.* gdyż w takiej konfiguracji była tworzona!

O projekcie:
Projekt był realizowany samodzielnie w trakcie kursu, w momencie gdzie nie mieliśmy jeszcze zajęć z frontendu czy springa. "Pogodynka" to prosty program, który korzysta z API OpenWeatherMap do wyszukiwania pogody po: nazwie, id (z użyciem bazy danych miast) oraz przy pomocy współrzędnych uzyskiwanych z IP-API. Interfejs graficzny JavaFX dodałem ponadprogramowo aby poćwiczyć tworzenie aplikacji okienkowych. Krótki opis funkcji programu znajduje się również w samej aplikacji pod ikonką "i" w prawym górnym rogu.

Twórca aplikacji - Maciej Wilga

