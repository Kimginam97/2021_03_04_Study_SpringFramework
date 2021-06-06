package day01;

public class Student {

	private String Dept;
	private String sNum;
	private String name;
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	public String getsNum() {
		return sNum;
	}
	public void setsNum(String sNum) {
		this.sNum = sNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [Dept=" + Dept + ", sNum=" + sNum + ", name=" + name + "]";
	}
	
	
	
}
