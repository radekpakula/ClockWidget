package pl.clock.client;

import java.util.Date;

public class ClockState extends com.vaadin.shared.AbstractComponentState {

	private static final long serialVersionUID = 1L;

	public String text = "This is Clock";

	public int interval=1000;

	public long time=0;
	
	public long startTime = new Date().getTime();

	public boolean work=true;

	public String clockMode="FORWARD";
	
	public String format = "yyyy-MM-dd h:mm:ss";
}