# Controller Configuration (WIP)

## Driver

| Control | Action | Status |
| --- | --- | --- |
| Left Analog Stick  | Tank Drive Left  | done |
| Right Analog Stick | Tank Drive Right | done |
| Up | |
| Down | |
<!-- | Left | Move on climbarm left |
| Right | Move on climbarm right | -->
| A | |
| B | |
| X | |
| Y | |
| LB | |
| LT | Operator Climb enable | 
| RB | Slow drive speed | done |
| RT | Intake (while held) | done |

## Operator

| Control | Action |
| --- | --- | 
| Left Analog Stick  | Pan/Tilt (manual, heavily reduced to 5%) |
| Right Analog Stick |  |
| Up | Move arm up to climb position + extend climber arm |
| Down | Move arm up to intake position |
| Left | Move arm to climb position |
| Right | Move arm to climb position + retract climber arm |
| A | Forward direction intake |
| B | Reverse direction intake |
| X | Unjam |
| Y | Long shot (pipeline change, while held, default back to other pipeline) | 
| LB | Hold to toggle on auto turret |
| LT | Launcher for bumper shot (manual) |
| RB | Spin up shooter |
| RT | (if spinner at speed, go thru launcher seq) Shoot while held |
| Select |  |
| Start |  |

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

