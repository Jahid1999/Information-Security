package othersPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PreProcessor {

    private String inputMessageFile;
    private List<byte []> processedMessageBlocks = new ArrayList<>();

    public List<byte[]> getProcessedMessageBlocks() {
        return processedMessageBlocks;
    }

    public PreProcessor(String inputMessage) throws Exception {
        this.inputMessageFile = inputMessage;

        process();
    }

    public String getInputMessage() {
        return inputMessageFile;
    }

    public void setInputMessage(String inputMessage) throws Exception {

        this.inputMessageFile = inputMessage;

        processedMessageBlocks.clear();

        process();
    }

    private void process () throws Exception
    {
        FileInputStream in = new FileInputStream(inputMessageFile);
        int character, blockCounter = 0, blockCharCounter = 0;
        byte [] messageBlock = new byte [128];
        while ((character = in.read()) != -1) {

            if(character==13)
                continue;

            if(blockCharCounter==128)
            {
                processedMessageBlocks.add(messageBlock);
                messageBlock = new byte [128];
                blockCharCounter = 0;
                blockCounter++;
            }
            byte temp = (byte) character;
            messageBlock[blockCharCounter] = temp;
            blockCharCounter++;
        }

        if(blockCharCounter==128) {
            processedMessageBlocks.add(messageBlock);
            messageBlock = new byte [128];
            blockCharCounter = 0;
            blockCounter++;
        }

        if(blockCharCounter<112)
        {
            int messageLength = blockCounter*128*8+blockCharCounter*8;

            messageBlock[blockCharCounter] = (byte) 128;
            blockCharCounter++;

            while(blockCharCounter!=112)
            {
                messageBlock[blockCharCounter] = (byte) 0;
                blockCharCounter++;
            }

            String temp = Integer.toBinaryString(messageLength);

            int lenPad = 16 - (int) Math.ceil(temp.length()/8.0);

            for(int i = 0; i < lenPad; i++)
            {
                messageBlock[blockCharCounter] = (byte) 0;
                blockCharCounter++;
            }

            int lenRem = temp.length()%8;

            if(lenRem!=0)
            {
                byte tempByte = (byte) Integer.parseInt(temp.substring(0,lenRem),2);

                messageBlock[blockCharCounter] = tempByte;
                blockCharCounter++;
            }
            int rem = temp.length()/8;

            for(int i = 0; i < rem; i++)
            {
                byte tempByte = (byte) Integer.parseInt(temp.substring(lenRem+i*8,lenRem+i*8+8),2);
                messageBlock[blockCharCounter] = tempByte;
                blockCharCounter++;
            }

            processedMessageBlocks.add(messageBlock);
            messageBlock = new byte [128];
        }
        else if(blockCharCounter==112)
        {
            int messageLength = blockCounter*128*8+blockCharCounter*8;

            String temp = Integer.toBinaryString(messageLength);

            int lenPad = 16 - (int) Math.ceil(temp.length()/8.0);

            for(int i = 0; i < lenPad; i++)
            {
                messageBlock[blockCharCounter] = (byte) 0;
                blockCharCounter++;
            }

            int lenRem = temp.length()%8;

            if(lenRem!=0)
            {
                byte tempByte = (byte) Integer.parseInt(temp.substring(0,lenRem),2);

                messageBlock[blockCharCounter] = tempByte;
                blockCharCounter++;
            }

            int rem = temp.length()/8;

            for(int i = 0; i < rem; i++)
            {
                byte tempByte = (byte) Integer.parseInt(temp.substring(lenRem+i*8,lenRem+i*8+8),2);
                messageBlock[blockCharCounter] = tempByte;
                blockCharCounter++;
            }

            processedMessageBlocks.add(messageBlock);
            messageBlock = new byte [128];
        }
        else
        {
            int messageLength = blockCounter*128*8+blockCharCounter*8;

            messageBlock[blockCharCounter] = (byte) 128;
            blockCharCounter++;

            while(blockCharCounter!=128)
            {
                messageBlock[blockCharCounter] = (byte) 0;
                blockCharCounter++;
            }

            processedMessageBlocks.add(messageBlock);
            messageBlock = new byte [128];

            blockCharCounter = 0;

            for(int i = 0; i < 112; i++)
            {
                messageBlock[blockCharCounter] = (byte) 0;
                blockCharCounter++;
            }

            String temp = Integer.toBinaryString(messageLength);

            int lenPad = 16 - (int) Math.ceil(temp.length()/8.0);

            for(int i = 0; i < lenPad; i++)
            {
                messageBlock[blockCharCounter] = (byte) 0;
                blockCharCounter++;
            }

            int lenRem = temp.length()%8;

            if(lenRem!=0)
            {
                byte tempByte = (byte) Integer.parseInt(temp.substring(0,lenRem),2);

                messageBlock[blockCharCounter] = tempByte;
                blockCharCounter++;
            }

            int rem = temp.length()/8;

            for(int i = 0; i < rem; i++)
            {
                byte tempByte = (byte) Integer.parseInt(temp.substring(lenRem+i*8,lenRem+i*8+8),2);
                messageBlock[blockCharCounter] = tempByte;
                blockCharCounter++;
            }

            processedMessageBlocks.add(messageBlock);
            messageBlock = new byte [128];
        }
    }
}
