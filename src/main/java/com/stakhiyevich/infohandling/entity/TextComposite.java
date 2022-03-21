package com.stakhiyevich.infohandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComposite implements TextComponent {

    private final TextElementType elementType;
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public String convertToString() {
        String separator = elementType.getSeparator();
        return this.getComponents().stream()
                .map(textComponent -> textComponent.convertToString() + separator)
                .collect(Collectors.joining());
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public TextElementType getElementType() {
        return elementType;
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComposite)) return false;

        TextComposite that = (TextComposite) o;

        if (getElementType() != that.getElementType()) return false;
        return getComponents() != null ? getComponents().equals(that.getComponents()) : that.getComponents() == null;
    }

    @Override
    public int hashCode() {
        int result = getElementType() != null ? getElementType().hashCode() : 0;
        result = 31 * result + (getComponents() != null ? getComponents().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextComposite{");
        sb.append("elementType=").append(elementType);
        sb.append(", components=").append(components);
        sb.append('}');
        return sb.toString();
    }
}
