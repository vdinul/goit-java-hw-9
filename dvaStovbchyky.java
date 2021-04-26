/* VOLODYMYR DINUL
 * GOIT
 * Housaufgaben #9
 * Aufgabe #2
 * Read some info from a file and put it into a .json file
 * */

package main.java.ua.goit.hw9;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class dvaStovbchyky {
    // as per the assignment the input data has key:value pair of name:age
    String name;
    int age;

    // constructor
    public dvaStovbchyky(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // this is how we want the output to look like
    @Override
    public String toString() {
        return "\n{\n" +
                "name: " + name + "\n" +
                "age: " + age + "\n" +
                '}' + "\n";
    }
}

class Golovny {
    // pathes where on my computer the sourse and output files are located
    private static final String dateiPfadAus = "C:\\Users\\PC\\Desktop\\Private\\GoIT\\Java\\file1.txt";
    private static final String dateiPfadIn = "C:\\Users\\PC\\Desktop\\Private\\GoIT\\Java\\user.json";

    public static void main(String[] args) throws IOException {
        /* as per the assingment необходимо считать файл в список объектов
        * read the content of the file into an array list*/
        List<dvaStovbchyky> arList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dateiPfadAus))) {


            String reihe; // variable to store a line from the file
            String[] ar; // as per the assignment каждая строка содержит одинаковое количество "колонок", разделенный пробелом
            int counter = 0; // variable that helps skip the first line in the input file

            /*Read the file line by line until the Reader reaches the end of the file.
            * each line is converted into an array.
            * the values from the array is then added to the arrayList.
            * As stated, the counter variable is necessary to step over the first line of the file*/
            while ((reihe = br.readLine()) != null) {
                ar = reihe.split(" ");
                if(counter > 0) {
                    arList.add(new dvaStovbchyky(ar[0], Integer.parseInt(ar[1])));
                }
                counter++;
            } // end of while
        }
        catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
        // the Gson instantiation block
        var gson = new Gson();
        var json = gson.toJson(arList);

        // writing the content of gson into the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateiPfadIn))) {
            bw.write(arList.toString());
        }
        catch (IOException exc) {
            System.out.println("Something went wrong " + exc);
        }
    }
}
