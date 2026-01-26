# Analýza filmov

Projekt je zameraný na analýzu filmov.

---

## Cieľ projektu
Cieľom projektu je:
- Načítať a spracovať databázu filmov
- Umožniť vyhľadávanie a filtrovanie filmov podľa mena herca alebo názvu filmu
- Výpis filmov v danom žánri v danom roku
- Implementovať stránkovaný výpis výsledkov

---


## Flowchart - Vyhľadávanie podľa herca 
```mermaid
A([Začiatok]) --> B[Zadaj meno herca]
B --> C[Načítaj zoznam filmov]
C --> D{Existuje ďalší film?}
D -- Áno --> E{Obsahuje film herca?}
E -- Áno --> F[Vypíš film]
E -- Nie --> G[Preskoč film]
F --> D
G --> D
D -- Nie --> H([Koniec])









---
## Zdroj dát
Filmy sú načítané z verejného JSON súboru:  
https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json

---

## Funkcionality
- Načítanie filmov z JSON súboru
- Modelovanie dát pomocou tried:
    - Movie
    - InputValidator
    - TextoveUI
- Vyhľadávanie filmov:
    - podľa názvu filmu
    - podľa herca
- Výpis filmov:
    - podľa žánru
    - podľa roku vydania
- Stránkovanie výpisu:
    - po desiatom riadku sa výpis pozastaví
    - pokračovanie po stlačení Enter klávesy

---

## UML diagram tried

```mermaid
classDiagram
    class Main {
        +main(String[] args)
    }

    class TextoveUI {
        -MovieManager manager
        +TextoveUI()
        +start()
    }

    class MovieManager {
        -List~Movie~ movies
        +loadMoviesFromUrl(String urlPath)
        +searchByActor(String actorName)
        +searchByTitle(String titleQuery)
        +filterByGenreAndYear(String genre, int year)
        +getMovieCount() int
    }

    class Movie {
        -String title
        -int year
        -List~String~ cast
        -List~String~ genres
        +getTitle() String
        +getYear() int
        +getCast() List~String~
        +getGenres() List~String~
        +toString() String
    }

    class InputValidator {
        -Scanner scanner
        +getInt(String prompt, int min, int max) int
        +getString(String prompt, int maxLength) String
        +getYear(String prompt) int
        +getMenuChoice(String prompt, int options) int
        +waitForEnter()
    }

    class Tester {
        +main(String[] args)
    }

    Main --> TextoveUI : spúšťa
    TextoveUI --> MovieManager : používa
    MovieManager "1" --> "*" Movie : spravuje
    TextoveUI ..> InputValidator : validuje vstup
