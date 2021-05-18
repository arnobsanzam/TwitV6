package basepackage.exception;

public class DatabaseDuplicateEntryException extends Throwable {
    public DatabaseDuplicateEntryException(String message) {
        super(message);
    }
}
