package hw;


/*
     * Left handed red-black tree implementation by task from GeekBrains.
     * 
     * @author Anasatasia Zadubinina
*/

public class LeftHandedRedBlackTree {

    
    private Node root;

    /**
        * This method is used to add element to tree
        *
        * @param value must be comparable
    */

    public boolean add(int value){
        if (root != null){
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        }
        else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    /**
     * This private class use recursion for add new node to tree
     * and balance tree if it needs on the way back
     *
     * @param node  This option need for recursion.
     * @param value This is value, which must be added.
     * @return boolean This returns true if node added successfully or return false.
     */

    private boolean addNode(Node node, int value){
        if (node.value == value){
            return false;
        }
        else {
            if (node.value > value) {
                if (node.leftChild != null){
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return result;
                }
                else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            }
            else {
                if (node.rightChild != null){
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                }
                else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    /**
     * This method launches rotate left, rotate right and swap colors if necessary.
     *
     * @param node need for check
     * @return node after balance operation
     */

    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BLACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.RED){
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED){
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }

    /**
     * This method makes left turn, this is local operation only for node and children.
     *
     * @param node node for rotate
     * @return return node after rotate
     */

    private Node rightSwap(Node node){
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }


     /**
     * This method makes right turn, this is local operation only for node and children.
     *
     * @param node node for rotate
     * @return return node after rotate
     */

    private Node leftSwap(Node node){
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    /**
     * This method invert colors node and children.
     */

    private void colorSwap(Node node){
        node.rightChild.color = Color.BLACK;
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;
    }
    
    /**
     * Base class, for this tree.
     * It has value, color, left and right child
     */

    private class Node {

        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        
        @Override
        public String toString() {
        return "Node{value = " + value + "color = " + color + "}";
        }

    }

    /*
     * Colors enumeration for implementation Red-Black tree
    */

    public enum Color {
            RED, BLACK
    }
    
}
