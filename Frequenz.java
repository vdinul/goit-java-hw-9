/* VOLODYMYR DINUL
 * GOIT
 * Housaufgaben #9
 * Aufgabe #3
 * Read some words from a file, count the number of words and put the words out to the console
 * in a sorted descending order
 * */

package main.java.ua.goit.hw9;

/* Напишите метод, который будет подсчитывать частоту каждого слова в файле words.txt.
Предпалагаем, что:
1. words.txt содержит только слова в нижнем регистре, разделенные пробелом
2. Каждое слово содержит только символы-буквы в нижнем регистре.
3. Слова разделены одим или несколькими пробелами, либо переносом строки.
Вывод на консоль должен быть отсортирован на частоте слов (от наибольшей к наименьшей)

The solution was copied from here
https://www.javatpoint.com/how-to-sort-hashmap-by-value*/

import com.sun.source.tree.Tree;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Frequenz {
    private static final String dateiPfadAus = "C:\\Users\\PC\\Desktop\\Private\\GoIT\\Java\\words.txt";

    Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Frequenz frequenz = new Frequenz();
        frequenz.createMap();
        frequenz.sortByValue(false);
    }

    void createMap () {
        try (BufferedReader br = new BufferedReader(new FileReader(dateiPfadAus))) {

            String reihe; // variable to store a line from the file
            String[] ar;  // array which stores individual words from the file

            /*Read the file line by line until the Reader reaches the end of the file.
             * each line is converted into an array. */
            while ((reihe = br.readLine()) != null) {
                ar = reihe.split("\\s+"); //this s+ is for ignoring multiple spaces

                for(int i = 0; i < ar.length; i++) {
                    if (map.containsKey(ar[i]))
                        map.put(ar[i], map.get(ar[i]) + 1);
                    else
                        map.put(ar[i], 1);
                }
            } // end of while
        }
        catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
    }

    /*This method sorts the data from the hash map.
    * It accepts true or false. "True" means that the sorting will be in the ascending order.
    * Correspondingly, "false" indicates a discending order of sorting.
    * I have no clue how this method works.*/
    void sortByValue(boolean order) {
        // convert hashmap into list
         List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());
        //sorting the list elements
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
            {
                if (order)
                {
                    //compare two object and return an integer
                    return o1.getValue().compareTo(o2.getValue());}
                else
                {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        printMap(sortedMap);
    }  // end of sortByValue method

    //method for printing the elements
    public void printMap(Map<String, Integer> map)
    {
        System.out.println("Word\t Frequency ");
        for (Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println(entry.getKey() +"\t\t"+entry.getValue());
        }
        System.out.println("\n");
    }
}


