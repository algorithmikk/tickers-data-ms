package interview.question2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import tickerdata.model.Employee;
import tickerdata.model.Person;

public class PersonTest {

	@Test
	public void testEqualsPerson() {
		Person p1 = new Person("jawad", 25);
		Person p2 = new Person("jawad", 25);

		assertEquals(p1, p2);
	}

	@Test
	public void testEmployeeDoesNotEqualsPerson() {

		Person p1 = new Person("jawad", 25);
		Employee e1 = new Employee("jawad", 25, "se");

		assertNotEquals(e1, p1);

		assertNotEquals(p1, e1);
	}

	@Test
	public void testEqualsEmployee() {

		Employee e1 = new Employee("jawad", 25, "se");
		Employee e2 = new Employee("jawad", 25, "se");

		assertEquals(e1, e2);

	}

}
