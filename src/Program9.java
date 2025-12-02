//********************************************************************
//
//  Author:       Afaq Ahmad
//
//  Program #:    9
//
//  File Name:    Program9.java
//
//  Course:       ITSE 2321 Object-Oriented Programming
//
//  Due Date:     12-01-2025
//
//  Instructor:   Prof. Fred Kumi 
//
//  Chapter:       Chapter 1 - 9, 11, and 15: ArrayList, Objects, Classes, Methods, Exception, and Files
//
//  Description:  This program reads household survey data from a file,
//                stores it in an ArrayList, and performs various analyses
//                including calculating average income, identifying households
//                below poverty level, and determining Medicaid eligibility.
//
//********************************************************************

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program9 {
    //***************************************************************
    //
    //  Method:       main
    // 
    //  Description:  The main method of the program
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A 
    //
    //**************************************************************

    private ArrayList<HouseHold> households;
    private PrintWriter output;

    public Program9() {
        households = new ArrayList<>();
    }

    public static void main(String[] args) {

        Program9 program = new Program9();

        program.run();

    } // End of the main method

    //***************************************************************
    //
    //  Method:       developerInfo
    // 
    //  Description:  The developer information method of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo() {
        output.println("Name:     Afaq Ahmad");
        output.println("Course:   ITSE 2321 Object-Oriented Programming");
        output.println("Program:  Nine");
        output.println("Due Date: 12-01-2025\n");
    } // End of the developerInfo method

    public void run() {
        try {
            openOutputFile();
            developerInfo();
            readInputFile();
            printAllHouseholds();
            calculateAndPrintAverageIncome();
            printHouseholdsAboveAverage();
            printHouseholdsBelowPoverty();
            printPercentageBelowPoverty();
            printMedicaidEligibility();
            closeOutputFile();
            System.out.println("Program completed successfully. Check Program9-Output.txt");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void openOutputFile() throws IOException {
        output = new PrintWriter(new FileWriter("Program9-Output.txt"));
    }

    public void closeOutputFile() {
        if (output != null) {
            output.close();
        }
    }

    public void readInputFile() throws IOException {
        File file = new File("Program9.txt");
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            int id = input.nextInt();
            double income = input.nextDouble();
            int members = input.nextInt();
            String state = input.nextLine().trim();

            HouseHold household = new HouseHold(id, income, members, state);
            households.add(household);
        }
        input.close();
    }

    public void printAllHouseholds() {
        output.println("===================================================================");
        output.println("ALL HOUSEHOLD RECORDS");
        output.println("===================================================================");
        output.printf("%-10s %-15s %-10s %-15s%n", "ID", "Income", "Members", "State");
        output.println("-------------------------------------------------------------------");

        for (HouseHold household : households) {
            output.println(household);
        }
        output.println();
    }

    public void calculateAndPrintAverageIncome() {
        double totalIncome = 0.0;

        for (HouseHold household : households) {
            totalIncome += household.getIncome();
        }

        double average = totalIncome / households.size();

        output.println("===================================================================");
        output.println("AVERAGE HOUSEHOLD INCOME");
        output.println("===================================================================");
        output.printf("Average Income: $%.2f%n", average);
        output.println();
    }

    public double getAverageIncome() {
        double totalIncome = 0.0;

        for (HouseHold household : households) {
            totalIncome += household.getIncome();
        }

        return totalIncome / households.size();
    }

    public void printHouseholdsAboveAverage() {
        double average = getAverageIncome();

        output.println("===================================================================");
        output.println("HOUSEHOLDS WITH INCOME ABOVE AVERAGE");
        output.println("===================================================================");
        output.printf("%-10s %-15s %-10s %-15s%n", "ID", "Income", "Members", "State");
        output.println("-------------------------------------------------------------------");

        for (HouseHold household : households) {
            if (household.getIncome() > average) {
                output.println(household);
            }
        }
        output.println();
    }

    public void printHouseholdsBelowPoverty() {
        output.println("===================================================================");
        output.println("HOUSEHOLDS BELOW POVERTY LEVEL");
        output.println("===================================================================");
        output.printf("%-10s %-15s %-15s %-10s %-15s%n",
                "ID", "Income", "Poverty Level", "Members", "State");
        output.println("-------------------------------------------------------------------");

        for (HouseHold household : households) {
            if (household.isBelowPovertyLevel()) {
                output.printf("%-10d $%-14.2f $%-14.2f %-10d %-15s%n",
                        household.getId(),
                        household.getIncome(),
                        household.getPovertyLevel(),
                        household.getMembers(),
                        household.getState());
            }
        }
        output.println();
    }

    public void printPercentageBelowPoverty() {
        int countBelowPoverty = 0;

        for (HouseHold household : households) {
            if (household.isBelowPovertyLevel()) {
                countBelowPoverty++;
            }
        }

        double percentage = (countBelowPoverty * 100.0) / households.size();

        output.println("===================================================================");
        output.println("POVERTY LEVEL STATISTICS");
        output.println("===================================================================");
        output.printf("Households below poverty level: %d out of %d%n",
                countBelowPoverty, households.size());
        output.printf("Percentage below poverty level: %.2f%%%n", percentage);
        output.println();
    }

    public void printMedicaidEligibility() {
        int countQualified = 0;

        for (HouseHold household : households) {
            if (household.qualifiesForMedicaid()) {
                countQualified++;
            }
        }

        double percentage = (countQualified * 100.0) / households.size();

        output.println("===================================================================");
        output.println("MEDICAID ELIGIBILITY (138% OF FPL)");
        output.println("===================================================================");
        output.printf("Households qualifying for Medicaid: %d out of %d%n",
                countQualified, households.size());
        output.printf("Percentage qualifying for Medicaid: %.2f%%%n", percentage);
        output.println();
    }

}
