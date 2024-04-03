package InsuranceSystem;

import Customer.*;
import DataSource.*;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.Scanner;

public class CardFeature {
    private static int cardAmount = Integer.parseInt(LoadDataBase.cardList.get(LoadDataBase.cardList.size()-1).getCardId());

    public static void displayAllInsuranceCard() {
        System.out.println("-------------------Card List---------------------");
        for (InsuranceCard card: LoadDataBase.cardList) {
            System.out.println(card);
        }
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
                    displayAllInsuranceCard();
                    updateCard();
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
                    InsuranceManager.displayMainMenu();
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
        System.out.println("-------------------Create Card---------------------");
        System.out.println("Enter the id of a policy holder: ");
        String policyHolderId =  scanner.nextLine();
        System.out.println("Enter the name of policy owner: ");
        String policyOwner = scanner.nextLine();
        System.out.println("Enter the expired date: ");
        String expiredDate = scanner.nextLine();
        System.out.println("Do you want to create this customer(Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Processing...");
            Customer foundPolicyHolder = LoadDataBase.findPolicyHolder(policyHolderId);
            if ( foundPolicyHolder != null ) {
                try {
                    InsuranceCard card = new InsuranceCard(generateCardId(), foundPolicyHolder, policyOwner, expiredDate);
                    LoadDataBase.cardList.add(card);
                    System.out.println("Create card successfully");
                } catch (ParseException e) {
                    System.out.println("Error when parsing the data value");
                }
            } else {
                System.out.println("This policy holder id do not existed");
                System.out.println("Do you want to create a policy holder for this insurance card (Y/N)");
                System.out.println("You can update the policy holder's info later");
                String answer2 = scanner.nextLine();
                String cardId = generateCardId();
                if (answer2.equalsIgnoreCase("y")) {
                    try {
                        Customer newCus = new PolicyHolder(CustomerFeature.generateCustomerId(), "", cardId);
                        InsuranceCard card = new InsuranceCard(cardId, newCus, policyOwner, expiredDate);
                        LoadDataBase.customerList.add(newCus);
                        LoadDataBase.policyHolderList.add(newCus);
                        LoadDataBase.cardList.add(card);
                        System.out.println("Create card successfully");
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

    public static void updateCard() throws ParseException, InterruptedException {
        System.out.println("-------------------Policy Holder List---------------------");
        CustomerFeature.displayAllPolicyHolder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Update Card---------------------");
        System.out.println("Enter card id you want to update: ");
        String cardID = scanner.nextLine();
        System.out.println("Enter the id of a policy holder to change: ");
        String policyHolderId =  scanner.nextLine();
        System.out.println("Enter the name of policy owner to change: ");
        String policyOwner = scanner.nextLine();
        System.out.println("Enter the expired date to change: ");
        String expiredDate = scanner.nextLine();
        System.out.println("Do you want to create this customer(Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            InsuranceCard foundCard = LoadDataBase.findCard(cardID);
            if (foundCard != null) {
                try {
                    foundCard.setPolicyOwner(policyOwner);
                    foundCard.setExpireDate(expiredDate);
                    Customer foundC =  LoadDataBase.findPolicyHolder(policyHolderId);
                    if (foundC != null && foundC.getInsuranceCard().equals("")) {
                        LoadDataBase.findCard(cardID).setCardHolder(foundC);
                    } else {
                        System.out.println("The policy holder's insurance card id should be changed first or the policy holder is not existed");
                    }
                    System.out.println("Create card successfully");
                } catch (ParseException e) {
                    System.out.println("Error when set date");
                } catch (Exception e) {
                    System.out.println("Error when updating card");
                }
            } else {
                System.out.println("The card is not existed");
            }
            Thread.sleep(1000);
        } else {
            System.out.println("The creating processed has been stopped");
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
                LoadDataBase.cardList.remove(foundCard);
            }
        } else {
            System.out.println("The card is not existed");
        }
        Thread.sleep(1000);
        System.out.println("Exiting deleting process");
        Thread.sleep(500);
    }

    //Auto Generating Insurance Card ID Function
    public static String generateCardId() {
        cardAmount = cardAmount + 1;
        int loopAdd = 10 - Integer.toString(cardAmount).length();
        return "0".repeat(Math.max(0, loopAdd)) + (cardAmount);
    }
}
