package gio229.demo;

import androidx.annotation.NonNull;

public class Calculatrice {
    public static double calculate(@NonNull String equation) {
        double result = 0.0;
        String userExp = equation.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        String noMinus = userExp.replaceAll("-", "+-");
        String[] byPluses = noMinus.split("\\+");

        for (String multipl : byPluses) {
            String[] byMultipl = multipl.split("\\*");
            double multiplResult = 1.0;
            for (String operand : byMultipl) {
                if (operand.contains("/") && !operand.contains("%")) {
                    String[] division = operand.split("\\/");
                    double divident = Double.parseDouble(division[0]);
                    for (int i = 1; i < division.length; i++) {
                        divident /= Double.parseDouble(division[i]);
                    }
                    multiplResult *= divident;
                }else if(operand.contains("%") && !operand.contains("/")){
                    String[] modulo = operand.split("\\%");
                    double mod = Double.parseDouble(modulo[0]);
                    for (int i = 1; i < modulo.length; i++) {
                        mod %= Double.parseDouble(modulo[i]);
                    }
                    multiplResult *= mod;
                } else {
                    multiplResult *= Double.parseDouble(operand);
                }


            }
            result += multiplResult;
        }

        return result;
    }

    public static String displayString(double result){
        String res = "" + result ;
        String[] transform = res.split("\\.");

        if(transform[1].equals("0")){
            return  transform[0];
        }else{
            return res;
        }

    }

    public static boolean checkEquation(String equation){

        String userExp = equation.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        int check = 0;

        if(!userExp.matches("[0-9]")){
            check = 1 ;
        }

        String[] checker = userExp.split("[\\+|\\*|\\-|/|\\%]");




        for(String el : checker){
            if(el.equals("")){
                check = 1 ;
            }
            if (!isNumeric(el)){
                check = 1 ;
            }

        }
        if(check == 1){
            return false;
        }

        return true;

    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


}
