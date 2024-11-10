public class Event {
    private String eventName;
    private String date;
    private int availableTickets;
    private int totalTickets;
    private double ticketPrice;
    private String venue;

    public Event(String eventName, String date, int totalTickets, double ticketPrice, String venue) {
        this.eventName = eventName;
        this.date = date;
        this.totalTickets = totalTickets;
        this.availableTickets = totalTickets;
        this.ticketPrice = ticketPrice;
        this.venue = venue;
    }

    // Getters
    public String getEventName() { return eventName; }
    public String getDate() { return date; }
    public int getAvailableTickets() { return availableTickets; }
    public int getTotalTickets() { return totalTickets; }
    public double getTicketPrice() { return ticketPrice; }
    public String getVenue() { return venue; }

    public synchronized boolean bookTickets(int numberOfTickets) {
        if (availableTickets >= numberOfTickets) {
            availableTickets -= numberOfTickets;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Event{name='%s', date='%s', available=%d/%d, price=%.2f, venue='%s'}",
                eventName, date, availableTickets, totalTickets, ticketPrice, venue);
    }
}
