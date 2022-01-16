
package tickerdata.model;

public class Employee extends Person {
	private final String role;

	public Employee(String name, int age, String role) {
		super(name, age);
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {

		if (!super.equals(obj))

			return false;

		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Employee otherEmployee = (Employee) obj;

		boolean roleEquals = (this.role == null && otherEmployee.role == null)
				|| (this.role != null && this.role.equals(otherEmployee.role));

		return roleEquals;
	}

}
