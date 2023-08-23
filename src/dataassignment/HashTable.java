package dataassignment;

public class HashTable {
	public static int counter = 0;
	public static int counter2 = 0;
		public static int type = 1; // you can change your hash table DH or LP (DH = 2, LP = 1).
		public static int typeOfHash = 2; // PAF = 2, SSF = 1.
		public static long collisionCount = 0;
	    private int TABLE_SIZE = 128;
		private int count = 0;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		private int primeNum = 11;
	    HashEntry[] table;

	    public HashTable() {

	          table = new HashEntry[TABLE_SIZE];
	          for (int i = 0; i < TABLE_SIZE; i++)
	                table[i] = null;
	         
	    }
	    private void findPrime() {
	    	int num = 0;
	        boolean flag = false;
	         for(int i = TABLE_SIZE - 1; i > 0; i--)
	         {
	        	 num = i;
	        	 for(int j = 2; j <= num/2; j++)
		         {
		             if(num % j == 0)
		             {
		                 flag = true;
		                 break;
		             }
		         }
	        	 if(!flag)
	        		 break;
	        	 else 
	        		 flag = false;
	         }
		}
	    private int hashPAF(String key) {
			int hash = 0;
			int order = 0;
			
			char[] ch  = key.toCharArray();
			int i = 1;
			for(char c : ch){
				
			    int temp = (int)c;
			    int temp_integer = 96; //for lower case
			    if(temp<=122 & temp>=97)
			        order = temp-temp_integer;
			    
			    hash += order * Math.pow(33, ch.length - i);
			    i++;
			    if(type == 2 ||  type == 1)
			    {
			    	hash = hash % TABLE_SIZE;
			    }
			}
			
			
			return hash;
			
		}
		private int hashSSF(String key) {
			int result = 0;
			int order = 0;
			
			char[] ch  = key.toCharArray();
			for(char c : ch){
			    int temp = (int)c;
			    int temp_integer = 96; //for lower case
			    if(temp<=122 & temp>=97)
			        order = (temp-temp_integer);
			    
			    result += order;
			}
			return result;
		}
	    private int hashFunction(String key) {   // you can choice paf or ssf
	    	
	    	if(typeOfHash == 1)
	    		return  hashSSF(key) % TABLE_SIZE;
	    	else
	    		return  hashPAF(key) % TABLE_SIZE;
	    	// Create a hash function that performs mod by table size
	    }

	    public void get(String key) {
	    	
	    	int index = 0;
	    	 if( type == 1)
	    		 index = getHashLP(key);
		     else if( type == 2)
		    	 index = getHashDH(key);
	    	
	    	if(index == -1) {
	    		counter2++;
	    		System.out.println("NOT FOUND.");
	    	}
	    	else {
	    		counter++;
	    		table[index].disPlay();
	    	}
	    	
	    }
	    public void put(String key, Address value) {
	    	
	    	
	    	int hash = hashFunction(key);
	        HashEntry hashEntry = new HashEntry(key, value);
	        
	        if( type == 1)
	        	putLP(key, hash, hashEntry, value);
	        else if( type == 2)
	        	putDH(key, hash, hashEntry, value);
	        
	         
	         if(loadFactor())
	        	resize();

	      
	    }
	    private boolean loadFactor() {
			
	    	if(((double)count / (double)TABLE_SIZE) >= (0.5))
	    		return true;
	    	else 
	    		return false;
		}
	    private int findPrimeSize()
	   {
		   int num = TABLE_SIZE;

	         boolean flag = false;
	         while(flag = false)
	         {
	        	 for(int j = 2; j <= num/2; j++)
		         {
		             if(num % j == 0)
		             {
		                 flag = true;
		                 break;
		             }
		         }
	        	 if(!flag)
	        		 break;
	        	 else 
	        		 flag = false;
	        	 num++;
	         }
	         return num;
	   }
	    private void resize()
	   {
		   TABLE_SIZE = TABLE_SIZE*2;
	        TABLE_SIZE = findPrimeSize();
	        if( type == 1)
	        	resizeLP();
	        else if( type == 2)
	        		resizeDH();
	   }  
	    public void remove(String key) {
	    	
	        if( type == 1)
	        	removeLP(key);
	        else if( type == 2)
	        	removeDH(key);
	    	
	    }
	    public void Size() {
	    	 
	    	System.out.println(count);
	    }
	    
	    
	  
	   
 
