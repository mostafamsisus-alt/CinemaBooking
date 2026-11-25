import java.util.*;

class Task_CinemaBooking {

    static class Seat {
        final char row;
        final int number;

        Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        @Override
        public String toString() {
            return "" + row + number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Seat)) {
                return false;
            }
            Seat other = (Seat) o;
            return this.row == other.row && this.number == other.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }
    }

    public static void main(String[] args) {

        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

        // Part A: book seats using put()
        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");
        System.out.println("Bookings:           " + bookings);

        // Part B: who booked seat C3? Use get().
        System.out.println("Who booked C3:      " + bookings.get(new Seat('C', 3)));

        // Part C: are seats A2 and E6 taken? Use containsKey().
        System.out.println("Is A2 taken:        " + bookings.containsKey(new Seat('A', 2)));
        System.out.println("Is E6 taken:        " + bookings.containsKey(new Seat('E', 6)));

        // Part D: Youssef wants seat B5. Use putIfAbsent().
        String previousCustomer = bookings.putIfAbsent(new Seat('B', 5), "Youssef");
        if (previousCustomer == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by " + previousCustomer);
        }

        // Part E: Ali moves from seat A1 to seat D2. Use remove() and put().
        String ali = bookings.remove(new Seat('A', 1));
        bookings.put(new Seat('D', 2), ali);
        System.out.println("After Ali moved:    " + bookings);

        // Part F: seats E1 and E2 are broken. Use contains().
        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));

        Seat requestedSeat = new Seat('E', 1);
        if (broken.contains(requestedSeat)) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(requestedSeat, "Hana");
            System.out.println("Hana -> E1:         booked");
        }

        // Part G: how many seats are booked? Use size().
        System.out.println("Seats booked:       " + bookings.size());

        System.out.println();
        printChart(bookings, broken);
    }

    // GIVEN - do not change.
    static void printChart(HashMap<Seat, String> bookings, HashSet<Seat> broken) {
        System.out.println("     1  2  3  4  5  6");
        for (char row = 'A'; row <= 'E'; row++) {
            System.out.print(row + "  ");
            for (int number = 1; number <= 6; number++) {
                Seat seat = new Seat(row, number);
                if (bookings.containsKey(seat)) {
                    System.out.print("  X");
                } else if (broken.contains(seat)) {
                    System.out.print("  #");
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
    }
}

/*
QUESTIONS - ANSWERS

Q1. Why did we have to override hashCode() and equals()?
HashMap and HashSet use hashCode() to locate an object and equals() to compare
objects in the same bucket. Different Seat objects can represent the same seat,
so both methods must be overridden consistently.

Q2. Delete hashCode() but keep equals(). What happens? Why?
The program may not find an equal Seat object because the default hashCode()
can return different values for objects that equals() considers equal. They
may therefore be placed in different buckets.

Q3. Why are row and number final? What could go wrong if the number of a booked
seat could be changed after it was put in the map?
They are final to make Seat objects immutable. Changing a key after insertion
could change its hash code, causing the HashMap to search in the wrong bucket.

Q4. Change equals(Object o) to equals(Seat o). What happens? Then also remove
@Override and run again. What happens now?
With @Override, compilation fails because equals(Seat) does not override
Object.equals(Object). Without @Override, it becomes an overload. HashMap
still calls equals(Object), so the custom comparison is not used.

BONUS. Replace the whole Seat class with record Seat(char row, int number) {}.
A record automatically generates equals(), hashCode(), and toString() based
on its components. Equal row and number values therefore represent equal seats.
*/
