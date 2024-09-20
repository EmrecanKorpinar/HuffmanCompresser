

class Link {
    public HuffmanNode huffmanNode;
    public Link next;

    public Link(HuffmanNode huffmanNode) {
        this.huffmanNode = huffmanNode;
        next = null;
    }

    public void displayLink() {
        System.out.print(huffmanNode.character + ": " + huffmanNode.frequency + " -> ");
    }
}

