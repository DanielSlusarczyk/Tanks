# Tanks
Projekt _Tanks_ zakładał stworzenie programu dedykowanego dwóm graczom rozmieszczonym po dwóch stronach okna rozgrywki, których zadaniem jest strzelanie do komórek w
srodkowej czesci okna za pomoca przypisanych czołgów.  
#### Autorzy: Daniel Ślusarczyk, Jakub Łaba

## Zasady
Gracze maja mozliwosc wertykalnego poruszania sie swoimi jednostkami, oraz obracania lufa czołgu. Kazdy pojazd strzela
pociskami o cechach mozliwych do okreslenie przez plik konfiguracyjny.
Kwadratowe komórki, które sa obiektem przyznawania punktów graczom, dziela sie na trzy rodzaje:  
- Zwykła komórka – kwadratowy obiekt o wszystkich krawedziach wrazliwych na kontakt z pociskiem. Punkty za jej unicestwienie przyznawane sa graczowi zadajacemu koncowe obrazenia.   
- Bomba – obiekt posiadajacy niewrazliwe krawedzie z pominieciem górnej, która jest jedynym sposobem na zastrzelenie tej komórki. Dokonanie tego konczy gre.  
- Kolonia – zbiór maksymalnie pieciu zwykłych komórek w losowej konfiguracji. Gracz otrzymuje zsumowane punkty wszystkich komórek wchodzacych w skład kolonii po zastrzeleniu ostatniej komórki w zbiorze.  

Wszystkie wyzej wymienione komórki poruszaja wertykalnie w dół w ramach okna rozgrywki. Podobnie jak pociski posiadaja parametry modyfikowalne z poziomu pliku konfiguracyjnego,
a ich najwazniejsza cecha jest wartość potrzebna do zastrzelenia znajdujaca sie w centralnej części komórki. Gra w sposób regularny zwieksza trudnosc rozgrywki poprzez zwiekszanie wartosci potrzebnej do unicestwienia komórki, szybkosci komórek i wystrzeliwanych pocisków, oraz zmniejszanie promienia pocisku i długosci boku komórki. Koniec gry przewiduja dwa scenariusze: 
- upłyniecie okreslonego czasu rozgrywki lub zastrzelenie komórki bomby. Gre wygrywa gracz o wiekszej ilosci punktów w momencie zakonczenia.

## Wnioski
Projekt _Tanks_ był pierwszą udaną próbą stworzenia aplikacji okienkowej w Javie. Pozwolił na poznanie problematyki działania pliku konfiguracyjnego i koncepcji programowania obiektowego. 

## Wygląd
### Rozgrywka
![rozgrywka1](https://user-images.githubusercontent.com/74370363/125438035-615f21cb-048b-419c-ab55-e296f6591966.png)

### Okno ustawień
![zakladka1](https://user-images.githubusercontent.com/74370363/125438349-b97c302a-de82-4079-a3f5-6fcf8d94ebc7.png)
![zakladka2](https://user-images.githubusercontent.com/74370363/125438362-e921ffa3-cf03-4708-aabb-5a6e25f53c09.png)
![zakladka3](https://user-images.githubusercontent.com/74370363/125438371-a84414a3-bfb2-440b-9685-4cb5089522b2.png)
