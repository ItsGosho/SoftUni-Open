public class Interval {

    private double start;
    private double end;

    public Interval(double start, double end) {
        this.validateInterval(start, end);
        this.setStart(start);
        this.setEnd(end);
    }

    public double getStart() {
        return this.start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return this.end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public boolean intersects(double start, double end) {
        validateInterval(start, end);

        return this.start < end && this.end > start;
    }

    @Override
    public boolean equals(Object obj) {
        Interval other = (Interval) obj;
        return this.start == other.start && this.end == other.end;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", this.start, this.end);
    }

    private void validateInterval(double start, double end) {
        if (end < start) {
            throw new IllegalArgumentException();
        }
    }
}
