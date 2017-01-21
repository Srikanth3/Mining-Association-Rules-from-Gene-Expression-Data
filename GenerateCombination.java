package Asst2;
import java.util.*;
public class GenerateCombination {
	
	public static void main(String args[]){
		
		
	}
	
	public static List<List<String>> generateCombinationSet(List<List<String>> prevList,List<List<String>> lengthOneFrequentItemSet){
		
			
		List<List<String>> newList = new ArrayList<List<String>>();
		
		for(List<String> itemList: prevList)
		{
			String lastItem = itemList.get(itemList.size()-1);
			int col1= lastItem.length()==5 ? Integer.parseInt(lastItem.substring(1,3)) : Integer.parseInt(lastItem.substring(1,2) );
			
			for(List<String> item: lengthOneFrequentItemSet)
			{
				String name= item.get(0);
				int col2= name.length()==5 ? Integer.parseInt(name.substring(1,3)) : Integer.parseInt(name.substring(1,2) );
				
				if(col1>=col2) continue;
				
				if(!itemList.contains(name))
				{
					
					List<String> res = new ArrayList<String>(itemList);
					res.add(name);
					newList.add(res);
				}
			}
		}
		
		return newList;
	}
}
