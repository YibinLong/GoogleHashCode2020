import org.omg.CORBA.INTERNAL;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class BookScanning {
    static int bookNum;
    static int libNum;
    static int dayNum;
    static int[] bookScore;

    public static void main(String[] args) {
        parse();



    }

    public static Scanner readLine(){
        try {
            Scanner input = new Scanner(System.in);

            File file = new File("a_example");

            input = new Scanner(file);

            return input;
            /*
            while (input.hasNextLine()) {
                String line = input.nextLine();
                //System.out.println(line);
            }
            input.close();
             */

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void parse(){
        Scanner result = readLine();

        String firstLine = result.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        bookNum = Integer.parseInt(firstLineSplit[0]);
        libNum = Integer.parseInt(firstLineSplit[1]);
        dayNum = Integer.parseInt(firstLineSplit[2]);

        String bookScoreResult = result.nextLine();
        String[] bookScoresSplit = bookScoreResult.split(" ");
        bookScore = new int[bookScoresSplit.length];
        int count = 0;
        for (String s: bookScoresSplit){
            bookScore[count] = Integer.parseInt(s);
            count++;
        }

    }
}
