package com.stakhiyevich.infohandling.entity;

import java.util.List;

public class Symbol implements TextComponent {

    private final TextElementType elementType;
    private final char value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol)) return false;

        Symbol symbol = (Symbol) o;

        if (value != symbol.value) return false;
        return getElementType() == symbol.getElementType();
    }

    @Override
    public int hashCode() {
        int result = getElementType() != null ? getElementType().hashCode() : 0;
        result = 31 * result + (int) value;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbol{");
        sb.append("elementType=").append(elementType);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
