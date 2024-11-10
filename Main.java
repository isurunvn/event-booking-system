// Main.java
public class Main {
    public static void main(String[] args) {
        try {
            // Create booking system
            BookingSystem bookingSystem = new BookingSystem();

            // Add events
            Event concert = new Event("Rock Concert", "2024-11-01", 100, 50.0, "Stadium Arena");
            Event theater = new Event("Shakespeare Play", "2024-11-15", 50, 75.0, "Grand Theater");
            Event sports = new Event("Football Match", "2024-11-30", 200, 100.0, "Sports Complex");

            bookingSystem.addEvent(concert);
            bookingSystem.addEvent(theater);
            bookingSystem.addEvent(sports);

            System.out.println("Initial Events:");
            bookingSystem.displayAllEvents();

            // Add customers with different priorities
            Customer customer1 = new Customer("John", 1, 2, "Rock Concert");
            Thread.sleep(100);
            Customer customer2 = new Customer("Alice", 3, 3, "Shakespeare Play");
            Thread.sleep(100);
            Customer customer3 = new Customer("Bob", 2, 1, "Football Match");
            Thread.sleep(100);
            Customer customer4 = new Customer("Carol", 3, 99, "Rock Concert");

            System.out.println("\nAdding customers to queue...");
            bookingSystem.addCustomerToQueue(customer1);
            bookingSystem.addCustomerToQueue(customer2);
            bookingSystem.addCustomerToQueue(customer3);
            bookingSystem.addCustomerToQueue(customer4);

            System.out.println("\nBefore sorting:");
            Customer[] customersBeforeSort = bookingSystem.getBookingQueue().toArray();
            for (Customer c : customersBeforeSort) {
                System.out.println(c);
            }

            // Sort customers by priority
            bookingSystem.sortCustomersByPriority();

            System.out.println("\nAfter sorting by priority:");
            Customer[] customersAfterSort = bookingSystem.getBookingQueue().toArray();
            for (Customer c : customersAfterSort) {
                System.out.println(c);
            }

            // Process bookings
            System.out.println("\nProcessing bookings...");
            int processedCount = 0;
            while (!bookingSystem.getBookingQueue().isEmpty()) {
                boolean success = bookingSystem.processNextBooking();
                processedCount++;
                System.out.println("Processed booking " + processedCount + ": " +
                        (success ? "Success" : "Failed"));
            }

            // Display final results
            System.out.println("\nProcessed Bookings:");
            for (Customer customer : bookingSystem.getProcessedBookings()) {
                System.out.println(customer);
            }

            System.out.println("\nFinal Event Status:");
            bookingSystem.displayAllEvents();

            System.out.println("\nComplete Booking History:");
            bookingSystem.displayBookingHistory();

            // Test error handling
            System.out.println("\nTesting Error Handling:");
            try {
                // Try to book for non-existent event
                Customer errorCustomer = new Customer("Error", 1, 1, "Non-existent Event");
                bookingSystem.addCustomerToQueue(errorCustomer);
            } catch (IllegalStateException e) {
                System.out.println("Expected error caught: " + e.getMessage());
            }

            try {
                // Try to book more tickets than available
                Event smallEvent = new Event("Small Event", "2024-12-01", 5, 20.0, "Small Venue");
                bookingSystem.addEvent(smallEvent);
                Customer overBookingCustomer = new Customer("Over Booker", 1, 10, "Small Event");
                bookingSystem.addCustomerToQueue(overBookingCustomer);
            } catch (IllegalStateException e) {
                System.out.println("Expected error caught: " + e.getMessage());
            }

        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\nBooking system execution completed.");
        }
    }
}