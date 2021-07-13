# Tanks
Projekt _Tanks_ zakładał stworzenie programu dedykowanego dwóm graczom rozmieszczonym po dwóch stronach okna rozgrywki, których zadaniem jest strzelanie do komórek w
środkowej części okna za pomoca przypisanych czołgów.  
#### Autorzy: Daniel Ślusarczyk, Jakub Łaba

## Zasady
Gracze maja możliwość wertykalnego poruszania sie swoimi jednostkami, oraz obracania lufą czołgu. Kazdy pojazd strzela pociskami o cechach możliwych do określenie przez plik konfiguracyjny.
Kwadratowe komórki, które sa obiektem przyznawania punktów graczom, dziela sie na trzy rodzaje:  
- Zwykła komórka – kwadratowy obiekt o wszystkich krawędziach wrażliwych na kontakt z pociskiem. Punkty za jej unicestwienie przyznawane są graczowi zadającemu końcowe obrażenia.   
- Bomba – obiekt posiadający niewrażliwe krawędzie z pominieciem górnej, która jest jedynym sposobem na zastrzelenie tej komórki. Dokonanie tego kończy grę.  
- Kolonia – zbiór maksymalnie pięciu zwykłych komórek w losowej konfiguracji. Gracz otrzymuje zsumowane punkty wszystkich komórek wchodzących w skład kolonii po zastrzeleniu ostatniej komórki w zbiorze.  

Wszystkie wyżej wymienione komórki poruszają się wertykalnie w dół w ramach okna rozgrywki. Podobnie jak pociski posiadają parametry modyfikowalne z poziomu pliku konfiguracyjnego,a ich najważniejszą cechą jest wartość potrzebna do zastrzelenia znajdująca sie w centralnej części komórki. Gra w sposób regularny zwiększa trudność rozgrywki poprzez zwiększanie wartości potrzebnej do unicestwienia komórki, szybkości komórek i wystrzeliwanych pocisków, oraz zmniejszanie promienia pocisku i długości boku komórki. Koniec gry przewidują dwa scenariusze: 
- upłyniecie okreslonego czasu rozgrywki lub zastrzelenie komórki bomby. Gre wygrywa gracz o większej ilosci punktów w momencie zakończenia.

## Wnioski
Projekt _Tanks_ był pierwszą udaną próbą stworzenia aplikacji okienkowej w Javie. Pozwolił na poznanie problematyki działania pliku konfiguracyjnego i koncepcji programowania obiektowego. 

## Wygląd
### Rozgrywka
![rozgrywka1](https://user-images.githubusercontent.com/74370363/125438035-615f21cb-048b-419c-ab55-e296f6591966.png)
![rozgrywka4](https://user-images.githubusercontent.com/74370363/125438574-3dcf01ea-a91d-43e5-b336-5e77afd08bd1.png)

### Okno ustawień
![zakladka1](https://user-images.githubusercontent.com/74370363/125438349-b97c302a-de82-4079-a3f5-6fcf8d94ebc7.png)
![zakladka2](https://user-images.githubusercontent.com/74370363/125438362-e921ffa3-cf03-4708-aabb-5a6e25f53c09.png)
![zakladka3](https://user-images.githubusercontent.com/74370363/125438371-a84414a3-bfb2-440b-9685-4cb5089522b2.png)
