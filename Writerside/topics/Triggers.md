# Triggers
Each trigger in the gamepad is stored as a TriggerHandler object. This allows them to act normally to receive a 0-1 value, but they can also be use as buttons with the same syntax. This is all configured by default and requires no extra code from the user. To update the state of the trigger, the `update()` function needs to be called, this is called automatically in the `update()` function of the gamepad.

## Commands
<tabs>
    <tab title="As a Trigger">
        <code-block lang="java">
            //Get the value of a trigger
            double gamepad.getValue(Trigger trigger);
        </code-block>
    </tab>
    <tab title="As a Button">
        <code-block lang="java">
            //Run a runnable when a trigger is pressed down
            Gamepad gamepad.onPress(Trigger trigger, Runnable runnable);
            <br/>
            //Run a runnable when a trigger is released from being pressed down
            Gamepad gamepad.onRelease(Trigger trigger, Runnable runnable);
            <br/>
            //Run a runnable when the state of the trigger changes
            Gamepad gamepad.onChange(Trigger trigger, Runnable runnable);
            <br/>
            //Run a runnable if a trigger is pressed down
            Gamepad gamepad.onDown(Trigger trigger, Runnable runnable);
            <br/>
            //Run a runnable if a trigger is released
            Gamepad gamepad.onUp(Trigger trigger, Runnable runnable);
            <br/>
            //Check if a trigger is pressed down
            boolean gamepad.isDown(Trigger trigger);
            <br/>
            //Check if a trigger is released
            boolean gamepad.isUp(Trigger trigger);
            <br/>
            //Get the limit of a trigger where is registers as a button press
            double gamepad.getTrigger(Trigger trigger);
            <br/>
            //Set the limit of a trigger where is registers as a button press
            TriggerHandler trigger.setLimit(Trigger trigger, double limit); //Can run gamepad.getGamepadTrigger(Trigger trigger) to get target trigger's TriggerHandler object before calling this method
        </code-block>
    </tab>
</tabs>

## Examples
### Extend lift when the right trigger is pressed

```java
gamepad.onPress(Trigger.RIGHT_TRIGGER, () -> {
    lift.setPosition(100);
});
```

### Set power to a motor based on the left trigger

```java
motor.setPower(gamepad.getValue(Trigger.LEFT_TRIGGER));
```

<seealso>
    <category ref="gp">
        <a href="Gamepad.md">Gamepad</a>
    </category>
    <category ref="sys">
        <a href="Motors.md">Motors</a>
    </category>
</seealso>