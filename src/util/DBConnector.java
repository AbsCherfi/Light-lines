package util;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

public class DBConnector {
	private Connection connection;
	private String url;
	private String username;
	private String password;
	ResultSetMetaData rsmd;


	    public DBConnector() throws FileNotFoundException {
		    connection = null;
		   url = "jdbc:mysql://localhost:3306/LightLinesTravels";
		   username = "root";
		   password = "123@Kaizen";
	}

	    
	            //DriverManager.getConnection(
				//"jdbc:mysql://localhost:3306/Bank",
				//"root", "123@Kaizen");
	
	    public void connect() throws FileNotFoundException {

	    	try {
	    		//declare and initialise a scanner object to get the input from the user
	    		//Scanner scan = new Scanner(System.in);
	    		//System.out.println(parser.toString());
	    		//prompt the user to enter the URL
				//System.out.println("please input your URL");
				//get the input from the user and store it as URL
				//String url = scan.nextLine();
				//prompt the user to enter the username
				//System.out.println("please input your username (this could be 'root')");
				//get the input from the user and store it as username
				//String username = scan.nextLine();
				//prompt the user to enter their password
				//System.out.println("please input your password");
				//get the input from the user and store it as password
				//String password = scan.nextLine();
				//use the input to gain access to the database
				connection = DriverManager.getConnection(url, username, password);
				//scan.close();	    		
				
			} catch (SQLException e) {
				//catch exception,print a message and the actual error
				System.out.println("Connection failure");
				e.printStackTrace();
				//come out of the method call
				return;
			}
	    	     
	    	if (connection != null ) {
	    		//print a success method to let the user they've successfully connected to the database
	    		System.out.println("Connection established.");
	    	}else {
	    		//print a message letting the user know the connection failed
				System.out.println("Connection is null.");
			}
	    	
	}
	    /*
    	 * prepare a query statement to run
    	 * 
    	 * execute query
    	 */
    		
	    public ResultSet runQuery(String sql) {
	    	//declare PreparedStatement give a name pst  and set it to null
	    	PreparedStatement pst = null;
	    	
	    	
         try {
        	 //set pst to be the query that we read from the file in the parser class
        	 pst = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
        			 ResultSet.CONCUR_UPDATABLE);
        	//execute the query that we read from the file in the parser class
        	 pst.execute();
        	 //declare and initialise a ResultSet object 
        	 ResultSet results = pst.getResultSet();
        	 //if the table isnt empty then
        	 if (results!= null) {
        		 //declare and set a variable rowCount to be 0
				int rowCount = 0;
				if (results.last()) {
					//if the row is the last row
					rowCount = results.getRow();
					//get the number of that row and set it to be rowCount
					results.beforeFirst();
					//move the cursor back the position before last because the cursor starts from the position thats 
					//after the one that it was in
				}
				//print the number of rows
				System.out.println("\n Success!  \nResult set has "+ rowCount+ " rows");
				//if there are no rows let the user know
			}else {
				System.out.println("\n Success!  No Results");
			}
        	 //return the results and exit the method call
        	   return results;
        	   
        	   //catch any SQLException
            } catch (SQLException e) {
            	// let the user know the program couldnt run and get the resultset
            	System.out.println(sql + "\n failed to run.");
            	//print the error
            	e.printStackTrace();
            	//return null and exit the method call
            	return null;
            }
	    	
	    	
	    }

	    
	    public void printResults(ResultSet rs) {
    		try {
    			//declare a ResultSetMetaData object and nmae it rmsd and set it to the result that our resultset holds as data
    			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
    			//declare and initialisea local variable columnNumber and set it to the number of colums we get from our ResulSetMetadata
    			int columnNumber = rsmd.getColumnCount();
    			//user friendly printing
    			System.out.print("the columns are \n");
    			System.out.println("********");
    			for(int i =1; i <= columnNumber;i++) {
    				//go through the colums that the resultset holds and print the names of the columns
    				//this prints the database name
    				//System.out.println(rsmd.getCatalogName(columnNumber));
    				
    				System.out.println("-"+rsmd.getColumnLabel(i) + ": " + rsmd.getColumnTypeName(i) + ".");
    			}
    			System.out.println("********");
    			
    			java.sql.DatabaseMetaData md = connection.getMetaData();
    			// while there is another row
    			
    			//while(rs.next()) {
    				//System.out.println("BAccount " + rsmd.getColumnLabel(2) + ": " + rs.getString(rsmd.getColumnLabel(2)));
    				
        			//System.out.println("The table is \n"+ rsmd.getTableName(columnNumber)+ " \n ");
    				//System.out.println(rsmd.getColumnLabel(1) + ": " + rs.getString(rsmd.getColumnLabel(1))+ "|");
    				//System.out.println(
    						 //rsmd.getColumnLabel(2) + ": " + rs.getString(rsmd.getColumnLabel(2)) + "|");
    				//System.out.println(
    						//"BAccount " + ">> " + rsmd.getColumnLabel(1) + ": " + rs.getInt(rsmd.getColumnLabel(1)) + "|");
    				//System.out.println(
    						//rsmd.getColumnLabel(3) + ": " + rs.getString(rsmd.getColumnLabel(3)) + "|");
    				//System.out.println(
    						//rsmd.getColumnLabel(4) + ": " + rs.getInt(rsmd.getColumnLabel(4)) + "|");
    				//System.out.println(
    						//rsmd.getColumnLabel(5) + ": " + rs.getInt(rsmd.getColumnLabel(5)) + "|");
    				//System.out.println(
    				//		rsmd.getColumnLabel(6) + ": " + rs.getInt(rsmd.getColumnLabel(6)) + "|");
    				//System.out.println(
    				//		rsmd.getColumnLabel(7) + ": " + rs.getInt(rsmd.getColumnLabel(7)) + "|");
    				//System.out.println(
    				//		rsmd.getColumnLabel(8) + ": " + rs.getInt(rsmd.getColumnLabel(8)) + "|");
    				//System.out.println(
    						//rsmd.getColumnLabel(9) + ": " + rs.getInt(rsmd.getColumnLabel(9)) + "|");
    				//System.out.println(rsmd.getTableName(columnNumber)+ " \n ");
    			
    			//print the name of the database
    			System.out.println("_the database name is " + "'"+ rsmd.getCatalogName(columnNumber)+"'"+ " \n ");
    			//print the name of the table
    				System.out.println("_The table is "+ "'"+rsmd.getTableName(columnNumber)+"'"+ " \n ");
    				// while there is another row
    				while(rs.next()) {
    					//go through all the columns
    					for(int i =1; i <= columnNumber;i++) {
    						//declare and set a local variable ColumnValue and set it to be the the value that the  name of the column holds
    						String ColumnValue = rs.getString(i);
    						//print the name od the column and the value it holds
    						System.out.println("*"+rsmd.getColumnName(i)+ " >> " + ColumnValue + "."  );
    						System.out.println("-------");	
    					}
    					
    				}
    			//}
    				
			} catch (SQLException e) {
				//handle the exception and prinbt what it is
				System.out.println("an error happened in DBConnector");
				e.printStackTrace();
			}
	    }    
		//"jdbc:mysql://localhost:3306/Bank",
		//"root", "123@Kaizen");


		public void close() {
			try {
				//close the connection
				connection.close();
				//ptint to the user that the connection was successfully closed
				System.out.println("Connection closed");
			} catch (SQLException e) {
				// handle the exception and print to the user that the connection wasn't closed
				System.out.println("connection not closed ");
				//print the error
				e.printStackTrace();
			}
			
		}
	    
	      
	    
}
