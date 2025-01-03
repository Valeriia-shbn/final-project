package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseHelper {

    private static final String USER = "app";
    private static final String PASSWORD = "pass";
    private static final QueryRunner runner = new QueryRunner();


    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection(System.getProperty("db.url"), USER, PASSWORD);
    }

    @SneakyThrows
    public static TransactionRecordCredit getLastTransactionCredit() {
        String query = "SELECT * " +
                "FROM credit_request_entity " +
                "ORDER BY created DESC LIMIT 1";

        try (var connection = getConnection()) {
            return runner.query(connection, query, new BeanHandler<>(TransactionRecordCredit.class));
        }
    }

    @SneakyThrows
    public static TransactionRecordPayment getLastTransactionPayment() {
        String query = "SELECT * " +
                "FROM payment_entity " +
                "ORDER BY created DESC LIMIT 1";

        try (var connection = getConnection()) {
            return runner.query(connection, query, new BeanHandler<>(TransactionRecordPayment.class));
        }
    }

    @SneakyThrows
    public static OrderEntity getOrderByPaymentId(Long paymentId) {
        String sql = "SELECT id, created, credit_id AS creditId, payment_id AS paymentId FROM order_entity WHERE payment_id = ?";
        try (var connection = getConnection()) {
            return runner.query(connection, sql, new BeanHandler<>(OrderEntity.class), paymentId);
        }
    }

    @SneakyThrows
    public static int getOrderCountByPaymentId(String paymentId) {
        String sql = "SELECT COUNT(*) FROM order_entity WHERE payment_id = ?";
        try (var connection = getConnection()) {
            Long count =  runner.query(connection,sql, new ScalarHandler<>(), paymentId);
            return count != null ? count.intValue() : 0;
        }
    }

    @SneakyThrows
    public static int getOrderCountByCreditId(String creditId) {
        String sql = "SELECT COUNT(*) FROM order_entity WHERE credit_id = ?";
        try (var connection = getConnection()) {
            Long count =  runner.query(connection, sql, new ScalarHandler<>(), creditId);
            return count != null ? count.intValue() : 0;
        }
    }

}
