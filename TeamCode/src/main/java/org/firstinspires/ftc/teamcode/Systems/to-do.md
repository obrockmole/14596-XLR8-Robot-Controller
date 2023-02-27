# Systems
### Implemented;
|Implemented|To Do  |
|--|--|
|  |SetPowerMotor() |
|  |SetPositionMotor() |
|  |PIDFMotor() |
|  |SetPowerServo() |
|  |SetPositionServo() |
|  |() |
|  |() |
|  |() |

## How it works
In CAD there are multiple different components are used in a full robot assembly.
```mermaid
graph LR
A(Drivetrain) --> D{{Robot Assembly}}
B(Intake) --> D
C(Lift) --> D

style A fill:#fff,stroke:#333,stroke-width:2px
style B fill:#fff,stroke:#333,stroke-width:2px
style C fill:#fff,stroke:#333,stroke-width:2px
style D fill:#d4d4d4,stroke:#333,stroke-width:3px
```
We can achieve this same outcome in code by using separate classes for each commonly used subsystem of the robot.
```mermaid
graph LR
A(Gamepad Joystick) --> B(Set Power Motor) --> C(Drivetrain Movement)
D(Gamepad Button) --> E(Set Position Motor) --> F(Lift Height)
G{TeleOp} --> A
G --> D

style A fill:#fff,stroke:#333,stroke-width:2px
style B fill:#fff,stroke:#333,stroke-width:2px
style C fill:#fff,stroke:#333,stroke-width:2px
style D fill:#fff,stroke:#333,stroke-width:2px
style E fill:#fff,stroke:#333,stroke-width:2px
style F fill:#fff,stroke:#333,stroke-width:2px
style G fill:#d4d4d4,stroke:#333,stroke-width:3px
```
