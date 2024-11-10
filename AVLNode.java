public class AVLNode {
    Event event;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(Event event) {
        this.event = event;
        this.height = 1;
    }
}
