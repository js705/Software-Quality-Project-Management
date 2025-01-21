package com.ontariotechu.sofe3980;

import org.joda.time.LocalTime;

/**
 * Hello world!
 *
 */
public class App
{
    /**
     * Main program:  The entry point of the program. The local time will be printed first,
     *      Then it will create two binary variables, add them and print the result.
     *
     * @param args: not used
     */
    public static void main( String[] args )
    {
        LocalTime currentTime = new LocalTime();
        System.out.println("Binary Addition");
        System.out.println("The current local time is: " + currentTime);
        Binary binary1=new Binary("00010001000");
        System.out.println( "First binary number is "+binary1.getValue());
        Binary binary2=new Binary("111000");
        System.out.println( "Second binary number is "+binary2.getValue());
        Binary sum= Binary.add(binary1,binary2);
        System.out.println( "Their summation is "+sum.getValue());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Binary OR Operation");
        Binary binary3=new Binary("10100");
        System.out.println( "First binary number is "+binary3.getValue());
        Binary binary4=new Binary("0110");
        System.out.println( "Second binary number is "+binary4.getValue());
        Binary or= Binary.or(binary3,binary4);
        System.out.println( "Their OR operation gives: "+or.getValue());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Binary AND Operation");
        Binary binary5=new Binary("10100");
        System.out.println( "First binary number is "+binary5.getValue());
        Binary binary6=new Binary("0110");
        System.out.println( "Second binary number is "+binary6.getValue());
        Binary and= Binary.and(binary5,binary6);
        System.out.println( "Their OR operation gives: "+and.getValue());

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Binary Multipliaction");
        Binary binary7=new Binary("10100");
        System.out.println( "First binary number is "+binary7.getValue());
        Binary binary8=new Binary("0110");
        System.out.println( "Second binary number is "+binary8.getValue());
        Binary multiplication= Binary.multiply(binary7,binary8);
        System.out.println( "Their Multiplication gives: "+multiplication.getValue());

    }
}