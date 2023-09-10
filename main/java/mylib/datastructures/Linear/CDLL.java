package mylib.datastructures.Linear;

import mylib.datastructures.nodes.DNode;


public class CDLL extends DLL {

    /**
     * Default constructor that creates a empty CDLL
     */
    public CDLL(){
        super();
    }

    /**
     * Overloaded constructor that initializes a DLL with an arg object
     * @param node a DNode object to be  inserted into the DLL
     */
    public CDLL(DNode node){
        super(node);
        this.head.setPrevious(this.tail);
        this.tail.setNext(this.head);


    }

       /**
     * func: insert, inserts node object in indexed position
     * @param node - DNode object to be inserted
     * @param position - index of position to insert node
     */
    public void insert(DNode node, int index) throws IndexOutOfBoundsException {
        if ( index > this.tracker|| index < 0 ) { 
            throw new IndexOutOfBoundsException();
        }
        

        if (index == this.tracker) {
 
            this.insertTail(node);
        }
        else if (index== 0) {
          
            this.insertHead(node);

        } else {
   
            super.insert(node, index);
            this.head.setPrevious(this.tail);
            this.tail.setNext(this.head);
        }
        
    }


    /**
     * func: insertHead which inserts a node object at the head of the list
     * @param node - DNode onject to be inserted at head
     */

    @Override
    public void insertHead(DNode node) {
        if (this.tracker != 0) {
            node.setNext(this.head);
            this.head.setPrevious(node);
            node.setPrevious(this.tail);
            this.tail.setNext(node);
            this.head = node;
        } else {
            node.setPrevious(node);
            node.setNext(node);
            this.head = node;
            this.tail = node;
        }
        this.tracker++;
    }


    /**
     * func: insertTail insets node object at the tail of the list
     * @param node - DNode onject to be inserted at tail
     */

    @Override
    public void insertTail(DNode node) {
        if (this.tracker != 0) {
            node.setPrevious(this.tail);
            node.setNext(this.head);
            this.tail.setNext(node);
            this.head.setPrevious(node);
            this.tail = node;
        } else {
            this.head = node;
            this.tail = node;
            node.setNext(node);
            node.setPrevious(node);
        }
        this.tracker++;
    }


     /**
     * func: sortedInsert, inserts node object so list remains in ascending order
     * @param node - DNode to be inserted in sorted linked list
     */
    public void sortedInsert(DNode node) {
        if (!isSorted()) {
            this.sort();
        }
    
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            node.setNext(node);
            node.setPrevious(node);
        } else if (node.getData() < this.head.getData()) {
            node.setNext(this.head);
            node.setPrevious(this.tail);
            this.head.setPrevious(node);
            this.head = node;
            this.tail.setNext(node);
        } else {
            DNode current = this.head.getNext();
            while (current != this.head && node.getData() > current.getData()) {
                current = current.getNext();
            }
            node.setNext(current);
            node.setPrevious(current.getPrevious());
            current.getPrevious().setNext(node);
            current.setPrevious(node);
        }
        if (node.getData() <= this.head.getData()) {
            this.head = node;
            this.tail.setNext(node);
            node.setPrevious(this.tail);
        }
        if (node.getData() >= this.tail.getData()) {
            this.tail = node;
            this.head.setPrevious(node);
            node.setNext(this.head);
        }
    
