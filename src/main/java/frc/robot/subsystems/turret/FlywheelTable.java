/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.Filesystem;

/**
 * Add your docs here.
 */
public class FlywheelTable {

    String row;
    BufferedReader csvReader;
    final String PATH = "flywheelTable.csv";
    String[] data;
    Path realPath = Filesystem.getDeployDirectory().toPath().resolve(PATH);

    // holds an ArrayList with a key (distance) as reference to [key]
    Map<Double, Integer[]> flyTable = new HashMap<Double, Integer[]>();

    // 2 size array that holds speed and angle
    Integer[] returnValues = new Integer[2]; 

    public FlywheelTable() {
        readCSV(PATH);
        printArrayTest();
    }

    public void readCSV(String path) { // change return type
        try {
            System.out.println(realPath.toString());
            csvReader = new BufferedReader (new FileReader(realPath.toString()));
        } catch (FileNotFoundException ie) {
            ie.printStackTrace();
        }

        try {
            while ((row = csvReader.readLine()) != null) {
                System.out.println(row);
                data = row.split(",");
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public void printArrayTest() {

        System.out.println(data.toString());

    }
}
