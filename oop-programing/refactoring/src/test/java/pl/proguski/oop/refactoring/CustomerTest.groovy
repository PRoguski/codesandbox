package pl.proguski.oop.refactoring

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class CustomerTest extends Specification {

    private static final NAME = "Tom"

    @Shared
    Customer customer

    void setup() {
        customer = new Customer(NAME)
    }

    def 'Customer with empty rental should have 0 amounts and 0 points'() {
        expect:
        customer.statement() ==
                "Rental Record for $NAME\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points"
    }

    @Unroll
    def 'rent one movie(price code #price_code) for #days day/s should cost #amount and return #freqPoints point/s'() {
        expect:
        customer.addRental(new Rental(new Movie("Temp movie title", price_code), days))
        customer.statement() ==
                "Rental Record for $NAME\n" +
                "\tTemp movie title\t$amount\n" +
                "Amount owed is $amount\n" +
                "You earned $freqPoints frequent renter points"

        where:
        days | price_code || amount | freqPoints
        /* REGULAR */
        1    | 0          || 2.0    | 1
        2    | 0          || 2.0    | 1
        3    | 0          || 3.5    | 1
        5    | 0          || 6.5    | 1
        10   | 0          || 14.0   | 1
        /* NEW_RELEASE */
        1    | 1          || 3.0    | 1
        2    | 1          || 6.0    | 2
        3    | 1          || 9.0    | 2
        5    | 1          || 15.0   | 2
        10   | 1          || 30.0   | 2
        /* NEW_RELEASE */
        1    | 2          || 1.5    | 1
        2    | 2          || 1.5    | 1
        3    | 2          || 1.5    | 1
        5    | 2          || 4.5    | 1
        10   | 2          || 12.0   | 1
    }

    @Unroll
    def 'rent two movie (REGULAR and NEW_RELEASE) for #days day/s shoud cost #total_amount and return #freqPoints point/s'() {
        expect:

        customer.addRental(new Rental(new Movie("Regular", 0), days))
        customer.addRental(new Rental(new Movie("New release", 1), days))

        customer.statement() ==
                "Rental Record for $NAME\n" +
                "\tRegular\t$regular_movie_amount\n" +
                "\tNew release\t$newrelese_movie_amount\n" +
                "Amount owed is $total_amount\n" +
                "You earned $freqPoints frequent renter points"

        where:
        days || regular_movie_amount | newrelese_movie_amount | total_amount | freqPoints
        1    || 2.0                  | 3.0                    | 5.0          | 2
        5    || 6.5                  | 15.0                   | 21.5         | 3
        10   || 14.0                 | 30.0                   | 44.0         | 3
    }

}
