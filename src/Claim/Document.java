package Claim;

public class Document {
    private String claimId;
    private String cardNumber;
    private String documentName;

    public Document(String claimId, String cardNumber, String documentName) {
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj  instanceof Document)) {
            return false;
        }

        Document otherDoc = (Document) obj;
        return this.toString().equals(otherDoc.toString());
    }

    @Override
    public String toString() {
        return claimId + "_" + cardNumber + "_" + documentName;
    }
}
