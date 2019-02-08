package textProj;

public class QuestionClass  {
	
	public String [] QandA;	
	public int qId;
	public boolean answeredFlag = false;
	public String question;
	public String usResponse;
	
	QuestionClass(){
	
		qId = 0;
		answeredFlag = false;
		question = "";
		usResponse = "";
		
	}
	
	QuestionClass(int id , String q){
		
		qId = id;
		answeredFlag = false;
		question = q;
		
	}
	
}
