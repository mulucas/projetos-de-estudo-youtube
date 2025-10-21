package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void clearTransactions() {
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics() {
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
        //OffsetDateTime cutoff = OffsetDateTime.now().minusSeconds(60);

        //transactions.removeIf(tx -> tx.getDataHora().isBefore(cutoff));

        return transactions.stream().mapToDouble(Transaction::getValor).summaryStatistics();
    }
}
