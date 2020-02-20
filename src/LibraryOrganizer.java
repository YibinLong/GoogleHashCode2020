import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class LibraryOrganizer {
   
    int days;
    int books;
    ArrayList<Library> libraries;
    
    LibraryOrganizer(int days, int books, ArrayList<Library> libraries){
        this.days = days;
        this.books = books;
        this.libraries = libraries;
    }

    // Return an array of library ID's
    // Organized by order of signup by ascending signup length
    // Break ties by putting libraries that can ship more books per day first
    int[] organizeSignupDays(){
        int numOfLibs = this.libraries.size();

        int[] signUpOrder = new int[numOfLibs];

        for(int i = 0; i < numOfLibs; i++){
            
            int minSignupDays = libraries.get(i).getSignupDays();
            int maxShipments = libraries.get(i).getShipPerDay();
            int position = i;            

            for(int j = i; j < numOfLibs; j++){
                if (libraries.get(j).getSignupDays() < minSignupDays)
                {
                    minSignupDays = libraries.get(i).getSignupDays();
                    position = j;
                }
                else if (libraries.get(j).getSignupDays() == minSignupDays)
                {
                    if (libraries.get(j).getShipPerDay() > maxShipments)
                    {
                        minSignupDays = libraries.get(j).getSignupDays();
                        position = j;
                    }
                }
            }


            Library temp = libraries.get(i);

            libraries.set(i, libraries.get(position));
            libraries.set(position, temp);
            
        }

        for(int i = 0; i < libraries.size(); i++){
            signUpOrder[i] = libraries.get(i).getID();
        }

        return signUpOrder;

    }

    // Override sort method


    // Sorts list of books a library will send
    ArrayList<Book> sortBooks(Library input){

        Collections.sort(input.getBooks(), new Comparator<Book>(){
            public int compare(Book s1, Book s2) {
                return Math.max(s1.getID(),s2.getID());
            }
        });

        return input.getBooks();
    }

    //public ArrayList<Book> sortBooks(ArrayList<Book> input){

    //}
}