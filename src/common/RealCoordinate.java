package common;

public class RealCoordinate {
    private final double x;
    private final double y;
    private final double z;
    private final double r;

    public static class Builder {
        // must
        private final double x;
        private final double y;

        // opt
        private double z;
        private double r;

        public Builder(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Builder z(double value) {
            z = value;
            return this;
        }

        public Builder r(double value) {
            r = value;
            return this;
        }

        public RealCoordinate build() {
            return new RealCoordinate(this);
        }
    }

    private RealCoordinate(Builder builder) {
        x = builder.x;
        y = builder.y;
        z = builder.z;
        r = builder.r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getR() {
        return r;
    }
}
