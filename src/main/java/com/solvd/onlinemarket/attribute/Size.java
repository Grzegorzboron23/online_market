package com.solvd.onlinemarket.attribute;


import com.solvd.onlinemarket.exception.InvalidValueException;

public class Size {

    private Float height;
    private Float width;
    private Float depth;

    public Size(Float height, Float width, Float depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        if (width < 0) {
            throw new InvalidValueException("Width cannot be negative.");
        }
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        if (height < 0) {
            throw new InvalidValueException("Height cannot be negative.");
        }
        this.height = height;
    }

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        if (depth < 0) {
            throw new InvalidValueException("Depth cannot be negative.");
        }
        this.depth = depth;
    }
}
