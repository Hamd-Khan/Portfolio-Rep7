package mylib.datastructures.Linear;

import mylib.datastructures.nodes.DNode;

public class DLL extends SLL {
    /**
     * Default constructor that creates a empty DLL
     */
    public DLL() {
        this.head = null;
        this.tracker = 0;
        this.tail = null;
    }

    /**
     * Overloaded constructor that initializes a DLL with an arg object
     * @param node a DNode object to be  inserted into the DLL
     */
    public DLL(DNode node) {
        this.head = node;
        this.tail = node;
        this.tracker =1;
    }




    /**
     * func: Insert, inserts node object in specified position
     * @param node - DNode object to be inserted
     * @param position - index of  position to insert node
     */

    @Override
    public void insert(DNode node, int position) throws IndexOutOfBoundsException {
       if (position < 0 || position > size()) {
           throw new IndexOutOfBoundsException();
       }
   
       if (position == 0) { // inserting at the beginning of the list
           node.setNext(head);
           if (head != null) {
               head.setPrevious(node);
           }
           head = node;
           if (tail == null) {
               tail = node;
           }
       } else if (position == size()) { // inserting at the end of the list
           node.setPrevious(tail);
           if (tail != null) {
               tail.setNext(node);
           }
           tail = node;
           if (head == null) {
               head = node;
           }
       } else { // inserting in the middle of the list
           DNode temp = getNode(position - 1);
           node.setNext(temp.getNext());
           node.setPrevious(temp);
           temp.getNext().setPrevious(node);
           temp.setNext(node);
       }
       this.tracker++;
   }
  

    /**
     * func: InsertHead which inserts a node object at the head of the list
     * @param node - DNode onject to be inserted at head
     */
    @Override
    public void insertHead(DNode node) {

        if (this.tracker != 0) {
            node.setNext(this.head);
            this.head.setPrevious(node);
            node.setPrevious(null);
            this.head = node;
            
            this.tracker++;

        } else {
            node.setPrevious(null);
            node.setNext(null);
            this.head = node;
            this.tail = node;
            this.tracker++;
        }
       
    }



    /**
     * func: InsertTail insets node object at the tail of the list
     * @param node - DNode onject to be inserted at tail
     */

   
    @Override
    public void insertTail(DNode node) {
        if (this.tracker != 0) {
            node.setPrevious(this.tail);
            this.tail.setNext(node);
            node.setNext(null);
            this.tail = node;
        } else {
            this.head = node;
            this.tail = node;
            node.setNext(null);
            node.setPrevious(null);
        }
        this.tracker++;
    }
    

     /**
     * func: SortedInsert, inserts node object in its proper position in a sorted list
     * @param node - DNode to be inserted in sorted linked list
     */
    @Override
    public void sortedInsert(DNode node) {
        node.setNext(null);
        node.setPrevious(null);
    
        if (!isSorted()) {
            this.sort();
        }
    
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else if (node.getData() < this.head.getData()) {
            node.setNext(this.head);
            this.head.setPrevious(node);
            this.head = node;
        } else {
            recursiveInsert(node, this.head);
        }
        this.tracker++;
    }
    
    /**
     * func: recursiveInsert, helper function used in sortedInsert
     * @param node - DNode to be inserted in sorted linked list
     * @param current- DNode current Dnose in list
     */
    private void recursiveInsert(DNode node, DNode current) {
        if (current.getData() <= node.getData()) {
            if (current.getNext() != null) {
                recursiveInsert(node, current.getNext());
            } else {
                current.setNext(node);
                node.setPrevious(current);
                this.tail = node;
            }
        } else {
            node.setPrevious(current.getPrevious());
            node.setNext(current);
            if (current.getPrevious() == null) {
                this.head = node;
            } else {
                current.getPrevious().setNext(node);
            }
            current.setPrevious(node);
        }
    }
    
