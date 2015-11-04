package com.example.tasprojekt_test;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("tasprojekt_test")
public class Tasprojekt_testUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Tasprojekt_testUI.class)
	public static class Servlet extends VaadinServlet {
	}

	public static final String BASE_URI = "http://localhost:8080/TASprojekt_test/rest/";

	private WebTarget target;

	private TextField textField;

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();

		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label(test2() + " -> " + textField.getValue()));

				layout.addComponent(new Label(getPerson(textField.getValue()).toString()));

				layout.addComponent(new Label(getPersonsList().toString()));
			}
		});

		textField = new TextField();
		layout.addComponents(button, textField);

		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		target = client.target(BASE_URI);

	}

	// odbieranie obiektu typu String
	public String test2() {
		return target.path("test").request().accept(MediaType.TEXT_PLAIN).get(String.class);
	}

	// odbieranie listy obiektów w³asnego typu Person
	public List<Person> getPersonsList() {
		return target.path("test/persons").request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Person>>() {
				});
	}

	// odbieranie obiektu w³asnego typu Person i przesy³anie parametru
	public Person getPerson(String name) {
		return target.path("test").path("one-person").queryParam("name", name).request()
				.accept(MediaType.APPLICATION_JSON).get(Person.class);
	}

}