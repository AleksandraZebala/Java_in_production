package com.virtuslab.rx_intro.task;


import io.reactivex.Observable;
import io.reactivex.Single;

import java.math.BigDecimal;
import java.util.function.Supplier;

import io.reactivex.ObservableSource;
import javafx.util.Pair;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * ConfirmedTransactionSummarizer is responsible for calculation of total confirmed transactions value.
 * HINT:
 * - Use zip operator to match transactions with confirmations. They will appear in order
 * - Filter only confirmed
 * - Aggregate value of confirmed transactions using reduce operator
 *
 * HINT2:
 * - add error handling which will wrap an error into SummarizationException
 *
 */
class ConfirmedTransactionSummarizer {

    private final Supplier<Observable<Transaction>> transactions;
    private final Supplier<Observable<Confirmation>> confirmations;

    ConfirmedTransactionSummarizer(Supplier<Observable<Transaction>> transactions,
                                   Supplier<Observable<Confirmation>> confirmations) {
        this.transactions = transactions;
        this.confirmations = confirmations;
    }

    Single<BigDecimal> summarizeConfirmedTransactions() {
        return Observable
                .zip(transactions.get(), confirmations.get(), Pair::new)
                .onErrorResumeNext(Observable.error(new SummarizationException("Booom")))
                .filter(confirmation -> confirmation.getValue().isConfirmed)
                .map(confirmation -> confirmation.getKey().value)
                .reduce(BigDecimal::add)
                .toSingle();
    }

    static class SummarizationException extends RuntimeException {

        public SummarizationException(String message) {
            super(message);
        }
    }
}
