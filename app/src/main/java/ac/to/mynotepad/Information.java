package ac.to.mynotepad;

public class Information {
    private String note;
    String currentDate;
    String currentTime;

    public Information(String note, String date, String time) {
        this.note = note;
        currentDate = date;
        currentTime = time;
    }

    public String getNote() {
        return note;
    }
    public String getCurrentDate() { return currentDate; }
    public String getCurrentTime() { return currentTime; }

    public void setNote(String note) {
        this.note = note;
    }
    public void setCurrentDate(String currentDate) { this.currentDate = currentDate; }
    public void setCurrentTime(String currentTime) { this.currentTime = currentTime; }
}


