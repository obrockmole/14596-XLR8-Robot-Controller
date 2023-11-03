package org.firstinspires.ftc.teamcode.Systems.AutoMenu;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.GamepadButtons.Button;

import java.util.HashMap;

public class MenuItem {
    private String name;
    private HashMap<String, Button> options;
    private int selectedOption;

    public MenuItem(String name, HashMap<String, Button> options) {
        this.name = name;
        this.options = options;
        this.selectedOption = 0;
    }

    public String toString() {
        String string = "";
        for (String optionName : options.keySet()) {
            string += options.get(optionName) + ": " + optionName + "\n";
        }

        return string;
    }
}
