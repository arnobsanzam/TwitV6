package basepackage.errorresponse;

import java.util.List;

public class ErrorResponse
{
    public ErrorResponse(String message, String location, int status, List<String> details) {
        super();
        this.message = message;
        this.status = status;
        this.details = details;
        this.location = location;
    }

    private String message;
    private int status;
    private String location;
    private List<String> details;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}