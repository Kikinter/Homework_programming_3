public class FileOutput {
    FileOutput(){
        try{
            String[] fileName = Main.lastChosen.getName().split("\\.");
            String extention = null;
            if(fileName.length == 2) extention = fileName[1];
            if(extention == null || extention.equals("txt") || extention .equals("xml") || extention.equals("json")){

                Main.changed = false;
            }
            else throw new Exception("Wrong type of extention");
        } catch (Exception e){
            FileError err = new FileError(e);
        }
    }
}
