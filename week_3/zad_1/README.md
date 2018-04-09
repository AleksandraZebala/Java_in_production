# Loggers Hell

Stwórz program który wczyta (np. z parametrów uruchomieniowych) cenę biletu, wiek klienta, jego Id oraz Id naszej firmy. Następnie skorzystaj z klasy DiscountCalculator (napisanej przez Wasz zespół dawno temu i przechowywanej we wspólnej dla wielu aplikacji bibliotece) aby policzyć zniżkę dla tego klienta. Finalną cenę biletu oraz oba identyfikatory podaj do klasy PaymentsService (dostarczanej nam przez zewnętrzną firmę, implementacja nieznana).

Według nowych wytycznych frameworkiem do logowania, którego powinniśmy używać w firmie jest Logback.
Spraw aby logi zarówno z Twojej aplikacji jak i obu bibliotek wyświetliły się poprawnie. Pamiętaj, że nie możesz modyfikować kodu zewnętrznej firmy. Zadbaj o to, aby Twój FatJar nie zawierał niepotrzebnych implementacji loggerów.
Skorzystaj z poniższych zależności (Tools oraz Payments są dołączone do zadania):

## Komentarz do rozwiązania

Komendy do użycia:

Budowanie projektu:

    gradle build
    
Uruchomienie programu z przykładowymi argumentami:

    java -jar build/libs/zad_1-1.0-SNAPSHOT.jar -price 30 -age 45 -clientId 231 -companyId 447
