
package tickerdata.model;

public class Person {
	private final int age;
	private final String name;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Person otherPerson = (Person) obj;
		
		boolean nameEquals = (this.name == null && otherPerson.name == null)
			      || (this.name != null && this.name.equals(otherPerson.name));

		return this.age == otherPerson.age && nameEquals;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
	    hash = 31 * hash + age;
	    hash = 31 * hash + (name == null ? 0 : name.hashCode());
	   
	    return hash;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + name + " has " + age + " years old";
	}
}
