package com.example.vanya.lab_4;

public class Currency {
    double ask;
    double bid;

    Currency(double a,double b)
    {
        this.ask = a;
        this.bid = b;
    }
    double getBid()
    {
        return this.bid;
    }

    double getAsk()
    {
        return this.ask;
    }


}
