package dataassignment;

import java.util.ArrayList;

public class HashEntry {
   
	private String key;
	private int count = 0;
    private Address value;
    ArrayList<Address> values = new ArrayList<Address>();
    
    HashEntry(String key, Address value) {
          this.key = key;
          values.add(value);
          count++;
    }     
    HashEntry(String key) {
        this.key = key;
        count = 0;
  }     
    
    
    public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public Address getValue() {
		return value;
	}



	public void setValue(Address value) {
		this.value = value;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}
	
	public void disPlay() {
		
		System.out.println(count + "documents found.");
		for(Address i : values) {
			
			
			i.disPly();
		}
	}
	public void addValues(Address value) {
		
		boolean flag = true;
		for(Address i : values) {
			
			if((i.getFolder()).equals(value.getFolder()) && (i.getTxt()).equals(value.getTxt())) 
				{
					flag = false;
					i.setCount(i.getCount() + 1);
				}		
		}
		if(flag)
			{
				count++;
				values.add(value);
			}
		
	}
}
