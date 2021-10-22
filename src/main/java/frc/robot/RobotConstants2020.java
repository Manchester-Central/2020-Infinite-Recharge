package frc.robot;

public class RobotConstants2020 {
    public final static String MAC_ADDRESS = "00-80-2F-17-E3-93";

    public final static int DRIVE_LEFT_SPARKMAX_A = 3; // CAN
    public final static int DRIVE_LEFT_SPARKMAX_B = 4; // CAN
    public final static int DRIVE_RIGHT_SPARKMAX_A = 1; // CAN
    public final static int DRIVE_RIGHT_SPARKMAX_B = 2; // CAN

    public final static int TURN_TABLE_SPARKMAX = 5; // CAN
    public final static int EJECTER_SPARKMAX = 6; // CAN

    public final static int CLIMB_SPARKMAX = 7; // CAN
    public final static int ARM_SPARKMAX = 8; // CAN

    public final static int TURRET_PAN = 9; // CAN
    public final static int TURRET_HOOD = 10; // CAN

    public final static int FLYWHEELA_SPARKMAX = 11; // CAN, left, closest to "Lime"
    public final static int FLYWHEELB_SPARKMAX = 12; // CAN, right, closest to "Light"

    public final static int UNJAMMER_SPARKMAX = 13;
    public final static int INTAKE_FALCON = 14;

    public final static int LIMIT_SWITCH = 1;
    public final static int CLIMB_POT = 0;

    public final static int MIN_PAN_RAW = 188; // 2021-03-13
    public final static int MAX_PAN_RAW = 502; // 2021-03-13
    public final static int PAN_ZERO_RAW = 338; // TODO: change
    public final static int PAN_BACKWARD_RAW = 30; // TODO: change!!!!!!!!!!!!!!!!
    public final static int PAN_SLOWDOWN_THRESHOLD = 45; // TODO: change!!!!!!!!!!!!!!!!
    public final static double PAN_MIN_POWER = 0.25; // TODO: change!!!!!!!!!!!!!!!!

    public final static int MIN_HOOD_RAW = 68; // 2021-03-13 - 64 hard stop
    public final static int MAX_HOOD_RAW = 164; // 2021-03-13 - 168 hard stop
    public final static int MIDDLE_HOOD_RAW = (MIN_HOOD_RAW + MAX_HOOD_RAW) / 2;

    public final static double EXTENDER_OUT = 0.5; // TODO: change!!
    public final static double EXTENDER_IN = 1; // TODO: change!!
    public final static double EXTENDER_ZERO = 0; // TODO: change!!
    public final static double INTAKE_POSITION = 0.616; // TODO: change!!
    public final static double CLIMB_POSITION = 1.86; // TODO: change!!
    public final static double CLEAR_OF_BALLS = 1.54; // TODO: get this value again

    public final static double CLIMBTAKE_INITIAL = 0; // TODO: change!

    public final static double PIVOT_THRESHOLD = 1.11; // Cale's other position: 1.00
    public final static double PIVOT_SLOW_SPEED = 0.1;
}