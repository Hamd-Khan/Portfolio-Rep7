package mylib.datastructures.Linear;
import mylib.datastructures.nodes.DNode;

public class StackLL extends SLL {

     /**
     * Default constructor for StackLL
     */
    public StackLL() {
        super();
    }
    /**
     * Overload constructor
     * @param node DNode to insert in stack
     */
    public StackLL(DNode node){
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
    * return the head of the stack
    * @return the head node of the stack
    */
    public DNode peek() {
        return this.head;
    }
    /**
     * Deletes the whole stack
     */
    public void clear(){
        super.clear();
    }
    /**
     * push implementation for stack
     * @param node DNode to add to stack
     */
    public void push(DNode node) {
        super.insertHead(node);
    }
    /**
     * pop implementation for stack
     * @return the popped DNode
     */
    public DNode pop() {
        DNode poppedValue = this.head;
        super.deleteHead();
        return poppedValue; 
    }
   /**
     * Search if DNode is in the stack
     * @param node the node to search for 
     * @return the value of the node being searched for if in the stack. Null otherwise
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
    public void insertTail(DNode node){}
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
        System.out.println("\n====== TESTS FOR STACKLL ======\n");
        StackLL newStack = new StackLL();
        StackLL stackItem = new StackLL(new DNode(8));

        System.out.println("____________OVERLOADEDCONSTRUCTOR TEST___________");
        System.out.println("The expected contents:\nList length: 1\nSorted: true\nList content: 8");
        System.out.println("The actual results:");
        stackItem.print();
        System.out.println();

        System.out.println("____________PUSH TEST___________");
        newStack.push(new DNode(46));
        newStack.push(new DNode(17));
        newStack.push(new DNode(15));
        newStack.push(new DNode(9));
        newStack.push(new DNode(2));
        newStack.push(new DNode(122));
        System.out.println("pushed values 46, 17, 15, 9, 2, 122");
        System.out.println("The expected contents:\nList length: 6\nSorted: false\nList content: 122 2 9 15 17 46");
        newStack.print();

        System.out.println("____________POP TEST___________");
        DNode poppedValue = newStack.pop();
        System.out.println("Expected popped value: 122");
        System.out.println("Popped value: " + poppedValue);
        System.out.println("\tChecking the stack print:");
        newStack.print();

        System.out.println();
        DNode poppedValue2 = newStack.pop();
        System.out.println("Expected popped value: 2");
        System.out.println("Popped value: " + poppedValue2);
        System.out.println("\tChecking the stack print:");
        newStack.print();

        System.out.println("____________PEEK TEST___________");
        System.out.println("The expected value when peeking: 9");
        System.out.println("Peeked value: " + newStack.peek());
        System.out.println();

        System.out.println("____________SEARCH TEST___________");
        System.out.println("Search for value: 5");
        System.out.println("The expected value for search: null");
        DNode nodeToFind = new DNode(5);
        DNode found = newStack.search(nodeToFind);
        System.out.println("The actual results: "+found);

        System.out.println("Search for value: 17");
        System.out.println("The expected value for search: 17");
        DNode nodeToFind2 = new DNode(17);
        DNode found2 = newStack.search(nodeToFind2);
        System.out.println("The actual results: "+found2);
        
        System.out.println("____________ISEMPTY TEST BEFORE CLEAR___________");
        System.out.println("The expected value: False");
        System.out.println("The actual results: " + newStack.isEmpty());

        System.out.println("____________CLEAR TEST___________");
        newStack.clear();
        System.out.println("Checking queue status:");
        newStack.print();

        System.out.println("____________ISEMPTY TEST AFTER CLEAR___________");
        System.out.println("The expected value for search: true");
        System.out.println("\nCheck if queue is empty after clear: "+ newStack.isEmpty());
    }
}


