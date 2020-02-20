import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class BookScanning {
    public static void main(String[] args) {
        Scanner result = readLine();
        System.out.println(result);

        String firstLine = result.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        int bookNum = Integer.parseInt(firstLineSplit[0]);
        int libNum = Integer.parseInt(firstLineSplit[1]);
        int dayNum = Integer.parseInt(firstLineSplit[2]);

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
