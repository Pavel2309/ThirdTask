package com.stakhiyevich.infohandling.parser;

import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.exception.ReaderException;
import com.stakhiyevich.infohandling.parser.impl.ParagraphParser;
import com.stakhiyevich.infohandling.reader.TextReader;
import com.stakhiyevich.infohandling.reader.impl.TextReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.Reader;

import static org.testng.Assert.*;

public class TextParserTest {

    private static final String TEXT_FILE = "data/text.txt";
    private TextParser parser;
    String text;

    @BeforeClass
    public void setUp() throws ReaderException {
        TextReader reader = new TextReaderImpl();
        parser = new ParagraphParser();
        text = reader.readText(TEXT_FILE);
    }

    @Test
    public void testParseText() {

        TextComposite textComposite = parser.parseText(text);
        String actual = textComposite.convertToString();
        String expected = "It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n" +
                "It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n" +
                "It is a established fact that a reader will be of a page when looking at its layout... \n" +
                "Bye бандерлоги. \n";

        assertEquals(actual, expected);
    }
}