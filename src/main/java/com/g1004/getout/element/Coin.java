package com.g1004.getout.element;

public class Coin extends Element {
    public Coin(int x, int y) {
        super(x, y, "$", "#B5A710");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Coin) o).getPosition()));
    }
}