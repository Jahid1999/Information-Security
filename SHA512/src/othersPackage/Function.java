package othersPackage;

public class Function
{
    private long [] keys = {
            Long.parseUnsignedLong("428A2F98D728AE22",16),
            Long.parseUnsignedLong("7137449123EF65CD",16),
            Long.parseUnsignedLong("B5C0FBCFEC4D3B2F",16),
            Long.parseUnsignedLong("E9B5DBA58189DBBC",16),
            Long.parseUnsignedLong("3956C25BF348B538",16),
            Long.parseUnsignedLong("59F111F1B605D019",16),
            Long.parseUnsignedLong("923F82A4AF194F9B",16),
            Long.parseUnsignedLong("AB1C5ED5DA6D8118",16),
            Long.parseUnsignedLong("D807AA98A3030242",16),
            Long.parseUnsignedLong("12835B0145706FBE",16),
            Long.parseUnsignedLong("243185BE4EE4B28C",16),
            Long.parseUnsignedLong("550C7DC3D5FFB4E2",16),
            Long.parseUnsignedLong("72BE5D74F27B896F",16),
            Long.parseUnsignedLong("80DEB1FE3B1696B1",16),
            Long.parseUnsignedLong("9BDC06A725C71235",16),
            Long.parseUnsignedLong("C19BF174CF692694",16),
            Long.parseUnsignedLong("E49B69C19EF14AD2",16),
            Long.parseUnsignedLong("EFBE4786384F25E3",16),
            Long.parseUnsignedLong("0FC19DC68B8CD5B5",16),
            Long.parseUnsignedLong("240CA1CC77AC9C65",16),
            Long.parseUnsignedLong("2DE92C6F592B0275",16),
            Long.parseUnsignedLong("4A7484AA6EA6E483",16),
            Long.parseUnsignedLong("5CB0A9DCBD41FBD4",16),
            Long.parseUnsignedLong("76F988DA831153B5",16),
            Long.parseUnsignedLong("983E5152EE66DFAB",16),
            Long.parseUnsignedLong("A831C66D2DB43210",16),
            Long.parseUnsignedLong("B00327C898FB213F",16),
            Long.parseUnsignedLong("BF597FC7BEEF0EE4",16),
            Long.parseUnsignedLong("C6E00BF33DA88FC2",16),
            Long.parseUnsignedLong("D5A79147930AA725",16),
            Long.parseUnsignedLong("06CA6351E003826F",16),
            Long.parseUnsignedLong("142929670A0E6E70",16),
            Long.parseUnsignedLong("27B70A8546D22FFC",16),
            Long.parseUnsignedLong("2E1B21385C26C926",16),
            Long.parseUnsignedLong("4D2C6DFC5AC42AED",16),
            Long.parseUnsignedLong("53380D139D95B3DF",16),
            Long.parseUnsignedLong("650A73548BAF63DE",16),
            Long.parseUnsignedLong("766A0ABB3C77B2A8",16),
            Long.parseUnsignedLong("81C2C92E47EDAEE6",16),
            Long.parseUnsignedLong("92722C851482353B",16),
            Long.parseUnsignedLong("A2BFE8A14CF10364",16),
            Long.parseUnsignedLong("A81A664BBC423001",16),
            Long.parseUnsignedLong("C24B8B70D0F89791",16),
            Long.parseUnsignedLong("C76C51A30654BE30",16),
            Long.parseUnsignedLong("D192E819D6EF5218",16),
            Long.parseUnsignedLong("D69906245565A910",16),
            Long.parseUnsignedLong("F40E35855771202A",16),
            Long.parseUnsignedLong("106AA07032BBD1B8",16),
            Long.parseUnsignedLong("19A4C116B8D2D0C8",16),
            Long.parseUnsignedLong("1E376C085141AB53",16),
            Long.parseUnsignedLong("2748774CDF8EEB99",16),
            Long.parseUnsignedLong("34B0BCB5E19B48A8",16),
            Long.parseUnsignedLong("391C0CB3C5C95A63",16),
            Long.parseUnsignedLong("4ED8AA4AE3418ACB",16),
            Long.parseUnsignedLong("5B9CCA4F7763E373",16),
            Long.parseUnsignedLong("682E6FF3D6B2B8A3",16),
            Long.parseUnsignedLong("748F82EE5DEFB2FC",16),
            Long.parseUnsignedLong("78A5636F43172F60",16),
            Long.parseUnsignedLong("84C87814A1F0AB72",16),
            Long.parseUnsignedLong("8CC702081A6439EC",16),
            Long.parseUnsignedLong("90BEFFFA23631E28",16),
            Long.parseUnsignedLong("A4506CEBDE82BDE9",16),
            Long.parseUnsignedLong("BEF9A3F7B2C67915",16),
            Long.parseUnsignedLong("C67178F2E372532B",16),
            Long.parseUnsignedLong("CA273ECEEA26619C",16),
            Long.parseUnsignedLong("D186B8C721C0C207",16),
            Long.parseUnsignedLong("EADA7DD6CDE0EB1E",16),
            Long.parseUnsignedLong("F57D4F7FEE6ED178",16),
            Long.parseUnsignedLong("06F067AA72176FBA",16),
            Long.parseUnsignedLong("0A637DC5A2C898A6",16),
            Long.parseUnsignedLong("113F9804BEF90DAE",16),
            Long.parseUnsignedLong("1B710B35131C471B",16),
            Long.parseUnsignedLong("28DB77F523047D84",16),
            Long.parseUnsignedLong("32CAAB7B40C72493",16),
            Long.parseUnsignedLong("3C9EBE0A15C9BEBC",16),
            Long.parseUnsignedLong("431D67C49C100D4C",16),
            Long.parseUnsignedLong("4CC5D4BECB3E42B6",16),
            Long.parseUnsignedLong("597F299CFC657E2A",16),
            Long.parseUnsignedLong("5FCB6FAB3AD6FAEC",16),
            Long.parseUnsignedLong("6C44198C4A475817",16)
    };

    private long [] initialVector;

    private long [] words;

    private long [] functionOutput = new long[8];


    private void functionOperations()
    {
        Round round;

        long [] h = initialVector;

        for(int i = 0; i < 80; i++)
        {
            round = new Round (h,words[i],keys[i]);
            h = round.getOutputH();
        }

        for(int i = 0; i < 8; i++)
        {
            functionOutput[i] = (h[i] + initialVector [i]);
        }
    }

    public Function(long[] words, long[] initialVector) {
        this.words = words;
        this.initialVector = initialVector;

        functionOperations();
    }

    public long[] getFunctionOutput() {
        return functionOutput;
    }
}
