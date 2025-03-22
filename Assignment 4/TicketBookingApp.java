import java.util.*;

class TicketBookingSystem {
    private final int totalSeats = 10;
    private final boolean[] seats = new boolean[totalSeats];

    public synchronized boolean bookSeat(int seatNumber, String user) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println("Invalid seat number selected by " + user);
            return false;
        }

        if (!seats[seatNumber - 1]) {
            seats[seatNumber - 1] = true;
            System.out.println("Seat " + seatNumber + " successfully booked by " + user);
            return true;
        } else {
            System.out.println("Seat " + seatNumber + " is already booked! " + user + " please choose another seat.");
            return false;
        }
    }

    public void displaySeatStatus() {
        System.out.println("\nCurrent Seat Status:");
        for (int i = 0; i < totalSeats; i++) {
            System.out.println("Seat " + (i + 1) + ": " + (seats[i] ? "Booked" : "Available"));
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String user;

    public BookingThread(TicketBookingSystem system, int seatNumber, String user, boolean isVIP) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.user = user;
        if (isVIP) {
            setPriority(Thread.MAX_PRIORITY);
        } else {
            setPriority(Thread.NORM_PRIORITY);
        }
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, user);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        List<Thread> threads = new ArrayList<>();

        threads.add(new BookingThread(system, 5, "Alice (VIP)", true));
        threads.add(new BookingThread(system, 6, "Bob", false));
        threads.add(new BookingThread(system, 5, "Charlie", false));
        threads.add(new BookingThread(system, 7, "David (VIP)", true));
        threads.add(new BookingThread(system, 6, "Eve", false));

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        system.displaySeatStatus();
    }
}
