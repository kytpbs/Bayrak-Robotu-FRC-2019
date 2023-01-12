package frc.robot;
import static frc.robot.Constants.driveTrain;
import static frc.robot.Constants.stick;

public class Teleop {
    public void drive() {
        double z = stick.getZ();
        if (stick.getZ() != 1) {
            z = stick.getZ() * (0.7);
        }
          else if (stick.getZ() == -1) {
            stick.getZ();
        }
          else {
            z = stick.getZ();
        }
        driveTrain.arcadeDrive(stick.getY(), z);
    }
    public void button1() {

    }
    public void button2() {

    }
    public void button3() {

    }
    public void button4() {

    }
    public void button5() {
      
    }
}
