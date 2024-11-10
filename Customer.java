public class Customer implements Comparable<Customer> {
    private String name;
    private long bookingTime;
    private int priority;
    private int requestedTickets;
    private String requestedEventName;

    public Customer(String name, int priority, int requestedTickets, String requestedEventName) {
        this.name = name;
        this.bookingTime = System.currentTimeMillis();
        this.priority = priority;
        this.requestedTickets = requestedTickets;
        this.requestedEventName = requestedEventName;
    }

    // Getters
    public String getName() { return name; }
    public long getBookingTime() { return bookingTime; }
    public int getPriority() { return priority; }
    public int getRequestedTickets() { return requestedTickets; }
    public String getRequestedEventName() { return requestedEventName; }

    @Override
    public int compareTo(Customer other) {
        int priorityCompare = Integer.compare(other.priority, this.priority);
        if (priorityCompare != 0) {
            return priorityCompare;
        }
        return Long.compare(this.bookingTime, other.bookingTime);
    }

    @Override
    public String toString() {
        return String.format("Customer{name='%s', priority=%d, requestedTickets=%d, event='%s'}",
                name, priority, requestedTickets, requestedEventName);
    }
}
