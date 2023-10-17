public class Event {
    boolean favourite = false;
    String name;
    String description;
    Date start;
    Date end;

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
    @Override
    public String toString(){
        return this.name;
    }
}
