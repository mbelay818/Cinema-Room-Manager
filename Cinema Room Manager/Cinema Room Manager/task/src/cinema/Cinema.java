package cinema;
import java.util.Scanner;

/* In this project, you will create an application that helps manage a
   cinema theatre: sell tickets, check available seats, see sales statistics, and more.*/
public class Cinema {
    final static int sixty = 60;
    final static int ten = 10;
    final static int eight = 8;
    
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of rows:");
        int cinemaNumRows = scanner.nextInt() + 1; //Retrieves the user input for the size of the rows
        System.out.println("Enter the number of seats in each row:");
        int cinemaNumSeats = scanner.nextInt() + 1; //Retrieves the user input for the size of the rows
        
        String[][] cinemaMatrix = new String[cinemaNumRows][cinemaNumSeats]; // This creates the 2d array of the cinema room
        
        showMenu(cinemaMatrix, cinemaNumRows, cinemaNumSeats); // calls the menu so the user can have options on what to do next
    }

    /* This method retrieves the user input of a number 0,1,2,3 and based of that the user
    can look at the seats,buy a ticket, look at the statistics of that cinema room or exit.*/
    public static void showMenu(String[][] cinemaMatrix, int cinemaNumRows, int cinemaNumSeats) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        switch(scanner.nextInt()){
            case 1:
                showTheSeat(cinemaMatrix);
                showMenu(cinemaMatrix, cinemaNumRows, cinemaNumSeats);
                break;
            case 2:
                buyATicket(cinemaMatrix, cinemaNumRows,cinemaNumSeats);
                showMenu(cinemaMatrix, cinemaNumRows, cinemaNumSeats);
                break;
            case 3:
                getStatistics(cinemaMatrix, cinemaNumRows, cinemaNumSeats);
                showMenu(cinemaMatrix, cinemaNumRows, cinemaNumSeats);
            case 0:
                break;
        }
        
    }
    /* This method calculates the statistics of the particular cinema room that the user inputted in the main method
    it retrieves how many tickets were purchased, the current revenue of the cinema room, the possible revenue of
    the cinema room, and the percentage of purchased seats. */
    public static void getStatistics(String[][] cinemaMatrix, int cinemaNumRows, int cinemaNumSeats){
        
        int count = 0;
        float percentPurchased;
        int currentIncome = 0;
        int totalIncome;
        float availableSeats;
        int frontRows;
        
        cinemaNumRows = cinemaNumRows - 1;
        cinemaNumSeats = cinemaNumSeats - 1;
                
        frontRows = cinemaNumRows / 2;
        availableSeats = cinemaNumRows * cinemaNumSeats;
        /* This loop is checking if the cinema matrix has any seats that are bought and counting them
        and also calculating the current revenue of the cinema room based on the seat */
        for (int i = 0; i < cinemaMatrix.length; i++) {
            for (int j = 0; j < cinemaMatrix[i].length; j++) {
                if (cinemaMatrix[i][j] == "B") {
                    count++;
                }
                if (cinemaMatrix[i][j] == "B" && availableSeats <= sixty) {
                    currentIncome += ten;
                } else if (cinemaMatrix[i][j] == "B" && i <= frontRows) {
                    currentIncome += ten;
                } else if (cinemaMatrix[i][j] == "B" && i > frontRows) {
                    currentIncome += eight;           
                } else {
                }
            }
        }
        totalIncome = possibleIncome(cinemaNumRows, cinemaNumSeats);
        percentPurchased = (count / availableSeats) * 100;
        
        System.out.println("Number of purchased tickets: " + count);
        System.out.printf("Percentage: %.2f%%", percentPurchased);
        System.out.println("\nCurrent Income: $" + currentIncome);
        System.out.println("Total Income: $" + totalIncome);
    }
       /* This method calculates the possible income or the total income of the cinema room */
    public static int possibleIncome(int cinemaNumRows, int cinemaNumSeats) {

        int possibleIncome;
        
        int frontRows = cinemaNumRows  / 2;
        int availableSeats = cinemaNumRows * cinemaNumSeats;

        if (cinemaNumRows * cinemaNumSeats <= sixty) { // this checks if the cinema rooms has less than 60 seats
            possibleIncome = availableSeats * 10;      // than the tickets are $10
            return possibleIncome;
        } else {                // if it has more than 60 than front row is $10 and backrow is $8
            possibleIncome = (frontRows * cinemaNumSeats * 10) + ((cinemaNumRows - frontRows) * cinemaNumSeats * 8);
            return possibleIncome;       
        }
    }
        /* This method loops through the cinemaMatrix and prints out the matrix*/
    public static void showTheSeat(String[][] cinemaMatrix) {
        System.out.println("Cinema: ");
        for (int i = 0; i < cinemaMatrix.length; i++) {
            for (int j = 0; j < cinemaMatrix[i].length; j++) {
                if ( i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (cinemaMatrix[i][j] == "B") {
                    System.out.print("B" + " ");
                } else {
                    System.out.print("S" + " ");
                }
            }
            System.out.println(" ");
        }
    }
        /* This method reads the user input for which seat the user wants and then checks if the seat
         they entered is even possibly in the cinema room if it is not then asks you to input again.
         Then after the user has inputted an appropriate input then we loop through the matrix check if that
         tickets has already been purchased by another user if not than the user can buy it and it is marked
         B. Then it prints out the ticket price*/
    public static void buyATicket(String[][] cinemaMatrix, int cinemaNumRows, int cinemaNumSeats) {
        
        Scanner scanner = new Scanner(System.in);
        
        int frontRows;

        System.out.println("\nEnter a row number: ");
        int customerRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int customerSeat = scanner.nextInt();
        
        if (customerRow >= cinemaMatrix.length || customerRow < 0) {
            System.out.println("\nWrong input!\n");    
            buyATicket(cinemaMatrix, cinemaNumRows,cinemaNumSeats);
        } else if (customerSeat >= cinemaMatrix.length || customerSeat < 0) {
            System.out.println("\nWrong input!\n");
            buyATicket(cinemaMatrix, cinemaNumRows,cinemaNumSeats);
        } else {
            
            for (int i = 0; i < cinemaMatrix.length; i++) {
                for (int j = 0; j < cinemaMatrix[i].length; j++) {
                    if (i == customerRow && j == customerSeat){
                        if (cinemaMatrix[i][j] == "B") {
                            System.out.println("\nThat ticket has already been purchased!\n");
                            buyATicket(cinemaMatrix, cinemaNumRows,cinemaNumSeats);
                        } else {
                            cinemaMatrix[i][j] = "B";
                            
                            frontRows = (cinemaNumRows - 1) / 2;
                            
                            if ((cinemaNumRows - 1) * (cinemaNumSeats - 1) <= sixty) {
                                System.out.println("\nTicket price: $" + ten);
                            } else if (customerRow <= frontRows) {
                                System.out.println("\nTicket price: $" + ten);
                            } else {
                                System.out.println("\nTicket price: $" + eight);
                            }
                        }
                        
                    }
                }
                
            }
            
        }
    }
}