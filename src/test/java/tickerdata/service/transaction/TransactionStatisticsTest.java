package tickerdata.service.transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionStatisticsTest {

    private TransactionStatistics transactionStatistics;

    @BeforeEach
    public void init(){
        transactionStatistics = new TransactionStatistics();
    }

    @Test
    public void returnMostPopTickerWhenListContainsZeroElement() {

        List<Transaction> transactions = new ArrayList<>();

        final String expectedPopularTicker = transactionStatistics.mostPopularTicker(transactions);

        assertEquals(0, expectedPopularTicker.length());

    }

    @Test
    public void returnMostPopTickerWhenListContainsOneElement() {

        Transaction tr5 = Transaction.transaction("b", 10, 1.0);

        List<Transaction> transactions = new ArrayList<>();

        Collections.addAll(transactions, tr5);

        final String actualPopularTicker = "b";

        final String expectedPopularTicker = transactionStatistics.mostPopularTicker(transactions);

        assertEquals(actualPopularTicker, expectedPopularTicker);

    }

    @Test
    public void returnMostPopTickerWhenListNotEmpty() {

        Transaction tr1 = Transaction.transaction("a", -10, 2.0);
        Transaction tr2 = Transaction.transaction("a", 20, 1.0);
        Transaction tr3 = Transaction.transaction("b", 5, 1.0);
        Transaction tr4 = Transaction.transaction("b", 10, 1.0);
        Transaction tr5 = Transaction.transaction("b", 10, 1.0);

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            Collections.addAll(transactions, tr1, tr2, tr3, tr4, tr5);
        }

        final String actualPopularTicker = "a";

        long startTime = System.currentTimeMillis();
        final String expectedPopularTicker = transactionStatistics.mostPopularTicker(transactions);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time Sequential: " + (endTime - startTime) + "ms");

        assertEquals(actualPopularTicker, expectedPopularTicker);

    }

    @Test
    public void returnMostPopTickerWithParallelExecution() {
        Transaction tr1 = Transaction.transaction("a", -10, 2.0);
        Transaction tr2 = Transaction.transaction("a", 20, 1.0);
        Transaction tr3 = Transaction.transaction("b", 5, 1.0);
        Transaction tr4 = Transaction.transaction("b", 10, 1.0);
        Transaction tr5 = Transaction.transaction("b", 10, 1.0);
        Transaction tr6 = Transaction.transaction("b", 10, 1.0);
        Transaction tr7 = Transaction.transaction("b", 20, 1.0);


        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            Collections.addAll(transactions, tr1, tr2, tr3, tr4, tr5, tr6, tr7);
        }

        final String actualPopularTicker = "b";

        long startTime = System.currentTimeMillis();
        final String expectedPopularTicker = transactionStatistics.mostPopularTickerParallel(transactions);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time Parallel : " + (endTime - startTime) + "ms");

        assertEquals(actualPopularTicker, expectedPopularTicker);
    }

}
