/**
   Question node for the Questions game.
   Stores the data in the node and then the left and right branches if there are any.
   @author Ian Nicholas
*/
public class QuestionNode{
   /**
      Fields to store the data of the node as well as its left and right branch.
   */
    private String data;
	private QuestionNode left;
	private QuestionNode right;
   /**
      Constructs a leaf node with the given data;
      @param data contains the answer to the question.  
   */
   public QuestionNode(String data){
      this(data, null, null);
   }
   
   public String getData(){
	   return data;
   }
   
   public QuestionNode getLeftNode(){
	   return left;
   }
   
   public QuestionNode getRightNode(){
	   return right;
   }
   /**
      Constructs a branch node which is the question, and its left and right nodes. 
      @param data is the node that contains the question being asked. 
      @param left if the answer is yes.
      @param right if the answer is no. 
   */
   public QuestionNode(String data, QuestionNode left, QuestionNode right){
      this.data = data;
      this.left = left;
      this.right = right;
   }
   /**
      Changes the data in the current node with new data. 
      @param other the node to replace the other.
   */
   public void nodeCopy(QuestionNode other){
      this.data = other.data;
      this.left = other.left;
      this.right = other.right;
   }   

}   