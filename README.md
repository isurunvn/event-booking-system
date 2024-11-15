# Event Booking System

A robust Java implementation of a priority-based event booking system utilizing advanced data structures for efficient ticket management and customer queue handling.

## 🎯 Overview

The Event Booking System is designed to manage ticket reservations for multiple events while handling customer priorities and maintaining booking history. The system implements custom data structures and algorithms to ensure efficient processing of booking requests.

## 🏗️ Architecture

### Core Data Structures

- **Custom Queue**: Priority-based queue for managing customer booking requests
- **AVL Tree**: Self-balancing binary search tree for efficient event storage and retrieval
- **QuickSort**: Implementation for sorting customers based on priority and booking time

### Key Components

#### Customer Management
```java
Customer customer = new Customer(
    name,        // Customer name
    priority,    // Booking priority (higher number = higher priority)
    tickets,     // Number of requested tickets
    eventName    // Target event name
);
```

#### Event Management
```java
Event event = new Event(
    eventName,     // Name of the event
    date,         // Event date
    totalTickets, // Total available tickets
    ticketPrice,  // Price per ticket
    venue         // Event venue
);
```

## ⚡ Features

### Priority Queue Management
- Custom implementation of a queue data structure
- Handles customer bookings based on priority levels
- Maintains FIFO order within same priority level
- O(1) enqueue and dequeue operations

### Event Search and Storage
- AVL Tree implementation for event management
- O(log n) search, insert, and delete operations
- Self-balancing to maintain optimal search efficiency
- Event lookup by name

### Booking Process
- Validates ticket availability
- Processes requests in priority order
- Maintains comprehensive booking history
- Thread-safe ticket allocation

### Customer Sorting
- QuickSort implementation for priority-based sorting
- Sorts based on:
  - Customer priority (primary criterion)
  - Booking timestamp (secondary criterion)

## 🛠️ Technical Implementation

### Class Structure

```plaintext
BookingSystem/
├── src/
│   ├── main/
│   │   ├── BookingSystem.java     # Main system orchestrator
│   │   ├── Customer.java          # Customer entity
│   │   ├── Event.java            # Event entity
│   │   ├── CustomQueue.java      # Queue implementation
│   │   ├── AVLTree.java         # AVL tree implementation
│   │   ├── AVLNode.java         # AVL tree node
│   │   └── QuickSort.java       # Sorting algorithm
└── test/                        # Test cases
```

### Time Complexities

| Operation | Time Complexity |
|-----------|----------------|
| Event Search | O(log n) |
| Customer Enqueue | O(1) |
| Customer Dequeue | O(1) |
| Priority Sort | O(n log n) |
| Event Insert | O(log n) |

## 💻 Usage

### Creating a Booking System
```java
BookingSystem bookingSystem = new BookingSystem();
```

### Adding Events
```java
Event concert = new Event("Rock Concert", "2024-11-01", 100, 50.0, "Stadium Arena");
bookingSystem.addEvent(concert);
```

### Processing Bookings
```java
// Add customer to queue
Customer customer = new Customer("John", 1, 2, "Rock Concert");
bookingSystem.addCustomerToQueue(customer);

// Process next booking
bookingSystem.processNextBooking();
```

## 🔍 Key Features Explained

### Priority-Based Processing
- Customers with higher priority are processed first
- Within same priority, earlier bookings take precedence
- Priority levels can be customized based on business rules

### Event Management
- Fast event lookup using AVL tree
- Maintains accurate ticket inventory
- Prevents overbooking
- Supports multiple concurrent events

### Booking History
- Tracks all booking attempts
- Records successful and failed bookings
- Maintains customer booking patterns
- Supports audit requirements

## 🚀 Performance Considerations

- **Memory Usage**: O(n) where n is the number of events + customers
- **Search Efficiency**: O(log n) for event lookups
- **Queue Operations**: O(1) for standard operations
- **Sorting**: O(n log n) for priority sorting

## 🔒 Thread Safety

- Synchronized methods for ticket booking
- Safe concurrent access to event data
- Protected queue operations
- Atomic booking transactions

## 🎯 Use Cases

1. **Theater Booking Systems**
   - Managing seat reservations
   - Handling VIP customer priorities
   - Multiple show management

2. **Concert Venue Management**
   - Large-scale ticket sales
   - Priority-based booking
   - Multiple event handling

3. **Sports Event Ticketing**
   - Season ticket holder priority
   - Multiple game management
   - Capacity management
