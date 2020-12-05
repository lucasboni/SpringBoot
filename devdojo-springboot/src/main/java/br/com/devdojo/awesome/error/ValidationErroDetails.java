package br.com.devdojo.awesome.error;

public class ValidationErroDetails extends ErroDetails {
    private String field;
    private String fieldMessage;

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public static final class Builder {
        protected String title;
        protected int status;
        protected String detail;
        protected long timestamp;
        protected String developerMessage;
        private String field;
        private String fieldMessage;

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

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErroDetails build() {
            ValidationErroDetails validationErroDetails = new ValidationErroDetails();
            validationErroDetails.setTimestamp(timestamp);
            validationErroDetails.setDeveloperMessage(developerMessage);
            validationErroDetails.field = this.field;
            validationErroDetails.detail = this.detail;
            validationErroDetails.status = this.status;
            validationErroDetails.title = this.title;
            validationErroDetails.fieldMessage = this.fieldMessage;
            return validationErroDetails;
        }
    }
}
