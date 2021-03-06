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
    
    static String name;

    static ArrayList<ArrayList<Integer>> libraries = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) throws FileNotFoundException {
        //parse();
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name: ");
        name = input.next();

        int[] info = basicInfo();

        ArrayList<Book> allBooks = getBooks(1); // Go to line 1 for all books
        System.out.println("Got all " + info[0] + " books");

        ArrayList<Library> libraries = new ArrayList<Library>();
        System.out.println("Got all " + info[1] + " libraries");        
        
        int books = info[0];
        int numOfLibraries = info[1];
        int days = info[2];

        // for (int i = 0; i < numOfLibraries; i++) {
        //     libraries.add(getLibInfo(i));

        //     System.out.println(i + " out of " + numOfLibraries + " finished processing");           

        //     // System.out.println("lib id: " + libraries.get(i).getID());
        //     // System.out.println("first book: " + libraries.get(i).getBooks().get(0).getID());
        //     // System.out.println("second book: " + libraries.get(i).getBooks().get(1).getID());
        //     // System.out.println("third book: " + libraries.get(i).getBooks().get(2).getID());

        // }

        Scanner result = readLine();
        
        //Scanner result = readLine();
        result.nextLine();
        result.nextLine();

        for(int i = 0; i < numOfLibraries; i++){

            int libID = i;

            String libLine = result.nextLine();
            String[] libInfo = libLine.split(" ");
            
            int numOfSignupDays = Integer.parseInt(libInfo[1]);
            int numOfBooksPerDay = Integer.parseInt(libInfo[2]);
    
            String booksLine = result.nextLine();
            String[] booksInfo = booksLine.split(" ");

            ArrayList<Book> libBooks = new ArrayList<Book>();

            for (int j = 0; j < booksInfo.length; j++) {
                libBooks.add(new Book(Integer.parseInt(booksInfo[j])));
            }
    
            Library newLib = new Library(libID, numOfSignupDays, numOfBooksPerDay, libBooks);
            libraries.add(newLib);     
            
            System.out.println(i + " out of " + numOfLibraries + " finished processing");                  

        }

        result.close();        

        System.out.println("Added all libraries' info");

        LibraryOrganizer libOrg = new LibraryOrganizer(days, books, libraries);
        
        int[] signupDays = libOrg.organizeSignupDays();
        System.out.println("Organized all library b signup days");

        File filename = new File("resultFile_" + name + ".txt");
        PrintWriter writer = new PrintWriter(filename);        

        System.out.println("Beginning to write...");
        // Number of libraries
        writer.println(numOfLibraries);

        // Order for signup
        for (int i = 0; i < numOfLibraries; i++) {

            int numOfBookInLib = 0;
            int position = 0;

            for (int j = 0; j < numOfLibraries; j++) {
                if (signupDays[i] == libraries.get(j).getID()) {
                    numOfBookInLib = libraries.get(j).getBooks().size();
                    position = j;
                }
            }

            // Print book order for each library
            writer.println(signupDays[i] + " " + numOfBookInLib);

            ArrayList<Book> sortedBooks = libOrg.sortBooks(libraries.get(position));

            for (int j = 0; j < sortedBooks.size(); j++) {
                writer.print(sortedBooks.get(sortedBooks.size() - j - 1).getID() + " ");
            }
            writer.println("");

        }

        writer.close();

    }

    public static Scanner readLine() {
        try {
            Scanner input = new Scanner(System.in);

            // Change as nessessary
            File file = new File(name + ".txt");
            input = new Scanner(file);

            return input;
            /*
             * while (input.hasNextLine()) { String line = input.nextLine();
             * //System.out.println(line); } input.close();
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
    static int[] basicInfo() {

        Scanner result = readLine();
        String firstLine = result.nextLine();

        String[] stringInfo = firstLine.split(" ");
        int[] info = new int[stringInfo.length];

        for (int i = 0; i < info.length; i++) {
            info[i] = Integer.parseInt(stringInfo[i]);
        }

        result.close();

        System.out.println("Books: " + info[0]);
        System.out.println("Libraries: " + info[1]);
        System.out.println("Days: " + info[2]);

        return info;

    }

    // Line determine which line to go to
    static ArrayList<Book> getBooks(int line) {
        Scanner result = readLine();

        for (int i = 0; i < line; i++) {
            result.nextLine();
        }

        String booksLine = result.nextLine();
        String[] booksInfo = booksLine.split(" ");

        ArrayList<Book> books = new ArrayList<Book>();

        for (int i = 0; i < booksInfo.length; i++) {
            books.add(new Book(Integer.parseInt(booksInfo[i])));
        }

        result.close();

        return books;
    }

    static Library getLibInfo(int ID) {
        Scanner result = readLine();

        // ID determines line to read
        int libID = ID;

        ID = (ID + 1) * 2;

        for (int i = 0; i < ID; i++) {
            result.nextLine();
        }

        String libLine = result.nextLine();
        String[] libInfo = libLine.split(" ");

        int numOfSignupDays = Integer.parseInt(libInfo[1]);
        int numOfBooksPerDay = Integer.parseInt(libInfo[2]);

        String booksLine = result.nextLine();
        String[] booksInfo = booksLine.split(" ");

        ArrayList<Book> libBooks = new ArrayList<Book>();

        for (int i = 0; i < booksInfo.length; i++) {
            libBooks.add(new Book(Integer.parseInt(booksInfo[i])));
        }

        Library newLib = new Library(libID, numOfSignupDays, numOfBooksPerDay, libBooks);
        result.close();
        return newLib;
    }

    public static void parse() {
        Scanner result = readLine();

        // parse first line (books, libraries, days)
        String firstLine = result.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        bookNumOverall = Integer.parseInt(firstLineSplit[0]);
        libNumOverall = Integer.parseInt(firstLineSplit[1]);
        dayNumOverall = Integer.parseInt(firstLineSplit[2]);

        // parse 2nd line (scores of books)
        String bookScoreResult = result.nextLine();
        String[] bookScoresSplit = bookScoreResult.split(" ");
        bookScore = new int[bookScoresSplit.length];
        int count = 0;
        for (String s : bookScoresSplit) {
            bookScore[count] = Integer.parseInt(s);
            count++;
        }

        // parse in sets of 2 lines: 1st line is library's book num,
        int counter = 0;
        while (result.hasNextLine()) {
            String[] libDetails = result.nextLine().split(" ");
            for (int i = 0; i < libDetails.length; i++) {
                libraries.add(new ArrayList<Integer>());
                libraries.get(counter).add(Integer.parseInt(libDetails[i]));
            }
            counter++;
            String[] libBooks = result.nextLine().split(" ");
            for (int i = 0; i < libBooks.length; i++) {
                libraries.add(new ArrayList<Integer>());
                libraries.get(counter).add(Integer.parseInt(libBooks[i]));
            }
            counter++;
        }
        // System.out.println(libraries);

    }

    public static String libraryProcess(Library lib) {

        return null;
    }

}
