# Controller Configuration (WIP)

## Driver

| Control | Action |
| --- | --- |
| Left Analog Stick  | Tank Drive Left  |
| Right Analog Stick | Tank Drive Right |
| Up | |
| Down | |
| Left | Move on climbarm left |
| Right | Move on climbarm right |
| A | Serializer slow (testing) |
| B | |
| X | |
| Y | Serializer fast w/ ejector(testing)|
| LB | NavX mode (right joystick turn, left joystick straight forward/backward) |
| LT | |
| RB | Slow drive speed |
| RT | Fast drive speed |

## Operator

| Control | Action |
| --- | --- | 
| Left Analog Stick  | Manual turret (testing) |
| Right Analog Stick | Manual flywheel (testing) |
| Up | Move arm up to climb position + extend climber arm |
| Down | Move arm up to climb position + retract climber arm |
| Left | Move arm to intake position |
| Right | Move arm to colorwheel position?!?!?!?  |
| A | Forward direction intake |
| B | Reverse direction intake |
| X | Activates climber mode for testing(gives right analog stick direct motor control) |
| Y | Move turret to 2pt position (manual) | 
| LB | Hold to toggle on auto turret |
| LT | Shoot once (override aim) |
| RB | Shoot once (needs aim) |
| RT | Shoot while held |
| Select | Colorwheel: turn specific amt. of times |
| Start | Colorwheel: align to color |

# Driver
Drive
    - fast/slow
NavX turn 
    - align climb
Move on climb arm

# Operator
.Shoot
Align
    - Turret
        *Do not want to aim at own goal
.Control intake (reverse or forward direction)
    - .move arm (at start, when climbing)
.Climb
Spin colorwheel??????

Combos
    - align and shoot
    - shoot all balls vs 1 ball

Manual Override
- d

# Dashboard
Ball count
Alignment
Can robot see target at all?
    .hasTarget();
Camera feeds
    at least Human Vision
Gyro angle relative to field
??? reset NavX ???
Auto Command
Limelight Pipeline (off in corner, maybe to troubleshoot)
    - button that changes Pipeline

