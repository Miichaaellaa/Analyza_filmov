# Analýza filmov


Projekt je zameraný na analýzu filmov. 

---

## Cieľ projektu
Cieľom projektu je:
- Načítať a spracovať databázu filmov
- Umožniť vyhľadávanie a filtrovanie filmov podľa mena herca alebo názvu filmu
- Výpis filmov v danom žánri v danom roku
- Implementovať stránkovaný výpis výsledkov.

---

##  Zdroj dát
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

