
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
	public static String mostPopularTicker(List<Transaction> transactions) {

		if (transactions.isEmpty()) {
			return EMPTY_STRING;
		}

		Map<String, Double> tradedValueByTickerMap = transactions.stream().collect(Collectors.groupingBy(
				Transaction::getTicker, Collectors.summingDouble(e -> e.getPrice() * Math.abs(e.getQuantity()))));

		return getMostPopularTickerFromMapSequential(tradedValueByTickerMap);
	}

	public static <K, V extends Comparable<V>> String getMostPopularTickerFromMapSequential(Map<K, V> map) {

		Optional<Entry<K, V>> maxEntry = map.entrySet().stream()
				.max((Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue().compareTo(e2.getValue()));

		return maxEntry.get().getKey().toString();
	}

	public static String mostPopularTickerParallel(List<Transaction> transactions) {

		if (transactions.isEmpty()) {
			return EMPTY_STRING;
		}

		Map<String, Double> concurrentHashMap = new ConcurrentHashMap<>();

		concurrentHashMap = transactions.parallelStream().collect(Collectors.groupingBy(Transaction::getTicker,
				Collectors.summingDouble(e -> e.getPrice() * Math.abs(e.getQuantity()))));

		return getMostPopularTickerFromMapParallel(concurrentHashMap);
	}

	public static <K, V extends Comparable<V>> String getMostPopularTickerFromMapParallel(Map<K, V> concurrentMap) {

		Optional<Entry<K, V>> maxEntry = concurrentMap.entrySet().parallelStream()
				.max((Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue().compareTo(e2.getValue()));

		return maxEntry.get().getKey().toString();
	}

	private TransactionStatistics() {
	}
}
