package Claim;

public class Document {
    private String claimId;
    private String cardNumber;
    private String documentName;

    public Document( String claimId, String cardNumber, String documentName) {
        this.claimId = claimId;
        this.cardNumber = cardNumber;
        this.documentName = documentName;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    @Override
    public String toString() {
        return claimId + "_" + cardNumber + "_" + documentName;
    }
}
