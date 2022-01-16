package interview.question3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import tickerdata.service.statistic.StatisticImplementation;

public class StatisticImplementationTest {

	@Test
	public void testMovingMeanWhenInputDataNotEmpty() {
		int[] input_data = { 1, 3, 5, 6, 8, 12, 18, 21, 22, 25 };
		int per = 3;
		StatisticImplementation obj = new StatisticImplementation(per);

		List<Float> listOfMovingMeans = new ArrayList<>();

		for (int x : input_data) {
			obj.event(x);

			listOfMovingMeans.add(obj.mean());

		}

		Queue<Integer> dataSetExpected = new ArrayDeque<>();

		dataSetExpected.add(21);
		dataSetExpected.add(22);
		dataSetExpected.add(25);

		Queue<Integer> dataSetActual = obj.getDataSet();

		assertEquals(dataSetExpected.element(), dataSetActual.element());
		assertThat(dataSetActual.contains(25));

		System.out.println("List of moving means for given input: " + Arrays.toString(input_data));
		listOfMovingMeans.forEach(e -> System.out.print(e + " "));

	}

	@Test
	public void testMinWhenInputDataNotEmpty() {
		int[] input_data = { 4, 3, 5, 6, 8, 5, 18, 33, 22, 15 };
		int per = 4;
		StatisticImplementation obj = new StatisticImplementation(per);

		List<Integer> listOfMin = new ArrayList<>();

		for (int x : input_data) {
			obj.event(x);

			listOfMin.add(obj.minimum());

		}

		Queue<Integer> dataSetExpected = new ArrayDeque<>();

		dataSetExpected.add(18);
		dataSetExpected.add(33);
		dataSetExpected.add(22);
		dataSetExpected.add(15);

		Queue<Integer> dataSetActual = obj.getDataSet();

		assertEquals(dataSetExpected.element(), dataSetActual.element());
		assertThat(dataSetActual.contains(15));

		System.out.println("List of min for given input: " + Arrays.toString(input_data));
		listOfMin.forEach(e -> System.out.print(e + " "));
		System.out.println();
	}

	@Test
	public void testMaxWhenInputDataNotEmpty() {
		int[] input_data = { 4, 3, 5, 6, 8, 5, 18, 33, 22, 15 };
		int per = 2;
		StatisticImplementation obj = new StatisticImplementation(per);

		List<Integer> listOfMax = new ArrayList<>();

		for (int x : input_data) {
			obj.event(x);

			listOfMax.add(obj.maximum());

		}

		Queue<Integer> dataSetExpected = new ArrayDeque<>();

		dataSetExpected.add(22);
		dataSetExpected.add(15);

		Queue<Integer> dataSetActual = obj.getDataSet();

		assertEquals(dataSetExpected.element(), dataSetActual.element());
		assertThat(dataSetActual.contains(15));

		System.out.println("List of max for given input" + Arrays.toString(input_data));
		listOfMax.forEach(e -> System.out.print(e + " "));
		System.out.println();
	}

}
