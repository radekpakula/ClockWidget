package pl.clock.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

import pl.clock.Clock;
import pl.clock.client.ClockClientRpc;
import pl.clock.client.ClockServerRpc;
import pl.clock.client.ClockState;
import pl.clock.client.ClockWidget;

import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;

@Connect(Clock.class)
public class ClockConnector extends AbstractComponentConnector {

	private static final long serialVersionUID = 1L;
	ClockServerRpc rpc = RpcProxy.create(ClockServerRpc.class, this);
	
	public ClockConnector() {
		registerRpc(ClockClientRpc.class, new ClockClientRpc(){
			private static final long serialVersionUID = 1L;});
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(ClockWidget.class);
	}

	@Override
	public ClockWidget getWidget() {
		return (ClockWidget) super.getWidget();
	}

	@Override
	public ClockState getState() {
		return (ClockState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		getWidget().setFormat(getState().format);
		getWidget().setMode(getState().clockMode);
		getWidget().setWork(getState().work);
		getWidget().setDelayFromStart(new Date().getTime()-getState().startTime);
		getWidget().setInterval(getState().interval);
		getWidget().setTime(getState().time);
	}

}

