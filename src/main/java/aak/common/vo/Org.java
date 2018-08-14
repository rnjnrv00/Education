package aak.common.vo;

public class Org {
	private String id;
	private String name;
	private String branch;
	private Address adrs;
	public String getId() {
		return id;
	}
	
	public Org() {
		super();
	}
	
	public Org(String jsonString) {
		super();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Address getAdrs() {
		return adrs;
	}
	public void setAdrs(Address adrs) {
		this.adrs = adrs;
	}
	
	
}
