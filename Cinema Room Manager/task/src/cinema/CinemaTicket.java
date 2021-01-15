package cinema;

import static cinema.Cinema.cinemaSpecs;

public class CinemaTicket {
    int rowNumberTicket;
    int colNumberTicket;
    int costTicket;


    public CinemaTicket(int rowNumberTicket, int colNumberTicket) {

        this.rowNumberTicket = rowNumberTicket;
        this.colNumberTicket = colNumberTicket;
        if (this.rowNumberTicket < (cinemaSpecs.numRowsTotal + 1 ) / 2 ||
                cinemaSpecs.numRowsTotal * cinemaSpecs.numColsTotal < 60) {
            this.costTicket = cinemaSpecs.ticketCostFront;
        } else {
            this.costTicket = cinemaSpecs.ticketCostBack;
        }

    }
}
