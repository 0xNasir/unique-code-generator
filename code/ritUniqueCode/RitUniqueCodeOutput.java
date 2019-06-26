package unique.code.ritUniqueCode;

import java.util.List;
import java.util.Map;

public class RitUniqueCodeOutput {
    private int actualCodeLength, finalCodeLength, maximumLimit, numberOfCode, numberOfCharacter;
    private String startValue, endValue, prefix, suffix;
    private List<String> characterSet, codes;
    private Map<Integer, String> separators;


    public int getActualCodeLength() {
        return actualCodeLength;
    }

    public void setActualCodeLength(int actualCodeLength) {
        this.actualCodeLength = actualCodeLength;
    }

    public int getFinalCodeLength() {
        return finalCodeLength;
    }

    public void setFinalCodeLength(int finalCodeLength) {
        this.finalCodeLength = finalCodeLength;
    }

    public int getMaximumLimit() {
        return maximumLimit;
    }

    public void setMaximumLimit(int maximumLimit) {
        this.maximumLimit = maximumLimit;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getNumberOfCode() {
        return numberOfCode;
    }

    public void setNumberOfCode(int numberOfCode) {
        this.numberOfCode = numberOfCode;
    }

    public int getNumberOfCharacter() {
        return numberOfCharacter;
    }

    public void setNumberOfCharacter(int numberOfCharacter) {
        this.numberOfCharacter = numberOfCharacter;
    }

    public List<String> getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(List<String> characterSet) {
        this.characterSet = characterSet;
    }

    public Map<Integer, String> getSeparators() {
        return separators;
    }

    public void setSeparators(Map<Integer, String> separators) {
        this.separators = separators;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}
