import sep.tinee.net.message.Push;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Locale;  
import java.util.ResourceBundle; 
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.net.channel.ClientChannel;

import sep.tinee.net.message.Bye;
import sep.tinee.net.message.Push;
import sep.tinee.net.message.ReadReply;
import sep.tinee.net.message.ReadRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Helitha
 */
public class MainCommandList implements MainList {
        /**rmatter clf = null;   
    String user = null;
    /**
     *
     * @param reader
     * @param helper
     * @return
     * @throws java.io.IOException
     */
    private final static String RESOURCE_PATH = "Resources/MessagesBundle";
    private final ResourceBundle strings; 
    
    String state;  

    // Holds the current draft data when in the "Drafting" state
    String draftTag = null;
    List<String> draftLines = new LinkedList<>();
    CLFormatter clf = null;   
    String user = null;

    
    // The app is in one of two states: "Main" or "Drafting"
    // Initial state
    public MainCommandList()
    {
        strings = ResourceBundle.getBundle(RESOURCE_PATH, new Locale("en" , "GB"));
        this.state = strings.getString("Initial_State");
    }
    
    
    public String readline(BufferedReader reader) throws IOException
    { 
      try {
          reader = new BufferedReader(new InputStreamReader(System.in));
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex); 
        }
     return null;
    }
    
    private void getformatter(CLFormatter clf)
    {
        this.clf = clf;
    }
   private void getUser(String user)
    {
    this.user = user;
    }
    
    @Override
    public void Read() {
       String[] rawArgs = null;
     try {
         // Read tines on server
         clf.chan.send(new ReadRequest(rawArgs[0]));
     } catch (IOException ex) {
         Logger.getLogger(MainCommandList.class.getName()).log(Level.SEVERE, null, ex);
     }
          ReadReply rep = null;
     try {
         rep = (ReadReply) clf.chan.receive();
     } catch (IOException | ClassNotFoundException ex) {
         Logger.getLogger(MainCommandList.class.getName()).log(Level.SEVERE, null, ex);
     }
          System.out.print(
              clf.formatRead(rawArgs[0], rep.users, rep.lines));
          //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Manage() {
        CLFormatter helper = clf;
        String cmd = null;
        state = "Drafting_State";
       String[] rawArgs = null;
          draftTag = rawArgs[0];
        //strings.getString("manage_tine").startsWith(cmd));
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Exit() {
          {       
          System.exit(0);
      }    
    }

    @Override
    public void Line() {
          CLFormatter helper = clf;
          String raw;
          raw = null;
          List<String> split = Arrays.stream(raw.trim().split("\\ "))
          .map(x -> x.trim()).collect(Collectors.toList());
           String cmd = split.remove(0);  // First word is the command keyword
           String[] rawArgs;
           rawArgs = split.toArray(new String[split.size()]);
           if (strings.getString("Add_Line").startsWith(cmd)) {
          // Add a tine message line
          String line = Arrays.stream(rawArgs).
              collect(Collectors.joining());
          draftLines.add(line);
        }//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Push() {
     try {
         // Send drafted tines to the server, and go back to "Main" state
         clf.chan.send(new Push(user, draftTag, draftLines));
     } catch (IOException ex) {
         Logger.getLogger(MainCommandList.class.getName()).log(Level.SEVERE, null, ex);
     }
          state = strings.getString("Initial_State");
          draftTag = null;
    }
    @Override
    public void printoptions() {
        if (state.equals(strings.getString("Initial_State"))) {
        System.out.print(clf.formatMainMenuPrompt());
         } else {  // state = "Drafting"
      System.out.print(clf.
            formatDraftingMenuPrompt(draftTag, draftLines));
      } //To change body of generated methods, choose Tools | Templates.
    }   
    
    @Override
    public void Undo()
    {  //CommandChooser CC = new CommandChooser();
       for (int i =0; i < draftLines.size(); i++)
       {
          if(i == draftLines.size())
          {
              draftLines.remove(draftLines.get(i));  
          }
       }
    }
  }
  
