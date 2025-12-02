public class HouseHold {

    private int id;
    private double income;
    private int members;
    private String state;

    public HouseHold(int id, double income, int members, String state) {
        this.id = id;
        this.income = income;
        this.members = members;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPovertyLevel() {
        int m = this.members;
        double povertyLevel;

        if (state.equalsIgnoreCase("Alaska")) {
            povertyLevel = 26430.00 + 6880.00 * (m - 2);
        } else if (state.equalsIgnoreCase("Hawaii")) {
            povertyLevel = 24320.00 + 6330.00 * (m - 2);
        } else {
            // 48 contiguous states or District of Columbia
            povertyLevel = 21150.00 + 5500.00 * (m - 2);
        }

        return povertyLevel;
    }

    public boolean isBelowPovertyLevel() {
        return income < getPovertyLevel();
    }
    public boolean qualifiesForMedicaid() {
        return income < (getPovertyLevel() * 1.38);
    }

    @Override
    public String toString() {
        return String.format("%-10d %-12.2f %-12d %-12s", id, income, members, state);
    }
}
