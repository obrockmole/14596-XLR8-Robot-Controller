# Sticks
Each stick can a normally to receive a -1 to 1 value in each direction, but they can also be use as buttons with the same syntax. This is all configured by default and requires no extra code from the user.

## Commands
<tabs>
    <tab title="As a Stick">
        <code-block lang="java">
            //Get the value of a stick in the X direction
            double gamepad.getStickX(Stick stick);
            <br/>
            //Get the value of a stick in the Y direction
            double gamepad.getStickY(Stick stick);
        </code-block>
    </tab>
    <tab title="As a Button">
        <code-block lang="java">
            //Run a runnable when a button is pressed down
            Gamepad gamepad.onPress(Button stick, Runnable runnable);
            <br/>
            //Run a runnable when a button is released from being pressed down
            Gamepad gamepad.onRelease(Button stick, Runnable runnable);
            <br/>
            //Run a runnable when the state of the button changes
            Gamepad gamepad.onChange(Button stick, Runnable runnable);
            <br/>
            //Run a runnable if a button is pressed down
            Gamepad gamepad.onDown(Button stick, Runnable runnable);
            <br/>
            //Run a runnable if a button is released
            Gamepad gamepad.onUp(Button stick, Runnable runnable);
            <br/>
            //Check if a button is pressed down
            Gamepad gamepad.isDown(Button stick);
            <br/>
            //Check if a button is released
            Gamepad gamepad.isUp(Button stick);
        </code-block>
    </tab>
</tabs>

## Examples
### Move a servo when the right stick is pressed

```java
gamepad.onPress(Stick.RIGHT_STICK, () -> {
    servo.setPosition(0.5);
});
```

### Set power to a motor based on the left stick Y

```java
motor.setPower(gamepad.getStickY(Stick.LEFT_STICK));
```

<seealso>
    <category ref="gp">
        <a href="Gamepad.md">Gamepad</a>
    </category>
    <category ref="sys">
        <a href="Motors.md">Motors</a>
    </category>
</seealso>