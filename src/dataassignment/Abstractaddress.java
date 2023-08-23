package dataassignment;

public abstract class Abstractaddress {

	private String folder;
	abstract public int getCount();

	abstract public void setCount(int count);

	abstract public String getFolder();

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
