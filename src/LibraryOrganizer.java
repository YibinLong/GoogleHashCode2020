import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static sun.swing.MenuItemLayoutHelper.max;

public class LibraryOrganizer {
    class Library{
        
        int ID;
        int signupDays;
        int shipPerDay;
        ArrayList<Book> books;

        Library(int ID, int signupDays, int shipPerDay, ArrayList<Book> books){
            this.ID = ID;
            this.signupDays = signupDays;
            this.shipPerDay = shipPerDay;
            this.books = books;
        }

        int getID(){
            return this.ID;
        }

        int getSignupDays(){
            return this.signupDays;
        }

        int getShipPerDay(){
            return this.shipPerDay;
        }

        ArrayList<Book> getBooks(){
            return books;
        }

    }

    class Book{
        int ID;
        
        Book(int ID){
            this.ID = ID;
        }

        int getID(){
            return this.ID;
        }

    }

    int days;
    ArrayList<Library> libraries;
    ArrayList<Book> books;


    LibraryOrganizer(int days, ArrayList<Library> libraries, ArrayList<Book> books){
        this.days = days;
        this.libraries = libraries;
        this.books = books;
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
    void sortBooks(Library input){
        Collections.sort(input.getBooks(), new Comparator<Book>(){
            public int compare(Book s1, Book s2) {
                return Math.max(s1.getID(),s2.getID());
            }
        });
    }




    //public ArrayList<Book> sortBooks(ArrayList<Book> input){

    //}
}