public class Time {

    //Properties
    private int minutes;
    private int hour;

    //Constructors
    public Time() {
        this.hour = 0;
        this.minutes = 0;
    }

    //Methods
    public boolean validate(){

        //validate time if its within 00:00 - 23:59
        boolean valid = false;

        if (this.hour >= 0 && this.hour <= 23){
            if (this.minutes >= 0 && this.minutes <= 59){
                valid = true;
            }
        }

        return valid;
    }

    public boolean isBefore(Time time){

        //Returns true if this.time is before

        boolean status = false;
        if(this.hour < time.hour){
            status = true;
        }
        else if(this.hour == time.hour){
            if(this.minutes < time.minutes){
                status = true;
            }
        }

        return status;
    }

    public boolean isAfter(Time time){

        //Returns true if this.time is after

        boolean status = false;
        if(this.hour > time.hour){
            status = true;
        }
        else if(this.hour == time.hour){
            if(this.minutes > time.minutes){
                status = true;
            }
        }

        return status;
    }

    public boolean equals(Time time) {
        //Returns true if this.time is equals
        return this.hour == time.hour && this.minutes == time.minutes;
    }

    public boolean isAfterOrEqual(Time time) {

        return this.isAfter(time) || this.equals(time);
    }

    public boolean isBeforeOrEqual(Time time) {
        return this.isBefore(time) || this.equals(time);
    }

    public String timeFormat(){
        // Formats the time into proper String
        String format = String.format("%02d:%02d", this.hour, this.minutes);
        return format;
    }

    public boolean initializeTime(String time){

        boolean valid = false;

        if (time.matches("\\d{2}:\\d{2}")){
            this.hour = Integer.parseInt(time.substring(0, 2));
            this.minutes = Integer.parseInt(time.substring(3, 5));
            valid = true;
        }

        return valid;
    }

    // Getters and Setters

    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public int getMinutes(){
        return this.minutes;
    }

    public int getHour(){
        return this.hour;
    }

}
