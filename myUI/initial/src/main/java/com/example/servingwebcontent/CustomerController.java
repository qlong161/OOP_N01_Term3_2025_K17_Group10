


package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.servingwebcontent.Database.CustomerAiven;

import java.util.ArrayList;


//controller to song list
@Controller
public class CustomerController {
    @GetMapping("/customerlist")

    public String songlist(Model model){

        ArrayList<Customer> cusList = new ArrayList<Customer>();
        CustomerAiven sa = new CustomerAiven();
        cusList = sa.customerAivenList();
        model.addAttribute("ListOfCustomer", cusList);

        //data to View is ${ListOfSong}
        
       //return view is songList
        return "customerlist";

    }

    
}

