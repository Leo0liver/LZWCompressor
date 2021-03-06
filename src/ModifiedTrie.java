import java.util.ArrayList;

class TrieNode {
    TrieNode[] array;
    byte value;
    int phraseNum;
    boolean isEnd;
    // Initialize your data structure here.
    public TrieNode() {
        this.array = new TrieNode[256];
    }

}


public class ModifiedTrie {
    private TrieNode root;
    private int phraseCounter = 0;
    public ModifiedTrie() {
        root = new TrieNode();
        //Priming the trie with every possible byte

        for(int i = 0; i < 256; i++){
            root.array[i] = new TrieNode();
            root.array[i].value = (byte)(phraseCounter);
            root.array[i].phraseNum = phraseCounter;
            phraseCounter++;
            System.out.println("adding" + phraseCounter);
        }
        System.out.println("finished priming trie");
    }


    /**
     * @param dataIn
     * @return An int array where the first element is the phrase number and the second element is the index of the offset of the first mismatch character
     */
    public int[] insertNextPhrase(ArrayList<Byte> dataIn) {
        TrieNode currentNode = root;
        Byte currentByte;
        int mismatch = 1;
        int[] phraseAndMismatch = new int[2];
        for(int i = 0; i < dataIn.size(); i++){
            mismatch = i;
            currentByte = dataIn.get(i);
            if(currentNode.array[Byte.toUnsignedInt(currentByte)] != null){
                if(currentNode.array[Byte.toUnsignedInt(currentByte)].value == currentByte) {
                    currentNode = currentNode.array[Byte.toUnsignedInt(currentByte)];
                }

            }
            else{
                //When we find a mismatch character add it to the trie with a new phrase number and return the phrase number of previous node and index of mismatch character
                currentNode.array[Byte.toUnsignedInt(currentByte)] = new TrieNode();
                currentNode.array[Byte.toUnsignedInt(currentByte)].value = currentByte;
                currentNode.array[Byte.toUnsignedInt(currentByte)].phraseNum = phraseCounter;
                phraseCounter++;

                phraseAndMismatch[0] = currentNode.phraseNum;
                phraseAndMismatch[1] = i;
                return phraseAndMismatch;
            }
            if(i+1 >= dataIn.size()){
                mismatch = i+1;
            }

        }

        phraseAndMismatch[0] = currentNode.phraseNum;
        phraseAndMismatch[1] = mismatch;
        return phraseAndMismatch;
    }


}