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
        return List.copyOf(components);
    }
}
