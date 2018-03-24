# Generator transakcji

## Komentarz do rozwiązania

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




