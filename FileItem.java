/**
 *
 * @author chintan
 */
public class FileItem {
    private String modifiedTime;
    private Boolean isDir;
    private Boolean isLink;
    private String linksTo;
    private long size;
    private String name;
    private FileList children;

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getIsDir() {
        return isDir;
    }

    public void setIsDir(Boolean isDir) {
        this.isDir = isDir;
    }

    public Boolean getIsLink() {
        return isLink;
    }

    public void setIsLink(Boolean isLink) {
        this.isLink = isLink;
    }

    public String getLinksTo() {
        return linksTo;
    }

    public void setLinksTo(String linksTo) {
        this.linksTo = linksTo;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileList getChildren() {
        return children;
    }

    public void setChildren(FileList children) {
        this.children = children;
    }
}
