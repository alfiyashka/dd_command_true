package dd.command;

public class DDCommand {
    private final String fileName;
    private final String redoRecordHeader;
    private final long blockSize;

    public DDCommand(String fileName, String redoRecordHeader, int blockSize) {
        this.fileName = fileName;
        this.redoRecordHeader = redoRecordHeader;
        this.blockSize = blockSize;
    }


    private long calc(long offset, long length) {
        long remainder = blockSize - offset;
        if (remainder > length) {
            return length;
        }
        return 16 + remainder + calc(0x10, length - remainder);
    }

    public String generate() {
        if (redoRecordHeader == null || redoRecordHeader.isEmpty()) {
            throw new RuntimeException("Cannot generate dd command, because redo record header is not defined");
        }
        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("Cannot generate dd command, because redo file name is not defined\"");
        }
        RedoRecordHeaderParser headerParser = new RedoRecordHeaderParser(redoRecordHeader);
        headerParser.parse();

        long len= calc(headerParser.offset(), headerParser.length());
        long skip = headerParser.blockNumber() * blockSize + headerParser.offset();

        return String.format("dd if=%s of=%s.dump skip=%d count=%d bs=1", fileName, headerParser.rba(), skip, len);
    }
}
