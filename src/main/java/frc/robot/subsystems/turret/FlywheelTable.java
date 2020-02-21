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
import java.util.Arrays;

import edu.wpi.first.wpilibj.Filesystem;

/**
 * Add your docs here.
 */
public class FlywheelTable {

    String row;
    BufferedReader csvReader;
    final String PATH = "flywheelTable.csv";
    String[] data;
    double[] doubleData;
    Path realPath = Filesystem.getDeployDirectory().toPath().resolve(PATH);

    // holds an ArrayList with a key (distance) as reference to [key]
    Map<Double, Double[]> flyTable = new HashMap<Double, Double[]>();

    public FlywheelTable() {
        readCSV(PATH);
        printArrayTest();
    }

    // parses data from .csv into doubles for addData()
    public void readCSV(String path) { // change return type
        try {
            // System.out.println(realPath.toString());
            csvReader = new BufferedReader (new FileReader(realPath.toString()));
        } catch (FileNotFoundException ie) {
            ie.printStackTrace();
        }

        try {
            csvReader.readLine(); // skips 1st line
            while ((row = csvReader.readLine()) != null) {
                // System.out.println(row);
                data = row.split(",");

                if (data.length == 3) {

                    // converts String array to double array
                    doubleData = Arrays.stream(data).mapToDouble(Double::parseDouble).toArray();

                    double distance = doubleData[0];
                    double speed = doubleData[1];
                    double angle = doubleData[2];

                    addData(distance, speed, angle);

                } else {
                    System.out.println("ERROR: Flywheel Table data less than 3 values at " + row);
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    // takes raw data, adds to data structure
    public void addData(double distance, double speed, double angle) {

        System.out.println(distance + " " + speed + " " + angle);
            
        // 2 size array that holds speed and angle
        Double[] returnValues = {speed, angle};
        flyTable.put(distance, returnValues);

    }
    
    public double getDistance() {
        return 0;
    }

    public double getSpeed() {
        return 0;
    }

    public double getAngle() {
        return 0;
    }

    public void printArrayTest() {

        System.out.println(data.toString());

    }
}
