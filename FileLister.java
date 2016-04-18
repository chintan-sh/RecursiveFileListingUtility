import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import org.yaml.snakeyaml.Yaml;

public class FileLister{
    private static StringBuilder sb;
    private static String path;
    private static String output;
    private static Boolean recursive;
    private static int indentation;
    
    /* Initialize objects/vars */
    public static void init(){
        sb = new StringBuilder();
        path = "";
        output = "text";
        recursive = false;
        indentation = 0;
    }
    
    /*Help instructions*/
    public static void help(){
        System.out.println("--path=<path to folder, required>");
        System.out.println("--recursive  (when set, list files recursively.  default is off)");
        System.out.println("--output=<json|yaml|text, default is text>");
    }
    
    /* Parse through files and folders */
    public static FileList structure(String path ){
        File directory = new File(path);
        File[] directoryListing = directory.listFiles();
        
        if(directoryListing == null){
            return null;
        }
        
        FileList fileList = new FileList();
        for (File currentFile : directoryListing) {
                //create new file item for current file entry
                FileItem file = new FileItem();
                
                //set modified time
                TimeZone tz = TimeZone.getTimeZone("UTC");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                df.setTimeZone(tz);
                String nowAsISO = df.format(new Date(currentFile.lastModified()));
                file.setModifiedTime(nowAsISO);
                
                //set size
                file.setSize(currentFile.length());
                
                //set name
                file.setName(currentFile.getName());
                
                //set isLink, linksTo & children
                if(currentFile.isDirectory() && !currentFile.isHidden()){
                    try {
                        //If absolute and canonical path are different for directory, it's a symlink
                        if(!currentFile.getCanonicalPath().equals(currentFile.getAbsolutePath())){
                            file.setIsLink(Boolean.TRUE);
                            file.setIsDir(Boolean.FALSE);
                            file.setLinksTo(currentFile.getCanonicalPath());
                        }else{
                            file.setIsLink(Boolean.FALSE);
                            file.setIsDir(Boolean.TRUE);
                            if(recursive == true){
                                file.setChildren(structure(currentFile.getCanonicalPath()));
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println("Exception encountered " + ex);
                    }
                }else{
                    file.setIsLink(Boolean.FALSE);
                    file.setIsDir(Boolean.FALSE);
                }
                
               fileList.getFileList().add(file);
        }
        
        return fileList;
    }
    
    
    /*Fetch the directory structure for text representation*/
    public static void text(FileList fileList, int indentation){
        int indentVal = indentation;
        FileList structureList = fileList;
        for (FileItem currentFile : structureList.getFileList()) {
            if(!currentFile.getIsDir()){
                sb.append(currentFile.getName());
                if(currentFile.getIsLink()){
                    sb.append(" * (" );
                    sb.append(currentFile.getLinksTo());
                    sb.append(")");
                }
                sb.append("\n");
            }else{
                sb.append(currentFile.getName());
                sb.append("/");
                sb.append("\n");
            }
            
            if(currentFile.getChildren()!=null){
                    //sb.append(currentFile.getName());
                    //sb.append("/");
                    //sb.append("\n");
                    indentation++;
                    indent(indentation);
                    text(currentFile.getChildren(), indentation);
            }
            
            if( structureList.getFileList().indexOf(currentFile) != (structureList.getFileList().size()-1)){
                indent(indentVal);
            }
        }
    }
    
    /*Apply indentation while scanning recursively*/
    public static void indent(int indentVal){
        for(int j = 0 ; j < indentVal; j++){
            sb.append(" ");
        }
    }
    
    /*Fetch the directory structure and convert to JSON*/
    public static String json(FileList fileList){
        FileList structureList = fileList;
        String json = new Gson().toJson(structureList);
        return json;
    }
    
    /*Fetch the directory structure and convert to YAML*/
    public static String yaml(FileList fileList){
        Yaml yaml = new Yaml();
        Map<String,Object> map = (Map<String, Object>) yaml.load(json(fileList));
        String yamlStr = yaml.dump(map);
        return yamlStr;
    }
    
    
    public static void main(String args[]) {   
        // initalize objects
        init();
        
        //set flag
        boolean flag = true;
        
        //loop arguments
        for (int i = 0; i < args.length; i++) {
            String argument = args[i];
            String param = "";
            
            //split argument if it contains value
            if(args[i].contains("=")){
                String parts[] = args[i].split("=");
                if(parts.length < 2){
                    System.out.println("Please enter valid file path : \n Use filelister --help for directives");
                    flag = false;
                    break;
                }
                
                argument = parts[0];
                param = parts[1];
            }
            
            //--help
            if(argument.equals("--help")){
                if(i!=0) System.out.println("--help cannot be used in conjunction with other params : \n Use filelister --help for directives"); 
                else help();
                flag = false;
                break;
            }
            
            //--recursive
            if(argument.equals("--recursive")){
                recursive = true;
            }
            
            //--path
            if(argument.equals("--path") && !param.trim().equals("")){
                path = param;
            }else if(argument.equals("--path") && param.trim().equals("")) {
                System.out.println("Please enter valid file path : \n Use filelister --help for directives");
                flag = false;
                break;
            }
            
            //--output
            if(argument.equals("--output") && !param.trim().equals("")){
                output = param.toLowerCase();
            }else if(argument.equals("--output") && param.trim().equals("")) {
                System.out.println("Please enter valid output type : \n Use filelister --help for directives");
                flag = false;
                break;
            }
        }
        
        if(flag){
             File directoryLocation = new File(path);
            if(!directoryLocation.exists()){
                System.out.println("Please enter valid file path : \n Use filelister --help for directives");
            }else{
                FileList newList = structure(path);
                switch (output) {
                    case "text": 
                        text(newList, indentation);
                        System.out.println(sb.toString());
                        break;
                    case "json": 
                        System.out.println(json(newList));
                        break;
                    case "yaml": 
                        System.out.println(yaml(newList));
                        break;
                    default:
                        System.out.println("Please enter valid output type  :\n  Use filelister --help for directives");
                        break;
                }
            }
        }
    }//end of main
    
    
}//end of class
