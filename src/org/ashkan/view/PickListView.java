package org.ashkan.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

public class PickListView {

	private DualListModel<String> motions;
	private List<String> finalMotions;
	private Map<String, StyledMotion> motionMap;
	private static final double DEFAULT_Q = 0.5;
	private static final double DEFAULT_R = 0.5;
	private static final double DEFAULT_P = 0.5;
	private static final double DEFAULT_S = 0.5;
	private static final MatlabProxyFactoryOptions OPTIONS = new MatlabProxyFactoryOptions.Builder().setMatlabLocation("/usr/local/MATLAB/R2014b/bin/matlab").setUsePreviouslyControlledSession(true).setMatlabStartingDirectory(new File("/home/radlab/ros_ws/Lin--Creating Trajectory/")).build();
	private double q = 0.1;
	private double r = 0.1;
	private double s = 1;
	private double p = 0.1;
	private ArrayList<String> motionsSource;
	//	private List<String> motionsSource;
	//	
	//	public List<String> getMotionsSource() {
	//		return motionsSource;
	//	}
	//
	//	public void setMotionsSource(List<String> motionsSource) {
	//		this.motionsSource = motionsSource;
	//	}
	//
	//	public List<String> getMotionsTarget() {
	//		return motionsTarget;
	//	}
	//
	//	public void setMotionsTarget(List<String> motionsTarget) {
	//		this.motionsTarget = motionsTarget;
	//	}
	//
	//	private List<String> motionsTarget;
	private ArrayList<String> motionsTarget;

	public double getQ() {
		return q;
	}

	public void setQ(double q) {
		this.q = q;

	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		if(r>0.5)
			weight = "Heavy";
		else weight = "Light";
		this.r = r;
	}

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	private String weight="value!";
	private String space;
	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	private String time;
	private String flow;

	public void updateHelp()
	{
		if(q>0.5)
			space = "Direct";
		else space = "Flexible";
		if(r>0.5)
			weight = "Heavy";
		else weight = "Light";
		if(p>0.5)
			time = "Sustained";
		else time = "Sudden";
		if(s>0.5)
			flow = "Bound";
		else flow = "Free";
	}

	@PostConstruct
	public void init()
	{
		updateHelp();
		motionsSource = new ArrayList<String>();
		motionsTarget = new ArrayList<String>();
		finalMotions = new ArrayList<String>();
		motionMap = new HashMap<String, StyledMotion>();

		StyledMotion motion1 = new StyledMotion(1, 2, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		StyledMotion motion2 = new StyledMotion(1, 3, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		StyledMotion motion3 = new StyledMotion(2, 1, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		StyledMotion motion4 = new StyledMotion(2, 4, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		StyledMotion motion5 = new StyledMotion(3, 1, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		StyledMotion motion6 = new StyledMotion(3, 4, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		StyledMotion motion7 = new StyledMotion(4, 2, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		StyledMotion motion8 = new StyledMotion(4, 3, DEFAULT_Q, DEFAULT_R, DEFAULT_S, DEFAULT_P);
		motionMap.put("Extension-1", motion1);
		motionMap.put("Scatter-1", motion2);
		motionMap.put("Flexion-1", motion3);
		motionMap.put("Scatter-2", motion4);
		motionMap.put("Gather-1", motion5);
		motionMap.put("Extension-2", motion6);
		motionMap.put("Gather-2", motion7);
		motionMap.put("Flexion-2", motion8);
		motionsSource.add("Flexion-1");
		motionsSource.add("Scatter-1");
		motionsSource.add("Extension-1");
		motionsSource.add("Scatter-2");
		motionsSource.add("Gather-1");
		motionsSource.add("Flexion-2");
		motionsSource.add("Gather-2");
		motionsSource.add("Extension-2");

		motions = new DualListModel<String>(motionsSource, motionsTarget);
	}

	public void onTransfer(TransferEvent event) {
		StringBuilder builder = new StringBuilder();
		for(Object item : event.getItems()) {
			builder.append((String)item).append("<br />");
			motions.getSource().add((String) item);
		}
		finalMotions = motions.getTarget();
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("Motion Selected");
		msg.setDetail(builder.toString());    
		FacesContext.getCurrentInstance().addMessage(null, msg);
	} 

	public void onSelect(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
	}

	public void onUnselect(UnselectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
	}

	public void onReorder() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
	} 

	public DualListModel<String> getMotions() {
		return motions;
	}

	public void setMotions(DualListModel<String> motions) {
		this.motions = motions;
	}

	public void submit() 
	{
		//Running the generated trajectories
	}

	public void verify()
	{
		StringBuilder builder = new StringBuilder();

		for(int i=0;i<finalMotions.size();i++)
		{
			StyledMotion styledMotion = motionMap.get(finalMotions.get(i));
			builder.append(finalMotions.get(i)).append(": From Pose#").append(styledMotion.getStartPose()).append(" To Pose#").append(styledMotion.getEndPose()).append("<br/>");
		}
		motions = new DualListModel<String>(motionsSource, motionsTarget);
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("You submitted the following");
		msg.setDetail(builder.toString());    
		FacesContext.getCurrentInstance().addMessage(null, msg);

		//generating the appropriate matlab command to run to get the trajectories
		StringBuilder builderForMatlab = new StringBuilder();
		builderForMatlab.append("TrackingOfSequence([");
		for(int i=0;i<finalMotions.size();i++)
		{
			StyledMotion styledMotion = motionMap.get(finalMotions.get(i));

			if(i==0)
			{
				builderForMatlab.append(styledMotion.getStartPose()).append(",").append(styledMotion.getEndPose()).append(",");
			}
			else if(i!=finalMotions.size()-1)
				builderForMatlab.append(styledMotion.getEndPose()).append(",");
			else
				builderForMatlab.append(styledMotion.getEndPose()).append("],");
		}
		builderForMatlab.append(q).append(",").append(r).append(",").append(s).append(",").append(p).append(")");

		String command = builderForMatlab.toString();
		System.out.println(command);

		//Create a proxy, which we will use to control MATLAB
		//MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setMatlabLocation("/usr/local/MATLAB/R2014b/bin/matlab").setUsePreviouslyControlledSession(true).build();
		MatlabProxyFactory factory = new MatlabProxyFactory(OPTIONS);
		MatlabProxy proxy;
		try {
			proxy = factory.getProxy();
			proxy.eval(command);
			//Disconnect the proxy from MATLAB
			proxy.disconnect();
		} catch (MatlabConnectionException e1) {
			e1.printStackTrace();
		}

		catch (MatlabInvocationException e) {
			e.printStackTrace();
		}
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	public void loadTrajectory()
	{
		System.out.println("Loading Trajectory from file...");
	}
	public void saveTrajectory()
	{
		System.out.println("Saving Trajectory to file...");
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("Saving...");
		msg.setDetail("Trajectory saved to file!");    
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
