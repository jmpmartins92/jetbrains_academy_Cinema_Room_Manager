package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static CinemaSpecs cinemaSpecs;
    static CinemaTicket cinemaTicket;
    static String[][] seats;
    static int purchasedTickets;
    static int incomeCurrent;

    public static String[][] cinemaRoom() {
        seats = new String[cinemaSpecs.numRowsTotal + 1][cinemaSpecs.numColsTotal + 1];
        for (int row = 0; row <= cinemaSpecs.numRowsTotal; row++) {
            for (int col = 0; col <= cinemaSpecs.numColsTotal; col++) {
                if (row == 0) {
                    seats[0][col] = Integer.toString(col);
                } else {
                    seats[row][col] = "S";
                }
            }
            seats[row][0] = Integer.toString(row);
        }
        seats[0][0] = " ";
        return seats;
    }

    public static void cinemaPrint(String[][] cinemaRoom) {
        System.out.println("\nCinema:");
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[0].length; col++) {
                System.out.print(seats[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void inputCinema() {
        System.out.println("\nEnter the number of rows: ");
        int rowsTotal = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int colsTotal = scanner.nextInt();
        int ticketCostFront = 10;
        int ticketCostBack = 8;
        int smallCinemaLimit = 60;
        cinemaSpecs = new CinemaSpecs(rowsTotal, colsTotal, ticketCostFront, ticketCostBack, smallCinemaLimit);
        cinemaRoom();
    }

    public static boolean seatCheck(int rowTicket, int cinemaRows, int colTicket, int cinemaCols) {
        if (rowTicket < 1 || rowTicket > cinemaRows || colTicket < 1 || colTicket > cinemaCols) {
            System.out.println("\nWrong input!");
            return false;
        }

        if (seats[rowTicket][colTicket].equals("B")) {
            System.out.println("\nThat ticket has already been purchased!");
            return false;
        }
        return true;
    }


    public static void inputTicket() {

        System.out.println("\nEnter a row number: ");
        int rowNumberTicket = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int colNumberTicket = scanner.nextInt();
        boolean checkInput = seatCheck(rowNumberTicket, cinemaSpecs.numRowsTotal, colNumberTicket, cinemaSpecs.numColsTotal);
        while (checkInput == false) {
            System.out.println("\nEnter a row number: ");
            rowNumberTicket = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            colNumberTicket = scanner.nextInt();
            checkInput = seatCheck(rowNumberTicket, cinemaSpecs.numRowsTotal, colNumberTicket, cinemaSpecs.numColsTotal);
        }

        cinemaTicket = new CinemaTicket(rowNumberTicket, colNumberTicket);
        System.out.println("Ticket price: $" + cinemaTicket.costTicket);
        seats[cinemaTicket.rowNumberTicket][cinemaTicket.colNumberTicket] = "B";
        purchasedTickets++;
        incomeCurrent += cinemaTicket.costTicket;
    }


    public static int income() {

        int income;
        int numSeatsTotal = cinemaSpecs.numRowsTotal * cinemaSpecs.numColsTotal;

        if (numSeatsTotal < cinemaSpecs.smallCinemaLimit) {
            income = numSeatsTotal * cinemaSpecs.ticketCostFront;
        } else {
            if (numSeatsTotal % 2 == 0) {
                income = cinemaSpecs.numRowsTotal / 2 * cinemaSpecs.ticketCostFront * cinemaSpecs.numColsTotal +
                        cinemaSpecs.numRowsTotal / 2 * cinemaSpecs.ticketCostBack * cinemaSpecs.numColsTotal;
            } else {
                income = cinemaSpecs.numRowsTotal / 2 * cinemaSpecs.ticketCostFront * cinemaSpecs.numColsTotal +
                        (cinemaSpecs.numRowsTotal / 2 + 1) * cinemaSpecs.ticketCostBack * cinemaSpecs.numColsTotal;

            }
        }
        return income;
    }

    public static void statistics() {
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%", (double) purchasedTickets / (cinemaSpecs.numRowsTotal * cinemaSpecs.numColsTotal) * 100);
        System.out.println("\nCurrent income: $" + incomeCurrent);
        System.out.println("Total income: $" + income());
    }

    public static int mainMenu() {

        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }



    public static void main(String[] args) {
        inputCinema();
        int mainMenuStatus = -1;
        while (mainMenuStatus != 0) {
            switch (mainMenuStatus) {
                case 0:
                    System.exit(0);
                case 1:
                    cinemaPrint(seats);
                    mainMenuStatus = mainMenu();
                    break;
                case 2:
                    inputTicket();
                    mainMenuStatus = mainMenu();
                    break;
                case 3:
                    statistics();
                    mainMenuStatus = mainMenu();
                    break;
                default:
                    System.out.println("Command not recognized");
                    mainMenuStatus = mainMenu();
                    break;
            }
        }
    }
}
