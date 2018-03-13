# Fat jar

Stworzyć projekt budowany Gradlem. Doodać zależność od biblioteki apache.lang.commons (lub dowolnej tego typu) w buildzie gradle’owym; użyć czegoś z tej biblioteki, stworzyć artefakt będący “fat-jarem” i zainstalować w lokalnym repo.

Komendy:

1. Budowanie fat-jara:
    
    gradle makeFatJar
    
2. Dodawanie do lokalnego repozytorium:

    gradle publishMavenJavaPublicationToMavenLocal
