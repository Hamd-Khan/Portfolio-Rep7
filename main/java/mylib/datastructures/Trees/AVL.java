package mylib.datastructures.Trees;
import mylib.datastructures.nodes.TNode;

public class AVL extends BST {
    private TNode root;
    
    /**
     * Default constructor for AVL
     */
    public AVL() {
        super();
    }
    /**
     * Overload constructor for AVL
     * @param val value to enter in AVL tree
     */
    public AVL(int val) {
        super(val);
    }
    /**
     * Overload constructor for AVL
     * @param obj node to enter into the AVL tree
     */
    public AVL(TNode obj) {
        super(obj);
        if (obj != null) {
            this.root = balance(obj);
        }
    }

    /**
     * balances a node
     * @param obj the node to balance
     * @return the balanced node
     */
    private TNode balance(TNode obj) {
        if (obj == null) {
            return null;
        }
        int balance = getterForBalance(obj);
        if (balance > 1) {
            obj.setLeft(balance(obj.getLeft()));
            obj = rotateRight(obj);
        } else if (balance < -1) {
            obj.setRight(balance(obj.getRight()));
            obj = rotateLeft(obj);
        } else {
            obj.setLeft(balance(obj.getLeft()));
            obj.setRight(balance(obj.getRight()));
        }
        return obj;
    }
    
    /**
     * get the balance of a node
     * @param obj the node to get the balance of
     * @return the balance of the node
     */
      private int getterForBalance(TNode obj) {
        if (obj != null) {
            return heightGetter(obj.getLeft()) - heightGetter(obj.getRight());
        } else {
            return 0;
        }
    }

    /**
     * Rotates the given binary search tree node to the left. 
     * @param obj the node to be rotated
     * @return the new root of the subtree that includes the given node 
     */
    private TNode rotateLeft(TNode obj) {
        TNode right = obj.getRight();
        obj.setRight(right.getLeft());
        right.setLeft(obj);
        int objLeftHeight = obj.getLeft() != null ? obj.getLeft().getBalance() : 0;
        int objRightHeight = obj.getRight() != null ? obj.getRight().getBalance() : 0;
        obj.setBalance(Math.max(objLeftHeight, objRightHeight) + 1);
        int rightLeftHeight = right.getLeft() != null ? right.getLeft().getBalance() : 0;
        int rightRightHeight = right.getRight() != null ? right.getRight().getBalance() : 0;
        right.setBalance(Math.max(rightLeftHeight, rightRightHeight) + 1);
        return right;
    }

    /**
     * Rotates the given binary search tree node to the right. 
     * @param obj the node to be rotated
     * @return the new root of the subtree that includes the given node 
     */
    private TNode rotateRight(TNode obj) {
        TNode left = obj.getLeft();
        obj.setLeft(left.getRight());
        left.setRight(obj);
        int objLeftHeight = obj.getLeft() != null ? obj.getLeft().getBalance() : 0;
        int objRightHeight = obj.getRight() != null ? obj.getRight().getBalance() : 0;
        obj.setBalance(Math.max(objLeftHeight, objRightHeight) + 1);
        int leftLeftHeight = left.getLeft() != null ? left.getLeft().getBalance() : 0;
        int leftRightHeight = left.getRight() != null ? left.getRight().getBalance() : 0;
        left.setBalance(Math.max(leftLeftHeight, leftRightHeight) + 1);
        return left;
    }
    

    /**
     * Gets the height of a node
     * @param obj the node to get the height of
     * @return the height of the node
     */
     private int heightGetter(TNode obj) {
        if (obj!= null) {
            int heightOfright = heightGetter(obj.getRight());
            int heightOfLeft= heightGetter(obj.getLeft());
            int max = Math.max(heightOfLeft, heightOfright) + 1;
            return max;
        } else {
            return 0;
        }
    }

    /**
     * insert a value into the AVL tree
     * @param val value to insert in tree
    */
    @Override
    public void insert(int val) {
        super.insert(val);
        this.root = balance(this.root);
    }
    /**
     * insert a node into the AVL tree
     * @param obj node to insert in tree
     */
    @Override
    public void insert(TNode obj) {
        super.insert(obj);
        this.root = balance(this.root);
    }

    /**
     * searches for the node with val as data and returns it or returns null if not found.
     * @param val value to search for in the tree
     * @return TNode that is found from the search, null if not found
    */
    @Override
    public TNode search(int val){
        TNode item = super.search(val);
        return item;
    }

