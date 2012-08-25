package com.kurui.kums.ecis.test.employee;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;

public class TestMongoUseSpring extends TestCase {

	public static void testEmployee() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"com/kurui/kums/ecis/test/employee/mongodb-config.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		Employee employee = new Employee("100", "firstName", "lastName", 23);
		// save
		mongoOperation.save("employees", employee);
		// find
		Employee savedEmployee = mongoOperation.findOne("employees", new Query(
				Criteria.where("id").is("100")), Employee.class);
		System.out.println("Saved Employee: " + savedEmployee);
		// update
		mongoOperation.updateFirst("employees",
				new Query(Criteria.where("firstname").is("firstName")),
				Update.update("lastname", "new lastName"));
		// find
		Employee updatedEmployee = mongoOperation.findOne("employees",
				new Query(Criteria.where("id").is("100")), Employee.class);
		System.out.println("Updated Employee: " + updatedEmployee);

		// delete
		// mongoOperation.remove("employees",
		// new Query(Criteria.where("id").is("100")), Employee.class);

		// List
		List<Employee> listEmployee = mongoOperation.getCollection("employees",
				Employee.class);
		System.out.println("size of employees = " + listEmployee.size());

	}
}
