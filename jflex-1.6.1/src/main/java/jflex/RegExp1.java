/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JFlex 1.6.1                                                             *
 * Copyright (C) 1998-2015  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package jflex;


/**
 * Stores a regular expression from the rules section of a JFlex specification.
 *
 * This class provides storage for one Object of content.
 * It is used for all regular expressions that are constructed from one object.
 * 
 * For instance:  a*  is new RegExp1(sym.STAR, 'a');
 *
 * @author Gerwin Klein
 * @version JFlex 1.6.1
 */
public class RegExp1 extends RegExp {

  /**
   * The child of this expression node in the syntax tree of a regular expression.
   */
  Object content;

  /** true if this regexp was created from a dot/point (.) metachar */
  boolean isPoint;
  
  /**
   * Constructs a new regular expression with one child object.
   *
   * @param type   a value from the cup generated class sym, defining the 
   *               kind of this regular expression
   *
   * @param content  the child of this expression
   */
  public RegExp1(int type, Object content) {
    super(type);
    this.content = content;
  }
  

  /**
   * Returns a String-representation of this regular expression
   * with the specified indentation.
   *
   * @param tab   a String that should contain only space characters and
   *              that is inserted in front of standard String-representation
   *              pf this object.
   */
  public String print(String tab) {
    if (content instanceof RegExp) {
      return tab+"expType = "+type+Out.NL+tab+"content :"+Out.NL+((RegExp)content).print(tab+"  ");
    }
    else
      return tab+"expType = "+type+Out.NL+tab+"content :"+Out.NL+tab+"  "+content;
  }

  
  /**
   * Returns a String-representation of this regular expression
   */
  public String toString() {
    return print("");
  }
}
