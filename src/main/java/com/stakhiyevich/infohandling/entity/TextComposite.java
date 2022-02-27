package com.stakhiyevich.infohandling.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private TextElementType elementType;
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public String convertToString() {
        //todo convert to string
        return null;
    }

    @Override
    public boolean add(TextComponent component) {
        return component.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return component.remove(component);
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
