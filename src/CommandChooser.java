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
    private final ArrayList<MainList> ManageList = new ArrayList<MainList>();
    private MainList MCL = new MainCommandList();
    /**
     *
     * @param CMD
     */
    public void takeCommands(MainList MCL)
    {
      this.MCL = MCL;
      ManageList.add(MCL);
    }
}
