# Controller Configuration (WIP)

## Driver

| Control | Action | Status |
| --- | --- | --- |
| Left Analog Stick  | Tank Drive Left  | done |
| Right Analog Stick | Tank Drive Right | done |
| Up | |
| Down | |
| Left | |
| Right | |
| A | |
| B | |
| X | |
| Y | |
| LB | |
| LT | Operator Climb enable | done |
| RB | Slow drive speed | done |
| RT | Intake (while held) | done, logic not done in ClimbTake2020 |

## Operator

| Control | Action | Status |
| --- | --- | --- |
| Left Analog Stick  | Pan/Tilt (manual, heavily reduced to 5%) | done |
| Right Analog Stick |  |
| Up | Move arm up to climb position + extend climber arm | done, need values |
| Down | Move arm up to intake position | done, need values |
| Left | Move arm to climb position | done, need values |
| Right | Move arm to climb position + retract climber arm | done, need values |
| A | Forward direction intake | done, logic not done in ClimbTake2020 |
| B | Reverse direction intake | done, logic not done in ClimbTake2020 |
| X | Unjam | done, logic not done in Unjammer |
| Y | Long shot (pipeline change, while held, default back to other pipeline) | done |
| LB | Hold to toggle on auto turret | done, test if math is right |
| LT | Launcher for bumper shot (manual) | done, test |
| RB | Spin up shooter | done, test |
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

