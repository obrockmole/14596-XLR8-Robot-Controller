# Buttons
Each button in the gamepad is stored as a ButtonHandler object. This stores all the data required for state tracking and manipulation, enabling `onPress()` and `onRelease()` functions instead of only getting a boolean for pressed or not. To update the state of the button, the `update()` function needs to be called, this is called automatically in the `update()` function of the gamepad.

## Commands
```java
//Run a runnable when a button is pressed down
Gamepad gamepad.onPress(Button button, Runnable runnable);

//Run a runnable when a button is released from being pressed down
Gamepad gamepad.onRelease(Button button, Runnable runnable);

//Run a runnable when the state of the button changes
Gamepad gamepad.onChange(Button button, Runnable runnable);

//Run a runnable if a button is pressed down
Gamepad gamepad.onDown(Button button, Runnable runnable);

//Run a runnable if a button is released
Gamepad gamepad.onUp(Button button, Runnable runnable);

//Check if a button is pressed down
Gamepad gamepad.isDown(Button button);

//Check if a button is released
Gamepad gamepad.isUp(Button button);
```

## Examples

### Extend lift when A is pressed

```java
gamepad.onPress(Button.A, () -> {
    lift.setPosition(100);
});
```


<seealso>
    <category ref="gp">
        <a href="Gamepad.md">Gamepad</a>
    </category>
    <category ref="sys">
        <a href="Motors.md">Motors</a>
    </category>
</seealso>