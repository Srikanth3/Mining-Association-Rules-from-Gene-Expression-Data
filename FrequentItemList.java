package Asst2;

import java.util.*;

public class FrequentItemList {

	public HashMap<List<String>,Integer> itemSetCountMap;
	
	public FrequentItemList(){
		
		itemSetCountMap = new HashMap<List<String>,Integer>();
	}
	
	public HashMap<List<String>,Integer> getItemSetCountMap(){
		
		return itemSetCountMap;
		
	}
	
public List<List<String>> genLengthOneFrequentItemSet(int[][] data,int support){
		
		int size = data.length;
		HashMap<Integer, Integer> countMap = new HashMap<Integer,Integer>();
		List<List<String>> lengthOneItemSet = new ArrayList<List<String>>();
		
		for(int j=0;j<data[0].length;j++)
		{
			String prefix = "G"+(j+1)+"_";
			countMap.clear();
			for(int i=0;i<data.length;i++)
			{
				countMap.put(data[i][j], (countMap.getOrDefault(data[i][j], 0)+1));
			}
			
			Iterator it = countMap.entrySet().iterator();
			
			while(it.hasNext())
			{
				Map.Entry<Integer,Integer> pair = (Map.Entry<Integer,Integer>)it.next();
				int key = (int)pair.getKey();
				int val = (int)pair.getValue();
			//	System.out.println(prefix+"Key:"+key+"---Val:"+val);
				
				double sup = (double)val/(double)size;
				sup*=100;
				if(sup>=support){
					List<String> tempList = new ArrayList<String>();
					tempList.add(prefix+key);
					lengthOneItemSet.add(tempList);
					itemSetCountMap.put(tempList,val);
				}
			}
		}
		
		return lengthOneItemSet;
	}
	
	public List<List<String>> getCountForListLengthN(List<List<String>> lengthItemSet,int length,int support,int[][] data){
			
		List<List<String>> lengthNItemSet = new ArrayList<List<String>>();
			
		int size= data.length;
		for(List<String> itemSet: lengthItemSet)
		{
			int count=0;
			for(int row=0;row<data.length;row++)
			{
				boolean res=false;
				for(String item : itemSet)
				{
					int col= item.length()==5 ? Integer.parseInt(item.substring(1,3)) : Integer.parseInt(item.substring(1,2) );
					col--;
					int val = item.charAt(item.length()-1)-48;
					//System.out.println("Row+"+row+"--Col:"+col);
					res = data[row][col]==val;
					if(!res) break;
				}
				
				if(res) count++;
			} 
			
			double sup = (double)count/(double)size;
			sup*=100;
			if(sup>=support){
				List<String> tempList = new ArrayList<String>(itemSet);	
				lengthNItemSet.add(tempList);
				itemSetCountMap.put(itemSet,count);
			}
			
			
		}
		return lengthNItemSet;
		//Function ends
	}
}
