package frc.robot;

public class RobotConstants2020 {
    public final static String MAC_ADDRESS = "00-80-2F-17-E3-93";

    public final static int DRIVE_LEFT_SPARKMAX_A = 1; // CAN
    public final static int DRIVE_LEFT_SPARKMAX_B = 2; // CAN
    public final static int DRIVE_RIGHT_SPARKMAX_A = 3; // CAN
    public final static int DRIVE_RIGHT_SPARKMAX_B = 4; // CAN

    public final static int TURN_TABLE_SPARKMAX = 5; // CAN
    public final static int EJECTER_SPARKMAX = 6; // CAN

    public final static int CLIMB_SPARKMAX = 7; // CAN
    public final static int ARM_SPARKMAX = 8; // CAN

    public final static int TURRET_PAN = 9; // CAN
    public final static int TURRET_HOOD = 10; // CAN
    
    public final static int FLYWHEELA_SPARKMAX = 11; // CAN, left, closest to "Lime"
    public final static int FLYWHEELB_SPARKMAX = 12; // CAN, right, closest to "Light"

    public final static int UNJAMMER_SPARKMAX = 13;

    public final static int LIMIT_SWITCH = 14; // TODO: find this value!

    // public final static int INTAKE_FALCON = ?;

    public final static double ANGLE_POT_SLOPE_X = 1; // TODO: change!!
    public final static double ANGLE_POT_INTERCEPT_X = 1; // TODO: change!!

    public final static double MAX_ANGLE_PAN = 1; // TODO: change
    public final static double MIN_ANGLE_PAN = 1; // TODO: change
    public final static int MIN_PAN_RAW = 10; // 136
    public final static int MAX_PAN_RAW = 681; // 329
    public final static int PAN_ZERO_RAW = 227; // TODO: change
    public final static int PAN_BACKWARD_RAW = 0; // TODO: change

    public final static int MIN_HOOD_RAW = 68; // 68
    public final static int MAX_HOOD_RAW = 169; // 169

    public final static double EXTENDER_OUT = 0.5; // TODO: change!! 
    public final static double EXTENDER_IN = 1; // TODO: change!!    
    public final static double EXTENDER_ZERO = 0; // TODO: change!!    
    public final static double INTAKE_POSITION = 0; // TODO: change!!    
    public final static double CLIMB_POSITION = 1; // TODO: change!!    


}