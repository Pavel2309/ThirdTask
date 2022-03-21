package com.stakhiyevich.infohandling.reader;

import com.stakhiyevich.infohandling.exception.ReaderException;

public interface TextReader {
    String readText(String textPath) throws ReaderException;
}