	    // lýnear proping.
	    
 	    private void putLP(String key, int hash, HashEntry hashEntry, Address value) {
			
 	    	if(table[hash] == null) {
	        	 
	        	 table[hash] = hashEntry;
	        	 count++;
	         }
	         else {
	        	 if(getHashLP(key) != -1){ // if same words is aldready added then only adds new address.
	           		 if(table[getHashLP(key)].getKey().equalsIgnoreCase(key))
	    	        			table[getHashLP(key)].addValues(value);
	    			}

	        	 else if(getHashLP(key) == -1){ 
	           		 
	           		 while(table[hash] != null) {
	           			 
	           			 	collisionCount++;
	           			 	hash += 1;
	           			 	if(hash > TABLE_SIZE - 1) {
	           			 		hash -= TABLE_SIZE -1;
	           			 	}
	           			 	if(table[hash] == null)
	           			 	{
	           			 		table[hash] = hashEntry;
	           			 		count++;
	           			 		break;
	           			 	}
	       				 
	           		 	}
	             }
	          }
		}
 	    private void resizeLP() {

	        HashEntry[] newtable = new  HashEntry[TABLE_SIZE];
	        
	        for (int i = 0; i < TABLE_SIZE; i++)
               newtable[i] = null;
	        for (int i = 0; i < table.length; i++) {
	        	 
	        	if(table[i] != null && !(table[i].equals("Default"))) {
	        		
	        		
	        		int hash = hashFunction(table[i].getKey());
	        		
	        		if(newtable[hash] == null) {
	        			newtable[hash] = table[i];
	        		}
	        		else {
	        			
	        		while(newtable[hash] != null) {
	          				
	        				hash += 1;
	          				 if(hash > TABLE_SIZE - 1) {
	          					 hash -= TABLE_SIZE -1;
	          				 }
	          				 if(newtable[hash] == null)
	          				 {
	          					 newtable[hash] = table[i];
	          					 break;
	          				 }
	        		}
      			 }		 	
	        	}
	           
	        }
	        table = newtable;
	     } // end resize()
 	   
