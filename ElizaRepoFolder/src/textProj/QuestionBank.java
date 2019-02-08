package textProj;

import java.util.Arrays;
import java.util.Random;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.List;


public class QuestionBank  extends QuestionClass{
	
	public int indexNum = 0;
	public List<String> questions;
	public List<QuestionClass> qanda;
	public List<QuestionClass> QuestionsAndAnswers;
	public QuestionClass inProgress;
	public int currQuestionIndex;
	public static final int NUM_QUESTIONS = 10; 
	
	public QuestionBank(){
		
		questions = new ArrayList();
		QuestionsAndAnswers = new ArrayList<QuestionClass>();
		qanda = new ArrayList();
		populateQuestionArray();
		
	}
	
	public void populateQuestionArray(){
		
		questions.add("Which three words describe you best?");
		questions.add("Which is your best feature?");
		questions.add("Which common saying or phrase describes you?");
		questions.add( "What’s the best thing that’s happened to you this week?");
		questions.add( "Who was your role model when you were a child?");
		questions.add( "Who was your favorite teacher and why?");
		questions.add( "What was your favorite subject at school?");
		questions.add( "What did you want to be when you grew up?");
		questions.add( "If you could have one wish come true what would it be?");
		questions.add( "Which would you prefer — three wishes over five years or one wish right now?");
		
		//qanA array
		for( int i = 0 ; i< questions.size() ; i++){
			
			qanda.add(new QuestionClass(i,questions.get(i)));
			
		}	
	}

	public String getNextQuestion2() {
		
		if(qanda.size() == 0) {
			
			return "error" ;
			
		}
		
		Random rand = new Random(); 
		int value = rand.nextInt(qanda.size()); 
		inProgress = qanda.get(value);
		qanda.remove(value);
		return inProgress.question; 
		
	}
	
	public void WriteResponse(String response){
		
		inProgress.usResponse = response;
		inProgress.answeredFlag = true;
		QuestionsAndAnswers.add(inProgress);
	
	}
	
	public int getCurrQuestionIndex() {
		return indexNum;
	}

	public void setCurrQuestionIndex(int currQuestionIndex) {
		this.currQuestionIndex = currQuestionIndex;
	}

}
