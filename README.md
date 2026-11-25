# Cinema Seat Booking – Java HashMap Task

A Java exercise demonstrating how to use a custom class (`Seat`) as a key in a
`HashMap`, and how `equals()` and `hashCode()` make logically equal objects work
correctly as keys in a `HashMap` and elements in a `HashSet`.

## Requirements

- Java JDK 8 or later
- A terminal / command prompt

## How to run

Open a terminal in this project folder and run:

```bash
javac Task_CinemaBooking.java
java Task_CinemaBooking
```

## What the program demonstrates

- **Part A:** `put()` – add seat bookings.
- **Part B:** `get()` – find the customer who booked a seat.
- **Part C:** `containsKey()` – check whether a seat is booked.
- **Part D:** `putIfAbsent()` – do not overwrite an existing booking.
- **Part E:** `remove()` and `put()` – move a booking.
- **Part F:** `HashSet` and `contains()` – prevent booking broken seats.
- **Part G:** `size()` – count bookings.
- `Seat.equals()` and `Seat.hashCode()` – compare seats by row and number.

The answers to the questions and the bonus explanation are included at the
bottom of `Task_CinemaBooking.java`.

## Expected result

The program should report four booked seats and print the seating chart.
The order of entries inside the printed `HashMap` may vary because `HashMap`
does not guarantee iteration order.
