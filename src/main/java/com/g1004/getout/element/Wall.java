package com.g1004.getout.element;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y, " ", "#964B00");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Wall) o).getPosition()));
    }
}