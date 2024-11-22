package application.model;

public class Ledsager extends Person {
    private Deltager deltager;

    public Ledsager(String navn, Deltager deltager) {
        super(navn);
        this.deltager = deltager;
    }

    public void setDeltager(Deltager deltager) {
        if (this.deltager!=deltager){
            this.deltager=deltager;
        }
    }

    @Override
    public String toString() {
        return getNavn();
    }
}
