package own.stu.redis.oneMaster.fakeDistribute.model;

import lombok.Data;

import java.util.List;

@Data
public class FileInfo {

    private String fileName;

    private Long fileSize;

    private String seed;

    private List<FilePartInfo> filePartInfoList;

    @Data
    public static class FilePartInfo {

        private String partFileOrderName;

        private String remotePartFileName; // backUp url should be define list

        public FilePartInfo(String partFileOrderName, String remotePartFileName) {
            this.partFileOrderName = partFileOrderName;
            this.remotePartFileName = remotePartFileName;
        }

        public FilePartInfo() {
        }
    }
}
