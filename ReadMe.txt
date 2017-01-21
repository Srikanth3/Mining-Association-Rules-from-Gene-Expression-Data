

1. Run the file named  "MainClass.java" for running all queries.

2. For Getting Count of the frequent iten sets, simply change the value of the variable "Support" on line 34 to the desired value and run the file.

3. To print all the association rules that have been generated, uncomment the line 76.

4. To run queries for the templates, uncomment the lines 84 -86 according to the query.

Arguments for tempaltes :
	Template 1: {HashMap consisting of all the rules}, {Length of set (ANY,NONE,1,2)}, {List<String> arguents}, {HashMap consisting of all the rules},
	Template 2: {HashMap consisting of all the rules},{HashMap consisting of all the rules}, {size}
	Template 3: {{HashMap consisting of all the rules1},{HashMap consisting of all the rules2},Logical Operation}

For running tempalte three, run the two parts of the queries through Template 1 and/or Template 2 and feed the returned values into Templare 3.


 					PLEASE NOTE 
	     USE THE FOLLOWING KEY TO REPRESENT THE FOLLOWING VALUES FOR RUNNING QUERIES
	     1. UP
	     0.Down
	     2 Breast Cancer
	     3.Colon Cancer
	     4.ALL
	     5.AML
	     (example: G1_1 would point to G1_UP
	    	       G45_0 would point to G45_Down
		       G100_2 would point to Breast Cancer
		       G100_3 would point to Colon Cancer
		)
	     