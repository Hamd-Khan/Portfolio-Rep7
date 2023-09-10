package mylib.datastructures.nodes;

public class TNode {
    private int data;
    private int balance;
    private TNode parent;
    private TNode left;
    private TNode right;

    /**
     * Default constructor for TNode
    */
    public TNode(){
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * Overload constructor for TNode
     * @param data node's data
     * @param balance balance of the node
     * @param parent parent node 
     * @param left node to left 
     * @param right node to right
    */
    public TNode(int data, int balance, TNode parent, TNode left, TNode right){
        this.data = data;
        this.balance = balance;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
    /**
     * get node data
     * @return data held by TNode
    */
    public int getData() {
        return this.data;
    }
    /**
     * get balance of node
     * @return balance of the TNode
    */
    public int getBalance() {
        return this.balance;
    }
    /**
     * get node to the left
     * @return node to the left of current TNode
    */
    public TNode getLeft() {
        return this.left;
    }
    /**
     * get node to the right
     * @return node to the right of current TNode
    */
    public TNode getRight() {
        return this.right;
    }
    /**
     * get parent node
     * @return parent node of current TNode
    */
    public TNode getParent() {
        return this.parent;
    }
    /**
     * set node data
     * @param data data to set in current TNode
    */
    public void setData(int data) {
        this.data = data;
    }
    /**
     * set node to the left of TNode
     * @param left node to which to connect to the left of current TNode
    */
    public void setLeft(TNode left) {
        this.left = left;
    }
    /**
     * set node to the right of TNode
     * @param right node to which to connect to the right of current TNode
    */
    public void setRight(TNode right) {
        this.right = right;
    }
    /**
     * set parent of current TNode
     * @param parent node to make make the parent of current TNode
    */
    public void setParent(TNode parent) {
        this.parent = parent;
    }
    /**
     * set balance of the TNode
     * @param balance int value that represents the balance of the node
    */
    public void setBalance(int balance) {
        this.balance = balance;
    }
     /**
     * prints the node data to terminal
     */
    public void print(){
        System.out.println(toString());
    }
     /**
     * Override toString to return the data member as a string
     */
    @Override
    public String toString() {
        return ("Data: " + getData() + " Balance: " + getBalance());
    }
}