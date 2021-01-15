package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static CinemaSpecs cinemaSpecs;
    static CinemaTicket cinemaTicket;
    static String[][] seats;

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
        System.out.println("Enter the number of rows: ");
        int rowsTotal = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int colsTotal = scanner.nextInt();
        int ticketCostFront = 10;
        int ticketCostBack = 8;
        int smallCinemaLimit = 60;
        cinemaSpecs = new CinemaSpecs(rowsTotal, colsTotal, ticketCostFront, ticketCostBack, smallCinemaLimit);
    }

    public static void inputTicket() {
        System.out.println("Enter a row number: ");
        int rowNumberTicket = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int colNumberTicket = scanner.nextInt();
        cinemaTicket = new CinemaTicket(rowNumberTicket, colNumberTicket);
        System.out.println("Ticket price: $" + cinemaTicket.costTicket);
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

    public static void occupiedSeat() {
        seats[cinemaTicket.rowNumberTicket][cinemaTicket.colNumberTicket] = "B";
    }

    public static void main(String[] args) {
        inputCinema();
        cinemaPrint(cinemaRoom());
        inputTicket();
        occupiedSeat();
        cinemaPrint(seats);

        //System.out.printf("Total income:\n $%d \n", income());
    }
}