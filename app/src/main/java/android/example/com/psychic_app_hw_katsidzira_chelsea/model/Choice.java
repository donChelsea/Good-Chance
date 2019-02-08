package android.example.com.psychic_app_hw_katsidzira_chelsea.model;

public class Choice {
    private int right;
    private int wrong;
    private int total;

    public Choice() {}

    public Choice(int right, int wrong, int total) {
        this.right = right;
        this.wrong = wrong;
        this.total = total;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
