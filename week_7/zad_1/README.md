# Generator transakcji

## Komentarz do rozwiązania

Raporty nie generują faktycznego pokrycia, ponieważ niestety używam PowerMockito (Jacoco i Mockito nie są kompatybilne z tego co udało mi sie dowiedzieć). Starałam się tego nie robić, ale niestety nie mam pojęcia jak bez jego użycia zamockować plik wejściowy, jeżeli w teście nie przekazuję go nigdzie bezpośrednio... W rzeczywistości klasy CommandParser oraz Generator są w 100% pokryte. 

plik wyjściowy ma nazwę "output.json", domyślnie zapisuje się w głównym folderze projektu

Komendy do użycia:

- Budowanie projektu i tworzenie raportów testowych:

        gradle build

- Budowanie fat-jara:
    
        gradle shadowJar
    
- Dodawanie do lokalnego repozytorium:

        gradle publishMavenJavaPublicationToMavenLocal
        
Przykładowa komenda (z treści zadania):

        java -jar transaction-generator.jar -customerIds 1:20 -dateRange "2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.999-0100" -itemsFile items.csv -itemsCount 5:15 -itemsQuantity 1:30 -eventsCount 1000 -outDir ./output




