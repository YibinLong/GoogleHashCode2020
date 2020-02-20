import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookScanning {
    static int bookNumOverall;
    static int libNumOverall;
    static int dayNumOverall;
    static int[] bookScore;
    static ArrayList<ArrayList<Integer>> libraries = new ArrayList<ArrayList<Integer>>();


    public static void main(String[] args) throws FileNotFoundException{
        parse();

        PrintWriter writer = new PrintWriter("resultFile.txt");
        writer.println(libNumOverall);

        int[] info = basicInfo();

        ArrayList<Book> allBooks = getBooks(1); // Go to line 1 for all books
        ArrayList<Library> libraries = new ArrayList<Library>();

        int books = info[0];
        int numOfLibraries = info[1];
        int days =  info[2];

        for(int i = 0; i < numOfLibraries; i++){
            libraries.add(getLibInfo(i));

            System.out.println("lib id: " + libraries.get(i).getID());
            System.out.println("first book: " + libraries.get(i).getBooks().get(0).getID());
            System.out.println("second book: " + libraries.get(i).getBooks().get(1).getID());
            System.out.println("third book: " + libraries.get(i).getBooks().get(2).getID());

        }

        


        LibraryOrganizer libOrg = new LibraryOrganizer(days, books, libraries);

        //writer.println("The first line");
        //writer.println("The second line");
        writer.close();


    }

    public static Scanner readLine(){
        try {
            Scanner input = new Scanner(System.in);

            File file = new File("../a_example");

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

    // Array w/ 3 indexes: 
    // 0: Number of books
    // 1: Number of libraries
    // 2: Number of days for scanning
    static int[] basicInfo(){
        Scanner result = readLine();
        String firstLine = result.nextLine();


        String[] stringInfo = firstLine.split(" ");
        int[] info = new int[stringInfo.length];

        for(int i = 0; i < info.length; i++){
            info[i] = Integer.parseInt(stringInfo[i]);
        }

        result.close();

        System.out.println("Books: " + info[0]);
        System.out.println("Libraries: " + info[1]);
        System.out.println("Days: " + info[2]);

        return info;

    }

    // Line determine which line to go to
    static ArrayList<Book> getBooks(int line){
        Scanner result = readLine();
        
        for(int i = 0; i < line; i++){
             result.nextLine();            
        }

        String booksLine = result.nextLine();
        String[] booksInfo = booksLine.split(" ");

        ArrayList<Book> books = new ArrayList<Book>();

        for(int i = 0; i < booksInfo.length; i++){
            books.add(new Book(Integer.parseInt(booksInfo[i])));
        }

        result.close();

        return books;  
    }

    static Library getLibInfo(int ID){
        Scanner result = readLine();

        // ID determines line to read
        int libID = ID;

        ID = (ID + 1)*2;

        for(int i = 0; i < ID; i++){
            result.nextLine();            
        }
      
        String libLine = result.nextLine();
        String[] libInfo = libLine.split(" ");

        int numOfSignupDays = Integer.parseInt(libInfo[1]);
        int numOfBooksPerDay = Integer.parseInt(libInfo[2]);

        ArrayList<Book> libBooks = getBooks(ID + 1);
            
        Library newLib = new Library(libID, numOfSignupDays, numOfBooksPerDay, libBooks);
        return newLib;
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
        //System.out.println(libraries);


    }

    public static String libraryProcess(Library lib){

        return null;
    }

}

