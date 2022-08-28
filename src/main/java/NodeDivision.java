public class NodeDivision<T extends MyNumber> extends NodeBinaryOperator<T> {

    public NodeDivision(Node<T> leftNode, Node<T> rightNode) {
        super("/", leftNode, rightNode);
    }

    public T getValue() {
        return (T)leftNode.getValue().dividedBy(rightNode.getValue());
    }
}
