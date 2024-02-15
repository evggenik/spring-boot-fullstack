package com.amigoscode.customer;

public record CustomerUpdateRequest(String name,
                                    String email,
                                    Integer age) {

}
/* to do
public class CustomerUpdateRequest {
    private final String name;
    private final String email;
    private final int age;

    public static class Builder {
        private String name;
        private String email;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public CustomerUpdateRequest build() {
            return new CustomerUpdateRequest(this);
        }
    }

    private CustomerUpdateRequest(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }
} */
