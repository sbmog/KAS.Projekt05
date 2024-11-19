package application.model;

import java.util.ArrayList;

public class Ledsager extends Person {
    private Deltager deltager;
    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    protected Ledsager(String navn, Deltager deltager) {
        super(navn);
        this.deltager = deltager;
    }

    public void addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
            udflugt.addLedsager(this);
        }
    }

    public void removeUdflugt (Udflugt udflugt){
        udflugter.remove(udflugt);
    }

    public void setDeltager(Deltager deltager) {
        if (this.deltager!=deltager){
            this.deltager=deltager;
        }
    }
}