    /**
     * delete a value from the AVL tree
     * @param val value to delete from tree
    */
    @Override
    public void delete(int val) {
        TNode nodeToDelete = search(val);
        if(nodeToDelete == null){
            return;
        }
        TNode parentOfDeleted = nodeToDelete.getParent();
        boolean isLeftChild = parentOfDeleted.getLeft() == nodeToDelete;
        if(nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (isLeftChild) {
                parentOfDeleted.setLeft(null);
            } else {
                parentOfDeleted.setRight(null); }
        } else if(nodeToDelete.getLeft() != null && nodeToDelete.getRight() == null){
            TNode child = nodeToDelete.getLeft();
            child.setParent(parentOfDeleted);
            if (isLeftChild) {
                parentOfDeleted.setLeft(child);
            } else {
                parentOfDeleted.setRight(child); }
        } else if(nodeToDelete.getLeft() == null && nodeToDelete.getRight() != null){
            TNode child = nodeToDelete.getRight();
            child.setParent(parentOfDeleted);
            if (isLeftChild) {
                parentOfDeleted.setLeft(child);
            } else {
                parentOfDeleted.setRight(child); }
        } else{
            TNode successor = findSuccessor(nodeToDelete);
            TNode successorParent = successor.getParent();
            if(successorParent != nodeToDelete){
                successorParent.setLeft(null);
                successor.setRight(nodeToDelete.getRight());
                successor.getRight().setParent(successor);
            }
            successor.setLeft(nodeToDelete.getLeft());
            successor.getLeft().setParent(successor);
            if (isLeftChild) {
                parentOfDeleted.setLeft(successor);
            } else {
                parentOfDeleted.setRight(successor);
            }
            successor.setParent(parentOfDeleted);
        }
        this.root = balance(this.root);
    }
    
    /**
     * find the successor of the input node
     * @param node node to find the successor of
     * @return successor of the input node
     */
    private TNode findSuccessor(TNode node){
        TNode current = node.getRight();
        while(current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    /**
     * prints the content data of the tree in ascending order
    */
    @Override
    public void printInOrder() {
        super.printInOrder();
    }
    /**
     * prints the content of the tree in Breadth-First order, each level of the
     * tree will be printed on a separate line
    */
    @Override
    public void printBF() {
        super.printBF();
    }

    public static void main(String[] args){

        TNode nodeTest = new TNode(13, 0, null, null, null);
        int[] treeData = { 124, 359, 126, 18, 16, 55, 2 };
        BST newAVL = new AVL();
         for (int x = 0; x < treeData.length; x++) {
            newAVL.insert(treeData[x]);
        }

        System.out.println("====== BST TESTS======\n");

        System.out.println("____________OVERLOADCONSTRUCTOR TESTS___________");
        System.out.println("Testing AVL integer constructor...");
        BST avlOverload1 = new AVL(5);
        System.out.println("expected to print: 5");
        avlOverload1.printBF();
        System.out.println("Testing AVL TNode constructor...");
        BST avlOverload2 = new AVL(nodeTest);
        System.out.println("expected to print: 13");
        avlOverload2.printBF();

        System.out.println();
        
        System.out.println("____________SEARCH TESTS___________");
        System.out.println("Searching for 126...\nexpected value: 126");
        TNode searchTest1 = newAVL.search(126);
        if(searchTest1 != null) {
             System.out.println("Actual value returned: " + searchTest1.getData());
        }

        System.out.println("Searching for 15...\nexpected value: Value not found");
        TNode searchtest2 = newAVL.search(15);
        if(searchtest2 != null) {
             System.out.println("Actual value returned: " + searchtest2.getData());
        }

        System.out.println("____________PRINTINORDER TESTS___________");
        newAVL.printInOrder();
        System.out.println();

        System.out.println("____________PRINTNODESBALANCE TESTS___________");
        System.out.println("Print the balance at each node:\n");
        newAVL.printNodesBalance();
        System.out.println();
        System.out.println("____________PRINTBREADTHFIRST TESTS___________");
        newAVL.printBF();

        System.out.println("____________INSERT TESTS___________");
        System.out.println("After inserting 69 into tree:");
        newAVL.insert(69);
        newAVL.printBF();
        System.out.println();
        newAVL.printNodesBalance();

        System.out.println("\nInserting node through TNode constructor:");
        newAVL.insert(nodeTest);
        newAVL.printBF();
        System.out.println();
        newAVL.printNodesBalance();

        System.out.println("____________DELETE TESTS___________");
        System.out.println("After deleting 16 from tree:");
        newAVL.delete(16);
        newAVL.printBF();

        System.out.println("\nChecking the balance at each node...\n");
        newAVL.printNodesBalance();
        System.out.println();
        System.out.println("After deleting 359 from tree:");
        newAVL.delete(359);
        newAVL.printBF();

        System.out.println("\nChecking the balance at each node...\n");
        newAVL.printNodesBalance();

        System.out.println("\nDelete value that doesn't exist");
        newAVL.delete(11123);
    }
}