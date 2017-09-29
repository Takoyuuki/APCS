 //Name:   Date:
 //modeling a polynomial using a treeMap
import java.util.*;
public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));

      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println(poly2.toString());

      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
   }
}
 interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
  private TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
  public void makeTerm(Integer exp, Integer coef){
    int curr = 0;
    if(m.keySet().contains(exp)){
      curr = m.get(exp);
    }
    curr += coef;
    m.put(exp, curr);
  }
  public double evaluateAt(double x){
    double val = 0.0;
    for(int pow: m.keySet()){
      val += (m.get(pow) * Math.pow(x, pow));
    }
    return val;
  }
  public Polynomial add(Polynomial other){
    Polynomial p = new Polynomial();
    for(int k: this.m.keySet()){
      p.makeTerm(k, this.m.get(k));
    }
    for(int k: other.m.keySet()){
      p.makeTerm(k, other.m.get(k));
    }
    return p;
  }
  public Polynomial multiply(Polynomial other){
    Polynomial p = new Polynomial();
    for(int i: this.m.keySet()){
      for(int k: other.m.keySet()){
        p.makeTerm(i+k, this.m.get(i)*other.m.get(k));
      }
    }
    return p;
  }
  public String toString(){
    String s = "";
    for(int k: m.keySet()){
      int coef = m.get(k);
      if(k == 0){
        s += coef;
      }
      else if(k == 1){
        if(coef == 1){
          s = "x + " + s;
        }
        else{
          s = coef + "x + " + s;
        }
      }
      else {
        if(coef == 1){
          s = "x^" + k  + " + " + s;
        }
        else {
          s = coef + "x^" + k  + " + " + s;
        }
      }
    }
    return s;
  }
}
/*
expected output
   2x^3 + -4x + 2
   10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */
