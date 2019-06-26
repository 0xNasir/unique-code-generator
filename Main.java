package unique;

import unique.code.ritUniqueCode.RitUniqueCode;
import unique.code.ritUniqueCode.RitUniqueCodeOutput;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        RitUniqueCode obj=new RitUniqueCode();
        obj.pushDigit();
        obj.pushUppercase();
        obj.pushLowercase();
        obj.setSeparator(4,"-");
        obj.setSeparator(3,"_");
        obj.setSeparator(6, "*");
        RitUniqueCodeOutput codelist=obj.generateCode(new BigDecimal("10000"), false, new BigDecimal("-1"));
        List<String> listofCode= codelist.getCodes();
        for (String s:listofCode){
            System.out.println(s);
        }
    }
}
