import java.util.*;
/**
 * the class use linklist strcture to repersent a polynomial expression
 * and able to do some things with this Polynomial class to change it
 * @author Lengfan Yan
 * @version Assignment 5 11/18
 */ 
public class Polynomial{
  private Term head;
  //private int numTerm;
    
  /**
   * construct a Polynomial based on string of numbers passed in,The numbers are always in pairs, 
   * coefficient followed by exponent. if in the pass in String see 2 same exponent value, replace the old one by new
   * @param s String of numbers of coefficient and exponent
   */ 
  public Polynomial(String s){
    Scanner instr = new Scanner(s);
    int count = 0;
    double nowcoe = 0;
    int nowexp = 0;
    double coe = 0;
    int exp = 0;
    
    while(instr.hasNext()){
      try{                                                           //check all input good.
        count++;
        if(count %2 == 1){
          nowcoe = Double.parseDouble(instr.next());
        }
        if(count %2 == 0){
          nowexp = Integer.parseInt(instr.next());}
      }
      catch(Exception e){ instr.close();
        throw new IllegalArgumentException("input must all numbers(bad data)");}
    }
    
    if(count%2 == 1){ 
      instr.close();
      throw new IllegalArgumentException("input number of numbers must be even"); }
    
    instr.close();
    
    //re open and putting data
    instr = new Scanner(s);
    count = 0;
    head = null;
    boolean firstone = true;
    Term temp;
    while(instr.hasNext()){
      if (count % 2 == 0){                                      // fisrt num set coefi
        coe = Double.parseDouble(instr.next());
      }
      if (count % 2 == 1){                                      // second num set expon
        exp = Integer.parseInt(instr.next());
      }
      count++;
      if(count % 2 == 0){
        this.addTerm(coe, exp);}
//      // if have coe and exp, make new term
//      if (coe == 0){
//        instr.next();                                            // if coefi = 0, skip next exp
//        count++;
//      }
//      
//      if (coe != 0 && count %2 == 0){                                                
//          if (firstone = false){                                  // if is term , test where it shoud be and put it.
//            temp = head;
//            if(temp.exponent <= exp){                              // at first place
//              if(temp.exponent == exp){
//                Term t = new Term(coe, exp, temp.next);           // if exponet same  replace it
//                 head = t;
//              }
//              else{
//                Term t = new Term(coe, exp, temp); 
//                 head = t;
//              }
//            }
//            
//            else{
//              temp = head;
//              while(temp.next != null){                            // at mid
//                if(temp.next.exponent <= exp){
//                  if(temp.next.exponent == exp){
//                    Term t = new Term(coe, exp, temp.next.next);  // replace it if same exp
//                    temp.next = t;
//                  }
//                  else{
//                    Term t = new Term(coe, exp, temp.next);
//                    temp.next = t;
//                  }
//                }
//                
//                if(temp.next == null){                             //at end
//                  temp.next = new Term(coe, exp, null); 
//                }
//              }
//            }
//          }                                                               
//         Term t = new Term(coe, exp, null);                        // make first term
//         head = t;
//         firstone = false;
//        }
      }
    instr.close();
    }
    
    
    /**
     * Construct a Polynomial that is a duplicate of the one provided without modifying p.
     * @param p Polynomial that wish to copy
     */ 
    public Polynomial(Polynomial p){
      head = new Term(p.head.coefficient, p.head.exponent, p.head.next);
      Term temp = head;
      while(p.head.next != null){
       temp.next = new Term (p.head.next.coefficient, p.head.next.exponent, p.head.next.next);
       temp= temp.next;
       p.head = p.head.next;
      }
    }
    
    
    /**
     * return the number of terms in this Polynomial
     * @return give the number of terms in this Polynomial
     */ 
    public int terms( ){
      int count = 0;
      Term temp = head;
      while(temp != null){
        temp = temp.next;
        count++;
      }
      return count;
    }
    
