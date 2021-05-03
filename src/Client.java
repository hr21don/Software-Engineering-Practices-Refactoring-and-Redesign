
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

import sep.tinee.net.message.Bye;
import sep.tinee.net.message.Push;
import sep.tinee.net.message.ReadReply;
import sep.tinee.net.message.ReadRequest;

/**
 * This class is an initial work-in-progress prototype for a command line
 * Tinee client. It has been hastily hacked together, as often the case
 * in early exploratory coding, and is incomplete and buggy. However, it
 * does compile and run, and <i>some</i> basic functionality, such as pushing
 * and reading tines to and from an instance of
 * {@link sep.tinee.server.Server}, is working. Try it out!
 * <p>
 * The arguments required to run a client correspond to the
 * {@link #set(String, String, int)} method: a user name, and the host name
 * and port number of a Tinee server.
 * <p>
 * You can compile and run this client using <b>NetBeans</b>; e.g., right-click
 * this file in the NetBeans editor and select "Run File".  Note, to provide
 * the above arguments, you should set up a <b>run configuration</b> for this
 * class: {@literal ->} "Set Project Configuration" {@literal ->} "Customize..."
 * {@literal ->} "New...".
 * <p>
 * Assuming compilation using NetBeans (etc.), you can also run {@code Client}
 * from the command line; e.g., on Windows, run:
 * <ul>
 * <li style="list-style-type: none;">
 * {@code C:\...\tinee>  java -cp build\classes Client userid localhost 8888}
 * </ul>
 * <p>
 * You will be significantly reworking and extending this client: your aim is
 * to meet the specification, and you have mostly free rein to do so.
 * (That is as opposed to the base framework, which you are <b>not</b> permitted
 * to modify, i.e., the packages {@link sep.tinee.server},
 * {@link sep.tinee.server}, {@link sep.tinee.server} and]
 * {@link sep.tinee.server}.) The constraints on your client are:
 * <ul>
 * <li>
 * You must retain a class named {@code Client}) as the frontend class for
 * running your client, i.e., via its static {@linkplain #main(String[]) main}
 * method.
 * <li>
 * The {@linkplain Client#main(String[]) main} method must accept the <i>same
 * arguments</i> as currently, i.e., user name, host name and port number.
 * <li>
 * Your client must continue to accept user commands on the <i>standard input
 * stream</i> ({@code System.in}), and output on the <i>standard output
 * stream</i> ({@code System.out}).
 * <li>
 * Any other conditions specified by the assignment tasks.
 * </ul>
 * <p>
 * <i>Tip:</i> generate and read the <b>JavaDoc</b> API documentation for the
 * provided base framework classes (if you're not already reading this there!).
 * After importing the code project into NetBeans, right-click the project in
 * the "Projects" window and select "Generate Javadoc".
 * By default, the output is written to the {@code dist/javadoc} directory.
 * You can directly read the comments in the code for the same information, but
 * the generated JavaDoc is formatted more nicely as HTML with click-able links.  
 *
 * @see CLFormatter
 */
public class Client {
 private final static String RESOURCE_PATH = "Resources/MessagesBundle";
 private final ResourceBundle strings; 
 private String user;
 private String host;
 private int port;

private final boolean printSplash;

 public Client() {
        this.printSplash = true;
        strings = ResourceBundle.getBundle(RESOURCE_PATH, new Locale("en" , "GB"));
    }
 
 
 /**
    public Client(String username, String hostname, int portnumber) {
         this.user = username;
        this.host = hostname;
        this.port = portnumber;
     * }
     */

  public static void main(String[] args) throws IOException {
    String user = args[0];
    String host = args[1];
    int port = Integer.parseInt(args[2]);
    Client client = new Client();
    client.set(user, host, port);
    client.run();
  }

   
  public void set(String user, String host, int port) {
    this.user = user;
    this.host = host;
    this.port = port;
  }

  // Run the client
  @SuppressFBWarnings(
      value = "DM_DEFAULT_ENCODING",
      justification = "When reading console, ignore 'default encoding' warning")
          
