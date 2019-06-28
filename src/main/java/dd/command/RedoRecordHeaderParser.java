package dd.command;

import java.util.Arrays;
import java.util.List;

public class RedoRecordHeaderParser {

    private final String  redoRecordHeader;

    private final String RBA = "RBA:";
    private final String LEN = "LEN:";
    private final String VLD = "VLD:";

    private Long blockNumber;
    private Long offset;
    private Long length;
    private String rba;

    public RedoRecordHeaderParser(String headerStr) {
        redoRecordHeader = headerStr;
    }

    public void parse() {
        int indRba = redoRecordHeader.indexOf(RBA);
        int indLen = redoRecordHeader.indexOf(LEN);
        int indVld = redoRecordHeader.indexOf(VLD);
        if (indRba < 0 || indLen < 0 || indVld < 0) {
            throw new RuntimeException("Cannot parse redo record header, because redo record header has incorrect format");

        }
        rba = redoRecordHeader.substring(indRba + RBA.length(), indLen).replaceAll("\\s+","");
        List<String> rbaNumbers = Arrays.asList(rba.split("\\.", 3));
        blockNumber = Long.parseLong(rbaNumbers.get(1), 16);
        offset = Long.parseLong(rbaNumbers.get(2), 16);

        String lenStr = redoRecordHeader.substring(indLen + LEN.length(), indVld);
        final String hexNumberPrefix = "0x";
        int lengthInd = lenStr.indexOf(hexNumberPrefix);
        String redoRecordLenStr = lenStr.substring(lengthInd + hexNumberPrefix.length())
                .replaceAll("\\s+","");
        length = Long.parseLong(redoRecordLenStr, 16);
    }

    Long blockNumber() {
        return blockNumber;
    }

    Long offset() {
        return offset;
    }

    Long length() {
        return length;
    }

    String rba() {
        return rba;
    }

}
