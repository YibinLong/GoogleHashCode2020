import java.util.ArrayList;

public class LibraryOrganizer {


    class Library{
        int ID;
        int signupDays;
        int shipPerDay;

        Library(int ID, int signupDays, int shipPerDay){
            this.ID = ID;
            this.signupDays = signupDays;
            this.shipPerDay = shipPerDay;
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

    void organizeSignupDays(){
        
    }



}