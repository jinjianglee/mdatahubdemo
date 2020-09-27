package com.dh.common.util;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class test {
    public static String compute(String b, Function<String, String>function){
        String result = function.apply(b);
        return String.valueOf(result);
    }

    public static String msgCompose(String a,Function<String,String> function,Function<String,String> function1){
        return  function.compose(function1).apply(a);
    }

    public static String msgAddThen(String b,Function<String,String> function,Function<String,String> function2){
        return function.andThen(function2).apply(b);
    }

    public static String biCompute(String a, String b, BiFunction<String,String,String>function){
        return function.apply(a,b);
    }

    public static String boCompute(String a, BinaryOperator<String> binaryOperator){
        return binaryOperator.apply(a,"v");
    }

    public  int[] DCSort(int[] arr){
       if (arr.length<2){
           return arr;
       }
       int[] copyArr = Arrays.copyOf(arr,arr.length);
       int middle = arr.length/2;
       int[] left = Arrays.copyOfRange(copyArr,0,middle);
       int[] right = Arrays.copyOfRange(copyArr,middle,arr.length);
       return merge(DCSort(left),DCSort(right));
    }

    private int[] merge(int[] left,int[] right){
        int[] result = new int[left.length+right.length];
        int i=0;
        while (left.length > 0 && right.length>0){
            if (left[0]<right[0]){
                result[i++] = left[0];
                left = Arrays.copyOfRange(left,1,left.length);
            }else{
                result[i++] = right[0];
                right = Arrays.copyOfRange(right,1 , right.length);
            }
        }
        while(left.length>0){
            result[i++] = left[0];
            left = Arrays.copyOfRange(left,1,left.length);
        }
        while(right.length>0){
            result[i++] = right[0];
            right = Arrays.copyOfRange(right,1 , right.length);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
