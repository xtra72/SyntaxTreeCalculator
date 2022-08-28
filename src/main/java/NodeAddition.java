public class NodeAddition<T extends MyNumber> extends NodeBinaryOperator<T> {

    public NodeAddition(Node<T> leftNode, Node<T> rightNode) {
        super("+", leftNode, rightNode);
    }

    public T getValue() {
        return (T)leftNode.getValue().plus(rightNode.getValue());
    }

}
