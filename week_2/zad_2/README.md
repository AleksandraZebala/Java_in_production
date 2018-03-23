# module.Generator transakcji

## Komentarz do rozwiązania

plik wyjściowy o nazwie "output.json" domyślnie zapisuje się w sr/main/java

Komendy do użycia:

- Budowanie fat-jara:
    
        gradle shadowJar
    
- Dodawanie do lokalnego repozytorium:

        gradle publishMavenJavaPublicationToMavenLocal
        
- Tworzenie raportów testowych:

        gradle build
        
        gradle test
        
Przykładowa komenda (z treści zadania):

        java -jar transaction-generator.jar -customerIds 1:20 -dateRange "2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.999-0100" -itemsFile items.csv -itemsCount 5:15 -itemsQuantity 1:30 -eventsCount 1000 -outDir ./output




