package bg.tu.pp.semaphors.main;

import bg.tu.pp.semaphors.implementation.CustomSemaphore;
import bg.tu.pp.semaphors.threads.QuerySimulationRunnerThread;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreDbConnectionConstraint {

    private static final int MAX_SIM_THREADS = 2;
    private static final String[] QUERIES = {
            "SELECT * FROM USER;",
            "INSERT INTO ROLE (ROLE_.nextval, 'Admin');",
            "SELECT * FROM SUBS;",
            "UPDATE CUSTOMER SET NAME='Petko' WHERE ID=2;",
            "DELETE FROM ACCOUNTS WHERE ID=1;",
            "SELECT * FROM PERMISSION WHERE NAME='CRM' AND ROLE_ID=6;",
            "SELECT * FROM SOME;",
            "INSERT INTO MORE (MORE_.nextval, 'Admin');",
            "SELECT * FROM SESSIONS;",
            "UPDATE BILL_ACCOUNT SET NAME='Somov' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=20;",
            "SELECT * FROM PRODUCT;",
            "INSERT INTO PURCHASE (PURCHASE_.nextval, 22, 20.25);",
            "SELECT * FROM BUNDLE;",
            "UPDATE CUSTOMER SET AGE=29 WHERE ID=283;",
            "DELETE FROM ACCOUNTS WHERE ID=345634;",
            "SELECT * FROM PERMISSION WHERE NAME='POS' AND ROLE_ID=21;",
            "SELECT * FROM SOME_MORE;",
            "INSERT INTO EVEN_MORE (EVEN_MORE_.nextval, 'CSR');",
            "SELECT * FROM PRODUCT_PURCHASE;",
            "UPDATE BILLING_CYCLE SET STATUS='Finished' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=23497;",
            "SELECT * FROM USER;",
            "INSERT INTO ROLE (ROLE_.nextval, 'Admin');",
            "SELECT * FROM SUBS;",
            "UPDATE CUSTOMER SET NAME='Petko' WHERE ID=2;",
            "DELETE FROM ACCOUNTS WHERE ID=1;",
            "SELECT * FROM PERMISSION WHERE NAME='CRM' AND ROLE_ID=6;",
            "SELECT * FROM SOME;",
            "INSERT INTO MORE (MORE_.nextval, 'Admin');",
            "SELECT * FROM SESSIONS;",
            "UPDATE BILL_ACCOUNT SET NAME='Somov' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=20;",
            "SELECT * FROM PRODUCT;",
            "INSERT INTO PURCHASE (PURCHASE_.nextval, 22, 20.25);",
            "SELECT * FROM BUNDLE;",
            "UPDATE CUSTOMER SET AGE=29 WHERE ID=283;",
            "DELETE FROM ACCOUNTS WHERE ID=345634;",
            "SELECT * FROM PERMISSION WHERE NAME='POS' AND ROLE_ID=21;",
            "SELECT * FROM SOME_MORE;",
            "INSERT INTO EVEN_MORE (EVEN_MORE_.nextval, 'CSR');",
            "SELECT * FROM PRODUCT_PURCHASE;",
            "UPDATE BILLING_CYCLE SET STATUS='Finished' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=23497;",
            "SELECT * FROM USER;",
            "INSERT INTO ROLE (ROLE_.nextval, 'Admin');",
            "SELECT * FROM SUBS;",
            "UPDATE CUSTOMER SET NAME='Petko' WHERE ID=2;",
            "DELETE FROM ACCOUNTS WHERE ID=1;",
            "SELECT * FROM PERMISSION WHERE NAME='CRM' AND ROLE_ID=6;",
            "SELECT * FROM SOME;",
            "INSERT INTO MORE (MORE_.nextval, 'Admin');",
            "SELECT * FROM SESSIONS;",
            "UPDATE BILL_ACCOUNT SET NAME='Somov' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=20;",
            "SELECT * FROM PRODUCT;",
            "INSERT INTO PURCHASE (PURCHASE_.nextval, 22, 20.25);",
            "SELECT * FROM BUNDLE;",
            "UPDATE CUSTOMER SET AGE=29 WHERE ID=283;",
            "DELETE FROM ACCOUNTS WHERE ID=345634;",
            "SELECT * FROM PERMISSION WHERE NAME='POS' AND ROLE_ID=21;",
            "SELECT * FROM SOME_MORE;",
            "INSERT INTO EVEN_MORE (EVEN_MORE_.nextval, 'CSR');",
            "SELECT * FROM PRODUCT_PURCHASE;",
            "UPDATE BILLING_CYCLE SET STATUS='Finished' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=23497;",
            "SELECT * FROM USER;",
            "INSERT INTO ROLE (ROLE_.nextval, 'Admin');",
            "SELECT * FROM SUBS;",
            "UPDATE CUSTOMER SET NAME='Petko' WHERE ID=2;",
            "DELETE FROM ACCOUNTS WHERE ID=1;",
            "SELECT * FROM PERMISSION WHERE NAME='CRM' AND ROLE_ID=6;",
            "SELECT * FROM SOME;",
            "INSERT INTO MORE (MORE_.nextval, 'Admin');",
            "SELECT * FROM SESSIONS;",
            "UPDATE BILL_ACCOUNT SET NAME='Somov' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=20;",
            "SELECT * FROM PRODUCT;",
            "INSERT INTO PURCHASE (PURCHASE_.nextval, 22, 20.25);",
            "SELECT * FROM BUNDLE;",
            "UPDATE CUSTOMER SET AGE=29 WHERE ID=283;",
            "DELETE FROM ACCOUNTS WHERE ID=345634;",
            "SELECT * FROM PERMISSION WHERE NAME='POS' AND ROLE_ID=21;",
            "SELECT * FROM SOME_MORE;",
            "INSERT INTO EVEN_MORE (EVEN_MORE_.nextval, 'CSR');",
            "SELECT * FROM PRODUCT_PURCHASE;",
            "UPDATE BILLING_CYCLE SET STATUS='Finished' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=23497;",
            "SELECT * FROM USER;",
            "INSERT INTO ROLE (ROLE_.nextval, 'Admin');",
            "SELECT * FROM SUBS;",
            "UPDATE CUSTOMER SET NAME='Petko' WHERE ID=2;",
            "DELETE FROM ACCOUNTS WHERE ID=1;",
            "SELECT * FROM PERMISSION WHERE NAME='CRM' AND ROLE_ID=6;",
            "SELECT * FROM SOME;",
            "INSERT INTO MORE (MORE_.nextval, 'Admin');",
            "SELECT * FROM SESSIONS;",
            "UPDATE BILL_ACCOUNT SET NAME='Somov' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=20;",
            "SELECT * FROM PRODUCT;",
            "INSERT INTO PURCHASE (PURCHASE_.nextval, 22, 20.25);",
            "SELECT * FROM BUNDLE;",
            "UPDATE CUSTOMER SET AGE=29 WHERE ID=283;",
            "DELETE FROM ACCOUNTS WHERE ID=345634;",
            "SELECT * FROM PERMISSION WHERE NAME='POS' AND ROLE_ID=21;",
            "SELECT * FROM SOME_MORE;",
            "INSERT INTO EVEN_MORE (EVEN_MORE_.nextval, 'CSR');",
            "SELECT * FROM PRODUCT_PURCHASE;",
            "UPDATE BILLING_CYCLE SET STATUS='Finished' WHERE ID=5;",
            "DELETE FROM ACCOUNTS WHERE ID=23497;"
    };

    //private static Semaphore semaphore;

    public static void main(String[] args) {
        long startMilis = new Date().getTime();

        CustomSemaphore semaphore = new CustomSemaphore(MAX_SIM_THREADS);

        for (String query : QUERIES) {
            QuerySimulationRunnerThread queryRunnerThread = new QuerySimulationRunnerThread(semaphore, query, startMilis);
            queryRunnerThread.start();
        }
    }
}
