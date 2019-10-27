package othersPackage;

public class Round {

    private long W, K;
    private long [] h;
    private long [] outputH = new long [8];

    private long rotr(long x, int n)
    {
        return Long.rotateRight(x,n);
    }

    private long S0(long x)
    {
        return rotr(x, 28) ^ rotr(x, 34) ^ rotr(x, 39);
    }

    private long S1(long x)
    {
        return rotr(x, 14) ^ rotr(x, 18) ^ rotr(x, 41);
    }

    private long ch (long e, long f, long g)
    {
        return (e & f) ^ ((~e) & g);
    }

    private long maj(long a, long b, long c)
    {
        return (a & b) ^ (a & c) ^ (b & c);
    }

    private void roundOperations ()
    {
        outputH [1] = h [0];
        outputH [2] = h [1];
        outputH [3] = h [2];
        outputH [5] = h [4];
        outputH [6] = h [5];
        outputH [7] = h [6];


        long temp1 = (h[7] + S1(h[4]) + ch(h[4], h[5], h[6]) + W + K);
        long temp2 = (S0(h[0]) + maj(h[0], h[1], h[2]));

        outputH[4] = (h[3] + temp1);
        outputH[0] = (temp1 + temp2);
    }

    public Round(long [] h, long W, long K) {
        this.h = h;
        this.W = W;
        this.K = K;

        roundOperations();
    }

    public long[] getOutputH() {
        return outputH;
    }
}