  void run() throws IOException {
    BufferedReader reader = null;
    CLFormatter helper = null;
    try {
      reader = new BufferedReader(new InputStreamReader(System.in));

      if (this.user.isEmpty() || this.host.isEmpty()) {
        System.err.println(strings.getString("UserHost_Message"));
        throw new RuntimeException("Runtime");      
      }
      helper = new CLFormatter(this.host, this.port);

      if (this.printSplash)
      {
        System.out.print(helper.formatSplash(this.user));
      }
      loop(helper, reader);
    } catch (Exception ex){
      throw new RuntimeException(ex);
    } finally {
      reader.close();
      if (helper.chan.isOpen()) {
        // If the channel is open, send Bye and close
        helper.chan.send(new Bye());
        helper.chan.close();
      }
    }
  }

// Main loop: print user options, read user input and process
  void ManageCommandsList(CLFormatter helper, BufferedReader reader) throws IOException,
      ClassNotFoundException  {
      MainList MCL = new MainCommandList(); 
      CommandChooser CC = new CommandChooser(MCL);
    
       //while(true) {
           CC.takeCommands(MCL);           
      //}
  }
   void loop(CLFormatter helper, BufferedReader reader) throws IOException,
      ClassNotFoundException {

    // The app is in one of two states: "Main" or "Drafting"
    String state = strings.getString("Initial_State");  // Initial state

    // Holds the current draft data when in the "Drafting" state
    String draftTag = null;
    List<String> draftLines = new LinkedList<>();

    // The loop
    for (boolean done = false; !done;) {

      // Print user options
      if (state.equals(strings.getString("Initial_State"))) {
        System.out.print(helper.formatMainMenuPrompt());
      } else {  // state = "Drafting"
        System.out.print(helper.
            formatDraftingMenuPrompt(draftTag, draftLines));
      }

      // Read a line of user input
      String raw = reader.readLine();
      if (raw == null) {
        throw new IOException(strings.getString("IO_Exception_Message"));
      }
      // Trim leading/trailing white space, and split words according to spaces
      List<String> split = Arrays.stream(raw.trim().split("\\ "))
          .map(x -> x.trim()).collect(Collectors.toList());
      String cmd = split.remove(0);  // First word is the command keyword
      String[] rawArgs = split.toArray(new String[split.size()]);
      // Remainder, if any, are arguments

      // Process user input
      if (strings.getString("exit_tine").startsWith(cmd)) {
        // exit command applies in either state
        done = true;
      } // "Main" state commandsq
      else if (state.equals(strings.getString("Initial_State"))) {
        if (strings.getString("manage_tine").startsWith(cmd)) {
          // Switch to "Drafting" state and start a new "draft"
          state = strings.getString("Drafting_State");
          draftTag = rawArgs[0];
        } else if (strings.getString("read_tine").startsWith(cmd)) {
          // Read tines on server
          helper.chan.send(new ReadRequest(rawArgs[0]));
          ReadReply rep = (ReadReply) helper.chan.receive();
          System.out.print(
              helper.formatRead(rawArgs[0], rep.users, rep.lines));
        } else {
          System.out.println(strings.getString("Error_message"));
        }
      } // "Drafting" state commands
      else if (state.equals(strings.getString("Drafting_State"))) {
        if (strings.getString("Add_Line").startsWith(cmd)) {
          // Add a tine message line
          String line = Arrays.stream(rawArgs).
              collect(Collectors.joining());
          draftLines.add(line);
        } else if (strings.getString("Push_Tine").startsWith(cmd)) {
          // Send drafted tines to the server, and go back to "Main" state
          helper.chan.send(new Push(user, draftTag, draftLines));
          state = strings.getString("Initial_State");
          draftTag = null;
        } else {
          System.out.println(strings.getString("Error_message"));
        }
      } else {
        System.out.println(strings.getString("Error_message"));
      }
    }
  }
}
