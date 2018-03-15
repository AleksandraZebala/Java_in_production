# Filtrowanie na modłę Mavena w Gradle'u

Stworzyć w Gradle’u filtrowanie działające na takiej samej zasadzie, jak w Mavenie.
Plik db.properties należy umieścić w ścieżce src/main/resources (standardowy layout)
Przełączenie profili może się odbywać przez przekazanie zmiennyc do Gradle'a, np tak: gradle myTask -Pprofile=dev
Docelowo, plik db.properties ze zmiennymi podmienionymi na wartości, ma znaleźć się w zbudowanym pliku jar.

## Komentarz do rozwiązania

Komenda do użycia:

    gradle changeProfile -Pprofile=dev
    
Podmienione wartości znajdą się w build/resources/main/db.properties
