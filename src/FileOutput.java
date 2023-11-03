public class FileOutput {
    FileOutput(){
        try{
            String[] fileName = Main.lastChosen.getName().split("\\.");
            String extention = null;
            if(fileName.length == 2) extention = fileName[1];
            if(extention.equals("txt") || extention .equals("xml") || extention.equals("json")){
                switch (extention){
                    case "txt":{
                        break;
                    }
                    case "xml":{
                        break;
                    }
                    case "json":{
                        break;
                    }
                }
                Main.changed = false;
            }
            else throw new Exception("Wrong type of extention");
        } catch (Exception e){
            new ErrorMessage(e);
        }
    }
}
