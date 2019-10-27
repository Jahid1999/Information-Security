package othersPackage;

public class WordGenerator
{
    private byte [] messageBlock;
    private long [] words = new long [80];

    private long rotr(long x, int n)
    {
        return Long.rotateRight(x,n);
    }

    private long s0(long x)
    {
        return rotr(x, 1) ^ rotr(x, 8) ^ (x >>> 7); //x>>7
    }

    private long s1(long x)
    {
        return rotr(x, 19) ^ rotr(x, 61) ^ (x >>> 6);
    }

    private void wordGeneration ()
    {
        String str = "";
        for(int i =0; i < 128; i++)
        {
            str = str + String.format("%8s", Integer.toBinaryString(messageBlock[i] & 0xFF)).replace(' ', '0');

            if((i+1)%8==0)
            {
                words[i/8] = Long.parseUnsignedLong(str,2);
                str="";
            }
        }

        for(int i = 16; i < 80; i++)
        {
            words [i] = (words[i-16]+s0(words[i-15])+words[i-7]+s1(words[i-2]));
        }
    }

    public long[] getWords() {
        return words;
    }

    public void setMessageBlock(byte[] messageBlock) {
        this.messageBlock = messageBlock;
        wordGeneration();
    }

    public WordGenerator(byte[] messageBlock) {
        this.messageBlock = messageBlock;
        wordGeneration();
    }
}
