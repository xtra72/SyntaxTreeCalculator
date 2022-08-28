public class NodeSubtraction<T extends MyNumber> extends NodeBinaryOperator<T> {

    public NodeSubtraction(Node<T> leftNode, Node<T> rightNode) {
        super("-", leftNode, rightNode);
    }

    public T getValue() {
        return (T)leftNode.getValue().minus(rightNode.getValue());
    }
}
