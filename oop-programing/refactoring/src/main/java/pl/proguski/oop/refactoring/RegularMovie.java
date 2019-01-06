package pl.proguski.oop.refactoring;

public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title);
    }

    @Override
    public double getPriceForDays(int days) {
        double total = 2;
        if (days > 2)
            total += (days - 2) * 1.5;
        return total;
    }

    @Override
    public int calculatePointsForDays(int days) {
        return 1;
    }
}
