/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import java.util.HashMap;
import java.util.Map;

/**
 * Takes Value format commandName?arg1&arg2... and separates it. :)
 */
public class ParseCommand {

    String commandName;
    // List that contains key and value as 2 strings (like an array)
    // think of it as an array, but indexes are names, not numbers
    Map<String, String> arguments = new HashMap<String, String>();

    public ParseCommand(String commandString) {

        // split separates commandName from arguments
        String[] splitStrings = commandString.split("\\?");

        // assigns commandName to left of split (array 0)
        commandName = splitStrings[0];
        System.out.println("CommandName: " + commandName);

        // if no arguments, stop
        if (splitStrings.length < 2) {
            return;
        }

        // stores right side of split for further use
        String rightSide = splitStrings[1];

        // for each loop: for every & symbol in string, split into new chunk and assign
        // to array
        for (String argString : rightSide.split("&")) {
            // for every "=" symbol in the & chunk, split into new chunk and assign to array
            // splitArgs
            String[] splitArgs = argString.split("=");
            // adds the arguments to map list arguments above
            arguments.put(splitArgs[0], splitArgs[1]);

            // Checking to see if code works
            System.out.println("Arguments: " + splitArgs[0]);
            System.out.println(arguments.get(splitArgs[0]));
        }

    }

    /***
     * returns arguments assigned from for each loop, found in map arguments
     *
     * @param key
     * @return arguments in specific key
     */
    public String getArgument(String key) {

        return arguments.get(key);

    }
}
