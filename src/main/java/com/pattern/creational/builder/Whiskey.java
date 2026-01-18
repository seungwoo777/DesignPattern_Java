package com.pattern.creational.builder;


import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Whiskey {
    private String name;
    private String type;
    private BigDecimal price;
    private String taste;
    private String year;

    Whiskey(String name, String type, BigDecimal price, String taste, String year) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.taste = taste;
        this.year = year;
    }

    public static WhiskeyBuilder builder() {
        return new WhiskeyBuilder();
    }


    public static class WhiskeyBuilder {
        private String name;
        private String type;
        private BigDecimal price;
        private String taste;
        private String year;

        WhiskeyBuilder() {
        }

        public WhiskeyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public WhiskeyBuilder type(String type) {
            this.type = type;
            return this;
        }

        public WhiskeyBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public WhiskeyBuilder taste(String taste) {
            this.taste = taste;
            return this;
        }

        public WhiskeyBuilder year(String year) {
            this.year = year;
            return this;
        }

        public Whiskey build() {
            return new Whiskey(this.name, this.type, this.price, this.taste, this.year);
        }

        public String toString() {
            return "Whiskey.WhiskeyBuilder(name=" + this.name + ", type=" + this.type + ", price=" + this.price + ", taste=" + this.taste + ", year=" + this.year + ")";
        }
    }
}