    /**
     * add a new term to this Polynomial if there is not already a aterm with the specified exponent.
     * if a term with the specified exponent already exists, update its coefficient by summing 
     * @param coef the coeffcient of the term that is adding
     * @param exp the exponent of the term that is adding
     */ 
    public void addTerm(double coef, int exp){
      if(coef == 0){return;}
      Term temp = head;
      
      if(temp == null){                                               // add first
        Term t = new Term(coef, exp, null);
        head = t;
        temp = t;
      }
      
      else{
        if(temp.exponent <= exp){                                     // at first place
          if(temp.exponent == exp){                                   // if exp same
            temp.coefficient += coef; 
            return;
          }
          else{
          Term t = new Term(coef, exp, temp); 
          temp = t;
          }
        }
        else{
          //temp = head;
          while(temp.next != null){                                   // at mid
            if(temp.next.exponent <= exp){                     
              if(temp.next.exponent == exp){                          // if exp same
                temp.next.coefficient += coef;
                return;
              }
              else{
              Term t = new Term(coef, exp, temp.next);
              temp.next = t;
              }
            }
            if(temp.next == null){                                    //at end
              temp.next = new Term(coef, exp, null);    
            }
          }
        }
      }
    }
    
    /**
     * if a term with the specified exponent exists in this Polynomial, delete it and return its coefficient, or 0.0
     * @param exp the term with this exponent that wish to delete
     * @return coefficient that is with is deleted exponent or 0.0
     */ 
    public double deleteTerm(int exp){
      Term temp = head;
      double recoef = 0.0;
      
      if(temp.exponent == exp){                              //check first posision
        recoef = temp.coefficient;
        head = head.next;
        return recoef;
      }
        
      while(temp.next != null){                             //check mid
        if(temp.next.exponent == exp){
          recoef = temp.next.coefficient;
          temp.next = temp.next.next;
          return recoef;
        }
        temp = temp.next;
      }
      return recoef;
    }
    
  /**
   * return the coefficient for the specified exponent; return 0.0 if no such term. 
   * @param exp the target term with this exponent
   * @return the coefficient with this exponent, or 0.0
   */ 
    public double getCoefficient(int exp){
      Term temp = head;
      double recoef = 0.0;
      
      if(temp.exponent == exp){                              //check first posision
        recoef = temp.coefficient;
        return recoef;
      }
      while(temp.next != null){                             //check mid
        if(temp.next.exponent == exp){
          recoef = temp.next.coefficient;
          return recoef;
        }
        temp = temp.next;
      }
      return recoef;
    }
  
  /**
   * return the value of this Polynomial for the given value for the variable x.
   * @param x the number for unknow variable x wish to be
   * @return  the answer of this Polynomial with this x
   */ 
  public double evaluate(double x){
    double ans = 0;
    Term temp = head;
    int exp;
    double tempans;
    double coe;
    
    while(temp != null){
      exp = temp.exponent;
      coe = temp.coefficient;
      tempans = 1;
      for(int i = 0; i< exp; i++){                                  //repeat exp times of * x  
        tempans = tempans * x;
      }
      ans += tempans * coe;                                         // add single term value to answer
    }
    return ans;
  }
  
  /**
   * Override the equals method in an appropriate way
   * @param o compare to this object to something?
   * @return boolean value of answer to this mehtod
   */ 
  public boolean equals(Object o){
    Term temp = head;
    double test = 0;
    if(o instanceof Polynomial){
      Polynomial g = (Polynomial)o;
      Term head2 = g.head;
      while(head2 != null){
        test = head2.coefficient / temp.coefficient;
        if(test > 1.05 || test < 0.95){return false;}
        if(head2.exponent != temp.exponent){return false;}    
        if(head2.next != temp.next){return false;} 
        head2 = head2.next;
        temp = temp.next;
      }
    }
    return true;
  }
  
  /**
   * return the first derivateive of this Polynomial.
   * @return give back new Polynomial
   */ 
  public Polynomial derivative( ){
    Polynomial c = new Polynomial("0 0");
    Term temp = head;
    int nexp = 0;
    double ncoe = 0;
    
    if(head == null){return c;}
    
    while(temp !=null){
      nexp = temp.exponent;
      if(nexp >= 1){                                                          //add derivatived term to c if old exp >1
        ncoe = temp.coefficient * nexp;
        nexp--;
        c.addTerm(ncoe, nexp);
      }
        temp = temp.next;
    }
    return c;
  }
  
