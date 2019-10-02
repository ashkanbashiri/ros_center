package org.ashkan.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class TaskRunner {

	private static final String ENABLE_FILE = "/home/radlab/webapp/enable.sh";
	private static final String UNTUCK_FILE = "/home/radlab/webapp/untuck.sh";
	private static final String CANON = "/home/radlab/webapp/canon.sh";
	private static final String PUPPET_R = "/home/radlab/webapp/puppet-r.sh";
	private static final String PUPPET_L = "/home/radlab/webapp/puppet-l.sh";
	private static final String MOCAP_LAUNCH = "/home/radlab/webapp/mocaplaunch.sh";
	private static final String FOUR_CORNERS = "/home/radlab/webapp/4corners.sh";
	private static final String TUCK_FILE = "/home/radlab/webapp/tuck.sh";
	private static final String STOP_FILE = "/home/radlab/webapp/stop.sh";
	private static final String ENSLAVE_FILE = "/home/radlab/webapp/enslave.sh";
	private static final String GREETINGS_FILE = "/home/radlab/webapp/greetings.sh";
	private static final String WAVE_FILE = "/home/radlab/webapp/wave.sh";
	private static final String SIT_FILE = "/home/radlab/webapp/sit.sh";
	private static final String SITRELAX_FILE = "/home/radlab/webapp/sitrelax.sh";
	private static final String STAND_FILE = "/home/radlab/webapp/stand.sh";
	private static final String CROUCH_FILE = "/home/radlab/webapp/crouch.sh";
	private static final String UN_ENSLAVE_FILE = "/home/radlab/webapp/unenslave.sh";
	private static final String TAKE_PIC_FILE = "/home/radlab/webapp/takepicture.sh";
	private static final String MOVEMENT_QUALITY = "/home/radlab/webapp/movementquality.sh";

	
	private double q=0.5;
	private double r=0.5;
	private double s=0.5;
	private double p=0.5;

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

	public void enableBaxter()
	{
		run(ENABLE_FILE);

	}
	public void takePicture()
	{
		FacesMessage message = null;
		System.out.println("Running " + TAKE_PIC_FILE);
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Picture Ready!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(TAKE_PIC_FILE);
	}

	public void stopBaxter()
	{
		run(STOP_FILE);
	}
	
	public void canon()
	{
		run(CANON);
	}
	
	public void puppetLeftArm()
	{
		run(PUPPET_L);
	}
	
	public void puppetRightArm()
	{
		run(PUPPET_R);
	}
	
	public void mocapLaunch()
	{
		run(MOCAP_LAUNCH);
	}
	public void movementQuality()
	{
		run(MOVEMENT_QUALITY);
	}
	public void fourCorners()
	{
		run(FOUR_CORNERS);
	}

	public void untuck()
	{
		System.out.println("Running untuck!");
		run(UNTUCK_FILE);
	}

	public void tuck()
	{
		System.out.println("Running tuck!");
		run(TUCK_FILE);
	}
	public void naoEnslave()
	{
		FacesMessage message = null;
		System.out.println("Running " + ENSLAVE_FILE);
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Enslaving the Robot!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(ENSLAVE_FILE);
	}
	public void naoUnEnslave()
	{
		FacesMessage message = null;
		System.out.println("Running " + UN_ENSLAVE_FILE);
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Un-Enslaving the Robot!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(UN_ENSLAVE_FILE);
	}
	public void naoSit()
	{
		System.out.println("Running " + SIT_FILE);
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Sitting down!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(SIT_FILE);
	}
	public void naoSitRelax()
	{
		System.out.println("Running " + SITRELAX_FILE);
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Sitting down and Relax!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(SITRELAX_FILE);
	}
	public void naoStand()
	{
		System.out.println("Running " + STAND_FILE);
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Standing up!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(STAND_FILE);
	}
	public void naoCrouch()
	{
		System.out.println("Running " + CROUCH_FILE);
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Crouching!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(CROUCH_FILE);
	}
	public void naoGreetings()
	{
		System.out.println("Running " + GREETINGS_FILE);
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Greeting the Students!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(GREETINGS_FILE);
	}
	public void naoWave()
	{
		System.out.println("Running " + WAVE_FILE);
		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Executing...", "Waving");
		FacesContext.getCurrentInstance().addMessage(null, message);
		run(WAVE_FILE);
	}
	private void run(String fileName)
	{
		FacesMessage message = null;
		ProcessBuilder probuilder = new ProcessBuilder(fileName);
		probuilder.redirectErrorStream(true);
		Process process = null;
		try {
			process = probuilder.start();
		} catch (IOException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		int shellExitStatus;
		try {
			shellExitStatus = process.waitFor();
		} catch (InterruptedException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "COMPLETE: CODE " + shellExitStatus);
		//FacesContext.getCurrentInstance().addMessage(null, message);		
}
	public void loadTrajectory()
	{
		System.out.println("Loading Trajectory from file...");
	}
	public void saveTrajectory()
	{
		System.out.println("Saving Trajectory to file...");

		
	}
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
		this.r = r;
	}
	
}