        this.tracker++;
    }
    

     /**
     * func: isSorted, checks if all elements in list are in ascending order, if so returns true else returns false
     */
    protected boolean isSorted() {
        if (this.head == null || this.head.getNext() == this.head) {
           
            return true;
        }
        DNode current = this.head.getNext();
        while (current != this.head) {
            if (current.getData() < current.getPrevious().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
    
    /**
     * func: Sort, applies insertion sort to the list 
     */
  
    @Override
    public void sort() {
        if (this.tracker <=1) {
            return;
        }
        DNode tempHolder = this.head.getNext(); 
        while (tempHolder != this.head) {
            DNode before = tempHolder.getPrevious();
            DNode after = tempHolder.getNext();
            if (tempHolder.getData() < before.getData()) {
                DNode holdingNode = tempHolder;
                while (holdingNode.getPrevious() != this.tail && tempHolder.getData() < holdingNode.getPrevious().getData()) {
                    holdingNode = holdingNode.getPrevious();
                }
                after.setPrevious(before);
                before.setNext(after);
                if(before.getNext() == this.head){
                    this.tail = before;
                }

                tempHolder.setNext(holdingNode);
                tempHolder.setPrevious(holdingNode.getPrevious());
                tempHolder.getPrevious().setNext(tempHolder);
                tempHolder.getNext().setPrevious(tempHolder);
                if(tempHolder.getPrevious() == this.tail){
                    this.head = tempHolder;
                }
                tempHolder = after;
            } else{
                tempHolder = tempHolder.getNext();
            }
        }
    }
    
    
        /**
         * func: delete, deletes the given Node from list if the node is in the list.
         * @param node - DNode object to be deleted from the linked list
         */
     
        @Override
        public void delete(DNode node) {
            if (this.head == null) {
                return; // list is empty
            }
            if (this.head == this.tail && this.head == node) {
                // list has only one node and it is the node to be deleted
                this.head = null;
                this.tail = null;
                this.tracker--;
                return;
            }
            if (this.head == node) {
                // node to be deleted is the head
                this.deleteHead();
                return;
            }
            if (this.tail == node) {
               this.deleteTail();
                return;
            }
           
            DNode current = this.head.getNext();
            while (current != this.head) {
                if (current.getData() == node.getData()) {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    this.tracker--;
                    return;
                }
                current = current.getNext();
            }

            System.out.println("value not found");
        }


         /**
         * func: DeleteHead, deletes head node
         */
        @Override
        public void deleteHead() {
          super.deleteHead();
          this.tail.setNext(this.head);
          this.head.setPrevious(this.tail);
        }


         /**
         * func: DeleteTail, deletes node at the tail
         * Deletes node at tail
         */
        @Override
        public void deleteTail() {
            super.deleteTail();
            this.head.setPrevious(this.tail);
            this.tail.setNext(this.head);
          }



         @Override
         public void print() {
            System.out.println("List length: " + this.tracker);
            boolean listSortBool = this.isSorted();
            System.out.println("Sorted: " + listSortBool);
            System.out.print("List content: ");
            if (this.head == null) {
                System.out.println("empty");
                return;
            }
            DNode current = this.head;
            do {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            } while (current != this.head);
             System.out.print("\n");
             System.out.println();
         }
      
         public static void main(String[] args){
   
            System.out.println("\n====== CIRCULARLY DOUBLY LINKED LIST ======= \n");
            
                SLL testerCDLL = new CDLL();
                SLL overloadCDLL = new CDLL(new DNode(14));
                System.out.println("____________OVERLOADED CONSTRUCTOR TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents after call to overloaded constructor:\nList length: 1\nSorted: true\nList content: 14");
                System.out.println("The actual results:");
                overloadCDLL.print();
            
                testerCDLL.insert(new DNode(18), 0);
                testerCDLL.insert(new DNode(1), 1);
                System.out.println("____________REGULAR INSERTION TESTS___________");
                System.out.print("\n");
                System.out.println("The expected contents after inserting 18 and 1 at indexes 0 and 1 respectively:\nList length: 2\nSorted: false\nList content: 18 1");
                System.out.println("The actual results:");
                testerCDLL.print();
            
                 testerCDLL.insertHead(new DNode(33));
                   
                System.out.println("____________INSERTION HEAD TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents after inserting 33 at head:\nList length: 3\nSorted: false\nList content: 33 18 1");
                System.out.println("The actual results:");
                testerCDLL.print();
                
                System.out.println("____________INSERTION TAIL TEST___________");
                System.out.print("\n");
              
                testerCDLL.insertTail(new DNode(24));
                System.out.println("The expected contents after inserting 24 at tail:\nList length: 4\nSorted: false\nList content: 33 18 1 24");
                System.out.println("The actual results:");
                testerCDLL.print();
           
                testerCDLL.sort();
                testerCDLL.sortedInsert(new DNode(0));
                testerCDLL.sortedInsert(new DNode(11));
                testerCDLL.sortedInsert(new DNode(27));
                testerCDLL.insertHead(new DNode(39));
                testerCDLL.sort();
                testerCDLL.sortedInsert(new DNode(4));
                System.out.println("____________SORT and SORTED INSERTION TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents after calling sort method and various insert calls:\nList length: 9\nSorted: true\nList content:  0 1 4 11 18 24 27 33 39 ");
                System.out.println("The actual results:");
                testerCDLL.print();
            
                testerCDLL.delete(new DNode(24));
            
                System.out.println("____________DELETE Node TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents after deleting 24:\nList length: 8\nSorted: true\nList content: 0 1 4 11 18 27 33 39");
                System.out.println("The actual results:");
                testerCDLL.print();
            
                testerCDLL.deleteHead();
            
                System.out.println("____________DELETE HEAD TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents after deleting head:\nList length: 7\nSorted: true\nList content:  1 4 11 18 27 33 39");
                System.out.println("The actual results:");
                testerCDLL.print();
            
                testerCDLL.deleteTail();
            
                System.out.println("____________DELETE TAIL TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents after deleting tail:\nList length: 6\nSorted: true\nList content:  1 4 11 18 27 33");
                System.out.println("The actual results:");
                testerCDLL.print();
            
                testerCDLL.delete(new DNode(13));
                System.out.println("____________DELETE NOT IN LIST TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents when trying to delete 13:\nvalue not found");
                System.out.println("The actual results:");
                testerCDLL.delete(new DNode(13));
               
                System.out.println("____________SEARCH  TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents when Searching for 4:\n4");
                System.out.println("The actual results:");
                System.out.println(testerCDLL.search(new DNode(4)));
            
                System.out.println("____________SEARCH NOT IN TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents when Searching for 123:\nnull");
                System.out.println("The actual results:");
                System.out.println(testerCDLL.search(new DNode(123)));
              
                System.out.println("____________CLEAR TEST___________");
                System.out.print("\n");
                System.out.println("The expected contents:\nList length: 0\nSorted: true\nList content:");
                System.out.println("The actual results:");
                testerCDLL.clear();
                testerCDLL.print();
            }
        }
            
            
            
            
            
            
            





