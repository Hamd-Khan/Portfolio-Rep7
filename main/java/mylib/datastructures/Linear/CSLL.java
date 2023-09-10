package mylib.datastructures.Linear;
import mylib.datastructures.nodes.DNode;

public class CSLL extends SLL {

    /**
     * Default constructor for CSLL
     */
    public CSLL() {
        super();
    }

    /**
     * Overload constructor for CSLL
     * @param node node to insert into CSLL
     */
    public CSLL(DNode node) {
        super(node);
        node.setNext(node);
    }

    /**
     * insert node to the head position
     * @param node node to insert
     */
    @Override
    public void insertHead(DNode node) {
        super.insertHead(node);
        this.tail.setNext(this.head);
    }

    /**
     * checks if CSLL is empty
     * @return true if CSLL is empty
     */
    public boolean isEmpty() {
        return this.tracker == 0;
    }
    /**
     * insert node to the tail position
     * @param node node to insert
     */
    @Override
    public void insertTail(DNode node) {
        if (this.tracker == 0) {
            this.tail = node;
            this.head = node;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }
        this.tracker++;
        this.tail.setNext(this.head);
    }

    /**
     * insert a node into a specified index in the CSLL
     * @param node node to insert into CSLL
     * @param position index to insert node
     */
    @Override
    public void insert(DNode node, int position) throws IndexOutOfBoundsException {
        if (position < 0 || position > this.tracker) {
            throw new IndexOutOfBoundsException();
        }
        if (this.tracker == 0) {
            this.head = node;
            this.tail = node;
            node.setNext(node);
        } else if (position == 0) {
            node.setNext(this.head);
            this.head = node;
            this.tail.setNext(node);
        } else {
            DNode current = this.head;
            for (int i = 0; i < position - 2; i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
            if (position == this.tracker) {
                this.tail = node;
            }
        }
        this.tracker++;
    }
    
    /**
     * insert a node into the CSLL. Sorts the CSLL if not already sorted
     * @param node node to insert into CSLL
     */
    @Override
    public void sortedInsert(DNode node) {
        if (this.tracker == 0) {
            // Insert into an empty list
            this.head = node;
            this.tail = node;
            node.setNext(this.head);
            this.tracker++;
            return;
        }
        if (node.getData() <= this.head.getData()) {
            // Insert at the beginning
            this.tail.setNext(node);
            node.setNext(this.head);
            this.head = node;
        } else if (node.getData() >= this.tail.getData()) {
            // Insert at the end
            this.tail.setNext(node);
            node.setNext(this.head);
            this.tail = node;
        } else {
            // Insert in the middle
            DNode nodeHead = this.head.getNext();
            DNode nodeTail = this.head;
            while (nodeHead != this.head) {
                if (node.getData() <= nodeHead.getData()) {
                    nodeTail.setNext(node);
                    node.setNext(nodeHead);
                    break;
                }
                nodeTail = nodeHead;
                nodeHead = nodeHead.getNext(); }
        }
        if (!isSorted()) {
            sort(); }
        this.tracker++;
    }

    /**
     * determines if CSLL is sorted
     * @return true if CSLL is sorted, false otherwise
     */
    @Override
    public boolean isSorted() {
        if (this.tracker == 0 || this.tracker == 1) {
            return true;
        }
        DNode current = this.head;
        while (current.getNext() != this.head) {
            if (current.getData() > current.getNext().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return this.head.getData() <= current.getData();
    }
    

    /**
     * searches for a specified node in the CSLL
     * @param node node to search for
     * @return node if node is found, else returns null
     */
    @Override
    public DNode search(DNode node) {
       return super.search(node);
    }

    /**
     * Deletes the node in the head position
     */
    @Override
    public void deleteHead() {
        super.deleteHead();
        this.tail.setNext(this.head);
    }

    /**
     * Deletes the node in the tail position
     */
    @Override
    public void deleteTail() {
        super.deleteTail();
        this.tail.setNext(this.head);
        ;
    }

    /**
     * deletes a specific node from CSLL
     * @param node node to be deleted
     */
    @Override
    public void delete(DNode node) {
        super.delete(node);
        this.tail.setNext(this.head);
    }

    /**
     * sorts the CSLL
     */
    @Override
    public void sort() {
        if (this.isSorted()) {
            return;
        }
        DNode nodeHead = this.head;
        do {
            DNode minNode = nodeHead;
            DNode temp = nodeHead.getNext();
            while (temp != this.head) {
                if (temp.getData() < minNode.getData()) {
                    minNode = temp;
                }
                temp = temp.getNext();
            }
            int tempData = nodeHead.getData();
            nodeHead.setData(minNode.getData());
            minNode.setData(tempData);
            nodeHead = nodeHead.getNext();
        } while (nodeHead != this.head);
    }
    
    /**
     * Clears the CSLL
     */
    @Override
    public void clear() {
        this.tail = null;
        this.tracker = 0;
        this.head = null;
    }

    /**
     * Prints the list information to the terminal
     */
   @Override
   public void print() {
       System.out.println("List length: " + this.tracker);
       boolean sorted = this.isSorted();
       System.out.println("Sorted: " + sorted);
       System.out.print("List content: ");
       if (this.head == null) {
           System.out.println("empty");
           return;
       }
       DNode currentHead = this.head;
       do {
           System.out.print(currentHead.getData() + " ");
           currentHead = currentHead.getNext();
       } while (currentHead != this.head);
       System.out.println();
   }

    public static void main(String[] args) {
        System.out.println("\n====== CIRCULAR SINGLY LINKED LIST ======n");
        CSLL circular = new CSLL();
        System.out.println("____________OVERLOADCONSTRUCTOR TEST___________");
        CSLL circularItem = new CSLL(new DNode(14));
        System.out.println("The expected contents:\nList length: 1\nSorted: true\nList content: 14");
        System.out.println("The actual results:");
        circularItem.print();
        
        System.out.println("____________INSERTTAIL TEST___________");
        circular.insertTail(new DNode(16));
        circular.insertTail(new DNode(55));
        circular.insertTail(new DNode(15));
        System.out.println("The expected contents:\nList length: 3\nSorted: false\nList content: 16 55 15");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________INSERT TEST___________");
        circular.insert(new DNode(17), 2);
        circular.insert(new DNode(22), 0);
        System.out.println("The expected contents:\nList length: 5\nSorted: false\nList content: 22 16 17 55 15");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________INSERTHEAD TEST___________");
        circular.insertHead(new DNode(87));
        circular.insertHead(new DNode(63));
        System.out.println("The expected contents:\nList length: 7\nSorted: false\nList content: 63 87 22 16 17 55 15");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________SORTEDINSERT TEST___________");
        circular.sortedInsert(new DNode(6));
        System.out.println("The expected contents:\nList length: 8\nSorted: true\nList content: 6 15 16 17 22 55 63 87");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________DELETETAIL TEST___________");
        circular.deleteTail();
        System.out.println("The expected contents:\nList length: 7\nSorted: true\nList content: 6 15 16 17 22 55 63");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________DELETEHEAD TEST___________");
        circular.deleteHead();
        System.out.println("The expected contents:\nList length: 6\nSorted: true\nList content: 15 16 17 22 55 63");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________SEARCH TEST___________");
        System.out.println("Search for 17: ");
        System.out.println("Expected result: 17");
        System.out.println("Actual result: "+circular.search(new DNode(17)));
        System.out.println("Search for 1234: ");
        System.out.println("Expected result: null");
        System.out.println("Actual result: "+circular.search(new DNode(1234)));

        System.out.println("____________DELETE TEST___________");
        circular.delete(new DNode(55));
        System.out.println("The expected contents:\nList length: 5\nSorted: true\nList content: 15 16 17 22 63");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________SORT TEST___________");
        System.out.println("Adding items to unsort list again:");
        circular.insert(new DNode(98), 2);
        System.out.println("The expected contents:\nList length: 6\nSorted: false\nList content: 15 16 98 17 22 63");
        circular.print();
        System.out.println("The actual results:");
        System.out.println("Now sorting: ");
        circular.sort();
        System.out.println("The expected contents:\nList length: 6\nSorted: true\nList content: 15 16 17 22 63 98");
        System.out.println("The actual results:");
        circular.print();

        System.out.println("____________ISEMPTY TEST BEFORE CLEAR___________");
        System.out.println("The expected value for search: false");
        System.out.println("Checking if CSLL is empty: " + circular.isEmpty());

        System.out.println("____________CLEAR TEST___________");
        circular.clear();
        circular.print();

        System.out.println("____________ISEMPTY TEST AFTER CLEAR___________");
        System.out.println("The expected value for search: true");
        System.out.println("Checking if CSLL is empty: " + circular.isEmpty());
    }
}