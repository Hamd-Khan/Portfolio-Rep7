package mylib.datastructures.Trees;
import java.util.*;
import mylib.datastructures.nodes.TNode;

public class BST {
    private TNode root;

    /**
     * Default constructor BST
     */
    public BST(){
        this.root = null;
    }
    /**
     * Overload constructor BST
     * takes in an integer value, and creates a TNode and use it as root
     * @param val value of the new TNode
     */
    public BST(int val){
        TNode newNode = new TNode(val, 0, null, null, null);
        newNode.setData(val);
        this.root = newNode;
    }
    /**
     * Overload constructor BST
     * takes a TNode as an argument and use it as the root of the tree.
     * @param obj the TNode to make root
     */
    public BST(TNode obj){
        this.root = obj;
    }
    /**
     * get the root node
     * @return the root of the tree
     */
    public TNode getRoot() {
        return this.root;
    }

     /**
     * set the root of the tree
     * @param root root node
     */
    public void setRoot(TNode root) {
        this.root = root;
    }

    /**
     * creates a new node with data val to be inserted into the tree
     * @param val the value being inserted into the tree
     */
    public void insert(int val) {
        TNode insertNode = new TNode(val, 0, null, null, null);
        insertNode.setData(val);
        if (getRoot() == null) {
            setRoot(insertNode);
        } else {
            TNode currentRoot = getRoot();
            while (true) {
                if (currentRoot.getData() < val) {
                    if (currentRoot.getRight() == null) {
                        currentRoot.setRight(insertNode);
                        insertNode.setParent(currentRoot);
                        break; }
                    currentRoot = currentRoot.getRight();
                } else {
                    if (currentRoot.getLeft() == null) {
                        currentRoot.setLeft(insertNode);
                        insertNode.setParent(currentRoot);
                        break;
                    }
                    currentRoot = currentRoot.getLeft(); }
            }
        }
        updateBalance(root);
    }

    /**
     * inserts the node passed as argument into the tree
     * @param node the node being inserted
     */
    public void insert(TNode node) {
        if (this.root == null) {
            this.root = node;
        } else {
            insertRecursive(this.root, node);
        }
    }
    
