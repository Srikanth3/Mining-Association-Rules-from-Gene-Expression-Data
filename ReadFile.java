package Asst2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFile {
	
	public int[][] readFile() throws FileNotFoundException{
		
		File file=new File("D:/JavaWorkSpace/dmproject/src/Asst2/geneexpression.txt");
        Scanner scan=new Scanner(file);
        
        int[][] data = new int[100][101];
        int count=0;
        int row=0,col=0;
        
        while(scan.hasNextLine())
        {
        	StringTokenizer st = new StringTokenizer(scan.nextLine());
        	st.nextToken();
        	col=0;
        	while(st.hasMoreTokens()) 
        	{
        		String token = (String)st.nextToken();
        		//System.out.println(token);
        		if(token.equals("UP")) data[row][col]=1;
        		else if(token.equals("Down")) data[row][col]=0;
        		else if(token.equals("Breast")) data[row][col]=2;
        		else if(token.equals("Colon")) data[row][col]=3;
        		else if(token.equals("ALL")) data[row][col]=4;
        		else if(token.equals("AML")) data[row][col]=5;
        	        	
        		col++;
        	}
        	row++;
        }
		
        
        return data;
		
	}
	
}
