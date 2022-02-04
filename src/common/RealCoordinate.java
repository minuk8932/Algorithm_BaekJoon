package common;

public class RealCoordinate {
    private final double x;
    private final double y;

    public static class Builder {
        // must
        private final double x;
        private final double y;

        // opt
        private double z;

        public Builder(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Builder z(double value) {
            z = value;
            return this;
        }

        public RealCoordinate build() {
            return new RealCoordinate(this);
        }
    }

    private RealCoordinate(Builder builder) {
        x = builder.x;
        y = builder.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
