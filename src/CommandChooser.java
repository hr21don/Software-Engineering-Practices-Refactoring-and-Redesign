/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author Helitha
 */
 
public class CommandChooser {
    private final ArrayList<Command> ManageList = new ArrayList<Command>();
    private MainList MCL = new MainCommandList();
    String draftTag = null;
    List<String> draftLines = new LinkedList<>();
    CLFormatter clf = null;  
    String user = null;
    String state = ""; 
    String command = "";
    private final static String RESOURCE_PATH = "Resources/MessagesBundle";
    private final ResourceBundle strings; 
    
    public CommandChooser(MainList MCL)
    {
      strings = ResourceBundle.getBundle(RESOURCE_PATH, new Locale("en" , "GB"));
      this.MCL = MCL;
      ManageList.add(new ExitCommandList());
      ManageList.add(new LineCommandList());
      ManageList.add(new PushCommandList());
      ManageList.add(new ReadCommandList());
    }
    
    private Command convertCommands(String cmd)
    {
        if(cmd.equals("manage_tine"))
        {
            System.out.print(clf.formatMainMenuPrompt());
            return new ManageCommandList();
        }
        else if(cmd.equals("read_tine"))
        {
            System.out.print(clf.formatMainMenuPrompt());
            return new ReadCommandList();
        }
        else if(cmd.equals("exit_tine"))
        {
             return new ExitCommandList();         
        }
        else if(cmd.equals("Add_Line"))
        {
             System.out.print(clf.
             formatDraftingMenuPrompt(draftTag, draftLines));
             return new LineCommandList();
        }
        else if(cmd.equals("Push_Tine"))
        {
            System.out.print(clf.
            formatDraftingMenuPrompt(draftTag, draftLines));
             return new PushCommandList();
        }
        else 
        {
            System.out.println(strings.getString("Error_message"));
            return null;
        }
    }
     
    public void takeCommands(MainList MCL)
    {
       OutputOption();
       Command invoker = convertCommands(inputOption());
       //OutputOption();
       ManageList.add(invoker);
       invoker.execute(MCL);
    }
    
   public String inputOption()
    {
        
        CLFormatter helper = clf;
        String raw;
        raw = null;
        System.out.println("InputOption");
        List<String> split = new ArrayList<String>();
        /* Scanner stdin = new Scanner(System.in);*/
        System.out.println("Add_Line" + split);
        String cmd = split.remove(0);
        command = cmd;
          return cmd;
        }
    
    
    public void OutputOption()
    { 
      if (command.equals("Manage") && command.equals("Read") ) {
        System.out.print(clf.formatMainMenuPrompt());
      } else { 
         // state = "Drafting"
        System.out.print(clf.
            formatDraftingMenuPrompt(draftTag, draftLines));
      }
    }   
}
