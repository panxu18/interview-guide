package com.xup.interviewguide.ch3;

import java.util.List;

public class HappyParty {
	
	class Employee {
		public int happy;
		List<Employee> subordinates;
	}
	
	public int getMaxHappy(Employee e) {
		if (e == null)
			return 0;
		if (e.subordinates == null)
			return e.happy;
		return Math.max(e.happy, e.subordinates.stream()
				.mapToInt(subList->getMaxHappy(subList))
				.sum());
	}
}
