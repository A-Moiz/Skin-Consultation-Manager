public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDate() {
        String date = String.format("%02d/%02d/%04d", day, month, year);
        return date;
    }

    @Override
    public String toString(){
        String dateStr = String.format("%02d/%02d/%04d", day, month, year);
        return dateStr;
    }

    // Comparing 2 Dates
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Date date = (Date) obj;
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }
}
