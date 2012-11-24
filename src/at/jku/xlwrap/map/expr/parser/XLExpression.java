/* Generated By:JavaCC: Do not edit this line. XLExpression.java */
package at.jku.xlwrap.map.expr.parser;

import at.jku.xlwrap.common.XLWrapException;
import at.jku.xlwrap.map.expr.*;
import at.jku.xlwrap.map.expr.val.*;
import at.jku.xlwrap.map.expr.func.*;
import java.io.Reader;
import java.io.StringReader;

/** XLWrap Expression Parser */
@SuppressWarnings("all") // suppress compiler warnings
public class XLExpression implements XLExpressionConstants {
        private static XLExpression initialized = null;

        /** a main for isolated testing of the parser */
        public static void main(String args[]) throws ParseException {
                XLExpression parser = new XLExpression(System.in);
                XLExpr e = parser.XLExpression();
                System.out.println("OK: " + e);
        }

        public static XLExpr parse(String input) throws ParseException {
                if (initialized == null)
                        initialized = new XLExpression(new StringReader(input));
                else
                        XLExpression.ReInit(new StringReader(input));
                return XLExpression.XLExpression();
        }

/* main non-terminals */
  static final public XLExpr XLExpression() throws ParseException {
        XLExpr e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQ1:
      jj_consume_token(EQ1);
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    e = OrExpr();
    jj_consume_token(0);
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr OrExpr() throws ParseException {
        XLExpr e, other;
    e = AndExpr();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      jj_consume_token(OR);
      other = AndExpr();
                                                  e = new E_LogicalOr(e, other);
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr AndExpr() throws ParseException {
        XLExpr e, other;
    e = Comparable();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(AND);
      other = Comparable();
                                                  e = new E_LogicalAnd(e, other);
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr Comparable() throws ParseException {
        XLExpr e, other;
        E_Compare compOp;
    e = Concatable();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
      case LE:
      case GE:
      case GT:
      case NE:
      case EQ1:
      case EQ2:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      compOp = CompOp(e);
      other = Concatable();
                                                          compOp.setArg2(other); e = compOp;
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr Concatable() throws ParseException {
        XLExpr e, sub;
    e = Expr();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 36:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_4;
      }
      jj_consume_token(36);
      sub = Expr();
                                                                  e = new E_Concat(e, sub);
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr Expr() throws ParseException {
        XLExpr e;
        XLExpr2 e2;
        XLExpr sub;
    e = Term();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
                                                  e2 = new E_Add(e, null);
        break;
      case MINUS:
        jj_consume_token(MINUS);
                                                  e2 = new E_Substract(e, null);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      sub = Term();
                                          e2.setArg2(sub); e = e2;
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr Term() throws ParseException {
        XLExpr e;
        XLExpr2 e2;
        XLExpr sub;
    e = Factor();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
      case DIV:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
        jj_consume_token(MUL);
                                                  e2 = new E_Multiply(e, null);
        break;
      case DIV:
        jj_consume_token(DIV);
                                                  e2 = new E_Divide(e, null);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      sub = Factor();
                                          e2.setArg2(sub); e = e2;
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr Factor() throws ParseException {
        XLExpr e;
        XLExpr2 e2;
        XLExpr sub;
    e = Atom();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case POW:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_7;
      }
      jj_consume_token(POW);
                                          e2 = new E_Power(e, null);
      sub = Atom();
                                  e2.setArg2(sub); e = e2;
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public XLExpr Atom() throws ParseException {
        boolean neg = false;
        boolean not = false;
        boolean percent = false;

        XLExpr e, sub;
        XLExprFunction func;
        Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        break;
      case MINUS:
        jj_consume_token(MINUS);
                                          neg = true;
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
                                                  not = true;
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    if (jj_2_1(2)) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        t = jj_consume_token(INT);
                                                                  e = new E_Long(t.image);
        break;
      case DEC:
        t = jj_consume_token(DEC);
                                                                  e = new E_Double(t.image);
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PERCENT:
        jj_consume_token(PERCENT);
                                                                  percent = true;
        break;
      default:
        jj_la1[14] = jj_gen;
        ;
      }
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
      case FALSE:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TRUE:
          t = jj_consume_token(TRUE);
                                                                  e = E_Boolean.TRUE;
          break;
        case FALSE:
          t = jj_consume_token(FALSE);
                                                          e = E_Boolean.FALSE;
          break;
        default:
          jj_la1[15] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      case NULL:
        t = jj_consume_token(NULL);
                                                  e = new XLExprNullValue();
        break;
      case SQ_STRING:
        t = jj_consume_token(SQ_STRING);
                                                                  e = new E_String(t.image.substring(1, t.image.length() - 1));
        break;
      case DQ_STRING:
        t = jj_consume_token(DQ_STRING);
                                                                  e = new E_String(t.image.substring(1, t.image.length() - 1));
        break;
      case CELLRANGE:
        // cell range
                                t = jj_consume_token(CELLRANGE);
                                try {
                                        e = new E_RangeRef(t.image);
                                } catch (XLWrapException ex) {
                                        {if (true) throw new ParseException("Failed to parse xl:Expr because of an invalid range: " + ex.getMessage());}
                                }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PERCENT:
          jj_consume_token(PERCENT);
                                                                  percent = true;
          break;
        default:
          jj_la1[16] = jj_gen;
          ;
        }
        break;
      case 37:
        jj_consume_token(37);
        e = OrExpr();
        jj_consume_token(38);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PERCENT:
          jj_consume_token(PERCENT);
                                                                  percent = true;
          break;
        default:
          jj_la1[17] = jj_gen;
          ;
        }
        break;
      case FUNCIDENT:
        // function call
                                t = jj_consume_token(FUNCIDENT);
                                                                  func = FunctionRegistry.createInstance(t.image);
        jj_consume_token(37);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MINUS:
        case NOT:
        case TRUE:
        case FALSE:
        case NULL:
        case INT:
        case DEC:
        case CELLRANGE:
        case FUNCIDENT:
        case CONSTIDENT:
        case SQ_STRING:
        case DQ_STRING:
        case 37:
          // optional arguments
                                                  sub = OrExpr();
                                                                          func.addArg(sub);
          label_8:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case 39:
            case 40:
              ;
              break;
            default:
              jj_la1[18] = jj_gen;
              break label_8;
            }
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case 39:
              jj_consume_token(39);
              break;
            case 40:
              jj_consume_token(40);
              break;
            default:
              jj_la1[19] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
            // more arguments, comma or semicolon-separated list
                                                            sub = OrExpr();
                                                                          func.addArg(sub);
          }
          break;
        default:
          jj_la1[20] = jj_gen;
          ;
        }
        jj_consume_token(38);
                                                                                          e = func;
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PERCENT:
          jj_consume_token(PERCENT);
                                                                          percent = true;
          break;
        default:
          jj_la1[21] = jj_gen;
          ;
        }
        break;
      case CONSTIDENT:
        // constant reference
                                t = jj_consume_token(CONSTIDENT);
                                                                  e = new E_ConstantRef(t.image.substring(1, t.image.length()));
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                if (not) { e = new E_LogicalNot(e); }
                if (neg) { e = new E_Negative(e); }
                if (percent) { e = new E_Percent(e); }
                {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

/* operators */
  static final public E_Compare CompOp(XLExpr e) throws ParseException {
        E_Compare comp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LT:
      jj_consume_token(LT);
               comp = new E_LessThan(e, null);
      break;
    case LE:
      jj_consume_token(LE);
               comp = new E_LessOrEqual(e, null);
      break;
    case GE:
      jj_consume_token(GE);
               comp = new E_GreaterOrEqual(e, null);
      break;
    case GT:
      jj_consume_token(GT);
               comp = new E_GreaterThan(e, null);
      break;
    case NE:
      jj_consume_token(NE);
               comp = new E_NotEqual(e, null);
      break;
    case EQ1:
      jj_consume_token(EQ1);
                comp = new E_Equal(e, null);
      break;
    case EQ2:
      jj_consume_token(EQ2);
                comp = new E_Equal(e, null);
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return comp;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_3R_11() {
    if (jj_scan_token(PERCENT)) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_scan_token(DEC)) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(INT)) return true;
    return false;
  }

  static private boolean jj_3_1() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_9()) {
    jj_scanpos = xsp;
    if (jj_3R_10()) return true;
    }
    xsp = jj_scanpos;
    if (jj_3R_11()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public XLExpressionTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[24];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20000,0x80000,0x100000,0x7f000,0x0,0xc0,0xc0,0x300,0x300,0x800,0xc0,0xc0,0x200000,0x6000000,0x400,0xc00000,0x400,0x400,0x0,0x0,0x17e000c0,0x400,0x11c00000,0x7f000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x10,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x180,0x180,0x2f,0x0,0x2f,0x0,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[1];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public XLExpression(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public XLExpression(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new XLExpressionTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public XLExpression(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new XLExpressionTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public XLExpression(XLExpressionTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(XLExpressionTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[41];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 24; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 41; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
