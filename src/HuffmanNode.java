class HuffmanNode implements Comparable<HuffmanNode> {
    public char character;
    public int frequency;
    public HuffmanNode leftChild;
    public HuffmanNode rightChild;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        leftChild = null;
        rightChild = null;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return frequency - other.frequency;
    }
}
