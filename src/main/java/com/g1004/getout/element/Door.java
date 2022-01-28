package com.g1004.getout.element;

public class Door extends Element {
    public Door(int x, int y) {
        super(x, y, "0", "#0000FF");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Door) o).getPosition()));
    }
}