    private void insertRecursive(TNode current, TNode newNode) {
        if (newNode.getData() <= current.getData()) {
            if (current.getLeft() == null) {
                current.setLeft(newNode);
                newNode.setParent(current);
            } else {
                insertRecursive(current.getLeft(), newNode);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newNode);
                newNode.setParent(current);
            } else {
                insertRecursive(current.getRight(), newNode);
            }
        }
    }
    
    /**
     * finds the node with val as data and deletes it, if not found prints
     * a statement that the value is not in the tree
     * @param val value to search for and delete
     */
    public void delete(int val){
        TNode deleteNode = search(val);
        if(deleteNode == null){
            return;
        }
        TNode parentdel = deleteNode.getParent();
        TNode delChild = null;
        if(deleteNode.getLeft() != null && deleteNode.getRight() != null) {
            TNode current = deleteNode.getRight();
            while(current.getLeft() != null) {
                current = current.getLeft();
            }
            delChild = current.getRight();
            if(current != deleteNode.getRight()) {
                current.getParent().setLeft(delChild);
                current.setRight(deleteNode.getRight());
            }
            current.setLeft(deleteNode.getLeft());
        } else {
            delChild = (deleteNode.getLeft() != null) ? delChild.getLeft() : delChild.getRight();
        }
        if(parentdel != null) {
            if(parentdel.getLeft() == deleteNode) {
                parentdel.setLeft(delChild);
            } else {
                parentdel.setRight(delChild); }
        } else {
            this.root = delChild; }
        updateBalance(root);
    }
    
    /**
     * searches for the node with val as data and returns it or returns null if not found.
     * @param val value to search tree for
     * @return node with val or null of not found
     */
    public TNode search(int val) {
        if (getRoot() == null) {
            System.out.println("Tree is empty");
            return null;
        }
        TNode currentRoot = getRoot();
        while (true) {
            if (currentRoot.getData() == val) {
                return currentRoot;
            } else if (currentRoot.getData() > val) {
                if (currentRoot.getLeft() == null) {
                    System.out.println("Value not found");
                    return null;
                    
                } else {
                    currentRoot = currentRoot.getLeft(); }
            } else {
                
                if (currentRoot.getRight() == null) {
                    System.out.println("Value not found");
                    return null;
                } else {
                    currentRoot = currentRoot.getRight(); }
            }
        }
    }

    /**
     * prints the content of the tree in Breadth-First order, each level of the
     * tree will be printed on a separate line
     */
    public void printBF() {
        if (this.root == null) {
            System.out.println("Tree is empty");
            return;
        }
        List<TNode> level = new ArrayList<>();
        level.add(this.root);
        while (!level.isEmpty()) {
            List<TNode> nextLevel = new ArrayList<>();
            for (TNode current : level) {
                System.out.print(current.getData() + " ");
                if (current.getLeft() != null) {
                    nextLevel.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    nextLevel.add(current.getRight()); }
            }
            System.out.println();
            level = nextLevel;
        }
    }
    

    /**
     * prints the content data of the tree in ascending order
     */
    public void printInOrder() {
        if (this.root == null) {
            System.out.println("Tree has no nodes to print");
            return; }
        System.out.println("Nodes in ascending order:");
        TNode current = this.root;
        TNode prev = null;
        while (current != null) {
            if (current.getLeft() == null) {
                System.out.println(current.getData());
                current = current.getRight();
            } else {
                prev = current.getLeft();
                while (prev.getRight() != null && prev.getRight() != current) {
                    prev = prev.getRight();
                }
                if (prev.getRight() == null) {
                    prev.setRight(current);
                    current = current.getLeft();
                } else {
                    prev.setRight(null);
                    System.out.println(current.getData());
                    current = current.getRight(); } }
        }
    }
    
    /**
     * Get the balance of a node
     * @param node node to update balance 
     * @return the balance of the node
     */
    private int updateBalance(TNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = updateBalance(node.getLeft());
        int rightHeight = updateBalance(node.getRight());
        int balance = leftHeight - rightHeight;
        node.setBalance(balance);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    /**
     * Print balance of each node
     */
    public void printNodesBalance() {
        inOrderTraversal(root);
    }
    /**
     * Helper method to print the balance of each node
     * @param node the node to print the balance of
     */
    private void inOrderTraversal(TNode node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.getLeft());
        node.print();
        inOrderTraversal(node.getRight());
    }
    
    public static void main(String[] args){

        TNode nodeTest = new TNode(13, 0, null, null, null);
        int[] treeData = { 1, 8, 0, 6, 5, 12, 123, 87, 89, 39 };
        BST newBST = new BST();
         for (int x = 0; x < treeData.length; x++) {
            newBST.insert(treeData[x]);
        }

        System.out.println("====== BST TESTS======\n");

        System.out.println("____________OVERLOADCONSTRUCTOR TESTS___________");
        BST bstOverload1 = new BST(5);
        System.out.println("expected to print: 5");
        bstOverload1.printBF();
        BST bstOverload2 = new BST(nodeTest);
        System.out.println("expected to print: 13");
        bstOverload2.printBF();

        System.out.println();

        System.out.println("____________SEARCH TESTS___________");
        System.out.println("expected value: 12");
        TNode searchTest1 = newBST.search(12);
        if(searchTest1 != null) {
             System.out.println("Actual value returned: " + searchTest1.getData());
        }

        System.out.println("expected value: Value not found");
        TNode searchtest2 = newBST.search(15);
        if(searchtest2 != null) {
             System.out.println("Actual value returned: " + searchtest2.getData());
        }
       
        System.out.println("____________PRINTINORDER TESTS___________");
        newBST.printInOrder();

        System.out.println();

        System.out.println("____________PRINTNODESBALANCE TESTS___________");
        System.out.println("Print the balance at each node:\n");
        newBST.printNodesBalance();


        System.out.println("____________PRINTBREADTHFIRST TESTS___________");
        newBST.printBF();

        System.out.println("____________INSERT TESTS___________");
        System.out.println("After inserting 69 into tree:");
        newBST.insert(69);
        newBST.printBF();

        System.out.println("\nInserting node through TNode constructor:");
        newBST.insert(nodeTest);
        newBST.printBF();

        System.out.println("____________DELETE TESTS___________");
        System.out.println("After deleting 8 from tree:");
        newBST.delete(8);
        newBST.printBF();

        System.out.println("Delete value that doesn't exist:");
        newBST.delete(9987);
    }
}
