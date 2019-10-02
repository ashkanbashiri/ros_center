package org.ashkan.view;

public class StyledMotion {
	
	
	public StyledMotion(int startPose, int endPose, double q, double r,
			double s, double p) {
		super();
		this.startPose = startPose;
		this.endPose = endPose;
		this.q = q;
		this.r = r;
		this.s = s;
		this.p = p;
	}
	private int startPose;
	public int getStartPose() {
		return startPose;
	}
	public void setStartPose(int startPose) {
		this.startPose = startPose;
	}
	public int getEndPose() {
		return endPose;
	}
	public void setEndPose(int endPose) {
		this.endPose = endPose;
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
	private int endPose;
	private double q;
	private double r;
	private double s;
	private double p;

}
