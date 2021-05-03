/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Helitha
 */
public class ClientTest {
    
    /**
     *
     */
    public ClientTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Client.
     * @throws java.lang.Exception
     */

    @Test
    public void testMain() throws Exception {
        String[] args ={"userid", "localhost", "8888"};
        
        String input= "exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);//SetsSystem.intothesuppliedstream
        
        Client.main(args);
        // TODO review the generated test code and remove the default call to fail.
     
    }  
   
    
    @Test
    public void testDraftingMode() throws Exception {
        String[] args ={"userid", "localhost", "8888"};
        
        String input= "manage draft\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);//SetsSystem.intothesuppliedstream
        
        Client.main(args);
        // TODO review the generated test code and remove the default call to fail.
     
    }   
    
    /**
     *
     * @throws Exception
     */
   
    @Test
    public void addingtext() throws Exception {
        String[] args ={"userid", "localhost", "8888"};
        
        String input= "manage draft\n line Hello World! \n exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);//SetsSystem.intothesuppliedstream
        
        Client.main(args);
        // TODO review the generated test code and remove the default call to fail.
     
    }  
     
    /**
     *
     * @throws Exception
     */
  
    @Test
    public void pushingtext() throws Exception {
        String[] args ={"userid", "localhost", "8888"};
        
        String input= "manage draft\n line Hello World! \n  push \n exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);//SetsSystem.intothesuppliedstream
        
        Client.main(args);
        // TODO review the generated test code and remove the default call to fail.
     
    }  

    /**
     *
     * @throws java.io.IOException
     * @throws Exception
     */

    
  
    @Test
    public void readingtext() throws Exception {
        String[] args ={"userid", "localhost", "8888"};
        
        String input= "manage draft\n line Hello World! \n  push \n read draft \n exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);//SetsSystem.intothesuppliedstream
        
        Client.main(args);
        // TODO review the generated test code and remove the default call to fail.
     
    }
    
    /**
     * Test of set method, of class Client.
     * @throws java.lang.Exception
     */

    @Test
    public void testSet() throws Exception {
        System.out.println("set");    
        String user = "";
        String host = "";
        int port = 8888;
        Client instance = new Client();
        instance.set(user, host, port);
        //instance.run();
        // TODO review the generated test code and remove the default call to fail.
    } 

    /**
     * Test of run method, of class Client.
     * @throws java.lang.Exception
     */
 
    @Test
    public void testRun() 
    {  System.out.println("run");
        String user = "";
        String host = "";
        System.err.println("User/host has not been set.");
    }
   
    
    /**
     * Test of loop method, of class Client.
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */

    @Test
    public void testLoop() throws Exception {
      System.out.println("Main loop: print user options, read user input and process");      
      
       for (boolean done = false; !done;) {
           System.out.println("Testing Loop");
          return;
       }
        
         //CLFormatter helper = String user, int port;
         // BufferedReader reader = null
         // TODO review the generated test code and remove the default call to fail.
    }
        /**
     * Test of ManageCommandsList method, of class Client.
     */
  /*  @Test
    public void testManageCommandsList() throws Exception {
        System.out.println("ManageCommandsList");
        CLFormatter helper = null;
        BufferedReader reader = null;
        Client instance = new Client();
        instance.ManageCommandsList( helper, reader);
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
    }
*/
}
