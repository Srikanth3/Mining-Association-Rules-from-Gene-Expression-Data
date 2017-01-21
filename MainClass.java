package Asst2;

import java.io.FileNotFoundException;
import java.util.*;

public class MainClass {
	
	 /*PLEASE NOTE 
	    * USE THE FOLLOWING KEY TO REPRESENT THE FOLLOWING VALUES FOR RUNNING QUERIES
	    * 1. UP
	    * 0.Down
	    * 2 Breast Cancer
	    * 3.Colon Cancer
	    * 4.ALL
	    * 5.AML
	    * (example: G1_1 would point to G1_UP
	    * 			G45_0 would point to G45_Down)
	    * */
	
	static HashMap<Integer,List<List<String>>> lengthMap;
	static HashMap<Integer,Integer> resultCountMap;
	
	@SuppressWarnings("static-access")
	public static void main(String args[]) throws FileNotFoundException{
		
		
		//Initialize Global Variables
		lengthMap = new HashMap<Integer,List<List<String>>>();
		resultCountMap = new HashMap<Integer,Integer>();
		
		List<List<String>> lengthOneItemSet = new ArrayList<List<String>>();
		
		//-----------------|| SET SUPPORT AND CONFIDENCE VALUES ||--------------------------------
		int support=50;
		int confidence=60;
		
		
		//Read the file and fill data matrix
		ReadFile rf = new ReadFile();
		int[][] data = rf.readFile();
		
		//Get Frequent item List Of Length One 
		FrequentItemList fq = new FrequentItemList();
		lengthOneItemSet =fq.genLengthOneFrequentItemSet(data, support);
		
		//Add the Frequent item List Of Length One To Hash Map 
		lengthMap.put(1, lengthOneItemSet);
		
		//Class to generate the next combination of item sets of length N
		GenerateCombination gc = new GenerateCombination();
		
		//Generate Frequent Item Sets Until Count Reaches Zero
		System.out.println("Frequent Item Sets For Support Value:"+support);
		for(int length=2;;length++)
		{
			List<List<String>> lengthNList =gc.generateCombinationSet(lengthMap.get(length-1), lengthOneItemSet);
			lengthNList = fq.getCountForListLengthN(lengthNList, length, support, data);
			lengthMap.put(length,lengthNList);
			resultCountMap.put(length, lengthNList.size());
			
			System.out.println("Frequent Data Set Length: "+length+"=>"+resultCountMap.get(length));
		
			if(lengthNList.size()==0) break;

		}
       
	   // HashMap to store the count of every frequent item set
   	   HashMap<List<String>,Integer> countMap = fq.getItemSetCountMap();
   	   GenerateAssosiationRule ga = new GenerateAssosiationRule();
   	   ga.fillAssociationMap(countMap, confidence);
   	  
   	  // Generate all the possible rules
   	  HashMap<List<String>,List<List<String>>> rules = ga.getAssosiationRules();
   	  
   	  //To print all rules, uncomment the line below
   	 // printAllRules(rules);
  	   
  	   RuleTemplates rt = new RuleTemplates();
  	   
  	   //USE THE FOLLOWING STUBS TO MAKE USE OF THE THREE TEMPLATES
  	  
  	   //List<String> headItemSet = new ArrayList<String>();
  	   //headItemSet.add("G1_1");
  	   //HashMap<List<String>,List<List<String>>> resultRules1 = rt.templateOne(rules,"1",headItemSet,"BODY");
  	   //HashMap<List<String>,List<List<String>>> resultRules2 = rt.templateTwo(rules,"RULE", 3);
	   //int templateThreeCount=rt.templateThree(resultRules1,resultRules2,"AND");
	  
	}  
	
	//Function to get Count
	public static int countAllRules(HashMap<List<String>,List<List<String>>> rules){
		
		  Iterator it = rules.entrySet().iterator();
	  	   int count=0;
	  	   while(it.hasNext())
	  	   {
	  		   Map.Entry pair = (Map.Entry) it.next();
	  		   List<String> key = (List<String>) pair.getKey();
	  		   List<List<String>> list = (List<List<String>>) pair.getValue();
	  		   
	  		   for(List<String> items : list)
	  		   {
	  			 if(items.size()>0)count++;
	  		   }   
	  		
	  	   }
	  	   
	  	   return count;
		
	}
	
	//Function to print all the rules
	public static void printAllRules(HashMap<List<String>,List<List<String>>> rules){
		
		
	      
	      //Print all the rules
	  	   Iterator it = rules.entrySet().iterator();
	  	   
	  	   while(it.hasNext())
	  	   {
	  		   Map.Entry pair = (Map.Entry) it.next();
	  		   List<String> key = (List<String>) pair.getKey();
	  		   List<List<String>> list = (List<List<String>>) pair.getValue();
	  		   key= changeIdToValue(key);
	  		   for(List<String> items : list)
	  		   {
	  			 if(items.size()>0)
				   {	
	  				    items= changeIdToValue(items);
					   System.out.println(key+"=>"+items);
				   }
	  		   }   
	  		System.out.println("-----------------------------------------------");
	  	   }
		
		
	}
	
	//Change the number ID given to each type of data to its original value
	public static List<String> changeIdToValue(List<String> list){
		
		List<String> resultList = new ArrayList<String>();
		
		for(String name : list)
		{
			String prefix = name.substring(0,name.length()-1);
			int key = name.charAt(name.length()-1)-48;
			if(key<=1)
			{
				if(key==0) prefix+="down";
				if(key==1) prefix+="up";
			}
			if(key>1)
			{
				if(key==2) prefix="Breast Cancer";
				else if(key==3) prefix="Colon Cancer";
				else if(key==4) prefix="ALL";
				else prefix="AML";
			}
			
			resultList.add(prefix);
		}
		
		
		return resultList;
		
	}
	
}
