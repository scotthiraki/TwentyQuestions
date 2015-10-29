/**
   @author Ian Nicholas
   @author Scott Hiraki
   This program constructs a binary tree by first reading the question file the user inputs.
   Once it has the file, it is read and the questions are placed as branches and the answers become leaves.
   A playGame method is used to play a game where the computer guesses what the object is that the user is thinking of.
   The user answers yes or no questions until the computer gets to an answer node and guesses that it is the object.
   If the computer is right they win. 
   If the computer is wrong then it asks the user for the object they were thinking of and a new question to distinguish between the two.
   The answer that the computer guessed is replaced with the new question. The yes answer to the new question is assigned to the left leaf node and the 
   no answer is assigned to the right leaf node. The question and answers are saved for future plays of the game.
   The game ends once the user says they no longer want to play.
*/
import java.io.*;
import java.util.*;

public class QuestionsGame{
   /**
      This is the field that holds the overall root of the tree. 
   */
   private QuestionNode overallRoot;
	/**
      This is the constructor used if no file is selected to play the game.
      Starts a new game with a new file.
      @param data the first object of the new game.
   */
	public QuestionsGame(String data){
      overallRoot = new QuestionNode(data);
   }
   /**
      This constructor reads the file of the guessing game the user sends. 
      @param input is the scanner of the file. 
   */
	
   public QuestionsGame(Scanner input){
      readTree(input); 								
   }
	/**
      This readTree method takes the file and sets overall root to the method that will be used to make the tree.
      @param input is the scanner of the file.
   */
   public void readTree(Scanner input){
      overallRoot = readTreeHelper(input);
   }	
   /**
      This private method is used to read the input from the file and form the tree.
      The base case is when an answer is found then a new leaf node is formed.
      Otherwise, for each question a branch node is made and it uses recursion for the left child and right child of each question node.
      It returns null if the tree is empty.
      @param input is the scanner used to read the file.
      @return the leaf node containing the answer.
      @return the branch node containing the question and its two children.
      @return null if tree is empty.
   */
	
   private QuestionNode readTreeHelper(Scanner input){
      String line = input.nextLine();
      String text = input.nextLine();
   	// if it's A, it's a leaf
      if(line.equals("A:")){
         QuestionNode a = new QuestionNode(text);
         return a;//returns the answer aka leaf node. 
      }
      else if(line.equals("Q:")){
      	// use constructor QuestionNode(data, left, right)
         return new QuestionNode(text, readTreeHelper(input), readTreeHelper(input));	
      }	
      return null;
   }	
	/**
      This method is used to save the questions. 
      It is the public version of its private method that is used to save all new questions and answers.
      @param output is where the new questions and answers will be saved.
      @throws IllegalArgumentException if empty printstream.
   */
   public void saveQuestions(PrintStream output){
      if(output == null){
         throw new IllegalArgumentException();
      }
      else{   
   	   questionHelper(output, overallRoot);
      }   
   }      
   /**
      This method saves the questions.
      It takes the location to print the new answer or question as well as the node.
      If the root is a leaf then it saves it as an answer.
      If the root is not it saves it as a questoin and then sends it the left spot and right spot to add onto it.
      @param output is where the new questions and answers will be saved.
      @param root is the node that the helpers is looking at.
   */
   private void questionHelper(PrintStream output, QuestionNode root){
      if(root.getLeftNode() == null && root.getRightNode() == null){
         output.println("A:");
         output.println(root.getData());
      }
      else{
         output.println("Q:");
         output.println(root.getData());
         questionHelper(output, root.getLeftNode());
         questionHelper(output, root.getRightNode());
      }
   }            
   /**
      Public version of the play method for playing the game.
   */
   public void play(){
      playGame(overallRoot);
   }
	/**
      This method plays the game. 
      If the root is a leaf then the computer guesses. 
      If its right the computer wins.
      Otherwise, the computer asks what they were thinking of and a question to distinguish between the new object and the old answer.
      The leaf node is replaced with a new branch node containing the question. Its left child is the yes answer and the right child is the no.
      If it is not a leaf node then recursion is used to play the game displaying the data of each node(questions) and asks the user for their answer.
      @param root the node being looked at by the play game. 
   */
   private void playGame(QuestionNode root){
       Scanner input = new Scanner(System.in);
       if(root.getLeftNode() == null && root.getRightNode() == null){
         String answer = root.getData();
         System.out.println("Is the object a " + answer + "?");
         System.out.print("Is that correct? (y/n)\t");
         String response = input.nextLine();
         if(response.toLowerCase().startsWith("y")){
            System.out.println("I WIN!");
         }
         else if(response.toLowerCase().startsWith("n")){
            System.out.println("I Lost!");
            System.out.print("What object were you thinking of?");
            String actual = input.nextLine();
            QuestionNode newLeaf= new QuestionNode(actual);
            QuestionNode oldLeaf= new QuestionNode(answer);
            System.out.println("Please give me a question to distinguish between " + answer + " and " + actual + ".");
            String question = input.nextLine();
            //root.data = question;
				System.out.print("Is the answer yes to " + actual + "(y/n)? ");
            String newAnswer = input.nextLine();
            if(newAnswer.toLowerCase().startsWith("y")){
               //root.left = newLeaf;
              // root.right = oldLeaf;
            	root.nodeCopy(change(root, question, newLeaf, oldLeaf));
               
            }
            else if(newAnswer.toLowerCase().startsWith("n")){
               //root.right = oldLeaf;
               //root.left = newLeaf;
					root.nodeCopy(change(root, question, oldLeaf, newLeaf));
            }
         }         
      }
      else{
         System.out.print(root.getData());
         String answer = input.next();
         if(answer.toLowerCase().startsWith("y")){
            playGame(root.getLeftNode());
         }
         else if(answer.toLowerCase().startsWith("n")){
            playGame(root.getRightNode());
         }
      }		
   }			
   /**
      This private method makes a new question node to replace the old answer with the users new question and the two answers.
      @param root is the node containing the location to be changed.
      @param question is the new question.
      @param leaf1 is the yes answer.
      @param leaf2 is the no answer.
      @return the new node containing the question and both its answers.
   */
   private QuestionNode change(QuestionNode root, String question, QuestionNode leaf1, QuestionNode leaf2){
      root = new QuestionNode(question, leaf1, leaf2);
      return root;
   }
}
