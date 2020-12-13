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


    public static final class Builder {
        protected String title;
        protected int status;
        protected String detail;
        protected long timestamp;
        protected String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErroDetails build() {
            ErroDetails erroDetails = new ErroDetails();
            erroDetails.setTimestamp(timestamp);
            erroDetails.setDeveloperMessage(developerMessage);
            erroDetails.status = this.status;
            erroDetails.title = this.title;
            erroDetails.detail = this.detail;
            return erroDetails;
        }
    }
}
