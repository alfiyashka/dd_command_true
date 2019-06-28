import dd.command.DDCommand;

public class DDMain {

    private static int calc(int offset, int length, int block_size) {

        int remainder = block_size - offset;
        if (remainder > length) {
            return length;
        }
        return 16 + remainder + calc(0x10, length - remainder, block_size);
    }

    public static void main(String[] args) {
        int blockSize = 512;

        // 72 REDO RECORD - Thread:1 RBA: 0x0361fc.000cb3d3.01b0 LEN: 0x0428 VLD: 0x01 CON_UID: 0
        int len126= calc(0x1b0, 0x0428, blockSize);
        int skip126 = 0x00cb3d3 * blockSize + 0x1b0;

        System.out.println("dd if=1_221692_922312515.dbf of=0x0361fc.000cb3d3.01b0.dump skip=" + skip126 + " count=" + len126+ " bs=1");

        System.out.println(new DDCommand("1_221692_922312515.dbf",
                "REDO RECORD - Thread:1 RBA: 0x0361fc.000cb3d3.01b0 LEN: 0x0428 VLD: 0x01 CON_UID: 0",
                512).generate());

        // 72 REDO RECORD - Thread:1 RBA: 0x0361fc.000caaf2.018c LEN: 0x0570 VLD: 0x01 CON_UID: 0
        len126= calc(0x18c, 0x0570, blockSize);
        skip126 = 0x00caaf2 * blockSize + 0x18c;

        System.out.println("dd if=1_221692_922312515.dbf of=0x0361fc.000caaf2.018c.dump skip=" + skip126 + " count=" + len126+ " bs=1");


        System.out.println(new DDCommand("1_221692_922312515.dbf",
                "REDO RECORD - Thread:1 RBA: 0x0361fc.000caaf2.018c LEN: 0x0570 VLD: 0x01 CON_UID: 0",
                512).generate());

        // 72 REDO RECORD - Thread:1 RBA: 0x0361fc.000cb3d1.0168 LEN: 0x0428 VLD: 0x01 CON_UID: 0
        len126= calc(0x168, 0x0428, blockSize);
        skip126 = 0x00cb3d1 * blockSize + 0x168;

        System.out.println("dd if=1_221692_922312515.dbf of=0x0361fc.000cb3d1.0168.dump skip=" + skip126 + " count=" + len126+ " bs=1");

        System.out.println(new DDCommand("1_221692_922312515.dbf",
                "REDO RECORD - Thread:1 RBA: 0x0361fc.000cb3d1.0168 LEN: 0x0428 VLD: 0x01 CON_UID: 0",
                512).generate());

    }

}
