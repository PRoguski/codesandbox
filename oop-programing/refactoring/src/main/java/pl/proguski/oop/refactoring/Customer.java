package pl.proguski.oop.refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Customer {
    private String _name;
    private List<Rental> _rentals = new ArrayList<>();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder sb = new StringBuilder("Rental Record for " + getName() + "\n");

        frequentRenterPoints = _rentals.stream().mapToInt(Rental::calculatePoints).sum();
        totalAmount = _rentals.stream().mapToDouble(Rental::calculateAmount).sum();

        //todo performance issue twice iteration over rental collection
        sb.append(_rentals.stream().map(printAmountForOneMovie()).collect(Collectors.joining()));

        //add footer lines
        sb.append("Amount owed is ").append(totalAmount).append("\n");
        sb.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return sb.toString();
    }

    private Function<Rental, String> printAmountForOneMovie() {
        return rental -> "\t"+rental.getMovie().getTitle()+"\t"+rental.calculateAmount()+"\n";
    }

}