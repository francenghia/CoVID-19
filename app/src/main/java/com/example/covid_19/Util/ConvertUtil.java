package com.example.covid_19.Util;

public class ConvertUtil {
    public static int ReplaceAllStringToInt(String data)
    {
        String newConvert = data.replaceAll(",","");
        int result = Integer.parseInt(newConvert);
        return result;
    }
}
