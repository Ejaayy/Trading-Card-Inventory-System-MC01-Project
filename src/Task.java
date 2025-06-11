public class Task {

    //Properties / Attributes

    private String shortName;
    private String description;
    private Time startTime;
    private Time endTime;

    // Constructors
    public Task() {
        this.startTime = new Time();
        this.endTime = new Time();
    }

    // Methods
    public boolean isValid(){
        //checks if start and end times are valid
        boolean status = false;

        if(this.startTime.isBefore(this.endTime)){
            status = true;
        }
        return status;
    }

    public String taskFormat(){
        //Returns in correct format
        String format = startTime.timeFormat() + " to " + endTime.timeFormat() + " : " + shortName + " - " + description;
        return format;
    }

    // Getters and Setters

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public String getShortName() {
        return shortName;
    }
}
