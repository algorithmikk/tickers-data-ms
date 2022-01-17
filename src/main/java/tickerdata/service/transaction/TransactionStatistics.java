
package tickerdata.service.transaction;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * sequential and a parallel algorithm implementation.
 */
public final class TransactionStatistics {

    private static final String EMPTY_STRING = "";

    public TransactionStatistics() {
    }

    /**
     * Return the most popular ticker in terms of transaction total absolute value
     * (i.e. abs(price * quantity)). For example, let's say we have these
     * transactions:
     * <ul>
     * <li>transaction("a", -10, 2.0)</li>
     * <li>transaction("a", 20, 1.0)</li>
     * <li>transaction("b", 5, 1.0)</li>
     * <li>transaction("b", 10, 1.0)</li>
     * <li>transaction("b", 10, 1.0)</li>
     * </ul>
     * The most popular one ticker is "a" with a traded value of 40 compared to b
     * that only has 25.
     *
     * @param transactions List containing all transactions we want to look at
     * @return the most popular ticker
     */
    public String mostPopularTicker(List<Transaction> transactions) {

        if (transactions.isEmpty()) {
            return EMPTY_STRING;
        }


        return getMostPopularTickerFromMapSequential(transactions);
    }

    private String getMostPopularTickerFromMapSequential(List<Transaction> transactions) {

        Map<String, Double> tradedValueByTickerMap = transactions.stream().collect(Collectors.groupingBy(
                Transaction::getTicker, Collectors.summingDouble(e -> e.getPrice() * Math.abs(e.getQuantity()))));

        Optional<Entry<String, Double>> maxEntry = tradedValueByTickerMap.entrySet().stream()
                .max(Entry.comparingByValue());

        return maxEntry.map(kvEntry -> kvEntry.getKey().toString()).orElse(null);
    }

    public String mostPopularTickerParallel(List<Transaction> transactions) {

        if (transactions.isEmpty()) {
            return EMPTY_STRING;
        }

        return getMostPopularTickerFromMapParallel(transactions);
    }

    private String getMostPopularTickerFromMapParallel(List<Transaction> transactions) {

        Map<String, Double> concurrentHashMap = transactions.parallelStream().collect(Collectors.groupingBy(Transaction::getTicker,
                Collectors.summingDouble(e -> e.getPrice() * Math.abs(e.getQuantity()))));

        Optional<Entry<String, Double>> maxEntry = concurrentHashMap.entrySet().parallelStream()
                .max(Entry.comparingByValue());

        return maxEntry.map(kvEntry -> kvEntry.getKey().toString()).orElse(null);
    }

}
