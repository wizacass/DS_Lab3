package edu.ktu.ds.lab3.petrauskas;

import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.Parsable;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Phone implements Parsable<Phone>
{
    final static private int minYear = 2014;

    final static private double minPrice = 100.0;
    final static private double maxPrice = 1500.0;

    private String _manufacturer;
    private String _model;
    private int _batteryCapacity;
    private int _makeYear;
    private double _price;
    private double _diagonalScreenSize;
    private Boolean _hasHeadphoneJack;

    private Boolean _status;

    public Phone() { }

    public Phone(
            String manufacturer, String model, int batteryCapacity,
            int makeYear, double price, double diagonalScreenSize,
            Boolean hasHeadphoneJack)
    {
        this._manufacturer = manufacturer;
        this._model = model;
        this._batteryCapacity = batteryCapacity;
        this._makeYear = makeYear;
        this._price = price;
        this._diagonalScreenSize = diagonalScreenSize;
        this._hasHeadphoneJack = hasHeadphoneJack;
        this._status = true;
    }

    public Phone(String data)
    {
        parse(data);
    }

    public static int getMinYear()
    {
        return minYear;
    }

    public static double getMinPrice()
    {
        return minPrice;
    }

    public static double getMaxPrice()
    {
        return maxPrice;
    }

    public String getManufacturer()
    {
        return _manufacturer;
    }

    public String getModel()
    {
        return _model;
    }

    public int getBatteryCapacity()
    {
        return _batteryCapacity;
    }

    public int getMakeYear()
    {
        return _makeYear;
    }

    public double getPrice()
    {
        return _price;
    }

    public double getDiagonalScreenSize()
    {
        return _diagonalScreenSize;
    }

    public Boolean getHasHeadphoneJack()
    {
        return _hasHeadphoneJack;
    }

    public Boolean parsedSuccessfully()
    {
        return _status;
    }

    public void setPrice(double price)
    {
        this._price = price;
    }

    @Override
    public void parse(String dataString)
    {
        _status = true;
        try
        {
            var data = new Scanner(dataString);
            this._manufacturer = data.next();
            this._model = data.next();
            this._batteryCapacity = data.nextInt();
            this._makeYear = data.nextInt();
            this._price = data.nextDouble();
            this._diagonalScreenSize = data.nextDouble();
            var headphoneData = data.next();

            if (headphoneData.equals("true"))
            {
                this._hasHeadphoneJack = true;
            }
            else if (headphoneData.equals("false"))
            {
                this._hasHeadphoneJack = false;
            }
            else
            {
                throw new InputMismatchException();
            }
        }
        catch (InputMismatchException e)
        {
            Ks.ern(String.format("Invalid Phone data string -> %s", dataString));
            this._status = false;
        }
        catch (NoSuchElementException e)
        {
            Ks.ern(String.format("Missing data on Phone -> %s", dataString));
            this._status = false;
        }
    }

    public int compareTo(Phone other)
    {
        if (Integer.compare(this.getMakeYear(), other.getMakeYear()) == 0)
        {
            return Double.compare(this.getPrice(), other.getPrice());
        }
        else
        {
            return -Integer.compare(this.getMakeYear(), other.getMakeYear());
        }
    }

    @Override
    public String toString()
    {
        if (this.parsedSuccessfully())
        {
            return String.format("%s %s, %s, %s$, %dmAh, %sin, headphone jack: %s.",
                    this.getManufacturer(),
                    this.getModel(),
                    this.getMakeYear(),
                    this.getPrice(),
                    this.getBatteryCapacity(),
                    this.getDiagonalScreenSize(),
                    this.getHasHeadphoneJack() ? "yes" : "no"
            );
        }
        else
        {
            return "Invalid phone object!";
        }
    }
}
