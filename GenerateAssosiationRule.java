package Asst2;

import java.util.*;
public class GenerateAssosiationRule {
		
  public HashMap<List<String>,List<List<String>>> assosiationRuleMap;
  
  public GenerateAssosiationRule(){
	  
	  assosiationRuleMap = new HashMap<List<String>,List<List<String>>>();
  }
  
  public HashMap<List<String>,List<List<String>>> getAssosiationRules(){
	  return assosiationRuleMap;
  }
  
  public void fillAssociationMap(HashMap<List<String>,Integer> itemSetCountMap,int minConf){
	  
	  	
	  Iterator it = itemSetCountMap.entrySet().iterator();
	  
	  while(it.hasNext())
	  {
		  @SuppressWarnings("unchecked")
		  Map.Entry<List<String>,Integer> pair = (Map.Entry<List<String>,Integer>)it.next();
		  List<String> itemSet = (List<String>) pair.getKey();
		  int size= itemSet.size();

		  int numerator =  itemSetCountMap.get(itemSet);
		  if(size==1) continue;
		  
		  //Get power set of all the indexes
		  List<Integer> indexList = new ArrayList<Integer>();
		  for(int i=0;i<size;i++) indexList.add(i);
		  List<List<Integer>> powerSet = powerList(indexList);
		  
		  //Loop through them to cehck for rules
		  for(List<Integer> indexSet: powerSet)
		  {
			  if(indexSet.size()==size) continue;
			  
			  List<String> bodyList = new ArrayList<String>(itemSet);
			  List<String> headList = new ArrayList<String>();
			  
			  for(int n: indexSet)
			  {
				  String item = itemSet.get(n);
				  headList.add(item);
				  bodyList.remove(bodyList.indexOf(item));
				  
			  }
			  if(headList.size()==0 || bodyList.size()==0) continue;
			  //For X=>Y and Y=>X
			  int denominator1 = itemSetCountMap.getOrDefault(bodyList,0);
			  int denominator2 = itemSetCountMap.getOrDefault(headList,0);
			  
			  //Add into Assosiation Rule Map
			  if(denominator1!=0 && isTrue(numerator,denominator1,minConf))
			  {
				  List<List<String>> temp;
				  temp = assosiationRuleMap.getOrDefault(bodyList, new ArrayList<List<String>>());
				  if(!temp.contains(headList))
				  temp.add(headList);
				  assosiationRuleMap.put(bodyList, temp);
			  }
			  if(denominator2!=0 &&  isTrue(numerator,denominator2,minConf))
			  {
				  List<List<String>> temp;
				  temp = assosiationRuleMap.getOrDefault(headList, new ArrayList<List<String>>());
				  if(!temp.contains(bodyList))
				  temp.add(bodyList);
				  assosiationRuleMap.put(headList, temp);
			  }
		  }
		  
		  
	  }
	  
  }
  
  
  //To check if mincOnf<= itemSetConf
  public boolean isTrue(int numerator,int denominator, int minConf){
	  
	  double confidence = (double)numerator/(double)denominator*100;
	  
	  return(confidence>=minConf);
	  
  }
  
  //To generate powersets
  public static  List<List<Integer>> powerList(List<Integer> list) {
	  List<List<Integer>> powerSet = new ArrayList<List<Integer>>();
	  powerSet.add(new ArrayList<Integer>());
	  for (Integer item : list) {
	    List<List<Integer>> newPs = new ArrayList<List<Integer>>();
	 
	    for (List<Integer> subset : powerSet) {
	      newPs.add(subset);
	 
	      List<Integer> newSubset = new ArrayList<Integer>(subset);
	      newSubset.add(item);
	      newPs.add(newSubset);
	    }
	    powerSet = newPs;
	  }
	  return powerSet;
  }
}
