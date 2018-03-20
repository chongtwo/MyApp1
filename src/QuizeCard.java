public class QuizeCard {
    String question;
    String answer;

    public QuizeCard(String inputQ, String inputA){
        question = inputQ;
        answer = inputA;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
