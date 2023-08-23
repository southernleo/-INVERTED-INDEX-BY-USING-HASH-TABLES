package dataassignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Test {
	
	
	public static void removeStpWrd(String[] splitted,ArrayList<String> wordsOfStp) {
		
			for(int i = 0; i < splitted.length; i++) {
						
				for(int j = 0; j < wordsOfStp.size(); j++)
				{
					String aString = splitted[i];
						if(aString.equals(wordsOfStp.get(j)))	
						{
							splitted[i] = null;
							break;
						}
				}
			}
		
		
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		// remove function 
		String DELIMITERS = "[-+=" + " " + // space
				"\r\n " + // carriage return line fit
				"1234567890" + // numbers
				"’'\"" + // apostrophe
				"(){}<>\\[\\]" + // brackets
				":" + // colon
				"," + // comma
				"‒–—―" + // dashes
				"…" + // ellipsis
				"!" + // exclamation mark
				"." + // full stop/period
				"«»" + // guillemets
				"-‐" + // hyphen
				"?" + // question mark
				"‘’“”" + // quotation marks
				";" + // semicolon
				"/" + // slash/stroke
				"⁄" + // solidus
				"␠" + // space?
				"·" + // interpunct
				"&" + // ampersand
				"@" + // at sign
				"*" + // asterisk
				"\\" + // backslash
				"•" + // bullet
				"^" + // caret
				"¤¢$€£¥₩₪" + // currency
				"†‡" + // dagger
				"°" + // degree
				"¡" + // inverted exclamation point
				"¿" + // inverted question mark
				"¬" + // negation
				"#" + // number sign (hashtag)
				"№" + // numero sign ()
				"%‰‱" + // percent and related signs
				"¶" + // pilcrow
				"′" + // prime
				"§" + // section sign
				"~" + // tilde/swung dash
				"¨" + // umlaut/diaeresis
				"_" + // underscore/understrike
				"|¦" + // vertical/pipe/broken bar
				"⁂" + // asterism
				"☞" + // index/fist
				"∴" + // therefore sign
				"‽" + // interrobang
				"※" + // reference mark
				"]";
		boolean flag = true;
		ArrayList<String> wordsOfStp = new ArrayList<String>();
		
		try {
			File input2 = new File("stop_words_en.txt"); //read stop word!!!
			Scanner scn2 = new Scanner(input2);
			while (scn2.hasNextLine()) {
				 String a = scn2.nextLine();
				 wordsOfStp.add(a.toLowerCase().replaceAll("\\p{C}","").trim());
			}
			scn2.close();

		}
		catch (FileNotFoundException e){
			
			flag = false;
			System.out.println("> words stop file is not found.");
			
		}
		catch (NullPointerException e) {
			
			flag = false;
			System.out.println("> words stop file is NullPointerException");
		}
		HashTable hashTable = new HashTable();
		long startTime = 0;
		try {
			
			File folder = new File("bbc");
			File[] listOfFiles = folder.listFiles();
			System.out.print("Loading.");
			startTime = System.currentTimeMillis();
			for(File i : listOfFiles) {
				
				File txtFiles = new File(i.toString());
				File[] listOfTxtFiles = txtFiles.listFiles();
				for(File j : listOfTxtFiles ){
					
					
					File input1 = new File(j.toString());
					Scanner scn1= new Scanner(input1);
				
					while (scn1.hasNextLine()) {
			
						String line = scn1.nextLine();
						line = line.toLowerCase().replaceAll("\\p{C}","").trim();
						String[] splitted = line.split(DELIMITERS + "");
						removeStpWrd(splitted,wordsOfStp);
						for(int k = 0; k < splitted.length; k++) {

							if(splitted[k] != null && !(splitted[k].equals("")))
							{
								Address adrs = new Address(i.getName(), j.getName());
								hashTable.put(splitted[k], adrs);
							}
							
						}
						
						
					}
					scn1.close();
				}
				
				System.out.print(" .");
				
			}
		}
		catch (NullPointerException e) {
			
			flag = false;
			System.out.println("> file is NullPointerException");
		}
		catch (FileNotFoundException e) {
			
			flag = false;
			System.out.println("> file is not found !");
		}
		
		System.out.println();
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		if(flag == true) {
			System.out.println();
			double average = 0;
			long minSearch = 0;
			double maxSearch = 0;
			try {
				File input = new File("search.txt");
				Scanner scn = new Scanner(input);
				while (scn.hasNextLine()) {
					
					String line = scn.nextLine();
					line = line.toLowerCase().replaceAll("\\p{C}","").trim();
					String[] words = line.split(DELIMITERS + "");
					for (int k = 0; k < words.length; k++) {
						
						System.out.println("> Search : " + words[k]);
						startTime = System.currentTimeMillis();
						hashTable.get(words[k]);
						endTime = System.currentTimeMillis();
						
						if(maxSearch < (double)(endTime - startTime))
							maxSearch = (double)(endTime - startTime);
						else if(minSearch > (double)(endTime - startTime))
							minSearch = (endTime - startTime);
						
						average += (double)(endTime - startTime);
					}
				}
				scn.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("> search word file is not found.");
			}
			catch (NullPointerException e) {
				
				System.out.println("> file is NullPointerException");
			}
			System.out.println(hashTable.getCount());
			System.out.println(hashTable.counter);
			System.out.println(hashTable.counter2);
			System.out.println(" > Collision : " +  hashTable.collisionCount);
			System.out.println("> loading time : " + (double)duration / (double)1000 +"sn");
			System.out.println("> Search average time : " + (average / 1000.0) / 1000.0 + "sn");
			System.out.println("> Max search time :" + maxSearch / 1000.0 +"sn");
			System.out.println("> Min search time : " + minSearch  + "sn");
			
		
			Scanner scn = new Scanner(System.in);
			while(true) {
				System.out.println("------MENU-----");
				System.out.println("1 - Search A Word ");
				System.out.println("2 - Remove A word ");
				System.out.println("0 - Exit ");
					String input = scn.nextLine().toLowerCase().replaceAll("\\p{C}","").trim();
				if(input.equals("1"))
				{	
					System.out.println("  >  Please enter a words.");
					hashTable.get(scn.nextLine().toLowerCase().replaceAll("\\p{C}","").trim());
				}
				else if(input.equals("2"))
				{
					System.out.println("  >  Please enter a words.");
					hashTable.remove(scn.nextLine().toLowerCase().replaceAll("\\p{C}","").trim());
				}
				else if(input.equals("3"))
				{
					System.out.println("by by  :)");
					break;
				}
				else {
					
					System.out.println(" > Please enter true select");
				}
				System.out.println();
				System.out.println();
				System.out.println();
			}
		}
	}

}
