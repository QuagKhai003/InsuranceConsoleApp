package InsuranceSystem;

/**
 * @author <Ngo Quang Khai - s3975831>
 */

import Claim.Claim;
import Claim.ListClaimOfCustomer;
import Customer.*;
import DataSource.*;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CardFeature {
    private static int cardAmount = Integer.parseInt(LoadDataBase.cardList.get(LoadDataBase.cardList.size()-1).getCardId());

    public static void displayAllInsuranceCard() {
        System.out.println("-------------------Card List---------------------");
        int index = 0;
        for (InsuranceCard card: LoadDataBase.cardList) {
            System.out.println(index + ". " +card);
            index = index + 1;
        }
    }

    public static InsuranceCard chooseInsuranceCard() {
        displayAllInsuranceCard();
        Scanner scanner = new Scanner(System.in);
        int indexCard = 0;
        System.out.println("Enter card index you want to update: ");
        while (scanner.hasNext()) {
            if(scanner.hasNextInt()) {
                indexCard = scanner.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid index");
                scanner.next();
            }
        }
        return LoadDataBase.cardList.get(indexCard);
    }

    public static void operationCard() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Insurance Card Menu---------------------");
        System.out.println("1. Create a card");
        System.out.println("2. Update a card");
        System.out.println("3. Delete a card");
        System.out.println("4. Display all card");
        System.out.println("5. Go Back");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    createCard();
                    operationCard();
                    break;
                case 2:
                    InsuranceCard card = chooseInsuranceCard();
                    updateCardOption(card);
                    operationCard();
                    break;
                case 3:
                    displayAllInsuranceCard();
                    deleteCard();
                    operationCard();
                    break;
                case 4:
                    displayAllInsuranceCard();
                    operationCard();
                    break;
                case 5:
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    operationCard();
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
            Thread.sleep(1000);
            operationCard();
        }
    }

    public static void createCard() throws ParseException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        CustomerFeature.displayAllPolicyHolder();
        System.out.println("-------------------Create Card---------------------");
        System.out.println("Enter the id of a policy holder: ");
        String policyHolderId = scanner.nextLine();
        System.out.println("Enter the name of policy owner: ");
        String policyOwner = scanner.nextLine();
        System.out.println("Enter the expired date dd/MM/YYYY): ");
        String expiredDate = scanner.nextLine();
        System.out.println("Do you want to create this card(Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Processing...");
            Customer foundPolicyHolder = LoadDataBase.findPolicyHolder(policyHolderId);
            String cardId = generateCardId();
            if ( foundPolicyHolder != null && foundPolicyHolder.getInsuranceCard().equalsIgnoreCase("null")) {
                try {
                    foundPolicyHolder.setInsuranceCard(cardId);
                    InsuranceCard card = new InsuranceCard(cardId, foundPolicyHolder, policyOwner, expiredDate);
                    LoadDataBase.cardList.add(card);
                    System.out.println("Create card successfully");
                } catch (ParseException e) {
                    System.out.println("Error when parsing the data value");
                }
            } else {
                System.out.println("This policy holder id do not existed or already had a card");
                System.out.println("Do you want to create a policy holder for this insurance card (Y/N)");
                System.out.println("You should update the policy holder's info later");
                String answer2 = scanner.nextLine();
                if (answer2.equalsIgnoreCase("y")) {
                    try {
                        Customer newCus = new PolicyHolder(CustomerFeature.generateCustomerId(), "New PolicyHolder-" + cardId, cardId);
                        InsuranceCard card = new InsuranceCard(cardId, newCus, policyOwner, expiredDate);
                        LoadDataBase.customerList.add(newCus);
                        LoadDataBase.policyHolderList.add(newCus);
                        LoadDataBase.cardList.add(card);
                        System.out.println("Create card successfully");
                        System.out.println("You should update the name of newly created policy holder");
                    } catch (ParseException e) {
                        System.out.println("Error when parsing the data value");
                    } catch (Exception e) {
                        System.out.println("Error when creating new card and policy holder");
                    }
                } else {
                    System.out.println("The creating processed has been stopped");
                }
            }
            Thread.sleep(1000);
        }
        System.out.println("Exiting creating card process");
        Thread.sleep(1000);
    }

    public static void updateCardOption(InsuranceCard card) throws Exception {
        System.out.println("Your chosen card: " + card);
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Update Card Option---------------------");
        System.out.println("1. Policy Holder");
        System.out.println("2. Policy Owner");
        System.out.println("3. Expired Day");
        System.out.println("4. Choose another card");
        System.out.println("5. Go Back");
        System.out.println("6. Go To Main Menu");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    updatePolicyHolder(card);
                    updateCardOption(card);
                    break;
                case 2:
                    updatePolicyOwner(card);
                    updateCardOption(card);
                    break;
                case 3:
                    updateExpireDay(card);
                    updateCardOption(card);
                    break;
                case 4: ;
                    InsuranceCard anotherCard = chooseInsuranceCard();
                    updateCardOption(anotherCard);
                    break;
                case 5:
                    break;
                case 6:
                    InsuranceManager.displayMainMenu();
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    updateCardOption(card);
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
            Thread.sleep(1000);
            updateCardOption(card);
        }
        System.out.println("Exiting updating process");
        Thread.sleep(1000);
    }

    public static void deleteCard() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Delete Card---------------------");
        System.out.println("Enter card id you want to delete: ");
        String cardID = scanner.nextLine();
        InsuranceCard foundCard = LoadDataBase.findCard(cardID);
        if ( foundCard != null ) {
            System.out.println("The card you want to delete: " + foundCard);
            System.out.println("Do you want to delete this card (Y/N)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                CustomerFeature.deleteRelate(foundCard.getCardHolder());
            }
        } else {
            System.out.println("The card is not existed");
        }
        Thread.sleep(1000);
        System.out.println("Exiting deleting process");
        Thread.sleep(500);
    }

    public static void updatePolicyHolder(InsuranceCard card) throws InterruptedException {
        CustomerFeature.displayAllPolicyHolder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new id of policy holder you want to update:");
        String newPolicyHolderId = scanner.nextLine();
        Customer foundCus = LoadDataBase.findPolicyHolder(newPolicyHolderId);
        PolicyHolder newPolicyHolder = ((PolicyHolder) foundCus);
        PolicyHolder oldPolicyHolder = ((PolicyHolder) card.getCardHolder());
        if (foundCus != null) {
            InsuranceCard oldCardOfNewPolicyHolder = LoadDataBase.findCard(newPolicyHolder.getInsuranceCard());
            ListClaimOfCustomer oldClaimHistory = oldPolicyHolder.getListClaims();
            for (Claim c : oldClaimHistory.getAll()) {
                c.setInsuredPerson(newPolicyHolder);
            }
            ListDependentOfCustomer oldDependentList = oldPolicyHolder.getListDependents();
            card.setCardHolder(newPolicyHolder);
            newPolicyHolder.setListClaims(oldClaimHistory);
            newPolicyHolder.setListDependents(oldDependentList);
            newPolicyHolder.setInsuranceCard(card.getCardId());
            oldPolicyHolder.setInsuranceCard("null");
            oldPolicyHolder.setListClaims(new ListClaimOfCustomer());
            oldPolicyHolder.setListDependents(new ListDependentOfCustomer());
            LoadDataBase.cardList.remove(oldCardOfNewPolicyHolder);
            System.out.println("Successfully updating new policy holder");
        } else {
            System.out.println("This policy holder is not existed");
        }
        System.out.println("Exiting updating policy holder process");
        Thread.sleep(1000);
    }

    public  static void updatePolicyOwner(InsuranceCard card) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new policy owner you want to update:");
        String policyOwner =  scanner.nextLine();
        card.setPolicyOwner(policyOwner);
        System.out.println("Successfully update policy owner");
        System.out.println("Exiting updating process");
        Thread.sleep(1000);
    }

    public  static void updateExpireDay(InsuranceCard card) throws InterruptedException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new expired day you want to update (dd/mm/yyyy):");
        String expireDay =  scanner.nextLine();
        card.setExpireDate(expireDay);
        System.out.println("Successfully update expire day");
        System.out.println("Exiting updating process");
        Thread.sleep(1000);
    }

    //Auto Generating Insurance Card ID Function
    public static String generateCardId() {
        cardAmount = cardAmount + 1;
        int loopAdd = 10 - Integer.toString(cardAmount).length();
        return "0".repeat(Math.max(0, loopAdd)) + (cardAmount);
    }
}
