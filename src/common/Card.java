package common;

public class Card<T, E> {
    private final T number;
    private final E word;

    private Card(Card.Builder<T, E> builder) {
        number = builder.number;
        word = builder.word;
    }

    public T getNumber() {
        return number;
    }

    public E getWord() {
        return word;
    }

    public static class Builder<T, E> {
        // must
        private final T number;
        private final E word;

        public Builder(T number, E word) {
            this.number = number;
            this.word = word;
        }

        public Card<T, E> build() {
            return new Card<>(this);
        }
    }
}
