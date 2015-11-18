package hunterscode.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * basic implementation of Huffman encoding
 * @author hstern
 *
 */
public class HuffmanTree {

    private Character character;
    private Integer frequency = 0;
    private HuffmanTree left;
    private HuffmanTree right;    

    public HuffmanTree() {}    
    public HuffmanTree(Character character, Integer frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    //first arg is string to build tree from, second is string to encode
    public static void main(String [] args) {
        if (args.length != 2) {
            System.out.println("Usage: java HuffmanTree <text to generating tree> <text to encode and decode>");
            System.exit(1);
        }
        
        HuffmanTree tree = buildTree(args[0]);
        
        String encoded = encode(args[1], tree);
        
        System.out.println("Encoded output: " + encoded);
        System.out.println("Decoded output: " + decode(encoded, tree));
    }

    private static String decode(String encodedText, HuffmanTree tree) {
        StringBuffer decoded = new StringBuffer();
        int i = 0;

        while (i < encodedText.length()) {
            HuffmanTree tmpTree = tree;
            
            while (true) {
                //check if at leaf node
                if (tmpTree.left == null) {
                    decoded.append(tmpTree.character);
                    break;
                }
                
                char bit = encodedText.charAt(i);                

                if (bit == '0') {
                    tmpTree = tmpTree.left;
                }
                else if (bit == '1') {
                    tmpTree = tmpTree.right;
                }

                ++i;
            }
        }
        
        return decoded.toString();
    }
    
    private static String encode(String text, HuffmanTree tree) {
        StringBuffer codes = new StringBuffer();
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);

            codes.append(getCode(c, tree));
            ++i;
        }

        return codes.toString();
    }
    
    private static class CharacterFrequencyMap implements Comparable<CharacterFrequencyMap> {
        Character character;
        Integer frequency;
        Integer position;
        HuffmanTree tree;
        
        CharacterFrequencyMap(Character character, Integer frequency, Integer position) {
            this.character = character;
            this.frequency = frequency;
            this.position = position;
        }
        
        @Override
        public int compareTo(CharacterFrequencyMap arg0) {
            return arg0.frequency < this.frequency ? 1
                    : (arg0.frequency > this.frequency ? -1
                            : (arg0.position > this.position ? 1
                                    : (arg0.position < this.position ? -1 : 0)));
        }

        @Override
        public String toString() {
            return character + " " + frequency + " " + position;                    
        }        
    }
   
    private static HuffmanTree buildTree(String text) {
        List<CharacterFrequencyMap> freqToChar = getCharFrequencies(text);
    
        return buildTree(freqToChar);
    }

    private static void walkTree(char c, HuffmanTree tree, StringBuffer code) {
        if (tree.left == null || tree.right == null) return;
            
        if (tree.left.character != null && tree.left.character.equals(c)) {
            code.append("0");
            return;
        }
        else if (tree.right.character != null && tree.right.character.equals(c)) {
            code.append("1");
            return;
        }
        else {
            StringBuffer tmp = new StringBuffer();
            tmp.append("0");
            
            walkTree(c, tree.left, tmp);
            
            if (tmp.length() > 1) {
                code.append(tmp);
                return;
            }
            
            tmp = new StringBuffer();
            tmp.append("1");            
            
            walkTree(c, tree.right, tmp);
            
            if (tmp.length() > 1) {
                code.append(tmp);
                return;
            }
        }
    }
    
    private static String getCode(Character c, HuffmanTree tree) {
        if (tree == null) return "";

        StringBuffer code = new StringBuffer();
        
        if (tree.character != null && tree.character.equals(c)) {
            return "0";
        }

        walkTree(c, tree, code);
        
        return code.toString();
    }
    
    private static HuffmanTree buildTree(List<CharacterFrequencyMap> freqToCharList) {
        HuffmanTree parent = null;
        
        while (true) {
            if (freqToCharList.size() == 0) return parent;

            CharacterFrequencyMap charFreqMap = freqToCharList.remove(0);
            HuffmanTree tree = null;
            
            if (charFreqMap.tree != null) {
                tree = charFreqMap.tree;
            }
            else {
                tree = new HuffmanTree(charFreqMap.character, charFreqMap.frequency);
            }
            
            if (parent != null) {
                if (freqToCharList.size() == 0) {
                    return tree;
                }
                else {
                    charFreqMap = freqToCharList.remove(0);
                    
                    HuffmanTree newParent = null;

                    if (charFreqMap.tree != null) {
                        newParent = new HuffmanTree();

                        newParent.left = charFreqMap.tree;
                        newParent.right = tree;
                        newParent.frequency += (tree.frequency + charFreqMap.tree.frequency);
                    }
                    else {
                        newParent = new HuffmanTree();

                        newParent.left = new HuffmanTree(charFreqMap.character, charFreqMap.frequency);
                        newParent.right = tree;
                        newParent.frequency += (tree.frequency + charFreqMap.frequency);
                    }
                    
                    CharacterFrequencyMap newCharFreqMap = new CharacterFrequencyMap(null, newParent.frequency, freqToCharList.size() - 1);
                    newCharFreqMap.tree = newParent;

                    freqToCharList.add(newCharFreqMap);
                    Collections.sort(freqToCharList);
                }
            }
            else {                
                if (freqToCharList.size() == 0) {
                    return tree;
                }
                
                charFreqMap = freqToCharList.remove(0);
                HuffmanTree tree2 = new HuffmanTree(charFreqMap.character, charFreqMap.frequency);
                
                parent = new HuffmanTree();
                parent.left = tree2;
                parent.right = tree;
                parent.frequency += (tree.frequency + tree2.frequency);
                
                CharacterFrequencyMap newCharFreqMap = new CharacterFrequencyMap(null, parent.frequency, freqToCharList.size() - 1);
                newCharFreqMap.tree = parent;
                        
                freqToCharList.add(newCharFreqMap);
                Collections.sort(freqToCharList);
            }
        }
    }
    
    private static List<CharacterFrequencyMap> getCharFrequencies(String text) {
        Map<Character, CharacterFrequencyMap> map = new HashMap<Character, CharacterFrequencyMap>();
        
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            
            if (map.get(c) != null) {
                CharacterFrequencyMap cfm = map.get(c);
                
                cfm.frequency++;
            }
            else {
                map.put(c, new CharacterFrequencyMap(c, 1, i));
            }
            
            ++i;
        }
        
        List<CharacterFrequencyMap> sortedList = new ArrayList<CharacterFrequencyMap>(map.values());        
        Collections.sort(sortedList);         
        return sortedList;
    }
}
