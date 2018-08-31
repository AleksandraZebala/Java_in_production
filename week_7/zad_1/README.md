# Broker transakcji

## Komentarz do rozwiązania

Przykładowa komenda:

        -itemsFile ~/items.csv -customerIds 1:1 -dateRange "2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.999-0100" -itemsCount 1:2 -itemsQuantity 1:2 -outDir ./output -eventsCount 1 -format xml -broker tcp://localhost:61616 -queue transactions-queue -topic transaction-topics


