import java.util.Stack;
import java.util.HashSet;

class Expresion {
	private String expr;
	private HashSet<Character> openBraces;
	private Stack<Character> stack;
	Expresion(String expr) {
		this.expr = expr;
		openBraces= new HashSet<>();
		openBraces.add('(');
		openBraces.add('{');
		openBraces.add('[');
		stack = new Stack<>();
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}
	public boolean matchCloseBraces(char closeBrace) {
		Character popData;
		if (stack.isEmpty())
			return false;
		popData = stack.pop();
		boolean bResult = false;
		if ((closeBrace == ')') &&  (popData == '(')) { 
			bResult = true;
		} else if ((closeBrace == '}') &&  (popData == '{')) {
			bResult = true;
		} else if ((closeBrace == ']') &&  (popData == '[')) {
			bResult = true;
		}
		// if (!bResult) {
			// System.out.println(String.valueOf(popData) + " : " + String.valueOf(closeBrace));
		// }
		return bResult;
		
	}
	//Using HashSet, we can achieve O(n) time complexity
	public boolean checkExpr() {
		boolean bResult = true;
		for(int i=0; i < expr.length() ; i++ ) {
			if (openBraces.contains(expr.charAt(i))) {
				stack.push(expr.charAt(i));
			} else {
				if (!matchCloseBraces(expr.charAt(i))) 
				{
					bResult = false;
					break;
				}
			}
		}
		return bResult;
	}
	
}
class BalancedExpressionCheck {
	public static void main(String[] args) {
		String balExpr = "[()()]"; //true
		Expresion expr = new Expresion(balExpr);
		
		System.out.println(expr.checkExpr());
		expr.setExpr("{}{}{}{}{}{}{}{}{}{}{}{{}{}{}{}{}{}{}}"); //true
		System.out.println(expr.checkExpr());

		expr.setExpr("{}[(]"); //true
		System.out.println(expr.checkExpr());
		
	}

}