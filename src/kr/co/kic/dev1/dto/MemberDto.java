package kr.co.kic.dev1.dto;

public class MemberDto {
	private int seq;
	private String id;
	private String email;
	private String name;
	private String pwd;
	private String phone;
	private String regdate;
	
		
	public MemberDto(int seq, String id, String email, String name, String pwd, String phone, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.phone = phone;
		this.regdate = regdate;
	}

	

	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String email, String pwd) {
		this(0,null,email,null,pwd,null,null);
	}

	public MemberDto(int seq, String id, String email, String name, String phone, String regdate) {
		this(seq,id,email,name,null,phone,regdate);
	}
	
	public MemberDto(String id, String email, String name,String pwd, String phone) {
		this(0,id,email,name,pwd,phone,null);
	}

	public MemberDto(int seq, String id, String email, String name, String phone) {
		this(seq,id,email,name,null,phone,null);
	}

	
	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	
}