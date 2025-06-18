package com.example.servingwebcontent.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.List;

import com.example.servingwebcontent.Customer;






public class CustomerAiven {

    public CustomerAiven(){}

    /*
     * to do
     * mapping database data to Model Song
     */

    ArrayList<Customer> items = new ArrayList<Customer>(); 
  

    /**
     * @return
     */
    public ArrayList<Customer> customerAivenList() {
      
        Connection conn = null;
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://",
                    "sqluser", "password");
            Statement sta = conn.createStatement();

            ResultSet setdata = sta.executeQuery("select * from Customer limit 10");
            int index =0;
            int columnCount = setdata.getMetaData().getColumnCount();
             System.out.println("column #"+columnCount);
   
          

            while (setdata.next()) {
                Customer cust = new Customer();
               
              
                String id = setdata.getString("id");
           
                String name = setdata.getString("name");
          
                String email = setdata.getString("email");

                String type = setdata.getString("type");

                System.out.println("Song AIVEN TEST:");
                System.out.println(id + " " + name + " " + email);

                cust.setId(id);
                cust.setName(name);
                cust.setEmail(email);
                cust.setType(type);


                System.out.println("Get SONG in song Aiven");
                System.out.println(cust.getName());
                System.out.println(index);
                

        
            items.add(cust);
       }

            setdata.close();
            sta.close();
            conn.close();
           
        } 
        catch (Exception e) {
            System.out.println("Error in database connecion");
            System.out.println(e);
            e.printStackTrace();
        }

        
        return items;

    }
    
}