     /**
     * func: getNode, helper function used to retrieve a node
     * @param index- integer to retrieve node from
     */
    private DNode getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        DNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;
    }
    
   

    /**
     * func: Sort, applies insertion sort to the list 
     */
 
    @Override
    public void sort() {
        // If the list is empty or has only one node, it is already sorted
        if (this.head == null || this.head.getNext() == null || this.isSorted()) {
            return;
        }

        boolean swapped;
        DNode current;
        do {
            swapped = false;
            current = this.head;
            while (current != null && current.getNext() != null) {
                if (current.getData() > current.getNext().getData()) {
                    swapNodes(current, current.getNext());
                    swapped = true;
                } else {
                    current = current.getNext();
                }
            }
        } while (swapped);
    }

    private void swapNodes(DNode node1, DNode node2) {
        int temp = node1.getData();
        node1.setData(node2.getData());
        node2.setData(temp);
    }



      /**
         * Deletes the given DNode object from the linked list, if it exists in the list.
         * @param node - DNode object to be deleted from the linked list
         */
     
        @Override
        public void delete(DNode node) {
            if (this.head.getData() == node.getData()) {
                this.deleteHead();
                return;
            } else if (this.tail.getData() == node.getData()) {
                this.deleteTail();
                return;
            } else {
                DNode holder= this.head;
                while (holder != null) {
                    if (holder.getData() == node.getData()) {
                        DNode nodeBefore = holder.getPrevious();
                        nodeBefore.setNext(holder.getNext());
                        nodeBefore.getNext().setPrevious(nodeBefore);
                        this.tracker--;
                        return;
                    }
                    holder= holder.getNext();
                }

                System.out.println("value not found");
            }

   
        }


         /**
         * func: DeleteHead, deletes head node
         */
        @Override
        public void deleteHead() { 
          super.deleteHead();
          if(this.head == null){
            return;
        }
            else{
            this.head.setPrevious(null);
        }
           
        }



         /**
         * func: DeleteTail, deletes node at the tail
         * Deletes node at tail
         */
        @Override
        public void deleteTail() {
            if (head == null) {
                return;
            }
        if (head.getNext() == null) {
            head = null;
            tracker = 0;
            tail = null;
            return;
        }
        DNode current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
        tracker--;
    }

public static void main(String[] args){
   
    System.out.println("\n======  DOUBLY LINKED LIST ======= \n");
   
   
            
    // Constructors

    SLL testerDLL = new DLL();
    SLL overloadDLL = new DLL(new DNode(14));
    System.out.println("____________OVERLOADED CONSTRUCTOR TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after call to overloaded constructor:\nList length: 1\nSorted: true\nList content: 14");
    System.out.println("The actual results:");
    overloadDLL.print();


    // Insertion methods tests
    testerDLL.insert(new DNode(18), 0);
    testerDLL.insert(new DNode(1), 1);
    System.out.println("____________REGULAR INSERTION TESTS___________");
    System.out.print("\n");
    System.out.println("The expected contents after inserting 18 and 1 at indexes 0 and 1 respectively:\nList length: 2\nSorted: false\nList content: 18 1");
    System.out.println("The actual results:");
    testerDLL.print();



  
        testerDLL.insertHead(new DNode(33));
       

        System.out.println("____________INSERTION HEAD TEST___________");
        System.out.print("\n");
        System.out.println("The expected contents after inserting 33 at head:\nList length: 3\nSorted: false\nList content: 33 18 1");
        System.out.println("The actual results:");
        testerDLL.print();
        

    System.out.println("____________INSERTION TAIL TEST___________");
    System.out.print("\n");


 
  
    testerDLL.insertTail(new DNode(24));
    System.out.println("The expected contents after inserting 24 at tail:\nList length: 4\nSorted: false\nList content: 33 18 1 24");
    System.out.println("The actual results:");
    testerDLL.print();

    testerDLL.sort();
    testerDLL.sortedInsert(new DNode(0));
    testerDLL.sortedInsert(new DNode(11));
    testerDLL.sortedInsert(new DNode(27));
    testerDLL.insertHead(new DNode(39));
    testerDLL.sort();
    testerDLL.sortedInsert(new DNode(4));
    System.out.println("____________SORT and SORTED INSERTION TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after calling sort method and various insert calls:\nList length: 9\nSorted: true\nList content:  0 1 4 11 18 24 27 33 39 ");
    System.out.println("The actual results:");
    testerDLL.print();

    testerDLL.delete(new DNode(24));


    System.out.println("____________DELETE Node TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after deleting 24:\nList length: 8\nSorted: true\nList content: 0 1 4 11 18 27 33 39");
    System.out.println("The actual results:");
    testerDLL.print();

    testerDLL.deleteHead();

    System.out.println("____________DELETE HEAD TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after deleting head:\nList length: 7\nSorted: true\nList content:  1 4 11 18 27 33 39");
    System.out.println("The actual results:");
    testerDLL.print();


    testerDLL.deleteTail();

    System.out.println("____________DELETE TAIL TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after deleting tail:\nList length: 6\nSorted: true\nList content:  1 4 11 18 27 33");
    System.out.println("The actual results:");
    testerDLL.print();

    testerDLL.delete(new DNode(13));
    System.out.println("____________DELETE NOT IN LIST TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents when trying to delete 13:\nvalue not found");
    System.out.println("The actual results:");
    testerDLL.delete(new DNode(13));
   
    System.out.println("____________SEARCH  TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents when Searching for 4:\n4");
    System.out.println("The actual results:");
    System.out.println(testerDLL.search(new DNode(4)));

    System.out.println("____________SEARCH NOT IN TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents when Searching for 123:\nnull");
    System.out.println("The actual results:");
    System.out.println(testerDLL.search(new DNode(123)));
  
    System.out.println("____________CLEAR TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents:\nList length: 0\nSorted: true\nList content:");
    System.out.println("The actual results:");
    testerDLL.clear();
    testerDLL.print();
    System.out.println("____________TESTS COMPLETE___________");

}

}


    