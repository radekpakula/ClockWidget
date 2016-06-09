package pl.clock;

import java.util.Date;

import pl.clock.client.ClockServerRpc;
import pl.clock.client.ClockState;

public class Clock extends com.vaadin.ui.AbstractComponent {

	private static final long serialVersionUID = 1L;
	private ClockServerRpc rpc = new ClockServerRpc() {
		private static final long serialVersionUID = 1L;
	};  

	public Clock() {
		registerRpc(rpc);
		setTime(new Date().getTime());
	}
	public Clock(Date d) {
		registerRpc(rpc);
		setTime(d.getTime());
	}
	@Override
	public ClockState getState() {
		return (ClockState) super.getState();
	}
	
	/**
	 * Set refresh time on client side in ms
	 * @param i Default is 1000 ms
	 */
	public void setInterval(int i){
		getState().interval=i;
	}
	/**
	 * set time for current clock
	 * @param date
	 */
	public void setDate(Date date){
		getState().time=date.getTime();
	}
	/**
	 * Set time for current clock
	 * @param time
	 */
	public void setTime(long time){
		getState().time=time;
	}
	/**
	 * Set work state of this clock
	 * @param work
	 */
	public void setWork(boolean work){
		getState().work=work;
	}
	/**
	 * Check if clock is mark as working
	 * @return
	 */
	public boolean isWork(){
		return getState().work;
	}
	
	public ClockMode getClockMode(){
		return ClockMode.valueOf(getState().clockMode);
	}
	/**
	 * Clock mode. Default is FORWARD. Possible change to BACKWARD
	 * @param clockMode
	 */
	public void setClockMode(ClockMode clockMode){
		getState().clockMode=clockMode.toString();
	}
	/**
	 * Set date format. Default is 'yyyy-MM-dd h:mm:ss'                   => 2007-06-09T17:46:21
	 * 
	 * @param format
	 */
	public void setFormat(String format){
		getState().format=format;
	}
	
}
