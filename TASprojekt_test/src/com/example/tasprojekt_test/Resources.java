package com.example.tasprojekt_test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Resources {
	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "REST test";
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> REST test" + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "REST test" + "</title>" + "<body><h1>" + "REST test" + "</body></h1>"
				+ "</html> ";
	}

	@GET
	@Path("/one-person")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getOnePerson(@QueryParam("name") String name) {
		return new Person(name, 15);
	}

	@GET
	@Path("/persons")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPersonsList() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Jan", 24));
		persons.add(new Person("Ela", 17));
		persons.add(new Person("Ola", 19));

		return persons;
	}
}
