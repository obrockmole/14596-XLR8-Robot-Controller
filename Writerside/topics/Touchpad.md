# Touchpad
Each finger detector for the touchpad in the gamepad is stored as a ButtonHandler object. The touchpad is able to tack two fingers at a time, and they are defined as `TouchpadFinger.FINGER_1` and `TouchpadFinger.FINGER_2` respectively. This stores all the data required for state tracking and manipulation, enabling `onPress()` and `onRelease()` functions instead of only getting a boolean for pressed or not. It is still able to track positions of the two fingers additionally. To update the state of the button, the `update()` function needs to be called, this is called automatically in the `update()` function of the gamepad.

## Commands
<tabs>
    <tab title="Location Tracking">
        <code-block lang="java">
            //Get the position of a finger
            Vector2D gamepad.getFingerPosition(TouchpadFinger finger);
            <br/>
            //Get the position delta of a finger
            Vector2D gamepad.getFingerPositionDelta(TouchpadFinger finger);
            <br/>
            //Get the X position of a finger
            double gamepad.getFingerX(TouchpadFinger finger);
            <br/>
            //Get the X position delta of a finger
            double gamepad.getFingerXDelta(TouchpadFinger finger);
            <br/>
            //Get the Y position of a finger
            double gamepad.getFingerY(TouchpadFinger finger);
            <br/>
            //Get the Y position delta of a finger
            double gamepad.getFingerYDelta(TouchpadFinger finger);
        </code-block>
    </tab>
    <tab title="As a Button">
        <code-block lang="java">
            //Run a runnable when a finger is pressed down
            Gamepad gamepad.onPress(TouchpadFinger finger, Runnable runnable);
            <br/>
            //Run a runnable when a finger is released from being pressed down
            Gamepad gamepad.onRelease(TouchpadFinger finger, Runnable runnable);
            <br/>
            //Run a runnable when the state of the finger changes
            Gamepad gamepad.onChange(TouchpadFinger finger, Runnable runnable);
            <br/>
            //Run a runnable if a finger is pressed down
            Gamepad gamepad.onDown(TouchpadFinger finger, Runnable runnable);
            <br/>
            //Run a runnable if a finger is released
            Gamepad gamepad.onUp(TouchpadFinger finger, Runnable runnable);
            <br/>
            //Check if a finger is pressed down
            Gamepad gamepad.isDown(TouchpadFinger finger);
            <br/>
            //Check if a finger is released
            Gamepad gamepad.isUp(TouchpadFinger finger);
        </code-block>
    </tab>
</tabs>

<seealso>
    <category ref="gp">
        <a href="Gamepad.md">Gamepad</a>
    </category>
    <category ref="sys">
        <a href="Motors.md">Motors</a>
    </category>
</seealso>