package com.g1004.getout.structures;

import com.g1004.getout.position.Position;
import com.g1004.getout.element.Bar;

import java.util.ArrayList;
import java.util.List;

public class Ladder {
    private final Position top;
    private final Position bottom;
    private final List<Bar> bars;

    public Ladder(Position top, Position bottom) {
        this.top = top;
        this.bottom = bottom;
        bars = createBars();
    }

    private List<Bar> createBars() {
        List<Bar> bars = new ArrayList<>();

        int n = bottom.getY() - top.getY() + 1;
        for (int i = 0; i < n; i++)
            bars.add(new Bar(top.getX(), top.getY() + i));

        return bars;
    }

    public Position getTop() {
        return top;
    }

    public Position getBottom() {
        return bottom;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public boolean hasElement(Position position) {
        return ((bottom.getX() == position.getX()) && (position.getY() <= bottom.getY()) && (position.getY() >= top.getY()));
    }
}