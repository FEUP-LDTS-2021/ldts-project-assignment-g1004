package com.g1004.getout.element;

public class Bar extends Element {
    public Bar(int x, int y) {
        super(x, y, " ", "#FFA500");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Bar) o).getPosition()));
    }
}