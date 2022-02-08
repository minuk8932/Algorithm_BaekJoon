package common;

public class Road {

    private final int start;
    private final int end;
    private final int cost;

    public static class Builder {

        //must
        private final int start;
        private final int end;

        // opt
        private int cost;

        public Builder(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Builder cost(int value) {
            cost = value;
            return this;
        }

        public Road build() {
            return new Road(this);
        }
    }

    private Road(Builder builder){
        start = builder.start;
        end = builder.end;
        cost = builder.cost;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getCost() {
        return cost;
    }
}