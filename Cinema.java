package cinema;

import java.util.Scanner;

public class Cinema {

    public static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    public static void showSeats(char[][] cinema, int rows, int seats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for(int i = 0; i < seats; i++) {
            System.out.print(" "+(i+1));
        }
        System.out.println();
        for(int i = 0; i < rows; i++) {
            System.out.print((i+1)+" ");
            for(int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void buyTicket(char[][] cinema, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        int rowNumber = 0;
        int seatNumber = 0;
        boolean validSeat = false;
        while(!validSeat){
            System.out.println("Enter a row number:");
             rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
             seatNumber = scanner.nextInt();
             if ( rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seats) {
                 System.out.println("Wrong input!");
             } else {
                 validSeat = true;
             }
        }

        boolean validTicket = false;
        while (!validTicket) {
            if (cinema[rowNumber-1][seatNumber-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                System.out.println("Enter a row number:");
                rowNumber = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatNumber = scanner.nextInt();
            } else {
                cinema[rowNumber-1][seatNumber-1] = 'B';
                validTicket = true;
            }
        }

        int ticketPrice = 0;

        if (rows * seats <= 60) {
            ticketPrice = 10;
        } else if (rowNumber <= rows / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        System.out.println("Ticket price: $"+ticketPrice);

    }
    public static void showStatistics(char[][] cinema, int rows, int seats) {
        int totalTickets = rows * seats;
        int purchasedTickets = 0;
        double percentage = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            totalIncome = (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8;
        }

        for(int i = 0; i < rows ; i++){
            for(int j = 0; j < seats; j++){
                if (cinema[i][j] == 'B') {
                   purchasedTickets++;
                    if (rows * seats <= 60) {
                        currentIncome += 10;
                    } else if (i < rows / 2) {
                        currentIncome += 10;
                    } else {
                        currentIncome += 8;
                    }
                }
            }
        }

        percentage =  ((double)purchasedTickets * 100 )/ totalTickets;



        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n" , percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }
    public static void main(String[] args) {
        // Write your code here
       Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
       int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
       int seats = scanner.nextInt();

       char[][] cinema = new char[rows][seats];
       for(int i = 0; i < rows; i++) {
           for(int j = 0; j < seats; j++) {
               cinema[i][j] = 'S';
           }
       }
       printMenu();
       int choice = scanner.nextInt();
       while (choice !=0) {
           if(choice == 1){
               showSeats(cinema,rows,seats);
               printMenu();
               choice = scanner.nextInt();
           } else if (choice == 2) {
               buyTicket(cinema,rows,seats);
               printMenu();
               choice = scanner.nextInt();
           } else if(choice == 3) {
               showStatistics(cinema,rows,seats);
               printMenu();
               choice = scanner.nextInt();
           } else {
               System.out.println("Not a valid command!");
           }
       }
    }
}