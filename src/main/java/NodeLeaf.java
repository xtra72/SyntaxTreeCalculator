public class NodeLeaf<T> implements Node<T> {
    T   value;
    public NodeLeaf(T value) {
        this.value = value;
    }

    public T getValue() {
        return  value;
    }

    public String toString() {
        return  value.toString();
    }
}
