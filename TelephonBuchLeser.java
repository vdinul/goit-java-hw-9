/* VOLODYMYR DINUL
 * GOIT
 * Housaufgaben #9
 * Aufgabe #1
 * Read & validate telephone numbers from a file
 * */

package main.java.ua.goit.hw9;

import java.io.*;
import java.lang.reflect.Array;

public class TelephonBuchLeser {
    // this is where file.txt is located on my computer
    private static final String dateiPfad = "C:\\Users\\PC\\Desktop\\Private\\GoIT\\Java\\file.txt";

    String telNummer = "";  // variable to store an individual phone number

    /*TelephoNummerLeser uses FileReader to create a Reader for the file*/
    void telephonNumLeser () {
        // create and use a FileReader wrapped in a BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(dateiPfad))) {

            /*Read the file line by line until the Reader reaches the end of the file.*/
            while ((telNummer = br.readLine()) != null) {
                /* If the number read is of a valid format, this number is outputted to the console.*/
                if(istNummerGeltend(telNummer))
                    System.out.println(telNummer);
            }
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
    }

    /*The assignment states that we differentiate between two valid phone number formats,
    * i.e. the one which begins with a bracket and another one which begins with a number.
    * istNummerGeltend method divides all the input into two categories depending on
    * whether a particular instance of a number begins with a bracket or not.*/
    public boolean istNummerGeltend (String telNummer) {
        boolean istGeltend = false;
        char ch = telNummer.charAt(0);
        if (ch == '(') {
            istGeltend = mitKlammern(telNummer);
        }
        else {
            istGeltend = ohneKlammern(telNummer);
        }
        return istGeltend;
    }

    /*mitKlammern method checks the validity of the phone numbers starting with a bracket.*/
    public boolean mitKlammern (String telNummer) {
        boolean istGueltig = false;

        /*The format which starts with a bracket has the following format:
        * length of 14 characters,
        * an opening bracket at position 0,
        * a closing bracket at position 4,
        * a space at position 5 and a dash at position 9.*/
        if(telNummer.length() != 14 ||
        telNummer.charAt(4) != ')' ||
        telNummer.charAt(5) != ' ' ||
        telNummer.charAt(9) != '-') {
            return istGueltig;
        }

        /*The following three cycles check whether symbols at the corresponding positions
        * determined by the format are numberic symbols.*/
        for(int i = 1; i < 4; i++) {
            if(!Character.isDigit(telNummer.charAt(i)))
                return istGueltig;
        }
        for(int i = 6; i < 9; i++) {
            if(!Character.isDigit(telNummer.charAt(i)))
                return istGueltig;
        }
        for(int i = 10; i < 14; i++) {
            if(!Character.isDigit(telNummer.charAt(i)))
                return istGueltig;
        }
        istGueltig = true;
        return istGueltig;
    }

    /*ohneKlammern method checks the validity of phone numbers starting with
    * a symbol other than a bracket.*/
    public boolean ohneKlammern (String telNummer) {
        boolean istGueltig = false;

        /*The format which starts with a symbol other than a bracket has the following format:
         * length of 12 characters,
         * a dash at position 3 and position 7.*/
        if(telNummer.length() != 12 ||
                telNummer.charAt(3) != '-' ||
                telNummer.charAt(7) != '-') {
            return istGueltig;
        }

        /*The following three cycles check whether symbols at the corresponding positions
         * determined by the format are numberic symbols.*/
        for(int i = 0; i < 3; i++) {
            if(!Character.isDigit(telNummer.charAt(i)))
                return istGueltig;
        }
        for(int i = 4; i < 7; i++) {
            if(!Character.isDigit(telNummer.charAt(i)))
                return istGueltig;
        }
        for(int i = 8; i < 12; i++) {
            if(!Character.isDigit(telNummer.charAt(i)))
                return istGueltig;
        }
        istGueltig = true;
        return istGueltig;
    }
}

class Principal {
    public static void main(String[] args) {
        TelephonBuchLeser tbl = new TelephonBuchLeser();
        tbl.telephonNumLeser();
    }
}
