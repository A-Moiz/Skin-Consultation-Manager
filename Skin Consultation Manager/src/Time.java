public class Time {
    private int minute;
    private int hour;

    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    Time() {
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getTime() {
        String time = String.format("%02d:%02d", hour, minute);
        return time;
    }

    @Override
    public String toString() {
        String timeStr = String.format("%02d:%02d", hour, minute);
        return timeStr;
    }

    // Comparing 2 Times
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Time time = (Time) obj;
        return this.hour == time.hour && this.minute == time.minute;
    }
}
