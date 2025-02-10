
# XLR8 14596 CENTER STAGE Robot Controller

#### This repository contains the public FTC SDK v8.2 for the CENTER STAGE (2023-2024) competition season.


- ### Systems
    - [Geometry](#geometry)
        - Pose2d
        - Vector2d
    - [Gamepad](#gamepad)
        - Gamepad
    - [Motors](#motors)
        - Motor List
        - Motor
        - Motor Group
        - Velocity Motor
        - Velocity Motor Group
    - [Servos](#servos)
        - Continuous Servo
        - Continuous Servo Group
        - Position Servo
        - Position Servo Group
    - [Sensors](#sensors)
        - Advanced Distance Sensor
        - Battery Voltage Sensor
        - Color Sensor
        - Distance Sensor
        - Encoder
        - IMU
        - Limit Switch
        - Potentiometer
        - Touch Sensor
    - [Data Filters](#data-filters)
        - Kalman Filter
        - Moving Average Filter
        - Multi Variable Kalman Filter
    - [Odometry](#odometry)
        - Odometry
        - Three Wheel Localizer
    - [Movement](#movement)
        - Movement
        - Blank Movement
        - Wait Movement
        - Odometry Drive
        - Movement Sequence
    - [Events](#events)
        - Event
        - Blank Event
        - Timed Event
    - [Vision](#vision)
        - AprilTag Detection Pipeline
        - Cascade Detection Pipeline
        - Contour Detection Pipeline
        - HuskyLens
        - Vision Detector
    - [Miscellaneous](#miscellaneous)
      - Blinkin LED Driver
      - Data Logger
      - Stopwatch
      - Time


# Geometry
## Pose2d
A Pose2d is used to represent the position and orientation of an object in 2d space. It is composed of a double representing the x and y position of the object, and a double representing the orientation of the object in radians.
```java
Pose2d pose = new Pose2d(0, 0, 0);
pose.toString(); // (0.0, 0.0, 0.0)
```

## Vector2d
A Vector2d is used to represent a vector in 2d space. It is composed of a double representing the x and y components of the vector.
```java
Vector2d vector = new Vector2d(0, 0);
vector.toString(); // (0.0, 0.0)
```


# Gamepad
## Gamepad
> Note: see Gamepad_Sample.java for full example code.
>
> For example LED code see GamepadLED_Sample.java
>
> For example rumble code see GamepadRumble_Sample.java
>
> For example touchpad code see GamepadTouchpad_Sample.java


# Motors
## Motor List
The MotorList enum contains a list of every GoBilda and REV motor that is available for use, it contains all statistics for each motor including free RPM, gear ratio, and encoder resolution.

## Motor
> Note: see Motor_Sample.java for full example code.

The Motor class is a wrapper for the DcMotorEx class providing additional functionality and ease of use. Additional features include, speed scaling, direction inversion, and a more accurate PIDF for precise position control.
```java
// Creates a new motor of name "motor" which is a GoBilda 435 motor, and forward direction
Motor motor = new Motor(hardwareMap, "motor", MotorList.GOBILDA_435, false);
```

## Motor Group
> Note: see MotorGroup_Sample.java for full example code.

The MotorGroup class is a way to control multiple motors as a single unit. This is useful for controlling multiple motors that are mechanically linked together, such as the left and right motors of a vertical lift or drive train. Each motor can be set to a different direction, but power and position will be controlled as a single group.
```java
// Creates a new motor group of name "motorGroup" which contains two GoBilda 435 motors going different directions
MotorGroup motorGroup = new MotorGroup(
    new Motor(hardwareMap, "motor1", MotorList.GOBILDA_435, false),
    new Motor(hardwareMap, "motor2", MotorList.GOBILDA_435, true)
);
```

## Velocity Motor
> Note: see VelocityMotor_Sample.java for full example code.

The VelocityMotor class is a wrapper for the DcMotorEx class with additional functionality for velocity control. This class provides a more accurate PIDF for precise velocity control, and allows for easy setting of velocity.
```java
// Creates a new velocity motor of name "velocityMotor" which is moving in the forward direction
VelocityMotor velocityMotor = new VelocityMotor(hardwareMap, "velocityMotor", false);
```

## Velocity Motor Group
> Note: see VelocityMotorGroup_Sample.java for full example code.

The VelocityMotorGroup class is a way to control multiple motors as a single unit with velocity control. This is useful for controlling multiple motors that are mechanically linked together, such as the left and right motors of a drive train. Each motor can be set to a different direction, but velocity will be controlled as a single group.
```java
// Creates a new velocity motor group of name "velocityMotorGroup" which contains two motors going different directions
VelocityMotorGroup velocityMotorGroup = new VelocityMotorGroup(
    new VelocityMotor(hardwareMap, "velocityMotor1", false),
    new VelocityMotor(hardwareMap, "velocityMotor2", true)
);
```


# Servos
## Continuous Servo
> Note: see ServoContinuous_Sample.java for full example code.

The ContinuousServo class is a wrapper for the CRServo class providing additional functionality and ease of use.
```java
// Creates a new continuous servo of name "servo" which is moving in the forward direction
ContinuousServo servo = new ContinuousServo(hardwareMap, "servo", false);
```

## Continuous Servo Group
> Note: see ServoGroupContinuous_Sample.java for full example code.

The ContinuousServoGroup class is a way to control multiple continuous servos as a single unit. This is useful for controlling multiple servos that are mechanically linked together, such as the left and right servos of a lift or intake. Each servo can be set to a different direction, but power will be controlled as a single group.
```java
// Creates a new continuous servo group of name "servoGroup" which contains two servos going different directions
ContinuousServoGroup servoGroup = new ContinuousServoGroup(
    new ContinuousServo(hardwareMap, "servo1", false),
    new ContinuousServo(hardwareMap, "servo2", true)
);
```

## Position Servo
> Note: see ServoPosition_Sample.java for full example code.

The PositionServo class is a wrapper for the Servo class providing additional functionality and ease of use. Additional features include, position range scaling, direction inversion, and target position toggling.
```java
// Creates a new position servo of name "servo" which is moving in the forward direction
PositionServo servo = new PositionServo(hardwareMap, "servo", 0, 1, false);
```

## Position Servo Group
> Note: see ServoGroupPosition_Sample.java for full example code.

The PositionServoGroup class is a way to control multiple position servos as a single unit. This is useful for controlling multiple servos that are mechanically linked together, such as the left and right servos of a lift or intake. Each servo can be set to a different direction or range scaling, but position will be controlled as a single group.
```java
// Creates a new position servo group of name "servoGroup" which contains two servos going different directions
PositionServoGroup servoGroup = new PositionServoGroup(
    new PositionServo(hardwareMap, "servo1", 0, 1, false),
    new PositionServo(hardwareMap, "servo2", 0, 1, true)
);
```

# Sensors
## Advanced Distance Sensor
> Note: see SensorAdvancedDistance_Sample.java for full example code.

## Battery Voltage Sensor

## Color Sensor
> Note: see SensorColor_Sample.java for full example code.

## Distance Sensor
> Note: see SensorDistance_Sample.java for full example code.

## Encoder

## IMU

## Limit Switch
> Note: see SensorLimitSwitch_Sample.java for full example code.

## Potentiometer
> Note: see SensorPotentiometer_Sample.java for full example code.

## Touch Sensor
> Note: see SensorTouch_Sample.java for full example code.


# Data Filters
## Kalman Filter
> Note: see KalmanFilter_Sample.java for full example code.

## Moving Average Filter
> Note: see MovingAverageFilter_Sample.java for full example code.

## Multi Variable Kalman Filter
> Note: see MultiVariableKalmanFilter_Sample.java for full example code.


# Odometry
## Odometry

## Three Wheel Localizer


# Movement
> Note: see MovementSequence_Sample.java for full example code.
## Movement

## Blank Movement

## Wait Movement

## Odometry Drive

## Movement Sequence


# Events
## Event

## Blank Event

## Timed Event


# Vision
## Vision Detector
> Note: see a following sections for example code and implementation.

The VisionDetector is what can control a camera and process the image to detect objects. It can be used to detect objects using a variety of different pipelines, such as AprilTag detection, cascade detection, or contour detection. All piplines use the same VisionDetector class, but the pipeline used is set through the constructor.

## AprilTag Detection Pipeline
> Note: see VisionAprilTagDetection_Sample.java for example code.

## Cascade Detection Pipeline
> Note: see VisionCascadeDetection_Sample.java for example code.

## Contour Detection Pipeline
> Note: see VisionContourDetection_Sample.java for example code.

## HuskyLens
> Note: see VisionHuskyLensObject_Sample.java for example object detection code.
> 
> Note: see VisionHuskyLensTag_Sample.java for example AprilTag detection code.


# Miscellaneous
## Blinkin LED Driver
> Note: see BlinkinLEDDriver_Sample.java for full example code.

The BlinklinLEDDriver class provides a simple way to control the color or pattern of an LED light strip using the REV Robotics Blinkin LED Driver. The Blinkin LED Driver can be used to display a variety of colors and patterns, such as a solid color, a rainbow pattern, or a strobe effect.

```java
BlinkinLEDDriver blinkin = new BlinkinLEDDriver(hardwareMap, "blinkin");
blinkin.setPattern(Pattern.RAINBOW);
```


## Data Logger
> Note: see DataLogging_Sample.java for example code.

Data logging is a way to record information from sensors on the robot during operation. This data is saved to a file on the Control Hub, and can be used to analyze the robot's performance and make improvements.

Please note that the DataLogger class does not function all the time, and its use is not recommended to rely on.

## Stopwatch
> Note: see Stopwatch_Sample.java for full example code.

The stopwatch class is used to measure the time elapsed between two points in time in both seconds and milliseconds.
```java
Stopwatch stopwatch = new Stopwatch();
stopwatch.start();
stopwatch.getTime(); // Time in milliseconds
```

## Timer
> Note: see Timer_Sample.java for full example code.

The timer class is used to execute a Runnable object after a specified amount of time has elapsed in milliseconds;
```java
Timer timer = new Timer(1000, () -> System.out.println("Done!");
timer.start();
timer.getTimeLeft(); // Time in milliseconds
```

## Related

- [FTC Robot Controller](https://github.com/FIRST-Tech-Challenge/FtcRobotController)
- [Road Runner](https://github.com/acmerobotics/road-runner-quickstart)
- [FTCLib](https://github.com/FTCLib/FTCLib)

