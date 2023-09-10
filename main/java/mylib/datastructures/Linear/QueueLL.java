package mylib.datastructures.Linear;
import mylib.datastructures.nodes.DNode;

public class QueueLL extends SLL {
    /**
     * Default constructor for QueueLL
     */
    public QueueLL(){
        super();
    }
   /**
     * Overload constructor for QueueLL
     * @param node DNode to add to queue
     */
    public QueueLL(DNode node){
        super(node);
    }
    /**
    * check if queue is empty
    * @return true if queue is empty, false otherwise
    */
    public boolean isEmpty(){
        return this.head == null;
    }
     /**
    * return the head of the queue
    * @return the head node of the queue
    */
    public DNode peek(){
        return this.head;
    }
    /**
     * Deletes the whole queue
     */
    public void clear(){
        super.clear();
    }
   /**
    * enqueue implementation for Queue
    * @param node DNode to add to queue
    */
    public void enqueue(DNode node){
        super.insertTail(node);
    }
   /**
    * dequeue implementation for Queue
    * @return the dequeued DNode
    */
    public DNode dequeue(){
        DNode poppedValue = this.head;
        super.deleteHead();
        return poppedValue; 
    }
    /**
     * Search if DNode is in the queue
     * @param node the node to search for 
     * @return the value of the node being searched for if in the queue. Null otherwise
     */
    public DNode search(DNode node) {
        DNode current = this.head;
        while (current != null) {
            if (current.getData() == node.getData()) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void insertHead(DNode node) {}
    /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void insertTail(DNode node) {}
    /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void insert(DNode node, int position){}
     /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void sortedInsert(DNode node) {}
    /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void deleteHead() {}
     /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void deleteTail() {}
    /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void delete(DNode node) {}
    /**
     * override method functionality in SLL to avoid side effects
     */
    @Override
    public void sort() {}

    public static void main(String[] args) {
        System.out.println("\n====== TESTS FOR QUEUELL ======\n");
        QueueLL newQueue = new QueueLL();
        QueueLL queueItem = new QueueLL(new DNode(11));

        System.out.println("____________OVERLOADEDCONSTRUCTOR TEST___________");
        System.out.println("The expected contents:\nList length: 1\nSorted: true\nList content: 11");
        System.out.println("The actual results:");
        queueItem.print();
        System.out.println();

        System.out.println("____________ENQUEUE TEST___________");
        newQueue.enqueue(new DNode(22));
        newQueue.enqueue(new DNode(9));
        newQueue.enqueue(new DNode(12));
        newQueue.enqueue(new DNode(51));
        newQueue.enqueue(new DNode(61));
        System.out.println("The expected contents:\nList length: 5\nSorted: false\nList content: 22 9 12 51 61");
        System.out.println("The actual results:");
        newQueue.print();
        System.out.println();

        System.out.println("____________DEQUEUE TEST___________");
        DNode poppedValue = newQueue.dequeue();
        System.out.println("The expected value when dequeuing: 22");
        System.out.println("Dequeued value: " + poppedValue);
        System.out.println("\tChecking the queue print:");
        newQueue.print();
        

        System.out.println();
        DNode poppedValue2 = newQueue.dequeue();
        System.out.println("The expected value when dequeuing: 9");
        System.out.println("Dequeued value: " + poppedValue2);
        System.out.println("\tChecking the queue print:");
        newQueue.print();

        System.out.println();
        System.out.println("____________PEEK TEST___________");
        System.out.println("The expected value when peeking: 12");
        System.out.println("Peeked value: " + newQueue.peek());
        System.out.println();

        System.out.println("____________SEARCH TEST___________");
        System.out.println("Search for value: 7");
        System.out.println("The expected value for search: null");
        DNode nodeToFind = new DNode(7);
        DNode found = newQueue.search(nodeToFind);
        System.out.println("The actual results: "+found);

        System.out.println("\nSearch for value: 61");
        System.out.println("The expected value for search: 61");
        DNode nodeToFind2 = new DNode(61);
        DNode found2 = newQueue.search(nodeToFind2);
        System.out.println("The actual results: "+found2);

        System.out.println("____________ISEMPTY TEST BEFORE CLEAR___________");
        System.out.println("The expected value for search: false");
        System.out.println("The actual results: " + newQueue.isEmpty());

        System.out.println("____________CLEAR TEST___________");
        newQueue.clear();
        System.out.println("Checking queue status:");
        newQueue.print();

        System.out.println("____________ISEMPTY TEST AFTER CLEAR___________");
        System.out.println("The expected value for search: true");
        System.out.println("\nCheck if queue is empty after clear: "+ newQueue.isEmpty());
    }
}