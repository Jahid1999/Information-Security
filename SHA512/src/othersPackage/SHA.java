package othersPackage;

import java.util.List;

public class SHA
{
    private long [] initialVector = {
            Long.parseUnsignedLong("6a09e667f3bcc908",16),
            Long.parseUnsignedLong("bb67ae8584caa73b",16),
            Long.parseUnsignedLong("3c6ef372fe94f82b",16),
            Long.parseUnsignedLong("a54ff53a5f1d36f1",16),
            Long.parseUnsignedLong("510e527fade682d1",16),
            Long.parseUnsignedLong("9b05688c2b3e6c1f",16),
            Long.parseUnsignedLong("1f83d9abfb41bd6b",16),
            Long.parseUnsignedLong("5be0cd19137e2179",16)
    };

    private String inputFilePath;
    private List<byte []> processedMessageBlocks;

    private String hashResult = "";

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getHashResult() {
        return hashResult;
    }

    private void postProcessing (long [] hashCode)
    {
        for(int i = 0; i < 8; i++)
        {
            hashResult = hashResult + Long.toHexString(hashCode[i]);
        }
    }

    private void hashingOperations()
    {
        Function func;

        long [] hashCode = initialVector;

        for(byte [] i : processedMessageBlocks)
        {
            func = new Function((new WordGenerator(i)).getWords(),hashCode);
            hashCode = func.getFunctionOutput();
        }

        postProcessing(hashCode);
    }

    public SHA(String inputFilePath) throws Exception
    {
        this.inputFilePath = inputFilePath;

        processedMessageBlocks = (new PreProcessor(this.inputFilePath)).getProcessedMessageBlocks();

        hashingOperations();
    }

    public void setInputFilePath(String inputFilePath) throws Exception
    {
        this.inputFilePath = inputFilePath;

        processedMessageBlocks = (new PreProcessor(this.inputFilePath)).getProcessedMessageBlocks();

        hashingOperations();
    }
}
