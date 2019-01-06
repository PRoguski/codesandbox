package pl.proguski.oop.refactoring;

abstract class Movie {

    private String _title;

    public Movie(String title) {
        _title = title;
    }

    public String getTitle() {
        return _title;
    }

    public abstract double getPriceForDays(int days);

    public abstract int calculatePointsForDays(int days);

    @Override
    public String toString() {
        return "Movie{" +
                "_title='" + _title + '\'' +
                '}';
    }
}