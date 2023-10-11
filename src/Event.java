public class Event {
    private boolean favourite = false;
    private String name;
    private String description;
    private Date start;
    private Date end;

    Event(boolean favourite, String name, String description, Date start, Date end){
        this.favourite = favourite;
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
    }
    Date getStart(){
        return start;
    }
}
