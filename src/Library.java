import java.util.ArrayList;

class Library {
        
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
