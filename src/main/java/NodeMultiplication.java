public class NodeMultiplication<T extends MyNumber> extends NodeBinaryOperator<T> {

    public NodeMultiplication(Node<T> leftNode, Node<T> rightNode) {
        super("*", leftNode, rightNode);
    }

    public T getValue() {
        return (T)leftNode.getValue().multipliedBy(rightNode.getValue());
    }
}
