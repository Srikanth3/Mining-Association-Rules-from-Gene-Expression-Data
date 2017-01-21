package Asst2;
import java.util.*;

public class RuleTemplates {

	
	public HashMap<List<String>,List<List<String>>> templateOne(HashMap<List<String>,List<List<String>>> rules, String len,List<String> itemSet,String pos){
		
		HashMap<List<String>,List<List<String>>> resultRules = new HashMap<List<String>,List<List<String>>>();
		
		Iterator it = rules.entrySet().iterator();
		int count=0;
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			
			List<String> body = (List<String>) pair.getKey();
			List<List<String>> valueList = (List<List<String>>)pair.getValue();
			
			if(pos.equals("BODY"))
			{
				if(len.equals("ANY"))
				{	
					for(String item : itemSet)
					{
						if(body.contains(item))
						{	
							for(List<String> singleItem : valueList)
							{	
								List<List<String>> tempList;
								tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
								tempList.add(singleItem);
								resultRules.put(body, tempList);
							}
							count+=valueList.size();
							break;
						}
						
					}
				}
				else if(len.equals("NONE"))
				{
					int flag=0;
					for(String item : itemSet)
					{
						if(body.contains(item)) flag++;
					}
					if(flag==0){ 
						count+=valueList.size();
						for(List<String> singleItem : valueList)
						{	
							List<List<String>> tempList;
							tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
							tempList.add(singleItem);
							resultRules.put(body, tempList);
						}
					}
				}
				else
				{
					int size= Integer.parseInt(len);
					int flag=0;
					for(String item : itemSet)
					{
						if(body.contains(item)) flag++;
					}
					if(flag==size) {
						count+=valueList.size();
						for(List<String> singleItem : valueList)
						{	
							List<List<String>> tempList;
							tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
							tempList.add(singleItem);
							resultRules.put(body, tempList);
						}
					}
				}
			}
			else if(pos.equals("RULE"))
			{
				for(List<String> head : valueList)
				{
					if(len.equals("ANY"))
					{
						for(String item : itemSet)
						{
							if(body.contains(item) || head.contains(item))
							{
								count++;
								{	
									List<List<String>> tempList;
									tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
									tempList.add(head);
									resultRules.put(body, tempList);
								}
								break;
							}
						}
					}
					else if(len.equals("NONE"))
					{
						int flag=0;
						for(String item : itemSet)
						{
							if(body.contains(item) || head.contains(item)) flag++;
						}
						if(flag==0)
						{
							count++;
							List<List<String>> tempList;
							tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
							tempList.add(head);
							resultRules.put(body, tempList);
						}
					}
					else
					{
						int size= Integer.parseInt(len);
						int flag=0;
						for(String item : itemSet)
						{
							if(body.contains(item) || head.contains(item)) flag++;
						}
						if(flag==size)
						{
							count++;
							List<List<String>> tempList;
							tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
							tempList.add(head);
							resultRules.put(body, tempList);
						}
					}
					
				}
			}
			else // IF POSITION = HEAD
			{
				for(List<String> head : valueList)
				{
					if(len.equals("ANY"))
					{
						for(String item : itemSet)
						{
							if(head.contains(item))
							{
								count++;
								List<List<String>> tempList;
								tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
								tempList.add(head);
								resultRules.put(body, tempList);
								break;
							}
						}
					}
					else if(len.equals("NONE"))
					{
						int flag=0;
						for(String item : itemSet)
						{
							if(head.contains(item)) flag++;
						}
						if(flag==0) 
						{
							count++;
							List<List<String>> tempList;
							tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
							tempList.add(head);
							resultRules.put(body, tempList);
						}
					}

					else
					{
						int size= Integer.parseInt(len);
						int flag=0;
						for(String item : itemSet)
						{
							if(head.contains(item)) flag++;
						}
						if(flag==size){ 
							count++;
							List<List<String>> tempList;
							tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
							tempList.add(head);
							resultRules.put(body, tempList);
						}
					}
					
				}
			}
			
			
		}
		
		System.out.println("Count of rules"+count);
		return resultRules;
	}
	
	public HashMap<List<String>,List<List<String>>> templateTwo(HashMap<List<String>,List<List<String>>> rules,String pos, int size){
		
		HashMap<List<String>,List<List<String>>> resultRules = new HashMap<List<String>,List<List<String>>>();
		Iterator it = rules.entrySet().iterator();
		int count=0;
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			
			List<String> body = (List<String>) pair.getKey();
			List<List<String>> valueList = (List<List<String>>)pair.getValue();
			
			if(pos.equals("BODY"))
			{
				if(body.size()>=size)
				{ 
					resultRules.put(body,valueList);
					count+=valueList.size();
				}
				continue;
			}
			
			for(List<String> head : valueList)
			{
				if(pos.equals("HEAD"))
				{
					if(head.size()>=size) 
					{
						count++;
						List<List<String>> tempList;
						tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
						tempList.add(head);
						resultRules.put(body, tempList);
						
					}
					
					continue;
				}
				
				if(pos.equals("RULE"))
				{
					int totalSize = body.size()+head.size();
					if(totalSize>=size) {
						count++;
						List<List<String>> tempList;
						tempList =resultRules.getOrDefault(body,  new ArrayList<List<String>>());
						tempList.add(head);
						resultRules.put(body, tempList);
					}
					continue;
				}
				
			}
			
		}
		System.out.println("Count of rules"+count);
		return resultRules;
	}
	
	public int templateThree(HashMap<List<String>,List<List<String>>> rulesLeft,HashMap<List<String>,List<List<String>>> rulesRight,String operation){
		
		Iterator it = rulesLeft.entrySet().iterator();
		int count=0,totalCount=0;
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			
			List<String> bodyLeft = (List<String>) pair.getKey();
			List<List<String>> valueListLeft = (List<List<String>>)pair.getValue();
			
				if(!rulesRight.containsKey(bodyLeft)) continue;
				List<List<String>> valueListRight = rulesRight.get(bodyLeft);
				
				for(List<String> itemSetLeft : valueListLeft)
				{
					for(List<String> itemSetRight: valueListRight)
					{
						if(itemSetLeft.equals(itemSetRight)) {count++;break;}
					}
				}
			
				
		}
		
		if(operation.equals("OR")){
			
			count*=-1;
			count+=countAllRules(rulesLeft);
			count+=countAllRules(rulesRight);
		}
		System.out.println("Count of rules"+count);
		 return count;
		
		
		
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
}
