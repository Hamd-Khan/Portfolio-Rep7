package mylib.datastructures.nodes;

public class DNode {
    private int data;
    private DNode previous;
    private DNode next;

    /**
     * Overload Constructor for DNode
     * @param data node's data
     */
    public DNode(int data) {
        this.next = null;
        this.previous = null;
        this.data = data;
    }
    /**
     * @return data of the node
     */
    public int getData() {
        return this.data;
    }
     /**
     * @return node next to current node (next pointer points to)
     */
    public DNode getNext() {
        return this.next;
    }
     /**
     * @return node previous to current node (previous pointer points to)
     */
    public DNode getPrevious() {
        return this.previous;
    }
     /**
     * @param data data to set in the node
     */
    public void setData(int data) {
        this.data = data;
    }
    /**
     * @param next set the next node 
     */
    public void setNext(DNode next) {
        this.next = next;
    }
     /**
     * @param previous set the previous node 
     */
    public void setPrevious(DNode previous) {
        this.previous = previous;
    }
     /**
     * @return the string value of the nodes data
     */
    public String toString(){
        return Integer.toString(this.data);

    }
}