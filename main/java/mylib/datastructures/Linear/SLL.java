package mylib.datastructures.Linear;

import mylib.datastructures.nodes.DNode;

public class SLL {
    protected DNode head;
    protected DNode tail;
    protected int tracker;

    /**
     * Default constructor it  creates a null SLL
     */
    public SLL() {
        this.head = null;
        this.tracker = 0;
        this.tail = null;
    
    }

    /**
     * Overloaded constructor that creates a SLL with one object
     * @param node a DNode object to be inserted into the SLL
     */
    public SLL(DNode node) {
        this.tracker = 1;
        this.head = node;
        this.tail = node;
    }


        /**
     * func: Insert, inserts node object in specified position
     * @param node - DNode object to be inserted
     * @param position - index of  position to insert node
     */
    public void insert(DNode node, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        
        if (index == 0) {
            node.setNext(head);
            head = node;
            if (tail == null) {
                tail = node;
            }
        } else {
            DNode tempNode = head;
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.getNext();
            }
            node.setNext(tempNode.getNext());
            tempNode.setNext(node);
            if (index == size()) {
                tail = node;
            }
        }

        this.tracker++;
    }
    

    /**
     * func: InsertHead which inserts a node object at the head of the list
     * @param node - DNode onject to be inserted at head
     */
    public void insertHead(DNode node) {

        if (this.tracker != 0) {
            node.setNext(this.head);
            this.head = node;
            
            this.tracker++;

        } else {
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

    public void insertTail(DNode node) {
        if (this.tracker == 0) {
            this.head = node;
            this.tail = node;
        } else {

            DNode current = this.head;
            while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(node);
            }
        
        this.tracker++;
    }


        /**
     * func: SortedInsert, inserts node object in its proper position in a sorted list
     * @param node - DNode to be inserted in sorted linked list
     */
    public void sortedInsert(DNode node) {
        node.setNext(null);
        node.setPrevious(null);
    
        if (!this.isSorted()) {
            this.sort();
        }

        if (this.head == null || node.getData() < this.head.getData()) {
            node.setNext(this.head);
            if (this.head != null) {
                this.head.setPrevious(node);
            }
            this.head = node;
            if (this.tail == null) {
                this.tail = node;
            }
        } else {
           
            DNode holder = this.head;
            while (holder.getNext() != this.tail.getNext() && holder.getNext().getData() <= node.getData()) {
                holder = holder.getNext();
            }
            if (holder.getNext() != null) {
                node.setNext(holder.getNext());
                holder.getNext().setPrevious(node);
                holder.setNext(node);
                node.setPrevious(holder);
            } else {
                holder.setNext(node);
                node.setPrevious(holder);
                this.tail = node;
            }

        }
        this.tracker++;
    }
    

      /**
     * func: isSorted , returns boolean weather the linked list is sorted or not
     * @return - true or false depending on if list is sorted
     */
    protected boolean isSorted() {
        DNode current = this.head;
        while (current != null && current.getNext() != null) {
            if (current.getData() > current.getNext().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
    

/*func: size a helper func to return the size of linked list
    @returns int 
 * 
 */
public int size() {
    int count = 0;
    DNode currNode = this.head;
    while (currNode != null) {
        count++;
        currNode = currNode.getNext();
    }
    return count;
}

  
    /**
     * func: Search, looks up node in the list if found returns object else returns null
     * @param node - DNode object you wantto find
     * @return - the DNode object if its in the list otherwise it returns a null value
     */
    public DNode search(DNode node) {
        DNode currentNode = this.head;
        while (currentNode != null){
            if (node.getData()== (currentNode.getData())) {
                return currentNode;
            } else {
                currentNode = currentNode.getNext();
                if( currentNode == this.head){
                    break;
                }
            }
        }
        return null;
    }
           /**
         * func: delete, Deletes the DNode argument object from the linked list, if its in the list
         * @param node - DNode object to be deleted from the linked list
         */
        public void delete(DNode node) {
            if (this.head.getData() == node.getData()) {
                this.head = this.head.getNext();
                this.tracker--;
                if (this.head == null) {
                    this.tail = null;
                }
                return;
            }
    

            if (this.tail.getData() == node.getData()) {
                DNode temp = this.head;
                int i = 0;
                while (i < this.tracker - 2) {
                    temp = temp.getNext();
                    i++;
                }
                temp.setNext(null);
                this.tail = temp;
                this.tracker--;
                return;
            }

            DNode previousNode = this.head;
            int j= 0;
            while (j < this.tracker - 1) {
                DNode current = previousNode.getNext();
                if (current.getData() == node.getData()) {
                    previousNode.setNext(current.getNext());
                    this.tracker--;
                    return;
                }
                
                previousNode = previousNode.getNext();
                j++;
            }
        }

        /**
         * func: DeleteHead, deletes head node
         */
        public void deleteHead() {
         
            if (this.head == null) {
                return;
            }
           
            if (this.head.getNext() == null) {
                this.tail = null;
            }
            this.head = this.head.getNext();
            this.tracker--;
        }
    /**
     * func: DeleteTail, deletes node at the tail
     * Deletes node at tail
     */
    public void deleteTail() {
        if (this.tail == null) {
            return;
        } else if (this.head == this.tail) {
            
            this.head = null;
            this.tail = null;
        } else {
            DNode holder = this.head;
            while (holder.getNext() != this.tail) {
                holder = holder.getNext();
            }
            holder.setNext(null);
            this.tail = holder;
        }
        this.tracker--;
    }
    /**
     * func: Sort, applies insertion sort to the list 
     */
    public void sort() {
        if ( this.isSorted() || this.head.getNext() == null || this.head == null) {
            return;
        }
        DNode holder = this.head.getNext();
        DNode previousNode = this.head;
        while (holder != this.tail.getNext()) {
            if (holder.getData() < previousNode.getData()) {
                previousNode.setNext(holder.getNext());
                this.tracker--;
                sortedInsert(holder);
                if (previousNode.getNext() == null) {
                    this.tail = previousNode;
                }
                holder = previousNode.getNext();
            } else {
                previousNode = holder;
                holder = previousNode.getNext();
            }
        }
    }

    /**
     * func: Clear, deletes the whole list
     * Clears the entire list
     */
    public void clear() {
        this.tail = null;
        this.tracker = 0;
        this.head = null;
    }
    /**
     * func: Print, prints the linked lis to terminal along with some properties 
     */
    public void print() {
        System.out.println("List length: " + this.tracker);
        boolean sorted = this.isSorted();
        System.out.println("Sorted: " + sorted);
        System.out.print("List content: ");
        DNode current = this.head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            
            current = current.getNext();
        }
        System.out.println();
    }


public static void main(String[] args){
   

    System.out.println("\n********** MODULE 1: LINEAR DATA STRUCTURES ********** \n");
    System.out.println("\n======  Singly LINKED LIST ======= \n");
   
   
            
    // Constructors

    SLL testerSLL = new SLL();
    SLL overloadSLL = new SLL(new DNode(14));
    System.out.println("____________OVERLOADED CONSTRUCTOR TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after call to overloaded constructor:\nList length: 1\nSorted: true\nList content: 14");
    System.out.println("The actual results:");
    overloadSLL.print();


    // Insertion methods tests
    testerSLL.insert(new DNode(18), 0);
    testerSLL.insert(new DNode(1), 1);
    System.out.println("____________REGULAR INSERTION TESTS___________");
    System.out.print("\n");
    System.out.println("The expected contents after inserting 18 and 1 at indexes 0 and 1 respectively:\nList length: 2\nSorted: false\nList content: 18 1");
    System.out.println("The actual results:");
    testerSLL.print();



  
        testerSLL.insertHead(new DNode(33));
       

        System.out.println("____________INSERTION HEAD TEST___________");
        System.out.print("\n");
        System.out.println("The expected contents after inserting 33 at head:\nList length: 3\nSorted: false\nList content: 33 18 1");
        System.out.println("The actual results:");
        testerSLL.print();
        

    System.out.println("____________INSERTION TAIL TEST___________");
    System.out.print("\n");


 
  
    testerSLL.insertTail(new DNode(24));
    System.out.println("The expected contents after inserting 24 at tail:\nList length: 4\nSorted: false\nList content: 33 18 1 24");
    System.out.println("The actual results:");
    testerSLL.print();




// Sort tests checks mutltiple edge cases by sorting then inserting then sorting and inserting to the head which is then sorted again.
    testerSLL.sort();
    testerSLL.sortedInsert(new DNode(0));
    testerSLL.sortedInsert(new DNode(11));
    testerSLL.sortedInsert(new DNode(27));
    testerSLL.insertHead(new DNode(39));
    testerSLL.sort();
    testerSLL.sortedInsert(new DNode(4));
    System.out.println("____________SORT and SORTED INSERTION TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after calling sort method and various insert calls:\nList length: 9\nSorted: true\nList content:  0 1 4 11 18 24 27 33 39 ");
    System.out.println("The actual results:");
    testerSLL.print();



//  Deletion tests which check multiple deletes through different functions
    testerSLL.delete(new DNode(24));


    System.out.println("____________DELETE Node TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after deleting 24:\nList length: 8\nSorted: true\nList content: 0 1 4 11 18 27 33 39");
    System.out.println("The actual results:");
    testerSLL.print();



    testerSLL.deleteHead();

    System.out.println("____________DELETE HEAD TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after deleting head:\nList length: 7\nSorted: true\nList content:  1 4 11 18 27 33 39");
    System.out.println("The actual results:");
    testerSLL.print();


    testerSLL.deleteTail();

    System.out.println("____________DELETE TAIL TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents after deleting tail:\nList length: 6\nSorted: true\nList content:  1 4 11 18 27 33");
    System.out.println("The actual results:");
    testerSLL.print();

    testerSLL.delete(new DNode(13));
    System.out.println("____________DELETE NOT IN LIST TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents when trying to delete 13:\n");
    System.out.println("The actual results:");
    testerSLL.delete(new DNode(13));




  
   
    System.out.println("____________SEARCH  TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents when Searching for 4:\n4");
    System.out.println("The actual results:");
    System.out.println(testerSLL.search(new DNode(4)));

    System.out.println("____________SEARCH NOT IN TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents when Searching for 123:\nnull");
    System.out.println("The actual results:");
    System.out.println(testerSLL.search(new DNode(123)));
  
    System.out.println("____________CLEAR TEST___________");
    System.out.print("\n");
    System.out.println("The expected contents:\nList length: 0\nSorted: true\nList content:");
    System.out.println("The actual results:");
    testerSLL.clear();
    testerSLL.print();
    System.out.println("____________TESTS COMPLETE___________");
  


  

}

}


  



