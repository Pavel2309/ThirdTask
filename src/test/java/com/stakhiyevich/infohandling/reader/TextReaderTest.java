package com.stakhiyevich.infohandling.reader;

import com.stakhiyevich.infohandling.exception.ReaderException;
import com.stakhiyevich.infohandling.reader.impl.TextReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextReaderTest {

    private static final String TEXT_FILE = "data/text.txt";
    private TextReader textReader;

    @BeforeClass
    public void setUp() {
        textReader = new TextReaderImpl();
    }

    @Test
    public void testReadText() {

        String actual = "";
        try {
            actual = textReader.readText(TEXT_FILE);
        } catch (ReaderException e) {
            fail("failed to read file");
        }

        String expected = "    It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n" +
                "    It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n" +
                "    It is a established fact that a reader will be of a page when looking at its layout...\n" +
                "    Bye бандерлоги.";

        assertEquals(actual, expected);
    }
}