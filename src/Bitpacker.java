import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class Bitpacker
{
    private static int workingInt = 0;
    private static int bitsInWorkingInt = 0;
    public static void main(String[] args) {
        String fileName = "EncodedData.txt";
        File file = new File(fileName);

        packer(file);
    }

    private static void packer(File file){
        long numBits;
        long phraseCounter = 255;
        byte[] bArray = null;
        try
        {
            File fileOut = new File("PackedData");
            FileOutputStream os = new FileOutputStream(fileOut, true);
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine())
            {
                numBits = Math.round((Math.log(phraseCounter))/(Math.log(2)));
                bArray = pack(Integer.parseInt(sc.nextLine()), numBits);
                for (byte b : bArray)
                {
                    os.write(b);
                }
                phraseCounter ++;
            }
            os.close();
            sc.close();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

    private static byte[] pack(int toBePacked, long numBits) {
        byte[] byteArray;
        int shiftedBits;
        System.out.println(bitsInWorkingInt);
        shiftedBits = toBePacked << bitsInWorkingInt;
        workingInt = workingInt | shiftedBits;
        if(workingInt >= 0xFFFFFF){
            byteArray = new byte[4];
        }
        else if(workingInt >= 0xFFFF){
            byteArray = new byte[3];
        }
        else if(workingInt >= 0xFF){
            byteArray = new byte[2];
        }
        else{
            byteArray = new byte[1];
        }

        int i = 0;
        while(workingInt >= 255){
            byte b = (byte)workingInt;
            byteArray[i] = b;
            workingInt = workingInt  >> 8;
            i++;
        }

        bitsInWorkingInt = (int) Math.round((Math.log(workingInt))/(Math.log(2)));
        return byteArray;

    }
}