package cinema;

public class CinemaSpecs {
    int numRowsTotal;
    int numColsTotal;
    int ticketCostFront;
    int ticketCostBack;
    int smallCinemaLimit;

    public CinemaSpecs(int numRowsTotal, int numColsTotal, int ticketCostFront, int ticketCostBack, int smallCinemaLimit) {

        this.numRowsTotal = numRowsTotal;
        this.numColsTotal = numColsTotal;
        this.ticketCostFront = ticketCostFront;
        this.ticketCostBack = ticketCostBack;
        this.smallCinemaLimit = smallCinemaLimit;
    }
}


