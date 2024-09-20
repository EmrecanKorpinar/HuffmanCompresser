import java.io.*;
import java.util.*;
public class HuffmanCompression {
    public static void main(String[] args) {

        String inputFileName = "C:\\Users\\Dell\\Downloads\\Dataset-0.txt";
        try {

            String text = FileManagement.readTextFromFile(inputFileName);
            System.out.println("Orijginal Data " + text);


            Map<Character, Integer> frequencyTable = buildFrequencyTable(text);



            HuffmanNode root = buildHuffmanTree(frequencyTable);
            Map<Character, String> encodingTable = buildEncodingTable(root);


            String compressedText = encodeText(text, encodingTable);
            System.out.println("Encodıng Data:" + compressedText);
            String outputFileName = "EncodeDataset.txt";
            writeCompressedDataToFile(frequencyTable, encodingTable, compressedText, outputFileName);
            System.out.println("Encodıng data" + outputFileName + " dosyasına yazıldı.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<Character, Integer> buildFrequencyTable(String text) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyTable.put(c, frequencyTable.getOrDefault(c, 0) + 1);
        }
        return frequencyTable;
    }

    private static void displayFrequencyTable(Map<Character, Integer> frequencyTable) {
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            System.out.println(entry.getKey()
                    + ": " + entry.getValue());
        }
    }

    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyTable) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            priorityQueue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.leftChild = left;
            parent.rightChild = right;

            priorityQueue.offer(parent);
        }

        return priorityQueue.poll();
    }

    private static Map<Character, String> buildEncodingTable(HuffmanNode root) {
        Map<Character, String> encodingTable = new HashMap<>();
        buildEncodingTableRecursive(root, "", encodingTable);
        return encodingTable;
    }

    private static void buildEncodingTableRecursive(HuffmanNode node, String code, Map<Character, String> encodingTable) {
        if (node.isLeaf()) {
            encodingTable.put(node.character, code);
            return;
        }

        buildEncodingTableRecursive(node.leftChild, code + "0", encodingTable);
        buildEncodingTableRecursive(node.rightChild, code + "1", encodingTable);
    }

    private static String encodeText(String text, Map<Character, String> encodingTable) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(encodingTable.get(c));
        }
        return encodedText.toString();
    }

    private static void writeCompressedDataToFile(Map<Character, Integer> frequencyTable, Map<Character, String> encodingTable, String compressedText, String outputFileName) throws IOException {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();
            String huffmanCode = encodingTable.get(character);

            output.append(character).append(": ").append(huffmanCode).append(" (").append(frequency).append(")");
        }

        System.out.println("Frekanslar ve Huffman kodları:");
        System.out.println(output.toString());
        System.out.println("Encodıng data:");
        System.out.println(compressedText);

        FileManagement.writeTextToFile(compressedText, outputFileName);
    }
}
