package com.g1004.getout.element;

public class Key extends Element {
    public Key(int x, int y) {
        super(x, y, "F", "#B57B0E");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Key) o).getPosition()));
    }
}