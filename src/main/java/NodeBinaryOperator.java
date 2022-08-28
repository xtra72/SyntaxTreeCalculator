public abstract  class NodeBinaryOperator<T> implements Node<T> {
    Node<T>    leftNode;
    Node<T>    rightNode;
    String      operator;

    public NodeBinaryOperator(String operator, Node<T> leftNode, Node<T> rightNode) {
        this.operator = operator;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node<T> getLeftNode() {
        return  this.leftNode;
    }

    public Node<T> getRightNode() {
        return  this.rightNode;
    }

    @Override
    public String toString() {
        return  "(" + operator + " " + leftNode + " " + rightNode + ")";
    }
}