  /**
   * String repersentation for the Polynmial
   * @return String repersentation1 for the Polynmial
   */ 
  public String toString( ){
    boolean first = true;
    boolean constant = false;
    Term temp = head;
    double coe = 0.0;
    int exp = 0;
    String p = "";
    boolean zerocase;
    
    if(head == null){return "0.0";}
    
    while(temp !=null){
      zerocase = false;
      coe = temp.coefficient;
      exp = temp.exponent;
      
      if(coe == 0){ zerocase = true;}
      if(coe > 0){  
        if(first == true){
          if(coe == 1 ){ first = false;}
          else{
            p += coe;}
        }
        else{
        p += "+ " +coe;
        }
      }
      
      if(coe < 0){ 
        if(first == true){
          if(coe == -1){ 
            p += "-";
            first = false;
          }
          else{p += coe;}
        }
        else{
          p += "- " + (-coe);
        }
      }
      
      if(zerocase != true){
        
        if(exp == 1){ p += "x";}
        else if(exp == 0){
          if( coe < 1.05 && coe > 0.95 ){ p += coe;}
          else if(coe < -0.95 && coe > -1.05 ){ p += -coe;}
        }
        else{
          p += "x^" + exp + " ";
          
        }
      } 
      temp = temp.next;
    }
    return p;
  }
  
  /**
   * another way of String repersentation
   * @return String repersentation2 for the Polynmial
   */ 
  public String description( ){
    //if(head == null){return "0.0";}
    Term temp = head;
    
    return description(temp);
  }
  /**
   * private description method that do a recursive to repersent a string repersentation for Polynomial
   * @param temp the Term pass in to give this String representation
   * @return String description for this Term of polynomial
   */ 
  private String description(Term temp){
    if(temp == null){return "0.0";}
    if(temp.exponent == 0){
      return ("constant term " + temp.coefficient);
    }
    if(head.next != null){
      return (description(temp.next) + "\nexponent " + temp.exponent + ", coefficient " + temp.coefficient);
    }
    else{
      return ("exponent " + temp.exponent + ", coefficient " + temp.coefficient);}
  }
  
  /**
   * returns a new Polynomial that represents the sum of two Polynomials withour modifying them
   * @param a Polynomial 1
   * @param b Polynomial 2
   * @return new Polynomial
   */ 
  public static Polynomial sum( Polynomial a, Polynomial b){
    Polynomial c = new Polynomial(a);
    Term tempa = c.head;
    Term tempb = b.head;
    //double sumcoe;
    
    if(tempa == null){return b;}
    if(tempb == null){return a;}
    
    while(tempb != null){
//      while(tempa != null){                                                   //look for same exp
//        sumcoe = 0;
//        if(tempa.exponent == tempb.exponent){
//          sumcoe = tempa.coefficient + tempb.coefficient;
//          if(sumcoe > -0.03 && sumcoe < 0.03){}                               // if coef == 0 , do nothing
//          else{
//            c.addTerm(sumcoe, tempa.exponent);                                // add to new Polynomial
//          }
//        }
//        tempa = tempa.next;
//      }
      
      c.addTerm(tempb.coefficient, tempb.exponent);                       // add everything in b to new c
      tempb = tempb.next;
    }
    
    tempa = c.head;
    if(tempa.coefficient > -0.03 && tempa.coefficient < 0.03){                     //check if first term coef 0
      if(tempa.next != null){
        a.head = tempa.next;}
      else{a.head = null;}
    }
    while(tempa.next != null){                                                     //check if any term coef 0
      if(tempa.next.coefficient > -0.03 && tempa.next.coefficient < 0.03){
        if(tempa.next.next != null){
          tempa.next = tempa.next.next;
        }
        else{tempa.next = null;}
      }
    }
    return c;
  }
  
  
  /**
   * returns a new Polynomial that represents the product of two Polynomials withou modifying them
   * @param a Polynomial 1
   * @param b Polynomial 2
   * @return new Polynomial
   */ 
  public static Polynomial product( Polynomial a, Polynomial b){
    Polynomial c = new Polynomial(a);
    Term tempa = a.head;
    Term tempb = b.head;
    //Term tempc = c.head;
    int nexp = 0;
    double ncoe = 0;
    
    if(tempa == null){return b;}
    if(tempb == null){return a;}
    
    while(tempb != null){
      while(tempa != null){   
        nexp = tempa.exponent + tempb.exponent;                               //get new exp
        ncoe = tempa.coefficient + tempb.coefficient;                         //get new coe
        c.addTerm(ncoe, nexp);                                                //add to new Polynomial
        tempa = tempa.next;
      }
      tempb = tempb.next;
    }
    return c;
  }
  
  
  //class that use as NODE of link strcuture in Polynomial
  class Term {
    int exponent;
    double coefficient;
    Term next;
    
    //Term(){
     // next = null;
   //  coefficient = 0;
   // }
    
    Term (double coe, int exp, Term next){
     coefficient = coe;
     exponent = exp;
     this.next = next;
    }
  }
}