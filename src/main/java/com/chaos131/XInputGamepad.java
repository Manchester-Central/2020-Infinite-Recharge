package com.chaos131;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class XInputGamepad extends Gamepad {

    public XInputGamepad(int port) {
        super(port);
        xButton = new JoystickButton(this, LEFT_X);
        aButton = new JoystickButton(this, DOWN_A);
        bButton = new JoystickButton(this, RIGHT_B);
        yButton = new JoystickButton(this, UP_Y);
        startButton = new JoystickButton(this, START);
        selectButton = new JoystickButton(this, SELECT);
        leftBumper = new JoystickButton(this, LEFT_BUMPER);
        rightBumper = new JoystickButton(this, RIGHT_BUMPER);
        leftTrigger = new Button(() -> getRawAxis(LEFT_TRIGGER_AXIS) > TRIGGER_THRESHOLD);
        rightTrigger = new Button(() -> getRawAxis(RIGHT_TRIGGER_AXIS) > TRIGGER_THRESHOLD);
        leftJoystick = new JoystickButton(this, LEFT_JOYSTICK);
        rightJoystick = new JoystickButton(this, RIGHT_JOYSTICK);
        dPadUp = new POVButton(this, DPAD_UP);
        dPadRight = new POVButton(this, DPAD_RIGHT);
        dPadDown = new POVButton(this, DPAD_DOWN);
        dPadLeft = new POVButton(this, DPAD_LEFT);
    }

    private static final int LEFT_X = 3;
    private static final int DOWN_A = 1;
    private static final int RIGHT_B = 2;
    private static final int UP_Y = 4;

    private static final int LEFT_BUMPER = 5;
    private static final int RIGHT_BUMPER = 6;
    private static final int LEFT_TRIGGER_AXIS = 4;
    private static final int RIGHT_TRIGGER_AXIS = 5;

    private static final int SELECT = 7;
    private static final int START = 8;
    private static final int LEFT_JOYSTICK = 9;
    private static final int RIGHT_JOYSTICK = 10;

    private static final int DPAD_UP = 0;
    private static final int DPAD_RIGHT = 90;
    private static final int DPAD_DOWN = 180;
    private static final int DPAD_LEFT = 270;

    private static final double TRIGGER_THRESHOLD = -0.5;
}
