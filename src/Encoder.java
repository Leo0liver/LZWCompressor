import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

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

    public static void encode(File file) {
        String outFileName = "EncodedData.txt";
        File fileOut = new File(outFileName);


        try {


            byte[] fileBytes = Files.readAllBytes(file.toPath());

            String str = new String(fileBytes);
            System.out.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
