import java.util.ArrayList;

/**
 *
 * @author chintan
 */
public class FileList {
    private ArrayList<FileItem> fileList;
    private FileItem fileItem;
    
    public FileList(){
        fileList = new ArrayList<>();
    }

    public ArrayList<FileItem> getFileList() {
        return fileList;
    }

    public FileItem getFileItem() {
        return fileItem;
    }
    
    
}
