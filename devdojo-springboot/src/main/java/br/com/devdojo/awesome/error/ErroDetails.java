package br.com.devdojo.awesome.error;

public class ErroDetails {
    protected String title;
    protected int status;
    protected String detail;
    protected long timestamp;
    protected String developerMessage;


    public String getTitle() {
        return title;
    }



    public int getStatus() {
        return status;
    }


    public String getDetail() {
        return detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
