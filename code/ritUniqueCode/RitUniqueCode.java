package unique.code.ritUniqueCode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class RitUniqueCode {

    private String[] digit, uppercase, lowercase;
    private String prefix, suffix, excludeChar;
    private int codeLength, numberofChar;
    private BigDecimal maximumLimit;
    private List<String> charSet;
    private Map<Integer, String> separators;
    public RitUniqueCode(){
        this.digit=new String[]{"0", "1", "3", "4", "5", "6", "7", "8", "9"};
        this.uppercase=new String[]{"A", "B", "C", "D", "E", "F", "G", "h", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        this.lowercase=new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        this.prefix="";
        this.suffix="";
        this.excludeChar="";
        this.codeLength=15;
        this.separators=new LinkedHashMap<>();
        this.charSet=new ArrayList<>();
    }
    public void updateParams(){
        String[] items=excludeChar.split("");
        List<String> listWithoutDuplicates = this.charSet.stream().distinct().collect(Collectors.toList());
        List<String> alist=new ArrayList<>();
        for (String s: listWithoutDuplicates){
            boolean same=false;
            for (String item:items){
                if (s.equals(item)){
                    same=true;
                }
            }
            if (!same){
                alist.add(s);
            }
        }
        this.charSet=alist;
        Collections.shuffle(this.charSet);
        numberofChar=this.charSet.size();
        double d=Math.pow(numberofChar,codeLength);
        maximumLimit=new BigDecimal(Double.toString(d));
    }
    public void pushDigit(){
        charSet.addAll(Arrays.asList(digit));
        updateParams();
    }
    public void pushUppercase(){
        charSet.addAll(Arrays.asList(uppercase));
        updateParams();
    }
    public void pushLowercase(){
        charSet.addAll(Arrays.asList(lowercase));
        updateParams();
    }
    public void includeCharacters(String str){
        String[] s= str.split("");
        charSet.addAll(Arrays.asList(s));
        updateParams();
    }
    public void excludeCharacters(String str){
        excludeChar=str;
        updateParams();
    }
    public int getNumberOfCharacters(){
        return this.numberofChar;
    }
    public BigDecimal getMaximumLimit(){
        return this.maximumLimit;
    }
    public void setCodeLength(int length){
        this.codeLength=length;
        updateParams();
    }
    private String decimalToCode(BigDecimal decVal){
        if (maximumLimit.compareTo(decVal) > 0){
            StringBuilder result = new StringBuilder();
            while(numberofChar>0){
                BigDecimal bd=new BigDecimal(Integer.toString(numberofChar));
                BigDecimal tn=decVal.remainder(bd);
                result.append(charSet.get(tn.intValue()));
                decVal=decVal.divide(bd, RoundingMode.FLOOR);
                if (decVal.compareTo(new BigDecimal("0"))==0){
                    for (int i=codeLength-result.length();i>0;i--){
                        result.append(charSet.get(0));
                    }
                    result = result.reverse();
                    break;
                }
            }
            return result.toString();
        }else{
            return null;
        }
    }

    public void setPrefix(String str){
        prefix=str;
    }
    public void setsuffix(String suffix){
        this.suffix=suffix;
    }
    public void setSeparator(Integer offset, String str){
        separators.put(offset, str);
        Map<Integer, String> reverseSortedMap = new LinkedHashMap<>();
        separators.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        separators=reverseSortedMap;
    }

    public String codeCompose(BigDecimal decVal){
        String compose=decimalToCode(decVal);
        Set<Integer> keys=separators.keySet();
        for (Integer key:keys){
            compose=compose.substring(0, key)+separators.get(key)+compose.substring(key);
        }
        return this.prefix+compose+this.suffix;
    }
    private RitUniqueCodeOutput codeReturnSchema(int quantity, BigDecimal startValue, BigDecimal endValue, List<String> code){
        RitUniqueCodeOutput ritUniqueCodeOutput= new RitUniqueCodeOutput();

        Map<Integer, String> separate=new LinkedHashMap<>();
        Set<Integer> keys=separators.keySet();
        for (Integer key:keys){
            separate.put(key, separators.get(key));
        }
        Map<String, Object> m=new LinkedHashMap<>();
        ritUniqueCodeOutput.setActualCodeLength(this.codeLength);
        ritUniqueCodeOutput.setFinalCodeLength(code.get(0).length());
        ritUniqueCodeOutput.setMaximumLimit(this.maximumLimit.intValue());
        ritUniqueCodeOutput.setStartValue(startValue.toString());
        ritUniqueCodeOutput.setEndValue(endValue.toString());
        ritUniqueCodeOutput.setNumberOfCode(quantity);
        ritUniqueCodeOutput.setNumberOfCharacter(numberofChar);
        ritUniqueCodeOutput.setCharacterSet(this.charSet);
        ritUniqueCodeOutput.setPrefix(this.prefix);
        ritUniqueCodeOutput.setSuffix(this.suffix);
        ritUniqueCodeOutput.setSeparators(separators);
        ritUniqueCodeOutput.setCodes(code);
        return ritUniqueCodeOutput;
    }
    public RitUniqueCodeOutput generateCode(BigDecimal quantity, Boolean ordered, BigDecimal starter) throws Exception {
        if (quantity.compareTo(this.maximumLimit)>0){
            throw new Exception("Error: Number of unique code quantity exceeds the maximum limit!");
        }else if (starter.compareTo(this.maximumLimit)>0){
            throw new Exception("Error: Unique code given starter value exceeds the maximum limit!");
        }
        List<String> codes=new ArrayList<>();
        BigDecimal std=new BigDecimal("-1");
        if (!ordered && starter.equals(std) && this.maximumLimit.compareTo(quantity.multiply(new BigDecimal("2")))>0){
            int segments=71;
            BigDecimal possible_number=this.maximumLimit.subtract(new BigDecimal("1"));
            BigDecimal divided_number= possible_number.divide(BigDecimal.valueOf(segments), RoundingMode.CEILING);
            BigDecimal divided_number_const=divided_number;
            int total_count=0;
            while(divided_number.compareTo(new BigDecimal("0"))>0){
                BigDecimal final_number=divided_number;
                for (int i=segments;i-1>0;i--){
                    total_count++;
                    //System.out.println(final_number);
                    codes.add(this.codeCompose(final_number));
                    if (total_count>=quantity.intValue()){
                        break;
                    }
                    final_number=final_number.add(divided_number_const);
                }
                if (total_count>=quantity.intValue()){
                    break;
                }
                divided_number=divided_number.subtract(new BigDecimal("1"));
            }
            //System.out.println(codes.toString());
            return this.codeReturnSchema(quantity.intValue(),new BigDecimal("0"),new BigDecimal("0"),codes);
        }else{
            BigDecimal d=new BigDecimal("0");
            if (d.compareTo(starter)>0){
                BigDecimal bm=this.maximumLimit.divide(new BigDecimal("2"), RoundingMode.CEILING);
                BigDecimal bq=quantity.divide(new BigDecimal("2"), RoundingMode.CEILING);
                starter=bm.subtract(bq);
            }else if (starter.compareTo(maximumLimit)>0){
                starter=new BigDecimal("0");
            }
            BigDecimal runner=starter;
            for (int i=0;i<quantity.intValue();i++){
                codes.add(codeCompose(runner));
                runner=runner.add(new BigDecimal("1"));
                if (runner.compareTo(maximumLimit)>0){
                    runner=new BigDecimal("0");
                }
            }
            runner=runner.subtract(new BigDecimal("1"));
            if (!ordered){
                Collections.shuffle(codes);
            }
            return this.codeReturnSchema(quantity.intValue(),starter, runner, codes);
        }
    }
}