	    public void removeLP(String key) {
	    	
	    	
	    	if(getHashLP(key) == -1) {
	    		System.out.println("NOT FOUND.");
	    	}
	    	else
	    		{	if(table[getHashLP(key)  + 1] == null)
	    				table[getHashLP(key)] = null;
	    			else {
	    				int i = 1;
	    				int temp = getHashLP(key);
	    				int temp2 = temp;
	    				HashEntry hashEntry;
	    				while(table[temp] != null) {
	    					
	    					if(table[temp + i] == null)
	    					{
	    						hashEntry = table[temp];
    							table[temp] = null;
    							table[getHashLP(key)] = hashEntry;
    							break;
	    					}
	    					else if(getHashLP(key) != hashFunction(table[temp + i].getKey()))
	    					{
	    						hashEntry = table[temp];
	    						if(temp == hashFunction(table[temp + i].getKey()))
	    							removeLP(table[temp].getKey());
	    						else
	    							table[temp] = null;
	    						table[temp2] = hashEntry;
	    						break;
	    					}
	    					table[temp] = table[temp + i];
	    					temp = temp + i;
	    					i++;
	    				}
	    				table[temp + i - 1] = null;
	    			}
	    		System.out.println( "> " + key + " - Deleted.");
	    		}
	    		
	    	
	    }
	    private int getHashLP(String key) {
	    	
	    	int hash = hashFunction(key);// Calculate hash value

	    	if (table[hash] == null )
	    		return -1;
	    	else
	    	{
        	  
	    		while(table[hash] != null) {
	    			if(table[hash] != null)
	    			{
	    				if(table[hash].getKey().equalsIgnoreCase(key))
    					return hash;
	    			}
	    			else if(table[hash] == null) {
	    				return -1;
	    			}
	    			hash += 1;
	    			if(hash > TABLE_SIZE - 1) {
	    				hash -= TABLE_SIZE -1;
	    			}
	    		}
			
	    	}
	    	return -1;
		
	    }
 	   
 	   
 	   // double hasýng.
	    private int hashDhFunction(String key, int trial) {
	    	
	    	int temp = 0;
	    	if(typeOfHash == 1)
	    		temp = (primeNum - (hashSSF(key) % primeNum));
	    	else if(typeOfHash == 2)
	    		temp = primeNum - (hashPAF(key) % primeNum);
	    	return ((hashFunction(key) + trial * temp) % TABLE_SIZE);
	    }
	    private int getHashDH(String key) {
    		int hash = hashFunction(key);// Calculate hash value
    		int hashDh = hash;
    	
          if (table[hash] == null)
        	  return -1;
          else if( table[hash] != null )
          {	
        	  
        	   if(table[hash].getKey().equals(key))
    		 		return hash;
        	   
      	 		int temp = hash;
      	 		int index = 0;
	       		while(table[temp] != null) {
	       		index++;
				temp = hashDhFunction(key, index);
				
      		 	if(table[temp] == null)
      		 		return -1;
      		 	else if(table[temp].getKey().equals(key))
      		 	{	
      		 		return temp;
      		 	}
      		 	
      	 	}
      	  }
		return hashDh; 
         
    }

 	   	private void putDH(String key, int hash, HashEntry hashEntry, Address value) {
	        
 	   		if(table[hash] == null) {
	        	 
	        	 table[hash] = hashEntry;
	        	 count++;
	         }
	         else {
	        	 	
	        	 if(table[hash].getKey().equals(key))
     		 		{
     		 		 table[hash].addValues(value);
     		 		}
	        	 else {
		        	 	int temp = hash;
		        	 	int index = 0;
		  	       		while(table[temp] != null) {
		  	       			
		  	       		 collisionCount++;
		        		 	index++;
		        		 	temp = hashDhFunction(key, index);
		        		 	if(table[temp] == null)
		        		 	{		
		  	       				 table[temp] = hashEntry;
		  	       				 count++;
		  	       				 break;
		        		 	}
		        		 	else if(table[temp].getKey().equals(key))
		        		 	{
		        		 		table[temp].addValues(value);
		        		 		break;
		        		 	}
		  	       		}
	        	 	}
	        }
 	    }

	    private void resizeDH() {
	    		
	    	findPrime(); //Finds the largest prime number smaller than Table size,
	    	HashEntry[] newtable = new  HashEntry[TABLE_SIZE];
		        
		        for (int i = 0; i < TABLE_SIZE; i++)
	               newtable[i] = null;
		        for (int i = 0; i < table.length; i++) {
		       
		        	if(table[i] != null && !(table[i].equals("Default"))) {
		        		
		        		int hash = hashFunction(table[i].getKey());
		        		
		        		if(newtable[hash] == null) {
		        			newtable[hash] = table[i];
		        		}
		        		else {
		        			int temp = hash;
		        			int index = 1;
		        			while(newtable[temp] != null) {
		        				
		        				temp = hashDhFunction(table[i].getKey(), index);
		        				index++;
		        				if(newtable[temp] == null)
		        				{
		        					newtable[temp] = table[i];
		        					break;
		        				}
		        			}
		        		}
		        	}
		        }
		        table = newtable;
	    }
	    private void removeDH(String key){
	    	
	    	
	    	HashEntry temp = new HashEntry("Default");
	    	if(getHashDH(key) == -1)
	    	{
	    		System.out.println("NOT FOUND.");
	    	}else {
				
	    		table[getHashDH(key)] = temp;
	    		count--;
	    		System.out.println( "> " + key + " - Deleted.");
			}
	    	
	    	
	    	
	    }
} 