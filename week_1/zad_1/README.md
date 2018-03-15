# Fat jar

Stworzyć projekt budowany Gradlem. Doodać zależność od biblioteki apache.lang.commons (lub dowolnej tego typu) w buildzie gradle’owym; użyć czegoś z tej biblioteki, stworzyć artefakt będący “fat-jarem” i zainstalować w lokalnym repo.

## Komentarz do rozwiązania

Komendy do użycia:

- Budowanie fat-jara:
    
    gradle makeFatJar
    
- Dodawanie do lokalnego repozytorium:

    gradle publishMavenJavaPublicationToMavenLocal
