package com.odetour.member;

public class MemberVO {
	
	int idx;
	String name;
	String pwd;
	String email;
	String birth_year;
	String birth_month;
	String birth_day;
	int point;
	String tel;
	
	
	public MemberVO() {}
	public MemberVO(String name, String pwd, String email, String birth_year, String birth_month,
			String birth_day, int point, String tel) {
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.birth_year = birth_year;
		this.birth_month = birth_month;
		this.birth_day = birth_day;
		this.point = point;
		this.tel = tel;
	}


	public int getIdx() { return idx; }
	public void setIdx(int idx) { this.idx = idx; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getPwd() {return pwd;	}
	public void setPwd(String pwd) { this.pwd = pwd; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getBirth_year() { return birth_year; }
	public void setBirth_year(String birth_year) { this.birth_year = birth_year; }
	
	public String getBirth_month() { return birth_month; }
	public void setBirth_month(String birth_month) { this.birth_month = birth_month; }
	
	public String getBirth_day() { return birth_day; }
	public void setBirth_day(String birth_day) { this.birth_day = birth_day; }
	
	public int getPoint() { return point; }
	public void setPoint(int point) { this.point = point; }
	
	public String getTel() { return tel; }
	public void setTel(String tel) { this.tel = tel; }
	
	
	@Override
	public String toString() {
		return "\n========= 회원정보 ========="
				+ "\nidx : "+ idx
				+ "\nname : "+ name
				+ "\npwd : "+ pwd
				+ "\nemail : "+ email
				+ "\nbirth_year : "+ birth_year
				+ "\nbirth_month : "+ birth_month
				+ "\nbirth_day : "+ birth_day
				+ "\npoint : "+ point
				+ "\ntel : "+ tel
				+ "\n=========================";
				
	}
	
	
	
//	public static void main(String[] args) {
//		MemberBean mBean = new MemberBean(1, "kbs", "1234", "kbs@naver.com", "1999", "01", "02", 0, "01012345678");
//		System.out.println(mBean);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
