/* Generated By:JavaCC: Do not edit this line. ADTParserTokenManager.java */
package Parser;
/** ID lister. */
import java.util.*;

/** Token Manager. */
public class ADTParserTokenManager implements ADTParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x38000L) != 0L)
         {
            jjmatchedKind = 9;
            return 27;
         }
         if ((active0 & 0x400L) != 0L)
            return 31;
         return -1;
      case 1:
         if ((active0 & 0x38000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 1;
            return 27;
         }
         return -1;
      case 2:
         if ((active0 & 0x38000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 2;
            return 27;
         }
         return -1;
      case 3:
         if ((active0 & 0x18000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 3;
            return 27;
         }
         return -1;
      case 4:
         if ((active0 & 0x18000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 4;
            return 27;
         }
         return -1;
      case 5:
         if ((active0 & 0x18000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 5;
            return 27;
         }
         return -1;
      case 6:
         if ((active0 & 0x18000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 6;
            return 27;
         }
         return -1;
      case 7:
         if ((active0 & 0x18000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 7;
            return 27;
         }
         return -1;
      case 8:
         if ((active0 & 0x18000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 8;
            return 27;
         }
         return -1;
      case 9:
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 9;
            return 27;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 35:
         return jjMoveStringLiteralDfa1_0(0xc0L);
      case 40:
         return jjStartNfaWithStates_0(0, 10, 31);
      case 41:
         return jjStopAtPos(0, 11);
      case 42:
         return jjStopAtPos(0, 20);
      case 45:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 58:
         return jjStopAtPos(0, 18);
      case 61:
         return jjStopAtPos(0, 21);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 69:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      default :
         return jjMoveNfa_0(2, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(1, 19);
         break;
      case 68:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      case 102:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 113:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L);
      case 116:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 84:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000L);
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(3, 17);
         break;
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000L);
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000L);
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 110:
         return jjMoveStringLiteralDfa8_0(active0, 0x10000L);
      case 114:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa9_0(active0, 0x8000L);
      case 115:
         return jjMoveStringLiteralDfa9_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
static private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(9, 16);
         break;
      case 115:
         return jjMoveStringLiteralDfa10_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
static private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(10, 15);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 41;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 31:
                  if ((0x7000ac0000000000L & l) != 0L)
                     kind = 12;
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 8)
                        kind = 8;
                     jjCheckNAdd(25);
                  }
                  else if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 38;
                  else if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 34;
                  else if (curChar == 40)
                     jjAddStates(0, 1);
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAdd(25);
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 9)
                     kind = 9;
                  jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 28:
                  if (curChar == 40)
                     jjAddStates(0, 1);
                  break;
               case 33:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 34;
                  break;
               case 35:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjAddStates(2, 3);
                  break;
               case 36:
                  if (curChar == 34 && kind > 13)
                     kind = 13;
                  break;
               case 37:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 38;
                  break;
               case 39:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjAddStates(4, 5);
                  break;
               case 40:
                  if (curChar == 39 && kind > 14)
                     kind = 14;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 31:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 2:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 9)
                        kind = 9;
                     jjCheckNAdd(27);
                  }
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 23;
                  else if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 17;
                  else if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 8;
                  else if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 0:
                  if (curChar == 116 && kind > 5)
                     kind = 5;
                  break;
               case 1:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 3:
                  if (curChar == 110 && kind > 5)
                     kind = 5;
                  break;
               case 4:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 5:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 8:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 10:
                  if (curChar == 114 && kind > 5)
                     kind = 5;
                  break;
               case 11:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 12:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 13:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 16:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 104)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 18:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 19:
                  if (curChar == 103 && kind > 5)
                     kind = 5;
                  break;
               case 20:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 21:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 23:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 24:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 26:
               case 27:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 9)
                     kind = 9;
                  jjCheckNAdd(27);
                  break;
               case 29:
                  if (curChar == 116 && kind > 12)
                     kind = 12;
                  break;
               case 30:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 34:
               case 35:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(35, 36);
                  break;
               case 38:
               case 39:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 41 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   31, 32, 35, 36, 39, 40, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, "\43\164", "\43\146", null, null, "\50", 
"\51", null, null, null, "\123\151\147\156\141\164\165\162\145\163\72", 
"\105\161\165\141\164\151\157\156\163\72", "\101\104\124\72", "\72", "\55\76", "\52", "\75", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x3fffe1L, 
};
static final long[] jjtoSkip = {
   0x1eL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[41];
static private final int[] jjstateSet = new int[82];
static protected char curChar;
/** Constructor. */
public ADTParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public ADTParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 41; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
