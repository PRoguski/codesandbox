package pl.proguski.oop.refactoring;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double getPriceForDays(int days) {
        return days * 3;
    }

    @Override
    public int calculatePointsForDays(int days) {
        int points = 1;
        if (days > 1) points++;
        return points;
    }
}
