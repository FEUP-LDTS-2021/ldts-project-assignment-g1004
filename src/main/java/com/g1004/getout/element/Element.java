package com.g1004.getout.element;

import com.g1004.getout.position.Position;

public abstract class Element {
    protected Position position;
    protected final String symbol;
    protected final String colour;

    public Element(int x, int y, String symbol, String colour) {
        position = new Position(x, y);
        this.symbol = symbol;
        this.colour = colour;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.moveTo(position);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColour() {
        return colour;
    }
}