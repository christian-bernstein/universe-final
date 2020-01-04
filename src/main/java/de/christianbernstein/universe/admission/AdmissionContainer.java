package de.christianbernstein.universe.admission;

import java.util.ArrayList;

public class AdmissionContainer extends ArrayList<Admission> {

    public boolean hasAdmissions(final AdmissionContainer container){
        for (final Admission admission : container) {
            if (contains(admission)) return true;
        }
        return false;
    }

    public boolean hasAdmission(final Admission admission){
       return this.contains(admission);
    }
}
