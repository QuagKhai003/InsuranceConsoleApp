package Customer;

import InsuranceSystem.InsuranceCard;

public class Dependent extends Customer {
    public Dependent() {
        super();
    }

    public Dependent(String id, String fullName, String insuranceCardId) {
        super(id, fullName, insuranceCardId);
    }

    @Override
    public boolean isPolicyHolder() {
        return false;
    }

    public String toString() {
        return "Dependent{" +
                "id='" + super.getId() + '\'' +
                ", fullName='" + super.getFullName() + '\'' +
                ", insuranceCard=" + super.getInsuranceCard() + '\'' +
                ", listClaims=" + "[" + super.listIdClaim() + "]" +
                '}';
    }
}
