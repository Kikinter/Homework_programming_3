public class FileInput {
    FileInput(){
        try{
            String[] fileName = Main.lastChosen.getName().split(".");
            String extention = new String();
            if(fileName.length == 2) extention = fileName[1];
            if(extention == null || extention == "txt" || extention == "xml"){
                UserFeedback feedback = new UserFeedback();
                if(!Main.changed || feedback.ask()){

                }
            }
            else throw new Exception("Can't open file");
        } catch (Exception e){
            FileError err = new FileError(e);
        }
    }
}
