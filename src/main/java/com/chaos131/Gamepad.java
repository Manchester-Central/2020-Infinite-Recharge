package com.chaos131;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Gamepad provides an interface to the controls expected on an XBox/PlayStation
 * style controller
 */
public abstract class Gamepad extends GenericHID {

    public Gamepad(int port) {
        super(port);
    }

    public Button aButton;
    public Button bButton;
    public Button xButton;
    public Button yButton;

    public Button startButton;
    public Button selectButton;

    public Button leftBumper;
    public Button rightBumper;
    public Button leftTrigger;
    public Button rightTrigger;

    public Button leftJoystick;
    public Button rightJoystick;

    public Button dPadUp;
    public Button dPadRight;
    public Button dPadDown;
    public Button dPadLeft;

    public double getLeftX() {
        return getRawAxis(0);
    }

    public double getLeftY() {
        return -getRawAxis(1);
    }

    public double getRightX() {
        return getRawAxis(2);
    }

    public double getRightY() {
        return -getRawAxis(3);
    }

    private double calculateAngle(double x, double y) {
        return -Math.toDegrees(Math.atan2(-x, y));
    }

    public double getRightJoystickAngle() {
        return calculateAngle(getRightX(), getRightY());
    }

    public double getLeftJoystickAngle() {
        return calculateAngle(getLeftX(), getLeftY());
    }

    public double getX(GenericHID.Hand hand) {
        if (hand == Hand.kRight) {
            return getRightX();
        } else {
            return getLeftX();
        }
    }

    public double getY(GenericHID.Hand hand) {
        if (hand == Hand.kRight) {
            return getRightY();
        } else {
            return getLeftY();
        }
    }
}
