package de.rub.nds.tlsscanner.constants;

import de.rub.nds.tlsscanner.rating.TestResult;
import java.util.HashMap;

public class ColorEncoding {

    private HashMap<TestResult, AnsiColor> colorMap;

    public ColorEncoding() {
        colorMap = null;
    }

    public ColorEncoding(HashMap<TestResult, AnsiColor> colorMap) {
        this.colorMap = colorMap;
    }

    public AnsiColor getColor(TestResult result) {
        AnsiColor color = colorMap.get(result);
        return color;
    }

    public String encode(TestResult result, String encodedText) {
        AnsiColor color = this.getColor(result);
        if (color != AnsiColor.DEFAULT_COLOR) {
            return color.getCode() + encodedText + AnsiColor.ANSI_RESET.getCode();
        } else {
            return encodedText;
        }
    }

}
