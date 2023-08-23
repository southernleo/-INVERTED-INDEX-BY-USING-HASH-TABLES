package dataassignment;

public class Address extends Abstractaddress {
	
	private String folder;
	private String txt;
	private int count = 0;
	public Address(String folder, String txt) {
		super.setFolder(folder);
		this.folder = folder;
		this.txt = txt;
		count++;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getFolder() {
		return folder;
	}

	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	public void disPly() {
		
		System.out.println(count + "----" + txt + " / " + folder);
	}
}
	
