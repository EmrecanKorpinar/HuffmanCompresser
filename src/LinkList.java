class LinkList {
    private Link first;

    public void displayList() {
        System.out.print("List: ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }
    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(HuffmanNode huffmanNode) {
        Link newLink = new Link(huffmanNode);
        newLink.next = first;
        first = newLink;
    }

}
