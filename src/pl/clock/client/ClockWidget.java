package pl.clock.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;

public class ClockWidget extends HTML {
	private Element element;
	private static final String CLASSNAME = "clock";
	private int interval;
	private boolean work;
	//Ustawiony czas
	private long time;
	private long delayFromStart;
	//czas po stronie klienta
	private long clientTime;
	private String timeDelay;
	private String mode;
	private String format;
	
	public ClockWidget() {
		setHTML("<div><span class=\"clock-time\"></span></div>");
		prototype();
		element=getElement();
		setStyleName(CLASSNAME);
		readClientLocalTime(this);
		initClock(this);
		
	}
	public native void readClientLocalTime(ClockWidget instance)/*-{	
		instance.@pl.clock.client.ClockWidget::setClientTime(Ljava/lang/String;)(new Date().getTime()+'')
	}-*/;
	public native void setIntervalClock(int interval)/*-{	
		window.intervalclock = interval;
	}-*/;
	public native void prototype()/*-{	
		Date.prototype.format = Date.prototype.format || function(format)
		{
		  var o = {
		    "M+" : this.getMonth()+1, //month
		    "d+" : this.getDate(),    //day
		    "h+" : this.getHours(),   //hour
		    "m+" : this.getMinutes(), //minute
		    "s+" : this.getSeconds(), //second
		    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
		    "S" : this.getMilliseconds() //millisecond
		  }
		  var replacement = [];
		  if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		    '<span class="year">'+((this.getFullYear()+"").substr(4 - RegExp.$1.length))+'</span>');
		  for(var k in o){
		  	if(new RegExp("("+ k +")").test(format)){
		    	var vals =  RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length);
		    	vals="<span>"+vals+"</span>";
		    	replacement.push([RegExp.$1,vals])
		   	}
	      }
	      for (var i = 0; i < replacement.length; i++) {
	      	format = format.replace(replacement[i][0],replacement[i][1]);
	      }
		  return format;
		}
	}-*/;
	public native void initClock(ClockWidget instance)/*-{	
		window.intervalclock = window.intervalclock || 1000;
		window.clocks = window.clocks || [];
		window.clocks.push(instance);
		window.tiktak = window.tiktak || false;
		if(!window.tiktak){
			window.tiktak=true;
			window.onload = startTime(true);
		}else{
			startTime(false);
		}
		function startTime(loopd) {
			for (var i = 0; i < window.clocks.length; i++) {
				instance=window.clocks[i];
				if(instance!=null && instance.@pl.clock.client.ClockWidget::isAttached()){
					var mode=instance.@pl.clock.client.ClockWidget::getMode()();
					var currentMs=new Date().getTime();
					var delay= instance.@pl.clock.client.ClockWidget::getTimeDelay()();
					var delayStart= instance.@pl.clock.client.ClockWidget::getDelayFromStart()();
					delay=parseInt(delay,10)-parseInt(delayStart,10);
					var currentTime =parseInt(currentMs,10)-parseInt(delay,10);
					if(mode=='BACKWARD'){
						var startTime = instance.@pl.clock.client.ClockWidget::getTime()();
						delay = currentTime - parseInt(startTime,10);
						currentTime=currentTime-delay-delay;
					}
				    var today = new Date(currentTime);
				    var el =  instance.@pl.clock.client.ClockWidget::element;
				    if(el!=null){
				    	var formatDate=instance.@pl.clock.client.ClockWidget::getFormat()();
				    	var newVal = today.format(formatDate+'');
				    	if(el.innerHTML!=newVal){
					    	el.innerHTML = newVal;
				    	}
				    };
			    }
		    }
		    if(loopd){
		    	var t = setTimeout(startTimeLoop, window.intervalclock);
	    	}
		    
		}
		function startTimeLoop(){
			startTime(true);
		}
	}-*/;
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
		setIntervalClock(interval);
	}
	public String getTime() {
		return time+"";
	}
	public void setTime(long time) {
		this.time = time;
		readClientLocalTime(this);
		initClock(this);
	}
	public boolean isWork() {
		return work;
	}
	public void setWork(boolean work) {
		this.work = work;
	}
	public String getClientTime() {
		return clientTime+"";
	}
	public void setClientTime(String cc) {
		this.clientTime = Long.parseLong(cc);
		setTimeDelay((clientTime-time));
	}
	public String getTimeDelay() {
		return timeDelay;
	}
	public void setTimeDelay(Long timeDelay) {
		this.timeDelay = timeDelay+"";
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDelayFromStart() {
		return delayFromStart+"";
	}
	public void setDelayFromStart(long delayFromStart) {
		this.delayFromStart = delayFromStart;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}