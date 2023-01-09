package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Event_Listener2{
    double x_value = 0;
    double y_value = 0;
    public double[] run() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();

        NetworkTable datatable = inst.getTable("Vision");

        NetworkTableEntry xEntry = datatable.getEntry("target_x");
        NetworkTableEntry yEntry = datatable.getEntry("target_y");
        
        inst.startClientTeam(7035);

        
        datatable.addEntryListener("X", (table, key, entry, value, flags) -> {
        System.out.println("X changed value: " + value.getValue());
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        yEntry.addListener(event -> {
            System.out.println("Y changed value: " + event.getEntry().getValue());
            double y_value = event.getEntry().getDouble(0);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        xEntry.addListener(event -> {
            System.out.println("X changed value: " + event.getEntry().getValue());
            double x_value = event.getEntry().getDouble(0);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        return new double[]{x_value, y_value};
    }
}