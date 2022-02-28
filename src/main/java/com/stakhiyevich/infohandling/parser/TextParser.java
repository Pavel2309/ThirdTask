package com.stakhiyevich.infohandling.parser;

import com.stakhiyevich.infohandling.entity.TextComposite;

public interface TextParser {
    TextComposite parseText(String text);
}
