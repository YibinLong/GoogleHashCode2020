import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class BookScanning {
    public static void main(String[] args) {
        Scanner result = readLine();

        String firstLine = result.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        int bookNum = Integer.parseInt(firstLineSplit[0]);
        int libNum = Integer.parseInt(firstLineSplit[1]);
        int dayNum = Integer.parseInt(firstLineSplit[2]);

        String bookScoreResult = result.nextLine();
        String[] bookScoresSplit = bookScoreResult.split(" ");
        int[] bookScore = new int[bookScoresSplit.length];
        int count = 0;
        for (String s: bookScoresSplit){
            bookScore[count] = Integer.parseInt(s);
            count++;
        }
        System.out.println(Arrays.toString(bookScore));


        int lineNum = 0;
        while(result.hasNextLine()){
            if(lineNum == 0){

            }
        }

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
}
