package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import static frc.robot.Constants.x_default_double;
import static frc.robot.Constants.y_default_double;


public class Event_Listener{
    double x_value = 0;
    double y_value = 0;
    double test_value = 0;
    public double[] run() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();

        NetworkTable datatable = inst.getTable("Vision");

        NetworkTableEntry xEntry = datatable.getEntry("target_x");
        NetworkTableEntry yEntry = datatable.getEntry("target_y");
        NetworkTableEntry test_Entry = datatable.getEntry("test");
        
        inst.startClientTeam(7035);

        
        datatable.addEntryListener("X", (table, key, entry, value, flags) -> {
        System.out.println("X changed value: " + value.getValue());
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        yEntry.addListener(event -> {
            System.out.println("Y changed value: " + event.getEntry().getValue());
            double[] y_value = event.getEntry().getDoubleArray(y_default_double);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        xEntry.addListener(event -> {
            System.out.println("X changed value: " + event.getEntry().getValue());
            double[] x_value = event.getEntry().getDoubleArray(x_default_double);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        test_Entry.addListener(event -> {
            System.out.println("X changed value: " + event.getEntry().getValue());
            double test_value = event.getEntry().getDouble(0);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        return new double[]{x_value, y_value, test_value};
    }
    public double test() {
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable datatable = inst.getTable("Vision");
    NetworkTableEntry test_Entry = datatable.getEntry("test");
    
    inst.startClientTeam(7035);
    double value = test_Entry.getDouble(0);
    return value;
    }
}