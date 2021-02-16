import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UnrolledLinkedListDoubleEndedQueue {

    private class Node {
        int[] array;
        int first;
        int last;

        Node nextNode;
        Node previousNode;

        public Node(Node previousIn, Node nextIn) {
            array = new int[4];

            nextNode = nextIn;
            previousNode = previousIn;

            if (previousIn == null && nextIn != null) {
                first = array.length;
                last = array.length;
            } else {
                first = 0;
                last = 0;
            }
        }

        public void addNextNode(Node nodeIn) {
            nextNode = nodeIn;
        }

        public void removePreviousNode() {
            previousNode = null;
        }

        public void addPreviousNode(Node nodeIn) {
            previousNode = nodeIn;
        }

        public void removeNextNode() {
            nextNode = null;
        }

        public boolean isFull() {
            return  first == array.length;
        }

        public boolean isEmpty() {
            return last == array.length;
        }

        public boolean isFullBot() {
            return last == 0;
        }

        public boolean isEmptyTop() {
            return first == 0;
        }

        public void addFirst(int itemIn) {
            array[first] = itemIn;
            first++;
        }

        public int removeLast() {
            int itemOut = array[last];
            array[last] = 0;
            last++;

            return itemOut;
        }

        public void addLast(int itemIn) {
            array[last - 1] = itemIn;
            last--;
        }

        public int removeFirst() {
            int itemOut = array[first - 1];
            array[first - 1] = 0;
            first--;

            return itemOut;
        }

        public void print() {
            for (int value : array) {
                System.out.println(value);
            }
            System.out.println("|");
        }
    }

    private Node firstNode;
    private Node lastNode;

    public UnrolledLinkedListDoubleEndedQueue() {
        firstNode = new Node(null, null);
        lastNode = firstNode;
    }

    public void addFirst(int itemIn) {
        if (firstNode.isFull()) {
            System.out.println("The node is going to be created");
            firstNode.addNextNode(new Node(firstNode, null));
            firstNode = firstNode.nextNode;
        }

        firstNode.addFirst(itemIn);
    }

    public int removeLast() {
        if (lastNode.isEmpty()) {
            System.out.println("The node is going to be deleted");
            lastNode.nextNode.removePreviousNode();
            lastNode = lastNode.nextNode;
        }

        return lastNode.removeLast();
    }

    public void addLast(int itemIn) {
        if (lastNode.isFullBot()) {
            System.out.println("The node is going to be created at the bot");
            lastNode.addPreviousNode(new Node(null, lastNode));
            lastNode = lastNode.previousNode;
        }

        lastNode.addLast(itemIn);
    }

    public int removeFirst() {
        if (firstNode.isEmptyTop()) {
            System.out.println("The node is going to be deleted at the top");
            firstNode.previousNode.removeNextNode();
            firstNode = firstNode.previousNode;
        }

        return firstNode.removeFirst();
    }

    public void print() {
        Node node = lastNode;
        node.print();
        while (node.nextNode != null) {
            node = node.nextNode;
            node.print();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        UnrolledLinkedListDoubleEndedQueue q = new UnrolledLinkedListDoubleEndedQueue();

        boolean flag = true;
        boolean isFirst = true;
        while (flag) {
            String input = reader.readLine();
            if (input.equals("f") || input.equals("F")) {
                isFirst = true;
                System.out.println("Add and remove operations would be performed on the FRONT of the queue");
            } else if (input.equals("b") || input.equals("B")) {
                isFirst = false;
                System.out.println("Add and remove operations would be performed on the BACK of the queue");
            } else if (input.equals("d") || input.equals("D") || input.equals("-")) {
                if (isFirst) {
                    System.out.println(q.removeFirst() + " Was removed");
                } else {
                    System.out.println(q.removeLast() + " Was removed");
                }
            } else if (input.equals("p") || input.equals("P")) {
                q.print();
            } else if (input.equals("")) {
                ;
            } else {
                int item = Integer.parseInt(input);
                if (isFirst) {
                    q.addFirst(item);
                } else {
                    q.addLast(item);
                }
            }
        }
    }
}
