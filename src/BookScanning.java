import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BookScanning {
    static int bookNumOverall;
    static int libNumOverall;
    static int dayNumOverall;
    static int[] bookScore;
    static ArrayList<ArrayList<Integer>> libraries = new ArrayList<ArrayList<Integer>>();

    static 

    public static void main(String[] args) {
        parse();

        for()


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

        //parse first line (books, libraries, days)
        String firstLine = result.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        bookNumOverall = Integer.parseInt(firstLineSplit[0]);
        libNumOverall = Integer.parseInt(firstLineSplit[1]);
        dayNumOverall = Integer.parseInt(firstLineSplit[2]);

        //parse 2nd line (scores of books)
        String bookScoreResult = result.nextLine();
        String[] bookScoresSplit = bookScoreResult.split(" ");
        bookScore = new int[bookScoresSplit.length];
        int count = 0;
        for (String s: bookScoresSplit){
            bookScore[count] = Integer.parseInt(s);
            count++;
        }

        //parse in sets of 2 lines: 1st line is library's book num,
        int counter = 0;
        while(result.hasNextLine()){
            String[] libDetails = result.nextLine().split(" ");
            for (int i = 0; i < libDetails.length; i++){
                libraries.add(new ArrayList<Integer>());
                libraries.get(counter).add(Integer.parseInt(libDetails[i]));
            }
            counter++;
            String[] libBooks = result.nextLine().split(" ");
            for (int i = 0; i < libBooks.length; i++){
                libraries.add(new ArrayList<Integer>());
                libraries.get(counter).add(Integer.parseInt(libBooks[i]));
            }
            counter++;
        }
        System.out.println(libraries);


    }
}
