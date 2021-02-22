package nl.guyonmaissan.Garage.exceptions;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException() {
        super("Cannot find specified record.");
    }

}