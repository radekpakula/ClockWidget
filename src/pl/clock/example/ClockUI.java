package pl.clock.example;

import java.util.Calendar;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import pl.clock.Clock;
import pl.clock.ClockMode;

@SuppressWarnings("serial")
@Theme("clock")
@PreserveOnRefresh
public class ClockUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ClockUI.class, widgetset = "pl.clock.ClockWidgetset")
	public static class Servlet extends VaadinServlet {
	}
	Clock c =new Clock();
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(new Clock());
		layout.addComponent(button);
		layout.addComponent(c);
		Button removeClock = new Button("Remove clock");
		layout.addComponent(removeClock);
		removeClock.addClickListener(e->{
			if(c!=null){
				layout.removeComponent(c);
				c=null;
			}else{
				c=new Clock();
				c.setClockMode(ClockMode.BACKWARD);
				layout.addComponent(c);
			}
			if(c!=null){
				System.out.println(c.isAttached());
			}
		});
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR,2);
		Clock ccc = new Clock(c.getTime());
		ccc.setClockMode(ClockMode.FORWARD);
		layout.addComponent(ccc);
		layout.addComponent(new Label("PRZERWA   "));
		c = Calendar.getInstance();
		c.add(Calendar.HOUR,2);
		
		ccc = new Clock(c.getTime());
		ccc.setClockMode(ClockMode.BACKWARD);
		ccc.setFormat("hh:mm:ss");
		layout.addComponent(ccc);
		
		ccc = new Clock(c.getTime());
		ccc.setClockMode(ClockMode.BACKWARD);
		ccc.setFormat("yyyy-MM-dd");
		layout.addComponent(ccc);
	}
}