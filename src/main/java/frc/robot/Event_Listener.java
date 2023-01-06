package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Event_Listener{
   
   public void run() {
      double[] values = new double[2];
      //get the default instance of NetworkTables
      NetworkTableInstance inst = NetworkTableInstance.getDefault();

      //get a reference to the subtable called "datatable"
      NetworkTable datatable = inst.getTable("Vision");

      //get a reference to key in "datatable" called "Y"
      NetworkTableEntry xEntry = datatable.getEntry("target_x");
      NetworkTableEntry yEntry = datatable.getEntry("target_y");
      inst.startClientTeam(190);
      
      //add an entry listener for changed values of "X", the lambda ("->" operator)
      //defines the code that should run when "X" changes
      datatable.addEntryListener("X", (table, key, entry, value, flags) -> {
         System.out.println("X changed value: " + value.getValue());
      }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

      //add an entry listener for changed values of "Y", the lambda ("->" operator)
      //defines the code that should run when "Y" changes
      yEntry.addListener(event -> {
         double y = event.getEntry().getDouble(0);
         values[1] = y;
         System.out.println("Y changed value: " + event.getEntry().getValue());
      }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

      xEntry.addListener(event -> {
         double x = event.getEntry().getDouble(0); 
         values[0] = x;
         System.out.println("X changed value: " + event.getEntry().getValue());
      }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
      
      try {
         Thread.sleep(1);
      } catch (InterruptedException ex) {
         System.out.println("Interrupted");
         Thread.currentThread().interrupt();
         return;
      }
   }
}