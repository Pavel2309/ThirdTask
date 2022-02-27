package com.stakhiyevich.infohandling.entity;

import java.util.List;

public interface TextComponent {

    String convertToString();

    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    TextElementType getElementType();

    List<TextComponent> getComponents();

}
