/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.model.Billing;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingDAO {

    private static final Logger logger = LoggerFactory.getLogger(BillingDAO.class);
    private static List<Billing> billings = new ArrayList<>();

    static {
        List<String> invoices = new ArrayList<>();
        invoices.add("Invoice 1");
        List<String> payments = new ArrayList<>();
        payments.add("Payment 1");

        Billing bill = new Billing(1, 1, "2024-05-05", invoices, payments);
        billings.add(bill);
    }

    public List<Billing> getAllBills() {
        logger.info("Fetching All The Billing Records From The Array List");
        return billings;
    }

    public Billing getBillbyId(int billingId) {
        for (Billing billing : billings) {
            if (billing.getBillingId() == billingId) {
                logger.info("The Billing Record Was Found With The Billing Identification Number  {}: {}", billingId, billing);
                return billing;
            }
        }
        logger.info("The Billing Record Of The Patient's Appointment Was Not Found With The Identification Number {}", billingId);
        return null;
    }

    public void addBill(Billing billing) {
        if (billing == null || billing.getBillingId() == 0) {
            throw new IllegalArgumentException("There's An Error In Adding The Billing Record Of The Patient's Appointment To The Array List");
        }
        billings.add(billing);
        logger.info("The Billing Record Of The Patient's Appointment Has Been Added: {}", billing);
    }

    public void updateBill(Billing updatedBilling) {
        if (updatedBilling == null || updatedBilling.getBillingId() == 0) {
            throw new IllegalArgumentException("There's An Error In Updating The Billing Record Of The Patient's Appointment Within The Array List");
        }

        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getBillingId() == updatedBilling.getBillingId()) {
                billings.set(i, updatedBilling);
                logger.info("The Billing Record Of The Patient's Appointment Has Been Updated: {}", updatedBilling);
                return;
            }
        }
        logger.error("The Billing Record Of The Patient's Appointment Was Not Found");
    }

    public void deleteBill(int billingId) {
        boolean removed = billings.removeIf(billing -> billing.getBillingId() == billingId);
        if (removed) {
            logger.info("The Billing Record {} Of The Patient's Appointment Has Been Deleted Successfully", billingId);
        } else {
            logger.error("The Billing Record {} Has Not Been Deleted From The Array List", billingId);
        }
    }
}