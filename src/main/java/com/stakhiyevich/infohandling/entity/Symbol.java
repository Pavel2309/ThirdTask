package com.stakhiyevich.infohandling.entity;

import java.util.List;

public class Symbol implements TextComponent {

    private TextElementType elementType;
    private char value;

    public Symbol(char value, TextElementType elementType) {
        this.value = value;
        this.elementType = elementType;
    }

    @Override
    public String convertToString() {
        return String.valueOf(value);
    }

    @Override
    public boolean add(TextComponent component) {
        throw new UnsupportedOperationException("can't invoke add for the leaf");
    }

    @Override
    public boolean remove(TextComponent component) {
        throw new UnsupportedOperationException("can't invoke remove for the leaf");
    }

    @Override
    public TextElementType getElementType() {
        return elementType;
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("can't get top children elements for the leaf");
    }
}
