package telran.time;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;


public class PastTemporalDateProximity implements TemporalAdjuster {
    //TODO some encapsulation
//array of temporals supprting Day, Month, Year (Dates)
    Temporal[] temporals;

    public static void main(String[] args) {
        PastTemporalDateProximity test = new PastTemporalDateProximity(new Temporal[]{
                LocalDate.of(2026, 4, 2), LocalDate.now(), LocalDate.of(2022, 9, 1)});
        Temporal res = test.adjustInto(LocalDate.of(2022, 2, 3));
        System.out.println(res);
    }


    public PastTemporalDateProximity(Temporal[] temporals) {
        LocalDate[] sorterDates;
        // this.sorterDates = new LocalDate[temporals.length];

        // int index = 0;
        // for (Temporal temporal : temporals) {
        //     this.localDates[index++] = convertToLocalDate(temporal);
        // }

        // Arrays.sort(this.localDates);
    }

    @Override
    public String toString() {
        return "PastTemporalDateProximity{" +
                "temporals=" + Arrays.toString(temporals) +
                '}';
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        // TODO Auto-generated method stub
        //return the temporal for the encapsulated array that is a nearest in past
        int currentDay = temporal.get(ChronoField.DAY_OF_MONTH);
        int currentMonth = temporal.get(ChronoField.MONTH_OF_YEAR);
        int currentYear = temporal.get(ChronoField.YEAR);

        int start = 0;
        int finish = temporals.length - 1;
        int res = -1;

        while (start <= finish) {
            int middle = start + (finish - start) / 2;

            if (temporals[middle].get(ChronoField.YEAR) > currentYear) {
                res = middle;
                finish = middle - 1;
            } else {
                start = middle + 1;
            }
        }


        return res != -1 ? res != 0 ? temporals[res - 1] : null : null;
    }

    
}

