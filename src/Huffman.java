import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Huffman {
    private HuffmanNode root;
    private Map<Character, String> encodingTable;

    public Huffman() {
        root = null;
        encodingTable = new HashMap<>();
    }
    public void buildEncodingTable() {
        buildEncodingTableHelper(root, "");
    }

    private void buildEncodingTableHelper(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            encodingTable.put(node.character, code);
        }

        buildEncodingTableHelper(node.leftChild, code + "0");
        buildEncodingTableHelper(node.rightChild, code + "1");
    }

    public String encodeText(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(encodingTable.get(c));
        }
        return sb.toString();
    }
    public Map<Character, String> getEncodingTable() {
        return encodingTable;
    }
    public void buildTree(Map<Character, Integer> frequencyTable) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.leftChild = left;
            parent.rightChild = right;
            pq.offer(parent);
        }

        root = pq.poll();
    }



}
