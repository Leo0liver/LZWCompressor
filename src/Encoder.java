import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Encoder {
    public Encoder() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        String fileName = args[0];
        while(true) {
            File file = new File(fileName);

            if(file.exists()) {
                encode(file);
                break;
            }
            else {
                System.out.println("File name " + fileName +  " does not exist in this directory please re input file name: ");
                BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
                try {
                    fileName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void encode(File fileIn) {
        String outFileName = "EncodedData.txt";
        File fileOut = new File(outFileName);
        ModifiedTrie trie = new ModifiedTrie();
        String encodedData = "";
        int phraseAndMismatch[] = {0,0};

        FileInputStream fin = null;
        FileWriter out;
        try {
            // create FileInputStream object
            fin = new FileInputStream(fileIn);
            out = new FileWriter(fileOut);

            byte fileBytes[] = new byte[(int) fileIn.length()];

            fin.read(fileBytes);
            //Convert fileBytes from byte array to array list so it can be easily shortened as we reduce the amount of bytes that need to be encoded
            Byte[] fileByteObjects = new Byte[fileBytes.length];
            int i=0;
            for(byte b: fileBytes)
                fileByteObjects[i++] = b;

            ArrayList<Byte> fileByteList = new ArrayList<Byte>(Arrays.asList(fileByteObjects));

            while(fileByteList.size() > 0) {
                phraseAndMismatch = trie.insertNextPhrase(fileByteList);
                for(int j = 0; j < phraseAndMismatch[1]; j++) {
                    fileByteList.remove(0);
                }
                encodedData += phraseAndMismatch[0] + "\n";

            }
            out.write(encodedData);
            out.flush();
            out.close();
            System.out.println(encodedData);





        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
