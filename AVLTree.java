public class AVLTree {
    private AVLNode root;

    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(Event event) {
        root = insertRec(root, event);
    }

    private AVLNode insertRec(AVLNode node, Event event) {
        if (node == null) return new AVLNode(event);

        int compareResult = event.getEventName().compareTo(node.event.getEventName());

        if (compareResult < 0)
            node.left = insertRec(node.left, event);
        else if (compareResult > 0)
            node.right = insertRec(node.right, event);
        else
            return node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && event.getEventName().compareTo(node.left.event.getEventName()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && event.getEventName().compareTo(node.right.event.getEventName()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && event.getEventName().compareTo(node.left.event.getEventName()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && event.getEventName().compareTo(node.right.event.getEventName()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public Event search(String eventName) {
        AVLNode result = searchRec(root, eventName);
        return result == null ? null : result.event;
    }

    private AVLNode searchRec(AVLNode node, String eventName) {
        if (node == null || node.event.getEventName().equals(eventName))
            return node;

        if (eventName.compareTo(node.event.getEventName()) < 0)
            return searchRec(node.left, eventName);

        return searchRec(node.right, eventName);
    }

    // Method to print all events (in-order traversal)
    public void printAllEvents() {
        System.out.println("All Available Events:");
        printInOrder(root);
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(node.event);
            printInOrder(node.right);
        }
    }
}
