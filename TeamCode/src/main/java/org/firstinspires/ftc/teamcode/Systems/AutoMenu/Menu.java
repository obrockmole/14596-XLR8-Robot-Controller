package org.firstinspires.ftc.teamcode.Systems.AutoMenu;

import org.firstinspires.ftc.teamcode.Systems.Gamepad.Gamepad;

/*
menu = new Menu(new MenuItem[] {
        new MenuItem("Park Location", new HashMap<String, Button>() {{
            put("Left", Button.DPAD_LEFT);
            put("Middle", Button.DPAD_DOWN);
            put("Right", Button.DPAD_RIGHT);
        }})
});
 */

//TODO: do it
public class Menu {
    private MenuItem[] menuItems;
    private int totalPages, currentPage = 1;

    public Menu(Gamepad gamepad, MenuItem[] menuItems) {
        this.menuItems = menuItems;
        this.totalPages = menuItems.length;
    }


}