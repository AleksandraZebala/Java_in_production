package jwzp.data;

public final class Range<T> {

    private T from;
    private T to;

    public Range(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public T getTo() {
        return to;
    }

    public T getFrom() {
        return from;
    }
}
