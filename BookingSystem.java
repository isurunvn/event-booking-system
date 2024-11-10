import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private CustomQueue<Customer> bookingQueue;
    private AVLTree eventTree;
    private List<Customer> processedBookings;
    private List<String> bookingHistory;
    private static final int MAX_QUEUE_SIZE = 100;

    public BookingSystem() {
        this.bookingQueue = new CustomQueue<>(MAX_QUEUE_SIZE, Customer.class);  // Updated constructor call
        this.eventTree = new AVLTree();
        this.processedBookings = new ArrayList<>();
        this.bookingHistory = new ArrayList<>();
    }

    public void addCustomerToQueue(Customer customer) throws IllegalStateException {
        Event event = eventTree.search(customer.getRequestedEventName());
        if (event == null) {
            throw new IllegalStateException("Event not found: " + customer.getRequestedEventName());
        }
        if (event.getAvailableTickets() < customer.getRequestedTickets()) {
            throw new IllegalStateException("Not enough tickets available for event: " + customer.getRequestedEventName());
        }
        bookingQueue.enqueue(customer);
        bookingHistory.add(String.format("Customer %s added to queue for event %s",
                customer.getName(), customer.getRequestedEventName()));
    }

    public void addEvent(Event event) {
        eventTree.insert(event);
        bookingHistory.add("New event added: " + event.getEventName());
    }

    public Event searchEvent(String eventName) {
        return eventTree.search(eventName);
    }

    public boolean processNextBooking() {
        if (bookingQueue.isEmpty()) return false;

        Customer customer = bookingQueue.dequeue();
        Event event = eventTree.search(customer.getRequestedEventName());

        if (event != null && event.bookTickets(customer.getRequestedTickets())) {
            processedBookings.add(customer);
            bookingHistory.add(String.format(
                    "Processed booking: Customer %s - Event %s - Tickets %d",
                    customer.getName(), event.getEventName(), customer.getRequestedTickets()));
            return true;
        }

        bookingHistory.add(String.format(
                "Failed booking: Customer %s - Event %s - Insufficient tickets",
                customer.getName(), customer.getRequestedEventName()));
        return false;
    }

    public void sortCustomersByPriority() {
        Customer[] customers = bookingQueue.toArray();
        QuickSort.sort(customers);
        bookingQueue.fromArray(customers);
        bookingHistory.add("Queue sorted by priority");
    }

    public void displayAllEvents() {
        eventTree.printAllEvents();
    }

    public void displayBookingHistory() {
        System.out.println("\nBooking History:");
        for (String entry : bookingHistory) {
            System.out.println(entry);
        }
    }

    public CustomQueue<Customer> getBookingQueue() {
        return bookingQueue;
    }

    public List<Customer> getProcessedBookings() {
        return processedBookings;
    }
}