package Claim;

import java.util.ArrayList;
import java.util.List;

public class ListDocumentOfClaim {
    private List<Document> documents;
    public ListDocumentOfClaim() {
        this.documents = new ArrayList<Document>();
    }

    public ListDocumentOfClaim(List<Document> documents) {
        this.documents = documents;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void add(Document doc) {
        this.documents.add(doc);
    }

    public void delete(Document doc) {
        this.documents.remove(doc);
    }

    @Override
    public String toString() {
        return "ListDocumentOfClaim{" +
                "documents=" + documents +
                '}';
    }
}
