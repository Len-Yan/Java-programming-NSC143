import java.util.*;

public class testt{
  public testt(){
    String e = "abc{(def}}{ghij{kl}m]";
    boolean k = isBalanced(e);
    System.out.println(k);
  }
  public static void main(String[] args){
    testt t = new testt();
  }
//  
//  public void firstSearch(Node root){
//    Queue<Node> q = new LinkedList<Node>();
//    if(root == null){return;}
//    q.add(root);                      
//    
//    while(q != empty){
//      System.out.print(q.peek());
//      if(q.peek().left != null){
//        q.add(root.left);}
//      if(q.peek().right != null){
//        q.add(root.right);}
//      q.remove();   
//    }
//  }     



  public boolean isBalanced(String s){
    boolean balanced = false;
    Map<Character, Character> pairs = new HashMap<Character, Character>();
    pairs.put('[',']');
    pairs.put('{','}');
    pairs.put('(',')');
    Stack<Character> p = new Stack<Character>();
    Stack<Character> p2 = new Stack<Character>();
    Character c;
    int pnum = 0;
    int between = 0;
    System.out.println(s);
    for(int i = 0; i < s.length(); i++){
      c = s.charAt(i);
      if(c == '[' || c == ']' || c == '{'|| c == '}' || c == '(' || c == ')'){
        if(c == '['  || c == '{' || c == '('){between = 0; }
        if(p.empty() == false && pairs.get(p.peek()) ==  c && between == 1){return false;}
        p.push(c);
        pnum++;
      }
      between++;
    }
    if(p.empty() == true){return false;}
    while(p.empty() != true){
      c = p.pop();
      p2.push(c);
      if(p.empty() == true){break;}
      while(p2.empty() == false && pairs.get(p.peek()) == p2.peek()){
        //System.out.println(p.peek() + "    " + p2.peek());
        p.pop();
        p2.pop();
        if(p.empty() == true){break;}
      }
    }
    if(p2.empty() == true){balanced = true;}
    
    return balanced;
  }}